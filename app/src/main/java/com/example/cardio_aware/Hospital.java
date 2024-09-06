package com.example.cardio_aware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Hospital extends AppCompatActivity {

    LinearLayout home, info, exercise, game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hospital);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospital.this, Dashboard.class));
            }
        });

        TextView title = findViewById(R.id.toolbarText);
        title.setText("Top Hospitals for CVD in Malaysia");

        //codes for bottom navigation
        home = findViewById(R.id.navHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospital.this, Dashboard.class));
            }
        });
        info = findViewById(R.id.navInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospital.this, Info.class));
            }
        });
        exercise = findViewById(R.id.navExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospital.this, Exercise.class));
            }
        });
        game = findViewById(R.id.navGame);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Hospital.this, SaveTheBunny.class));
            }
        });

    }
}