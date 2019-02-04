package com.example.background.workers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.background.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class BlurWorker extends Worker {

    private static final String TAG = "BlurWorker";

    public BlurWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        Context context = getApplicationContext();

        try {

            Bitmap picture = BitmapFactory.decodeResource(
                    context.getResources(),
                    R.drawable.test);

            Bitmap output = WorkerUtils.blurBitmap(picture, context);

            Uri outputUri = WorkerUtils.writeBitmapToFile(context, output);

            WorkerUtils.makeStatusNotification(
                    "Output is " + outputUri.toString(), context);

            return Result.success();
        } catch (Throwable throwable) {

            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}
