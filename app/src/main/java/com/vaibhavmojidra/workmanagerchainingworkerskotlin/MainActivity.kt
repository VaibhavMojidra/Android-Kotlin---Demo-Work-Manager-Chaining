package com.vaibhavmojidra.workmanagerchainingworkerskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.vaibhavmojidra.workmanagerchainingworkerskotlin.databinding.ActivityMainBinding
import com.vaibhavmojidra.workmanagerchainingworkerskotlin.workers.MyWorker1
import com.vaibhavmojidra.workmanagerchainingworkerskotlin.workers.MyWorker2
import com.vaibhavmojidra.workmanagerchainingworkerskotlin.workers.MyWorker3
import com.vaibhavmojidra.workmanagerchainingworkerskotlin.workers.MyWorker4

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.startWork.setOnClickListener {
            startWorkers()
        }
    }

    private fun startWorkers() {
        val workManager=WorkManager.getInstance(applicationContext)
        val oneTimeWorkRequestWorker1=OneTimeWorkRequest.Builder(MyWorker1::class.java).build()
        val oneTimeWorkRequestWorker2=OneTimeWorkRequest.Builder(MyWorker2::class.java).build()
        val oneTimeWorkRequestWorker3=OneTimeWorkRequest.Builder(MyWorker3::class.java).build()
        val oneTimeWorkRequestWorker4=OneTimeWorkRequest.Builder(MyWorker4::class.java).build()
//
//        workManager
//            .beginWith(oneTimeWorkRequestWorker1)
//            .then(oneTimeWorkRequestWorker2)
//            .then(oneTimeWorkRequestWorker3)
//            .then(oneTimeWorkRequestWorker4)
//            .enqueue() //Chaining

        val parallelList= mutableListOf<OneTimeWorkRequest>(oneTimeWorkRequestWorker1,oneTimeWorkRequestWorker2)
        workManager
            .beginWith(parallelList)
            .then(oneTimeWorkRequestWorker3)
            .then(oneTimeWorkRequestWorker4)
            .enqueue() //Parallel Workers
    }
}