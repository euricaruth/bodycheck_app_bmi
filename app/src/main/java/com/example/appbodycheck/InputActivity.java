package com.example.appbodycheck;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InputActivity extends AppCompatActivity {

    EditText fullName, weight, height, age;
    RadioGroup genderGroup;
    Button calculateBmi;
    ImageButton historyButton;

    private appdatabase mDb;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen.installSplashScreen(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mDb = appdatabase.getDatabase(getApplicationContext());
        executorService = Executors.newSingleThreadExecutor();

        fullName = findViewById(R.id.fullName);
        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        age = findViewById(R.id.age);
        genderGroup = findViewById(R.id.genderGroup);
        calculateBmi = findViewById(R.id.calculate_bmi);
        historyButton = findViewById(R.id.historyButton);
        ImageButton historyButton = findViewById(R.id.historyButton);

        calculateBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    processBmiCalculation();
                }
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private void processBmiCalculation() {
        String nameStr = fullName.getText().toString().trim();
        float weightVal = Float.parseFloat(weight.getText().toString());
        float heightValCm = Float.parseFloat(height.getText().toString());
        int ageVal = Integer.parseInt(age.getText().toString());
        int selectedGenderId = genderGroup.getCheckedRadioButtonId();
        RadioButton selectedGender = findViewById(selectedGenderId);
        String genderStr = selectedGender.getText().toString();

        float heightValM = heightValCm / 100;
        float bmi = weightVal / (heightValM * heightValM);
        String category = getBmiCategory(bmi);

        bmirecord record = new bmirecord();
        record.name = nameStr;
        record.bmiValue = bmi;
        record.category = category;
        record.weight = weightVal;
        record.height = heightValCm;
        record.age = ageVal;
        record.gender = genderStr;
        record.timestamp = System.currentTimeMillis();

        saveRecordToDatabase(record);

        Intent intent = new Intent(InputActivity.this, ResultActivity.class);
        intent.putExtra("name", nameStr);
        intent.putExtra("bmi", bmi);
        intent.putExtra("age", ageVal);
        intent.putExtra("gender", genderStr);
        intent.putExtra("weightVal", weightVal);
        intent.putExtra("heightVal", heightValCm);
        startActivity(intent);
    }

    private void saveRecordToDatabase(final bmirecord record) {
        executorService.execute(() -> mDb.bmiDao().insert(record));
    }

    @Override
    protected void onResume() {
        super.onResume();
        clearInputFields();
    }

    private void clearInputFields() {
        fullName.setText("");
        weight.setText("");
        height.setText("");
        age.setText("");
        genderGroup.clearCheck();
        fullName.requestFocus();
    }

    private String getBmiCategory(float bmi) {
        if (bmi < 18.5) return "Underweight";
        if (bmi < 24.9) return "Normal";
        if (bmi < 29.9) return "Overweight";
        return "Obesity";
    }

    private boolean validateInput() {
        if (fullName.getText().toString().trim().isEmpty()) {
            fullName.setError("Please enter your name");
            return false;
        }
        if (weight.getText().toString().trim().isEmpty()) {
            weight.setError("Please enter your weight");
            return false;
        }
        if (height.getText().toString().trim().isEmpty()) {
            height.setError("Please enter your height");
            return false;
        }
        if (age.getText().toString().trim().isEmpty()) {
            age.setError("Please enter your age");
            return false;
        }
        if (genderGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}