package database.kotlin.flow9.net.threadbasic.strictmode;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;
import java.io.IOException;

import database.kotlin.flow9.net.threadbasic.R;

import static database.kotlin.flow9.net.threadbasic.BuildConfig.DEBUG;

public class StrictModeActivity extends AppCompatActivity {

    public static final boolean DEBUG_MODE = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (DEBUG) {
            StrictMode.ThreadPolicy thPolicy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyDialog()
                    .penaltyLog()
                    .build();
            StrictMode.setThreadPolicy(thPolicy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strict_mode);
    }

    public void btnFile(View v) {
        try {
            FileOutputStream fos = new FileOutputStream("text.txt");
            String str = "strict mode test";
            fos.write(str.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {

        }
    }
    
}
