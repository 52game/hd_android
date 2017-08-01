package com.movebeans.lib.net;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by younminx on 2017/2/21.
 * 下载响应
 */

public class ProgressResponseBody extends ResponseBody
    {
        private final ResponseBody responseBody;
        private final DownloadProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, DownloadProgressListener progressListener)
            {
                this.responseBody = responseBody;
                this.progressListener = progressListener;
            }

        @Override
        public MediaType contentType()
            {
                return responseBody.contentType();
            }

        @Override
        public long contentLength()
            {
                return responseBody.contentLength();
            }

        @Override
        public BufferedSource source()
            {
                if (bufferedSource == null) {
                    bufferedSource = Okio.buffer(source(responseBody.source()));
                }
                return bufferedSource;
            }

        private Source source(Source source)
            {
                return new ForwardingSource (source)
                    {
                        long totalBytesRead = 0L;

                        @Override
                        public long read(Buffer sink, long byteCount) throws IOException
                            {
                                long bytesRead = super.read(sink, byteCount);
                                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                                progressListener.onProgress(totalBytesRead, responseBody
                                        .contentLength(), bytesRead == -1);
                                return bytesRead;
                            }
                    };
            }
    }
