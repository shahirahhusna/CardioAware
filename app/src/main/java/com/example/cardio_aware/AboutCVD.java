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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;

public class AboutCVD extends AppCompatActivity {

    LinearLayout home, info, exercise, game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_about_cvd);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutCVD.this, Dashboard.class));
            }
        });

        TextView title = findViewById(R.id.toolbarText);
        title.setText("About CVD");

        //get image slider id and set array
        ImageSlider imageSlider = findViewById(R.id.aboutimageSlider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        //insert image content for image slider
        slideModels.add(new SlideModel(R.drawable.whatiscvd, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.whatcvddetail, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typeofcvd, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd5, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.typecvd6, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        //codes for bottom navigation
        home = findViewById(R.id.navHome);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutCVD.this, Dashboard.class));
            }
        });
        info = findViewById(R.id.navInfo);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutCVD.this, Info.class));
            }
        });
        exercise = findViewById(R.id.navExercise);
        exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutCVD.this, Exercise.class));
            }
        });
        game = findViewById(R.id.navGame);
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AboutCVD.this, SaveTheBunny.class));
            }
        });

    }
}