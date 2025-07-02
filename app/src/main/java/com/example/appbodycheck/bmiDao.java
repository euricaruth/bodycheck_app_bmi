package com.example.appbodycheck;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface bmiDao {

    @Insert
    void insert(bmirecord record);

    @Query("SELECT * FROM bmi_history ORDER BY timestamp DESC")
    LiveData<List<bmirecord>> getAllRecords();

    @Query("DELETE FROM bmi_history")
    void deleteAll();
}