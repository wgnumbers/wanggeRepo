package com.wangge.network_base.down;

/**
 * Created by ly on 18/7/26.
 */

import android.util.Log;

import com.wangge.app.tools.FileUtils;
import com.wangge.network_base.ServiceFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 1. Description: 下载工具类
 * 2. Created by jia on 2017/11/30.
 * 3. 人之所以能，是相信能
 */
public class DownloadUtils {

    public static DownloadUtils downloadUtils;
    int downStatus = 0;
    Call call;
    String downFilePath;

    private DownloadUtils() {
    }

    public static DownloadUtils getInstance() {
        if (downloadUtils == null) {
            downloadUtils = new DownloadUtils();
        }
        return downloadUtils;
    }

    /**
     * @param url      下载连接
     * @param listener 下载监听
     */
    public void download(final String url, String desPath, final OnDownloadListener listener) {
        if (downStatus == 1) {
            cancelDown(downFilePath+FileUtils.getNameFromUrl(url));
        }
        Request request = new Request.Builder().url(url).build();
        try {
            downFilePath = FileUtils.isExistDir(desPath);
        } catch (Exception ex) {

        }
        call = ServiceFactory.getOkhttoClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                downStatus = 1;
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(downFilePath, FileUtils.getNameFromUrl(url));
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                        Log.i("DownloadUtils", "progress():" + progress);
                    }
                    fos.flush();
                    // 下载完成
                    downStatus = 0;
                    listener.onDownloadSuccess();
                } catch (Exception e) {
//                        listener.onDownloadFailed();
                    Log.i("DownloadUtils", "onDownloadFailed():" + e.getMessage());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }


    /**
     * @param url      下载连接
     * @param listener 下载监听
     */
    public void download(final String url, String desdir, final String desPath, final OnDownloadListener listener) {
        if (downStatus == 1) {
            cancelDown(desPath);
        }
        Request request = new Request.Builder().url(url).build();
        try {
            downFilePath = FileUtils.isExistDir(desdir);
        } catch (Exception ex) {

        }
        call = ServiceFactory.getOkhttoClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                listener.onDownloadFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                downStatus = 1;
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(desPath);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        listener.onDownloading(progress);
                        Log.i("DownloadUtils", "progress():" + progress);
                    }
                    fos.flush();
                    // 下载完成
                    downStatus = 0;
                    listener.onDownloadSuccess();
                } catch (Exception e) {
//                        listener.onDownloadFailed();
                    File file = new File(desPath);
                    file.delete();
                    Log.i("DownloadUtils", "onDownloadFailed():" + e.getMessage());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

//    public void cancelDown() {
//        if (call != null) {
//            call.cancel();
//        }
//        File file = new File(downFilePath);
//        if (file.exists()) {
//            file.deleteOnExit();
//        }
//    }
    private void cancelDown(String path){
                if (call != null) {
            call.cancel();
        }
        File file = new File(path);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    public interface OnDownloadListener {
        /**
         * 下载成功
         */
        void onDownloadSuccess();

        /**
         * @param progress 下载进度
         */
        void onDownloading(int progress);

        /**
         * 下载失败
         */
        void onDownloadFailed();
    }
}
