package com.vaibhavmojidra.workmanagerchainingworkerskotlin.workers

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker4(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        return try{
            for(i in 1..10){
                Thread.sleep(1000)
                Log.i("MyTag", "W4: $i")
            }
            Result.success()
        }catch (e:Exception){

            Result.failure()
        }
    }
}