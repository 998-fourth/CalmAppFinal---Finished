package com.example.calmappscreens;

import android.content.Intent;
import android.media.Image;
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

public class FaqFragment extends Fragment implements View.OnClickListener{

    public FaqFragment(){

    }

    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_faq,container,false);

        ImageView wtdw = (ImageView) rootView.findViewById(R.id.imageView20);

        wtdw.setOnClickListener(this);

        return rootView;
        }

public void onClick(View v){
        Fragment frag = null;
        switch(v.getId()){
            case R.id.imageView20:
                frag = new FaqEarthFragment();
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

