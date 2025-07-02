package com.example.appbodycheck;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryActivity extends AppCompatActivity {

    private historyviewmodel mHistoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.historyRecyclerView);
        final historyAdapter adapter = new historyAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mHistoryViewModel = new ViewModelProvider(this).get(historyviewmodel.class);

        mHistoryViewModel.getAllRecords().observe(this, records -> {
            adapter.setRecords(records);

            ImageButton backButton = findViewById(R.id.backButton);
            backButton.setOnClickListener(v -> {
                finish();
            });
        });
    }
}