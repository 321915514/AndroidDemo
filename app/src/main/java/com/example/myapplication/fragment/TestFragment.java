package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.Test;

public class TestFragment extends Fragment {
    private static TestFragment INSTANCE;

    private String param;

    public static TestFragment getInstance(String param){
        if(INSTANCE == null){
            synchronized (TestFragment.class){
                if(INSTANCE == null){
                    INSTANCE = new TestFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("param",param);
                    INSTANCE.setArguments(bundle);
                }
            }
        }
        return INSTANCE;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        TextView textView = view.findViewById(R.id.tv_fragment);
        textView.setText(param);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getArguments() != null) {
            param = getArguments().getString("param");
        }
    }
}
