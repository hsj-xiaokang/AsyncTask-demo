package com.example.hsj.asynctask_demo.AsyncTask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.hsj.asynctask_demo.util.MyApplication;

/**
 * 1. onPreExecute()
 这个方法会在后台任务开始执行之间调用，用于进行一些界面上的初始化操作，比如显示一个进度条对话框等。

 2. doInBackground(Params...)
 这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。
 任务一旦完成就可以通过return语句来将任务的执行结果进行返回，
 如果AsyncTask的第三个泛型参数指定的是Void，就可以不返回任务执行结果。
 注意，在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，
 可以调用publishProgress(Progress...)方法来完成。

 3. onProgressUpdate(Progress...)
 当在后台任务中调用了publishProgress(Progress...)方法后，这个方法就很快会被调用，
 方法中携带的参数就是在后台任务中传递过来的。在这个方法中可以对UI进行操作，利用参数中的数值就可以对界面元素进行相应的更新。

 4. onPostExecute(Result)
 当后台任务执行完毕并通过return语句进行返回时，这个方法就很快会被调用。
 返回的数据会作为参数传递到此方法中，可以利用返回的数据来进行一些UI操作，比如说提醒任务执行的结果，以及关闭掉进度条对话框等。
 */
public class DownloadTask extends AsyncTask<Void, Integer, Boolean> {
    int c = 0;
    ProgressDialog progressDialog ;

    public DownloadTask() {
    }

    public DownloadTask(int c) {
        this.c = c;
    }
    public DownloadTask(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public DownloadTask(int c,ProgressDialog progressDialog) {
        this.c = c;
        this.progressDialog = progressDialog;
    }

    /**
     * 模拟下载文件
     * @return
     */
    protected int doDownload(){
     return c++;
    }

    /**
     * 主线程UI线程里面执行
     */
    @Override
    protected void onPreExecute() {
        progressDialog.show();
        Toast.makeText(MyApplication.getContext(), "开始准备下载...", Toast.LENGTH_SHORT).show();
    }

    /**
     * 子线程里面执行
     */
    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            while (true) {
                Thread.sleep(500);
                int downloadPercent = doDownload();
                publishProgress(downloadPercent);
                if (downloadPercent >= 100) {
                    c = 0;
                    break;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 主线程UI线程里面执行
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setMessage("当前下载进度：" + values[0] + "%");
    }

    /**
     * 主线程UI线程里面执行
     */
    @Override
    protected void onPostExecute(Boolean result) {
        progressDialog.dismiss();
        if (result) {
            Toast.makeText(MyApplication.getContext(), "下载成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MyApplication.getContext(), "下载失败", Toast.LENGTH_SHORT).show();
        }
    }
}
