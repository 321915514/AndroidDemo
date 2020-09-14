package com.example.myapplication.model;

import android.util.Log;

public class Translation {
    private int status;
    private content content;
    private static class content{
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
//        private String ph_am;
//        private String ph_am_mp3;
//        private String ph_en;
//        private String ph_en_mp3;
//        private String ph_tts_mp3;
//        private String word_mean;


    }
    public String show(){
        Log.e("Rxjava","第1次请求"+content.out);
        return content.out;
    }

}
