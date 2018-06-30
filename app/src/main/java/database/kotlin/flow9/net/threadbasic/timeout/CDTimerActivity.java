package database.kotlin.flow9.net.threadbasic.timeout;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import database.kotlin.flow9.net.threadbasic.R;

public class CDTimerActivity extends AppCompatActivity {

    Handler mHandler = new Handler();
    private static final long NOT_STARTED = -1L;
    long startTime = NOT_STARTED;
    int mCount = 20;
    int TIME_INTERVAL = 1000;
    TextView messageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdtimer);
        messageView = findViewById(R.id.messageView);
        Button button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mHandler.removeCallbacks(countDownRunnable);
                mCount = 20;
                startTime = NOT_STARTED;
                mHandler.post(countDownRunnable);
            }
        });

    }

    Runnable countDownRunnable = new Runnable() {
        @Override
        public void run() {
            long currentTime = SystemClock.uptimeMillis();

            if (startTime == NOT_STARTED) {
                startTime = currentTime;
            }

            // 지난 시간 millisecond 단위
            int interval = (int) (currentTime - startTime);
            // 초 단위로 환산
            int delayCount = interval/TIME_INTERVAL;
            // 만약 1000 millisecond 보다 더 지났으면 TIME_INTERVAL 인 1000으로 나눈 나머지 값 만큼 빨리 다음 runnable 을 호출해 줘야 한다.
            int rest = TIME_INTERVAL - (interval % TIME_INTERVAL);

            if (mCount - delayCount > 0) {
                messageView.setText("count down : " + (mCount - delayCount));
                mHandler.postDelayed(countDownRunnable, rest);
            } else {
                messageView.setText("countdown completed");
            }

        }
    };
}
