package com.example.background.workers;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.example.background.Constants;
import com.example.background.R;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class BlurWorker extends Worker {

    private static final String TAG = BlurWorker.class.getSimpleName();

    public BlurWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {

        Context context = getApplicationContext();
        String resourceUri = getInputData().getString(Constants.KEY_IMAGE_URI);

        try {

//            Bitmap picture = BitmapFactory.decodeResource(
//                    context.getResources(),
//                    R.drawable.test);
            if (TextUtils.isEmpty(resourceUri)) {
                Log.e(TAG, "Invalid input URI");
                throw new IllegalArgumentException("Invalid input URI");
            }

            ContentResolver contentResolver = context.getContentResolver();
            Bitmap picture = BitmapFactory.decodeStream(
                    contentResolver.openInputStream(Uri.parse(resourceUri)));

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
