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
import android.widget.ImageView;

public class FaqFireFragment extends Fragment implements View.OnClickListener{

    public FaqFireFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.faqfire,container,false);

        Button eart = (Button) rootView.findViewById(R.id.button5);
        Button typh = (Button) rootView.findViewById(R.id.button11);

        eart.setOnClickListener(this);
        typh.setOnClickListener(this);

        return rootView;
    }

    public void onClick(View v){
        Fragment frag = null;
        switch(v.getId()){
            case R.id.button5:
                frag = new FaqEarthFragment();
                replaceFragment(frag);
                break;

                case R.id.button11:
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
