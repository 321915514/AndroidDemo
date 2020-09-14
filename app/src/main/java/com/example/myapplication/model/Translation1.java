package com.example.myapplication.model;

import android.util.Log;

import java.util.List;

public class Translation1 {
    private int status;
    private content content;
    private static class content{
        private String ph_am;
        private String ph_am_mp3;
        private String ph_en;
        private String ph_en_mp3;
        private String ph_tts_mp3;
        private List<String> word_mean;


//        private String from;
//        private String to;
//        private String vendor;
//        private String out;
//        private int errNo;


    }
    public String show(){
        Log.e("Rxjava","第二次请求"+content.word_mean.toString());
        return content.word_mean.toString();
    }

}
