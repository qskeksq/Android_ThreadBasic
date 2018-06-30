package database.kotlin.flow9.net.threadbasic.looper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class MainLooperThread extends Thread {

    Handler mHandler;

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int data = msg.arg1;
                if (msg.what == 0) {

                } else if (msg.what == 1) {

                }
            }
        };
        Looper.loop();
    }
}
