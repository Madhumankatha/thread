package com.madhumankatha.multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvOut;
    public static final int t1 = 1;
    public static final int t2 = 2;
    public static final int t3 = 3;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.out);
        button = findViewById(R.id.btnStart);

        tvOut.setText("Main Thread");

        Handler handler;
        handler = new Handler(msg -> {
            if (msg.what == t1)
                tvOut.append("\n In Thread 1");

            if (msg.what == t2)
                tvOut.append("\n In Thread 2");

            if (msg.what == t3)
                tvOut.append("\n In Thread 3");

            return true;
        });


        button.setOnClickListener(v -> {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(t1);
                }

            });
            thread.start();

            Thread thread2 = new Thread(() -> {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(t2);
                }

            });
            thread2.start();

            Thread thread3 = new Thread(() -> {
                for (int i = 0; i < 2; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(t3);
                }

            });
            thread3.start();



        });



    }

}