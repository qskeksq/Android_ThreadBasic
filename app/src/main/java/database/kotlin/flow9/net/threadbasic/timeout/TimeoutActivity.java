package database.kotlin.flow9.net.threadbasic.timeout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import database.kotlin.flow9.net.threadbasic.R;

public class TimeoutActivity extends AppCompatActivity {

    private static final int MESSAGE_DIALOG_TIMEOUT = 1;
    private static final long TIMEOUT_DIALOG_DELAY = 5000;
    private static final int MESSAGE_BACKKEY_TIMEOUT = 2;
    private static final long TIMEOUT_BACKKEY_DELAY = 2000;
    boolean isBackPressed = false;
    AlertDialog mDialog;

    Handler timeoutHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_DIALOG_TIMEOUT:
                    Toast.makeText(TimeoutActivity.this, "Dialog Timeout", Toast.LENGTH_LONG).show();
            }
        }
    };

    Handler backKeyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BACKKEY_TIMEOUT:
                    isBackPressed = false;
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeout);
        // timeout dialog
        mDialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle("Timeout Dialog")
                .setMessage("This dialog is time out test dialog")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        timeoutHandler.removeMessages(MESSAGE_DIALOG_TIMEOUT);
                        Toast.makeText(TimeoutActivity.this, "Timeout Canceled", Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        mDialog.show();
        timeoutHandler.sendEmptyMessageDelayed(MESSAGE_DIALOG_TIMEOUT, TIMEOUT_DIALOG_DELAY);
    }

    @Override
    public void onBackPressed() {
        if (!isBackPressed) {
            isBackPressed = true;
            Toast.makeText(this, "BackKey Pressed", Toast.LENGTH_LONG).show();
            backKeyHandler.sendEmptyMessageDelayed(MESSAGE_BACKKEY_TIMEOUT, TIMEOUT_BACKKEY_DELAY);
        } else {
            backKeyHandler.removeMessages(MESSAGE_BACKKEY_TIMEOUT);
            super.onBackPressed();
        }
    }



}
