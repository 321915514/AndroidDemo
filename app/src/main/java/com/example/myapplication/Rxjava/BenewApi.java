package com.example.myapplication.Rxjava;

import android.content.Intent;
import android.util.Log;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.subscribers.FutureSubscriber;
import io.reactivex.rxjava3.subjects.Subject;

public class BenewApi {

    private static final String TAG = "BenewApi";

    Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onComplete();
        }
    });
    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.e(TAG,"onSubscribe");
        }

        @Override
        public void onNext(@NonNull Integer integer) {
            Log.e(TAG,"onNext"+integer);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG,"onError");
        }

        @Override
        public void onComplete() {
            Log.e(TAG,"onComplete");
        }
    };






}
