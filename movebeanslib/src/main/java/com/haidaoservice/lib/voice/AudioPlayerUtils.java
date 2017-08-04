package com.haidaoservice.lib.voice;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by zhangfei on 2017/5/11.
 */

public class AudioPlayerUtils {

    MediaPlayer mediaPlayer = null;

    private ImageView ivVoice = null;

    private int resId;

    private int animId;

    private Context mContext;

    public static boolean isPlaying = false;

    public AudioPlayerUtils(Context mContext, ImageView ivVoice, int resId, int animId) {
        this.mContext = mContext;
        this.ivVoice = ivVoice;
        this.resId = resId;
        this.animId = animId;
    }

    public void playVoice(String filePath) {
        if (!(new File(filePath).exists())) {
            return;
        }
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();

        ivVoice.setImageResource(animId);
        AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);

        audioManager.setMode(AudioManager.MODE_NORMAL);
        audioManager.setSpeakerphoneOn(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_RING);
        try {
            mediaPlayer.setDataSource(filePath);
            mediaPlayer.prepare();
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mediaPlayer.release();
                    mediaPlayer = null;
                    stopPlayVoice(); // stop animation
                }

            });
            isPlaying = true;
            mediaPlayer.start();
            ((AnimationDrawable) ivVoice.getDrawable()).start();
        } catch (Exception e) {
            System.out.println();
        }
    }

    public void stopPlayVoice() {
        ((AnimationDrawable) ivVoice.getDrawable()).stop();
        ivVoice.setImageResource(resId);
        // stop play voice
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        isPlaying = false;
    }
}
