package com.example.appbodycheck;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class historyviewmodel extends AndroidViewModel {

    private bmiDao mBmiDao;
    private LiveData<List<bmirecord>> mAllRecords;

    public historyviewmodel(@NonNull Application application) {
        super(application);
        appdatabase db = appdatabase.getDatabase(application);
        mBmiDao = db.bmiDao();
        mAllRecords = mBmiDao.getAllRecords();
    }

    LiveData<List<bmirecord>> getAllRecords() {
        return mAllRecords;
    }
}