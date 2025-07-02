package com.example.appbodycheck;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bmi_history")
public class bmirecord {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "user_name")
    public String name;

    @ColumnInfo(name = "bmi_value")
    public float bmiValue;

    @ColumnInfo(name = "bmi_category")
    public String category;

    @ColumnInfo(name = "weight_kg")
    public float weight;

    @ColumnInfo(name = "height_cm")
    public float height;

    @ColumnInfo(name = "user_age")
    public int age;

    @ColumnInfo(name = "user_gender")
    public String gender;

    @ColumnInfo(name = "timestamp")
    public long timestamp;
}