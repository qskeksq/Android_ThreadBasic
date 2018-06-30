package database.kotlin.flow9.net.threadbasic.basic;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import database.kotlin.flow9.net.threadbasic.R;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new MyHandler();
    // thread pool member
    private ExecutorService mThreadPool;
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 64;
    private static final long KEEP_ALIVE_TIME = 0L;
    // thread local


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Basic Thread
        BasicThread1 thread1 = new BasicThread1(mHandler);
        thread1.start();
        Thread thread2 = new Thread(new BasicThread2(mHandler));
        thread2.start();

        // send message over handler
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mHandler.sendEmptyMessage(0);
                mHandler.sendEmptyMessageAtTime(0, System.currentTimeMillis() + 1000);
                mHandler.sendEmptyMessageDelayed(0, 1000);

                mHandler.sendMessage(Message.obtain());
                mHandler.sendMessageAtTime(Message.obtain(), System.currentTimeMillis() + 1000);
                mHandler.sendMessageAtFrontOfQueue(Message.obtain());
                mHandler.sendMessageDelayed(Message.obtain(), 1000);

                mHandler.post(new BasicThread2(mHandler));
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        });

        // ThreadPool
        mThreadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>()
        );
    }

    private void executeThreadPool() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    private void shutdownThreadPool() {
        mThreadPool.shutdown();
    }

    static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

            } else if (msg.what == 2) {

            }
        }
    }
}
