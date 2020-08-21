package com.example.serveyapp_rifatmahmud_v2tech.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.serveyapp_rifatmahmud_v2tech.repository.ServeyRepository;

public class ServeyVM extends AndroidViewModel {

    MutableLiveData<String> mResponse;

    public ServeyVM(@NonNull Application application) {
        super(application);

        ServeyRepository serveyRepository = new ServeyRepository();
        serveyRepository.StartFetching(application);

        mResponse = serveyRepository.getmResponse();


    }

    public MutableLiveData<String> getmResponse() {
        return mResponse;
    }
}
