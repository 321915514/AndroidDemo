package com.example.myapplication.component;

import com.example.myapplication.model.Translation;
import com.example.myapplication.model.Translation1;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=zh&w=hello%20world")
    Observable<Translation> getCall();

    @GET("ajax.php?a=fy&f=auto&t=auto&w=miracle")
    Observable<Translation1> getCall_2();

}
