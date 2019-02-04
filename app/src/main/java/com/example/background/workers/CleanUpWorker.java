package com.example.background.workers;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class CleanUpWorker extends Worker {

    private static final String TAG = CleanUpWorker.class.getSimpleName();

    public CleanUpWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        return null;
    }
}
