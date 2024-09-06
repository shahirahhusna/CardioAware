package com.example.cardio_aware;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class F_FemaleCalculator extends AppCompatActivity {

    ImageView back;
    // Define constants for risk calculation
    private static final int[] AGE_POINTS = {-7, -3, 0, 3, 6, 8, 10, 12, 14, 16};
    private static final int[][] CHOLESTEROL_POINTS = {
            {0, 4, 8, 11, 13},
            {0, 3, 6, 8, 10},
            {0, 2, 4, 5, 7},
            {0, 1, 2, 3, 4},
            {0, 1, 1, 2, 2}
    };
    private static final int[] SMOKER_POINTS = {9, 7, 4, 2, 1};
    private static final int[] HDL_POINTS = {-1, 0, 1, 2};
    private static final int[][] BLOOD_PRESSURE_POINTS = {
            {0, 1, 2, 3, 4},
            {0, 3, 4, 5, 6}
    };
    private static final int[] RISK_PERCENTAGES = {1, 2, 3, 4, 5, 6, 8, 11, 14, 17, 22, 27, 30};

    // Define UI elements
    private EditText ageEditText;
    private EditText cholesterolEditText;
    private CheckBox smokerCheckBox;
    private EditText hdlEditText;
    private EditText bloodPressureEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.f_femalecalculator);

        ImageView back = findViewById(R.id.backIcon);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(F_FemaleCalculator.this, FraminghamGender.class));
            }
        });

        TextView title = findViewById(R.id.toolbarText);
        title.setText("Woman Risk Calculator");

        // Initialize UI elements
        ageEditText = findViewById(R.id.ageEditText);
        cholesterolEditText = findViewById(R.id.cholesterolEditText);
        smokerCheckBox = findViewById(R.id.smokerCheckBox);
        hdlEditText = findViewById(R.id.hdlEditText);
        bloodPressureEditText = findViewById(R.id.bloodPressureEditText);
        resultTextView = findViewById(R.id.resultTextView);

        // Set onClickListener for the Calculate button
        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateRisk();
            }
        });
    }

    private void calculateRisk() {
        // Retrieve values from UI elements
        int age = Integer.parseInt(ageEditText.getText().toString());
        int cholesterol = Integer.parseInt(cholesterolEditText.getText().toString());
        boolean isSmoker = smokerCheckBox.isChecked();
        int hdl = Integer.parseInt(hdlEditText.getText().toString());
        int bloodPressure = Integer.parseInt(bloodPressureEditText.getText().toString());

        // Calculate total points
        int agePoints = AGE_POINTS[Math.min(age - 20, AGE_POINTS.length - 1)];
        int cholesterolPoints = CHOLESTEROL_POINTS[getCholesterolCategory(age, cholesterol)][getCholesterolLevel(cholesterol)];
        int smokerPoints = isSmoker ? SMOKER_POINTS[getSmokerCategory(age)] : 0;
        int hdlPoints = HDL_POINTS[getHdlCategory(hdl)];
        int bloodPressurePoints = BLOOD_PRESSURE_POINTS[getBloodPressureCategory(bloodPressure)][getBloodPressureLevel(bloodPressure)];

        int totalPoints = agePoints + cholesterolPoints + smokerPoints + hdlPoints + bloodPressurePoints;

        // Calculate 10-year risk percentage
        int riskPercentage = calculateRiskPercentage(totalPoints);

        // Display result
        resultTextView.setText("10-year risk: " + riskPercentage + "%");
    }

    private int getCholesterolCategory(int age, int cholesterol) {
        if (age < 40) return 0;
        else if (age < 50) return 1;
        else if (age < 60) return 2;
        else if (age < 70) return 3;
        else return 4;
    }

    private int getCholesterolLevel(int cholesterol) {
        if (cholesterol < 160) return 0;
        else if (cholesterol < 200) return 1;
        else if (cholesterol < 240) return 2;
        else if (cholesterol < 280) return 3;
        else return 4;
    }

    private int getSmokerCategory(int age) {
        if (age < 40) return 0;
        else if (age < 50) return 1;
        else if (age < 60) return 2;
        else if (age < 70) return 3;
        else return 4;
    }

    private int getHdlCategory(int hdl) {
        if (hdl >= 60) return 0;
        else if (hdl >= 50) return 1;
        else if (hdl >= 40) return 2;
        else return 3;
    }

    private int getBloodPressureCategory(int bloodPressure) {
        if (bloodPressure < 120) return 0;
        else return 1;
    }

    private int getBloodPressureLevel(int bloodPressure) {
        if (bloodPressure < 120) return 0;
        else if (bloodPressure < 130) return 1;
        else if (bloodPressure < 140) return 2;
        else if (bloodPressure < 160) return 3;
        else return 4;
    }

    private int calculateRiskPercentage(int totalPoints) {
        for (int i = 0; i < RISK_PERCENTAGES.length; i++) {
            if (totalPoints <= i + 9) {
                return RISK_PERCENTAGES[i];
            }
        }
        return 30; // Default risk percentage if total points exceed the maximum

    }
}