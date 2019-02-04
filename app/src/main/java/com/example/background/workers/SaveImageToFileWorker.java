package com.example.background.workers;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class SaveImageToFileWorker extends Worker {

    private static final String TAG = SaveImageToFileWorker.class.getSimpleName();

    public SaveImageToFileWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }
}
