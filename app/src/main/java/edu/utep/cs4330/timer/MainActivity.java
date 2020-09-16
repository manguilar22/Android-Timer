package edu.utep.cs4330.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    private TextView timeDisplay;
    private Button startButton;
    private Button stopButton;

    // Timer with Thread
    private TimerModel timerModel;


    /**
     * Android Life Cycle - START
     *  onCreate()
     *  onStart()
     *  onResume()
     *  onRestart()
     *  onPause()
     *  onStop()
     *  onDestroy()
     */
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
        // Start TimerModel.
        timerModel = new TimerModel(timeDisplay);
        timerModel.start();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"in method onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"in method onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"in method onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"in method onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"in method onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"in method onDestroy");
    }

    //  Android Life Cycle - END


    /*
    Handle Screen Orientation
    */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"in method onSaveInstanceState");

        outState.putString("myTimerTMP",timerModel.getElapsedTime());

        Toast.makeText(this," onSaveInstanceState Time Elapsed: "+timerModel.getElapsedTime(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG,"in method onRestoreInstanceState");

        timerModel.start();
        timerModel.run();

        Toast.makeText(this," onRestoreInstanceState Time Elapsed: "+this.timerModel.getElapsedTime(), Toast.LENGTH_LONG).show();

        String id = savedInstanceState.getString("myTimerTMP");
        timeDisplay.setText(id);
    }


    /*
        Handle Timer Operations.
    */
    private void startClick(View view) {
        // Debugger
        Toast.makeText(this," Start Tapped", Toast.LENGTH_SHORT).show();


        // Start timer thread on UI
        this.runOnUiThread(timerModel::run);

        startButton.setEnabled(false);   // Disable Button
        stopButton.setEnabled(true);    // Enable Button
    }

    private void stopClick(View view) {
        // Debugger
        Toast.makeText(this," Stop Tapped", Toast.LENGTH_SHORT).show();

        timeDisplay.setText(timerModel.getElapsedTime());

        timerModel.stop();

        startButton.setEnabled(true);
        stopButton.setEnabled(false);

    }


}
