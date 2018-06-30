package database.kotlin.flow9.net.threadbasic.basic;

import android.os.Handler;
import android.os.Message;

public class BasicThread2 implements Runnable {

    private Handler mHandler;

    BasicThread2(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            Message message = Message.obtain();
            mHandler.sendMessage(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}