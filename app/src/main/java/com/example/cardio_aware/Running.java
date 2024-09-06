package com.example.cardio_aware;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Running extends AppCompatActivity {

    ImageView back;
    private TextView timerTextView, resetButton;
    private Button startStopButton;
    private ListView listView;
    private Handler handler = new Handler();
    private long startTime = 0L;
    private boolean isRunning = false;
    private List<String> timeList = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "StopwatchPrefs";
    private static final String TIME_LIST_KEY = "TimeList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_running);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Running.this, Exercise.class);
                startActivity(intent);
                finish();
            }
        });
        timerTextView = findViewById(R.id.timerTextView);
        startStopButton = findViewById(R.id.startStopButton);
        resetButton = findViewById(R.id.resetButton);
        listView = findViewById(R.id.listView);

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        loadSavedTimes();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, timeList);
        listView.setAdapter(adapter);

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRunning) {
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimes();
            }
        });

    }

    private void startTimer() {
        startTime = System.currentTimeMillis();
        handler.postDelayed(updateTimerRunnable, 0);
        startStopButton.setText("Stop");
        isRunning = true;
    }

    private void stopTimer() {
        handler.removeCallbacks(updateTimerRunnable);
        addTimeToList(getCurrentTime());
        saveTimes();
        startStopButton.setText("Start");
        isRunning = false;
    }

    private void resetTimes() {
        timeList.clear();
        adapter.notifyDataSetChanged();
        saveTimes();
    }

    private Runnable updateTimerRunnable = new Runnable() {
        @Override
        public void run() {
            timerTextView.setText(getCurrentTime());
            handler.postDelayed(this, 1000);
        }
    };

    private String getCurrentTime() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private void addTimeToList(String time) {
        timeList.add(time);
        adapter.notifyDataSetChanged();
    }

    private void saveTimes() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TIME_LIST_KEY, String.join(",", timeList));
        editor.apply();
    }

    private void loadSavedTimes() {
        String savedTimes = sharedPreferences.getString(TIME_LIST_KEY, "");
        if (!savedTimes.isEmpty()) {
            String[] times = savedTimes.split(",");
            for (String time : times) {
                timeList.add(time);
            }
        }
    }

}