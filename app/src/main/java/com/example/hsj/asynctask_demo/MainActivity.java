package com.example.hsj.asynctask_demo;

import android.app.ProgressDialog;
import android.graphics.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.example.hsj.asynctask_demo.AsyncTask.DownloadTask;
import com.example.hsj.asynctask_demo.util.MyApplication;

/**
 * 讲解：张鸿洋大神
 * http://blog.csdn.net/guolin_blog/article/details/11711405
 * Android从1.5版本就引入了一个AsyncTask类，使用它就可以非常灵活方便地从子线程切换到UI线程，我们本篇文章的主角也就正是它了。
 * 首先来看一下AsyncTask的基本用法，由于AsyncTask是一个抽象类，所以如果我们想使用它，
 * 就必须要创建一个子类去继承它。在继承时我们可以为AsyncTask类指定三个泛型参数，这三个参数的用途如下：

 1. Params
 在执行AsyncTask时需要传入的参数，可用于在后台任务中使用。
 2. Progress
 后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位。
 3. Result
 当任务执行完毕后，如果需要对结果进行返回，则使用这里指定的泛型作为返回值类型。
 */
public class MainActivity extends AppCompatActivity {
    private Button at;

    private ProgressDialog progressDialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        at = findViewById(R.id.at);


        at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("下载文件模拟中...");
                progressDialog.setCancelable(false);
                new DownloadTask(progressDialog).execute();
            }
        });



    }
}
