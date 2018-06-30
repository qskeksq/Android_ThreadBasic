package database.kotlin.flow9.net.threadbasic.basic;

import android.os.Handler;
import android.os.Message;

public class BasicThread1 extends Thread {

    private Handler mHandler;

    BasicThread1(Handler mHandler) {
        this.mHandler = mHandler;
    }

    private int count = 0;
    private boolean isRunning = true;

    @Override
    public void run() {
        try {
            while (isRunning) {
                Thread.sleep(1000);
                count++;
                if(count == 10) {
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = "message";
                    mHandler.sendMessage(message);
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}