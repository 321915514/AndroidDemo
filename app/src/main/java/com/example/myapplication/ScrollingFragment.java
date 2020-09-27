package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ScrollingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling, container, false);
    }
    public static class ScrollingFragmentHolder{
        private static final ScrollingFragment INSTANCE = new ScrollingFragment();
    }
    public static ScrollingFragment getInstance(){
        return ScrollingFragmentHolder.INSTANCE;
    }

}