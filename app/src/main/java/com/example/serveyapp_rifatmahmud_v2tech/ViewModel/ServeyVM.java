package com.example.serveyapp_rifatmahmud_v2tech.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x;
import com.example.serveyapp_rifatmahmud_v2tech.repository.ServeyRepository;

public class ServeyVM extends AndroidViewModel {

    MutableLiveData<String> mResponse;
    MutableLiveData<schema2x[]> serveys;
    MutableLiveData<Boolean> shouldStartServey;

    public ServeyVM(@NonNull Application application) {
        super(application);

        ServeyRepository serveyRepository = new ServeyRepository();
        serveyRepository.StartFetching(application);

        mResponse = serveyRepository.getmResponse();
        serveys = serveyRepository.getServeyArrays();

    }

    public MutableLiveData<String> getmResponse() {
        return mResponse;
    }
    public MutableLiveData<schema2x[]> getServeys() {
        return serveys;
    }

    public MutableLiveData<Boolean> getShouldStartServey() {
        return shouldStartServey;
    }

    public void setShouldStartServey(MutableLiveData<Boolean> shouldStartServey) {
        this.shouldStartServey = shouldStartServey;
    }
}
