package com.example.calmappscreens;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RealTimeMonitorFragment extends Fragment {
    DatabaseReference reff;
    ImageView btngnrte;
    Button btnrun;
    String empname[] = new String[1000];
    String respond[] = new String[1000];
    String loc[] = new String[1000];
    TextView res, safe, nsafe;
    String names="";
    int counting = 0, counts, countns;
    long a;
    String A, name, response, location;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_realtimemonitoring,container,false);
        res = (TextView) view.findViewById(R.id.Results);
        safe =  view.findViewById(R.id.safe);
        Count();
        btnrun = (Button) view.findViewById(R.id.genr);
        btngnrte = (ImageView) view.findViewById(R.id.mainbg5);

        btngnrte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNames();
            }
        });

        //Display Results
         btnrun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ok Respond
                names = "Safe: \n";
                Log.d("", empname[0] + "'s response is: " + respond[0] + "\n");
                for(int i=0; i<Integer.parseInt(A); i++){
                    if(respond[i].equalsIgnoreCase("yes")){
                        Log.d("", "updatenames:" + names);
                        names = names + "Name: "+ empname[i]+" Location: " + loc[i] + "\n";
                        counts++;
                    }

                }
                //Not Okay/No Respond
                names = names + "\n\nNot Safe/No Response: \n";
                for(int i=0; i<=Integer.parseInt(A); i++){
                    if(respond[i].equalsIgnoreCase("no")||respond[i].equalsIgnoreCase("")){
                        Log.d("", "updatenames:" + names);
                        names = names + "Name: " + empname[i] + "\n";
                        countns++;
                    }
                }
                res.setText(names);
                safe.setText(counts + " are Safe \n" + countns + " are Not Safe");
            }
        });
        return view;
    }

    //First Click to Background to Get all the Names in DB
    public  void getNames(){
        for (int B = 0; B <=Integer.parseInt(A); B++) {
            reff = FirebaseDatabase.getInstance().getReference().child("Employee").child(("Employee " + B));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(int C=0; C<Integer.parseInt(A); C++){
                        name = dataSnapshot.child("_name").getValue().toString();
                        response = dataSnapshot.child("_res").getValue().toString();
                        location = dataSnapshot.child("_loc").getValue().toString();
                    }


                    empname[counting] = name;
                    respond[counting] = response;
                    loc[counting] = location;
                    Log.d("", empname[counting] + "'s response is: " +respond[counting] + "\n");
                    counting++;

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
    }

    //Count the number of Employee in DB
    public void Count(){
        reff = FirebaseDatabase.getInstance().getReference("Employee");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snap: dataSnapshot.getChildren()){
                    a = snap.getChildrenCount();
                }
                a-=1;
                A = "" + a;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
