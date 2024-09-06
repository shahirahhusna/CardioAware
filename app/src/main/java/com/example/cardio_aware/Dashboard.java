package com.example.cardio_aware;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    LinearLayout info, exercise, game;
    CardView about, framingham, hospital, ijn1, ijn2, ijn3;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //codes for bottom navigation
        info = findViewById(R.id.navInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Info.class));
            }
        });
        exercise = findViewById(R.id.navExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Exercise.class));
            }
        });
        game = findViewById(R.id.navGame);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SaveTheBunny.class));
            }
        });



        //codes for top button
        about = findViewById(R.id.aboutCard);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, AboutCVD.class));
            }
        });

        framingham = findViewById(R.id.framinghamCard);
        framingham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, FraminghamGender.class));
            }
        });

        hospital = findViewById(R.id.hospitalCard);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, Hospital.class));
            }
        });

        ijn1 = findViewById(R.id.ijn1);
        ijn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ijn.com.my/patient-care/how-the-heart-works/"));
                startActivity(intent);
            }
        });
        ijn2 = findViewById(R.id.ijn2);
        ijn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ijn.com.my/patient-care/inherited-heart-conditions/"));
                startActivity(intent);
            }
        });
        ijn3 = findViewById(R.id.ijn3);
        ijn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ijn.com.my/patient-care/nutrition-for-heart-failure/"));
                startActivity(intent);
            }
        });


        //get image slider id and set array
        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        //insert image content for image slider
        slideModels.add(new SlideModel(R.drawable.info1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.info2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.info3, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);


    }
}
