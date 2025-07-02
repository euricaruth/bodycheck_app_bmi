package com.example.appbodycheck; // Pastikan ini sesuai dengan nama package Anda

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Locale;

public class ResultActivity extends AppCompatActivity {

    private static final float MIN_BMI = 10.0f;
    private static final float MAX_BMI = 40.0f;
    private static final float MIN_ANGLE = -90.0f;
    private static final float MAX_ANGLE = 90.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ImageButton addNewButton = findViewById(R.id.addNewCalculationButton);
        addNewButton.setOnClickListener(v -> finish());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name", "User");
            float bmi = extras.getFloat("bmi", 0.0f);
            int age = extras.getInt("age", 0);
            String gender = extras.getString("gender", "-");
            float weight = extras.getFloat("weightVal", 0.0f);
            float height = extras.getFloat("heightVal", 0.0f);

            TextView userNameTextView = findViewById(R.id.userName);
            TextView weightTextView = findViewById(R.id.weightValue);
            TextView heightTextView = findViewById(R.id.heightValue);
            TextView ageTextView = findViewById(R.id.ageValue);
            TextView genderTextView = findViewById(R.id.genderValue);
            ImageView needleImageView = findViewById(R.id.needle);
            TextView bmiScoreTextView = findViewById(R.id.bmiScoreValue);
            TextView bmiCategoryTextView = findViewById(R.id.bmiCategory);
            TextView recommendationTitle = findViewById(R.id.recommendationTitle);
            TextView recommendationTextView = findViewById(R.id.recommendationText);
            ImageView recommendationIcon = findViewById(R.id.recommendationIcon);

            userNameTextView.setText(name);
            weightTextView.setText(String.format(Locale.US, "%.1f kg", weight));
            heightTextView.setText(String.format(Locale.US, "%.1f cm", height));
            ageTextView.setText(String.format(Locale.US, "%d tahun", age));
            genderTextView.setText(gender);
            bmiScoreTextView.setText(String.format(Locale.US, "%.1f", bmi));

            float rotationAngle = getRotationAngleForBmi(bmi);
            needleImageView.animate().rotation(rotationAngle).setDuration(1500).setStartDelay(500).start();

            updateRecommendation(bmi, bmiCategoryTextView, recommendationTitle, recommendationTextView, recommendationIcon);
        }
    }

    private float getRotationAngleForBmi(float bmiValue) {
        float clampedBmi = Math.max(MIN_BMI, Math.min(bmiValue, MAX_BMI));
        float bmiRange = MAX_BMI - MIN_BMI;
        float normalizedBmi = (clampedBmi - MIN_BMI) / bmiRange;
        float angleRange = MAX_ANGLE - MIN_ANGLE;

        return MAX_ANGLE - (normalizedBmi * angleRange);
    }

    private void updateRecommendation(float bmi, TextView categoryView, TextView titleView, TextView recommendationView, ImageView iconView) {
        String category;
        String title;
        String recommendation;
        int iconResource;

        if (bmi >= 18.5 && bmi < 24.9) {
            category = "Normal";
            title = "Keep up the good work!";
            recommendation = "Your BMI is in the normal range. Maintain your healthy lifestyle.";
            iconResource = R.drawable.baseline_thumb_up_24;
        } else {
            if (bmi < 18.5) {
                category = "Underweight";
                title = "Attention needed!";
                recommendation = "You are underweight. It's recommended to increase your intake of nutrient-rich foods.";
            } else if (bmi < 29.9) {
                category = "Overweight";
                title = "Attention needed!";
                recommendation = "You are in the overweight range. Consider a balanced diet and regular exercise.";
            } else {
                category = "Obesity";
                title = "Consult a specialist!";
                recommendation = "Your BMI falls within the obesity range. It is highly recommended to consult a doctor.";
            }
            iconResource = R.drawable.baseline_warning_amber_24;
        }

        categoryView.setText(category);
        titleView.setText(title);
        recommendationView.setText(recommendation);
        iconView.setImageResource(iconResource);
    }
}