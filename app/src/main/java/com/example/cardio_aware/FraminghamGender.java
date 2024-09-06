package com.example.cardio_aware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FraminghamGender extends AppCompatActivity {

    LinearLayout home, info, exercise, game;
    CardView femaleF, maleF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.framinghamgender);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, Dashboard.class));
            }
        });

        TextView title = findViewById(R.id.toolbarText);
        title.setText("Framingham Risk Calculator");

        femaleF = findViewById(R.id.femaleCard);
        maleF = findViewById(R.id.maleCard);

        femaleF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, F_FemaleCalculator.class));
            }
        });

        maleF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, F_MaleCalculator.class));
            }
        });

        //codes for bottom navigation
        home = findViewById(R.id.navHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, Dashboard.class));
            }
        });
        info = findViewById(R.id.navInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, Info.class));
            }
        });
        exercise = findViewById(R.id.navExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, Exercise.class));
            }
        });
        game = findViewById(R.id.navGame);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FraminghamGender.this, SaveTheBunny.class));
            }
        });

    }
}