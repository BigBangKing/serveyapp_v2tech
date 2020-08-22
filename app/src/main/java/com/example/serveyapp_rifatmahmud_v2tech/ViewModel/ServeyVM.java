package com.example.serveyapp_rifatmahmud_v2tech.ViewModel;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.serveyapp_rifatmahmud_v2tech.data.pojo.schema2x;
import com.example.serveyapp_rifatmahmud_v2tech.repository.SurveyRepository;

public class ServeyVM extends AndroidViewModel {

    MutableLiveData<String> mResponse;
    MutableLiveData<Boolean> showDoneButton;
    MutableLiveData<schema2x[]> surveys;
    schema2x[] CurrentSurveys;
    int i = 0;
    StringBuilder CurrentValues;


    public ServeyVM(@NonNull Application application) {
        super(application);

        SurveyRepository surveyRepository = new SurveyRepository();
        surveyRepository.StartFetching(application);

        //mResponse = serveyRepository.getmResponse();
        surveys = surveyRepository.getServeyArrays();


    }

    public void addValues(String values) {
        if (CurrentValues == null){
            CurrentValues = new StringBuilder();
        }
        CurrentValues.append(" --- ").append(values);
    }

    public void showValues() {
        Toast.makeText(getApplication(), CurrentValues.toString(), Toast.LENGTH_LONG).show();
    }

    public void populateSurvey() {
        CurrentSurveys = surveys.getValue();
    }

    public schema2x IncSurvey() {

        i++;
        if (i >= CurrentSurveys.length) {
            i = 0;
            showDoneButton.setValue(true);
        } else showDoneButton.setValue(false);

        return CurrentSurveys[i];

    }

    public schema2x decSurvey() {
        i--;
        if (i < 0) {
            i = CurrentSurveys.length - 1;
            showDoneButton.setValue(true);
        } else showDoneButton.setValue(false);

        return CurrentSurveys[i];

    }

    public schema2x getFirstSurvey() {
        return CurrentSurveys[0];

    }

    public MutableLiveData<String> getmResponse() {
        return mResponse;
    }

    public MutableLiveData<schema2x[]> getSurveys() {
        return surveys;
    }


    public MutableLiveData<Boolean> getShowDoneButton() {
        if (showDoneButton == null) {
            showDoneButton = new MutableLiveData<>();
        }
        return showDoneButton;
    }

    public void setShowDoneButton(MutableLiveData<Boolean> showDoneButton) {
        this.showDoneButton = showDoneButton;
    }

}
