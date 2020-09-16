package edu.utep.cs4330.timer;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class TimerModel extends AppCompatActivity implements Runnable  {
    private final AtomicBoolean running = new AtomicBoolean(false); // Feature Flag: START and STOP thread
    private Thread worker;
    private long startTime;
    private long elapsedTime;
    private final int sleepInterval = 1000;

    // Android UI
    private TextView textView;

    public TimerModel(TextView textView){
        this.textView = textView;
    }


    public void start(){
        worker = new Thread(this);
        worker.start();
    }

    public void stop() { running.set(false); }      // Feature Flag: Set to false to kill thread.

    private String millisToDisplayFormat(long milliseconds) {

        String result = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(milliseconds),
                TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds)));

        return result;
    }

    public String getElapsedTime() {
        return  millisToDisplayFormat(elapsedTime);
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        running.set(true);                          // Feature Flag: Set to true to start new thread.

        new Thread(() -> {
            while (running.get()) {
                elapsedTime = System.currentTimeMillis() - startTime;
                try {
                       textView.setText(millisToDisplayFormat(elapsedTime));
                       Thread.sleep(200);
                } catch (Exception e){
                    break;
                }
            }
        }).start();
    }
}