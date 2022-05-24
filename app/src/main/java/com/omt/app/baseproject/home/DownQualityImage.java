package com.omt.app.baseproject.home;

import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import com.arthenica.ffmpegkit.FFmpegKit;
import com.arthenica.ffmpegkit.FFmpegSession;
import com.arthenica.ffmpegkit.SessionState;
import com.blankj.utilcode.util.FileUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DownQualityImage {

    public String getImageDownQuality(String path) {
        try {
            if (path == null) return null;
            File file = new File(path);
            if (!file.exists() || file.isDirectory()) return null;
            String pathOut = createPathFileOut();
            String[] cmd = codeDownQualityCmd(path, pathOut);

            FFmpegSession fFmpegSession = FFmpegKit.executeWithArguments(cmd);
            if (fFmpegSession.getState() == SessionState.COMPLETED) {
                return pathOut;
            } else {
                Log.d("Trungnk", "getImageDownQuality: ");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Trungnk", "getImageDownQuality: ");
            return null;
        }
    }

//
//    public Observable<Object> downQualityObservable(List<ImageEntry> pathList) {
//        return Observable.from(pathList)
//                .flatMap((e) ->
//                                Observable.just(e)
//                                        .map(o -> {
//                                            e.path = getImageDownQuality(e.path);
//                                            return e;
//                                        })
//                                        .subscribeOn(Schedulers.newThread())
//                        , 4
//                );
//    }

    public void getImageDownQualityExecuteAsync(String path, OnDownQualityImageListener callback) {
        if (path == null) return;
        File file = new File(path);
        if (!file.exists() || file.isDirectory()) return;
        String pathOut = createPathFileOut();
        String[] cmd = codeDownQualityCmd(path, pathOut);

        FFmpegKit.executeWithArgumentsAsync(cmd, session -> {
            SessionState status = session.getState();
            if (status == SessionState.COMPLETED) {
                if (callback != null) callback.downQualitySuccess(pathOut);
            } else if (status == SessionState.FAILED) {
                if (callback != null) callback.downQualityFail(pathOut);
            }
        });
    }

    private String createPathFileOut() {
        String path = String.format(
                "%s/.kidsonline",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getPath());
        FileUtils.createOrExistsDir(path);
        return String.format(
                "%s/%s_%s.%s",
                path,
                System.currentTimeMillis(),
                new Random().nextInt(1000),
                "jpg");
    }


    private String[] codeDownQualityCmd(String pathInput, String pathOutput) {
        int orientation;
        try {
            ExifInterface exifInterface = new ExifInterface(pathInput);
            orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED
            );
        } catch (Exception exception) {
            orientation = ExifInterface.ORIENTATION_UNDEFINED;
        }

        List<String> outCmd = new ArrayList<>();
        outCmd.add("-i");
        outCmd.add(pathInput);
        outCmd.add("-vf");

        switch (orientation) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                outCmd.add("transpose=1,scale='min(-1,iw)':'min(1080,ih)'");
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                outCmd.add("transpose=2,transpose=2,scale='min(-1,iw)':'min(1080,ih)'");
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                outCmd.add("transpose=2,scale='min(-1,iw)':'min(1080,ih)'");
                break;
            default:
                outCmd.add("scale='min(-1,iw)':'min(1080,ih)'");
                break;
        }

        outCmd.add("-sws_flags");
        outCmd.add("-bilinear");
        outCmd.add(pathOutput);
        return outCmd.toArray(new String[0]);
    }

    interface OnDownQualityImageListener {
        void downQualitySuccess(String outPath);

        void downQualityFail(String outPath);
    }
}
