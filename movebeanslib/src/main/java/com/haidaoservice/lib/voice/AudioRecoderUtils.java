package com.haidaoservice.lib.voice;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.haidaoservice.lib.R;
import com.haidaoservice.lib.common.tool.StorageUtils;

import java.io.File;
import java.io.IOException;

/**
 * 录音
 * Created by fan on 2016/6/23.
 */
public class AudioRecoderUtils {

    //文件路径
    private String filePath;
    //文件夹路径
    private String FolderPath;

    private TextView view;

    private AudioPopupWindow mPop;

    ImageView mImageView;
    TextView mTextView;

    private MediaRecorder mMediaRecorder;


    private final String TAG = "fan";
    public static final int MAX_LENGTH = 1000 * 60 * 10;// 最大录音时长1000*60*10;

    private OnAudioListener audioListener;

    /**
     * 文件存储默认sdcard/record
     */
    public AudioRecoderUtils(String filePath, TextView view) {

        File path = new File(StorageUtils.getStorageFile(), filePath);
        if (!path.exists())
            path.mkdirs();

        this.FolderPath = path.getAbsolutePath();
        LayoutInflater inflater = (LayoutInflater) view.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View contentView = inflater.inflate(R.layout.layout_microphone, null);
        //PopupWindow布局文件里面的控件
        mImageView = (ImageView) contentView.findViewById(R.id.iv_recording_icon);
        mTextView = (TextView) contentView.findViewById(R.id.tv_recording_time);
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int width = v.getMeasuredWidth();
                int height = v.getMeasuredHeight();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (mPop == null) {
                            mPop = new AudioPopupWindow(v.getContext(), contentView, v);
                            mPop.showAtLocation();
                        }
                        ((TextView) v).setText("松开保存");
                        startRecord();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getX()) > width * 2 || Math.abs(event.getY()) > height * 2) {
                            cancelRecord();    //取消录音（不保存录音文件）
                        } else {
                            stopRecord();        //结束录音（保存录音文件）
                        }
                        if (mPop != null) {
                            mPop.dismiss();
                            mPop = null;
                        }
                        ((TextView) v).setText("按住说话");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getX()) > width * 2 || Math.abs(event.getY()) > height * 2) {
                            ((TextView) v).setText("松开取消");
                        } else {
                            ((TextView) v).setText("松开保存");
                        }
                        break;
                }
                return true;
            }
        });
    }

    private long startTime;
    private long endTime;


    /**
     * 开始录音 使用amr格式
     * 录音文件
     *
     * @return
     */
    public void startRecord() {
        // 开始录音
        /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
            /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
            /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
            /*
             * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
             * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
             */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            filePath = FolderPath + File.separator + TimeUtils.getCurrentTime() + ".amr";
            /* ③准备 */
            mMediaRecorder.setOutputFile(filePath);
            mMediaRecorder.setMaxDuration(MAX_LENGTH);
            mMediaRecorder.prepare();
            /* ④开始 */
            mMediaRecorder.start();
            // AudioRecord audioRecord.
            /* 获取开始时间* */
            startTime = System.currentTimeMillis();
            updateMicStatus();
            Log.e("fan", "startTime" + startTime);
        } catch (IllegalStateException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i(TAG, "call startAmr(File mRecAudioFile) failed!" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 停止录音
     */
    public long stopRecord() {
        if (mMediaRecorder == null)
            return 0L;
        endTime = System.currentTimeMillis();

        try {

            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
            audioListener.onStop(filePath, (System.currentTimeMillis() - startTime) / 1000);
            filePath = "";

        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

            File file = new File(filePath);
            if (file.exists())
                file.delete();

            filePath = "";

        }
        return endTime - startTime;
    }

    /**
     * 取消录音
     */
    public void cancelRecord() {

        try {

            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;

        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }

        File file = new File(filePath);
        if (file.exists())
            file.delete();

        filePath = "";

    }

    private final Handler mHandler = new Handler();
    private Runnable mUpdateMicStatusTimer = new Runnable() {
        public void run() {
            updateMicStatus();
        }
    };


    private int BASE = 1;
    private int SPACE = 100;// 间隔取样时间

    public void setOnAudioListener(OnAudioListener audioListener) {
        this.audioListener = audioListener;
    }

    /**
     * 更新麦克状态
     */
    private void updateMicStatus() {

        if (mMediaRecorder != null) {
            double ratio = (double) mMediaRecorder.getMaxAmplitude() / BASE;
            double db = 0;// 分贝
            if (ratio > 1) {
                db = 20 * Math.log10(ratio);
            }
            mImageView.getDrawable().setLevel((int) (3000 + 6000 * db / 100));
            mTextView.setText(TimeUtils.long2String(System.currentTimeMillis() - startTime));
            if ((System.currentTimeMillis() - startTime) / 1000 < 60) {
                mHandler.postDelayed(mUpdateMicStatusTimer, SPACE);
            } else {
                startRecord();
                if (mPop != null) {
                    mPop.dismiss();
                    mPop = null;
                }
                view.setText("按住说话");
            }
        }
    }

    public interface OnAudioListener {
        /**
         * 停止录音
         *
         * @param filePath 保存路径
         */
        public void onStop(String filePath, long time);
    }



}
