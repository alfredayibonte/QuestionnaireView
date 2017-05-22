package com.alfredayibonte.questionnaireview;

import android.app.Application;

import com.alfredayibonte.questionnaireview.models.ResponseObject;
import com.alfredayibonte.questionnaireview.utils.JsonHelper;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 *
 * Created by Alfredayibonte on 5/18/17.
 */

public class AppController extends Application {
    private List<ResponseObject> questions;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    Gson gson;
    @Override
    public void onCreate() {
        super.onCreate();
        questions = new ArrayList<>();
        String result = JsonHelper.loadJSONFromAsset(getApplicationContext());
        Type type = new TypeToken<List<ResponseObject>>() {}.getType();
        gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();
        questions = gson.fromJson(result, type);
    }

    public List<ResponseObject> getQuestions(){
        return questions;
    }
}
