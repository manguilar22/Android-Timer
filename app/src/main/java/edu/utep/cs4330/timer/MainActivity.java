package edu.utep.cs4330.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView timeDisplay;
    private Button startButton;
    private Button stopButton;

    // Timer with Thread
    private TimerModel timerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Setting up call to view's ids.
        timeDisplay = findViewById(R.id.timeDisplay);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        // Event Handling Buttons.
        startButton.setOnClickListener(this::startClick);
        stopButton.setOnClickListener(this::stopClick);
        // Setting up default timer.
        timeDisplay.setText("00:00:00");
        // Start TimerModel.
        timerModel = new TimerModel(timeDisplay);
        timerModel.start();
    }

    private void startClick(View view) {
        //timeDisplay.setText("Start Clicked");
        // Debugger
        Toast.makeText(this," Start Tapped", Toast.LENGTH_SHORT).show();

        // Timer
        timeDisplay.setText("00:00:00");

        // Start timer thread on UI
        this.runOnUiThread(timerModel::run);

        startButton.setEnabled(false);   // Disable Button
        stopButton.setEnabled(true);    // Enable Button
    }

    private void stopClick(View view) {
        timeDisplay.setText(timerModel.getElapsedTime());

        timerModel.stop();

        startButton.setEnabled(true);
        stopButton.setEnabled(false);

    }


}
