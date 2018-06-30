package database.kotlin.flow9.net.threadbasic.looper;

import android.os.Handler;
import android.os.Message;

import java.util.Random;

public class SubLooperThread extends Thread {

    Handler mHandler;

    public SubLooperThread(Handler mHandler) {
        this.mHandler = mHandler;
    }

    @Override
    public void run() {
        super.run();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int data = random.nextInt(10);
            Message msg = new Message();
            if (data % 2 == 0) {
                msg.what = 0;
            } else {
                msg.what = 1;
            }
            msg.arg1 = data;
            mHandler.sendMessage(msg);
        }
    }

}
