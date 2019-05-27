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

public class FaqEarthFragment extends Fragment implements View.OnClickListener{

    public FaqEarthFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.faqearth,container,false);

        Button fire = (Button) rootView.findViewById(R.id.button8);
        Button typh = (Button) rootView.findViewById(R.id.button9);

        fire.setOnClickListener(this);
        typh.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v){
        Fragment frag = null;
        switch(v.getId()){
            case R.id.button8:
                frag = new FaqFireFragment();
                replaceFragment(frag);
                break;

            case R.id.button9:
                frag = new FaqTyphoonFragment();
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
