package com.example.calmappscreens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FaqTyphoonFragment extends Fragment implements View.OnClickListener{

    public FaqTyphoonFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.faqtyphoon,container,false);

        Button eart = (Button) rootView.findViewById(R.id.button2);
        Button fire = (Button) rootView.findViewById(R.id.button3);

        eart.setOnClickListener(this);
        fire.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v){
        Fragment frag = null;
        switch(v.getId()){
            case R.id.button2:
                frag = new FaqEarthFragment();
                replaceFragment(frag);
                break;

            case R.id.button3:
                frag = new FaqFireFragment();
                replaceFragment(frag);
                break;
        }
    }
    public void replaceFragment(Fragment someFragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
