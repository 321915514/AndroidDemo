package com.example.myapplication.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class AFragment extends Fragment {
    private TextView textView;
    private Button button1,button2,btn_message;
    private Fragment bFragment;
    private IOnMessageClick click;


    public static AFragment newInstance(String title){
        AFragment aFragment = new AFragment();
        Bundle bundle = new Bundle();
         bundle.putString("title",title);
         aFragment.setArguments(bundle);
        return aFragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a,container,false);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.tv_fragment);
        // 传值
        if(getArguments() != null){
            textView.setText(getArguments().getString("title"));
        }
        button1 = view.findViewById(R.id.btn_change1);
        button2 = view.findViewById(R.id.btn_change2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bFragment == null){
                    bFragment = new BFragment();
                }
                Fragment fragment = getFragmentManager().findFragmentByTag("a");
                if(fragment!= null){
                    // 若manage含有a标签的fragment，将这个隐藏，再添加bfragment，同时加入回退栈。
                    getFragmentManager().beginTransaction().hide(fragment).add(R.id.btn_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }else {
                    getFragmentManager().beginTransaction().replace(R.id.btn_container,bFragment).addToBackStack(null).commitAllowingStateLoss();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("我是新文字");
            }
        });
        btn_message = view.findViewById(R.id.btn_message);
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 不推荐
                //((ContainerActivity)getActivity()).setData("你好");
                click.onClick("你好");
            }
        });
    }
    // fragment 和activity 有关系会调用
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //
        click = (IOnMessageClick) context;
    }

    public interface IOnMessageClick{
        void onClick(String s);
    }
}
