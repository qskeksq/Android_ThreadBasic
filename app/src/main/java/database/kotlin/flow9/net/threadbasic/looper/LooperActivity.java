package database.kotlin.flow9.net.threadbasic.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import database.kotlin.flow9.net.threadbasic.R;

public class LooperActivity extends AppCompatActivity {

    int mMainValue = 0;
    TextView mMainText;
    TextView mBackText;
    EditText mNumEdit;
    CalcThread mThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looper);
        mMainText = findViewById(R.id.mainvalue);
        mBackText = findViewById(R.id.backvalue);
        mNumEdit = findViewById(R.id.number);

        mThread = new CalcThread(mMainHandler);
        mThread.setDaemon(true);
        mThread.start();
    }

    public void mOnClick(View view) {
        Message msg;
        switch (view.getId()) {
            case R.id.increase:
                mMainValue++;
                mMainText.setText("MainValue : " + mMainValue);
                break;
            case R.id.square:
                msg = new Message();
                msg.what = 0;
                msg.arg1 = Integer.parseInt(mNumEdit.getText().toString());
                mThread.mBackHandler.sendMessage(msg);
                break;
            case R.id.root:
                msg = new Message();
                msg.what = 1;
                msg.arg1 = Integer.parseInt(mNumEdit.getText().toString());
                mThread.mBackHandler.sendMessage(msg);
                break;
        }
    }


    Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mBackText.setText("Square Result : " + msg.arg1);
                    break;
                case 1:
                    mBackText.setText("Root Result : " + ((Double)msg.obj).doubleValue());
                    break;
            }
        }
    };

    class CalcThread extends Thread {
        Handler mMainHandler;
        Handler mBackHandler;

        public CalcThread(Handler handler) {
            mMainHandler = handler;
        }

        public void run() {
            Looper.prepare();
            mBackHandler = new Handler() {
                public void handleMessage(Message msg) {
                    Message retmsg = new Message();
                    switch (msg.what) {
                        case 0:
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {

                            }
                            retmsg.what = 0;
                            retmsg.arg1 = msg.arg1 * msg.arg1;
                            break;
                        case 1:
                            try {
                                Thread.sleep(200);
                            } catch (InterruptedException e) {

                            }
                            retmsg.what = 1;
                            retmsg.obj = new Double(Math.sqrt((double)msg.arg1));
                            break;
                    }
                    mMainHandler.sendMessage(retmsg);
                }

            };
            Looper.loop();
        }
    }

}
