package com.example.appbodycheck;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.HistoryViewHolder> {

    private List<bmirecord> mRecords = new ArrayList<>();
    private Context mContext;

    public historyAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_history, parent, false);
        return new HistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        bmirecord currentRecord = mRecords.get(position);

        holder.nameTextView.setText(currentRecord.name);
        String bmiText = String.format(Locale.US, "BMI: %.1f (%s)", currentRecord.bmiValue, currentRecord.category);
        holder.bmiTextView.setText(bmiText);

        if ("Male".equalsIgnoreCase(currentRecord.gender)) {
            holder.profileImageView.setImageResource(R.drawable.male);
        } else {
            holder.profileImageView.setImageResource(R.drawable.female);
        }

        if ("Normal".equalsIgnoreCase(currentRecord.category)) {
            holder.bmiTextView.setTextColor(ContextCompat.getColor(mContext, R.color.green));
        } else {
            holder.bmiTextView.setTextColor(ContextCompat.getColor(mContext, R.color.orange));
        }

        holder.detailButton.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ResultActivity.class);

            intent.putExtra("name", currentRecord.name);
            intent.putExtra("bmi", currentRecord.bmiValue);
            intent.putExtra("age", currentRecord.age);
            intent.putExtra("gender", currentRecord.gender);
            intent.putExtra("weightVal", currentRecord.weight);
            intent.putExtra("heightVal", currentRecord.height);

            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }

    public void setRecords(List<bmirecord> records) {
        mRecords = records;
        notifyDataSetChanged();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView bmiTextView;
        private final ImageView profileImageView;
        private final Button detailButton;

        public HistoryViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.history_person_name);
            bmiTextView = itemView.findViewById(R.id.history_person_bmi);
            profileImageView = itemView.findViewById(R.id.history_profile_image);
            detailButton = itemView.findViewById(R.id.seeDetailButton);
        }
    }
}