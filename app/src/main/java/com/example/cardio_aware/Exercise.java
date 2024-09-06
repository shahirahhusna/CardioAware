package com.example.cardio_aware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Exercise extends AppCompatActivity {

    LinearLayout walking, running, cycling, swimming, dancing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_exercise);

        walking = findViewById(R.id.walking);
        walking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise.this, Walking.class);
                startActivity(intent);
                finish();
            }
        });

        running = findViewById(R.id.running);
        running.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise.this, Running.class);
                startActivity(intent);
                finish();
            }
        });

        cycling = findViewById(R.id.cycling);
        cycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise.this, Cycling.class);
                startActivity(intent);
                finish();
            }
        });

        swimming = findViewById(R.id.swimming);
        swimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise.this, Swimming.class);
                startActivity(intent);
                finish();
            }
        });

        dancing = findViewById(R.id.dancing);
        dancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exercise.this, Dancing.class);
                startActivity(intent);
                finish();
            }
        });

    }
}