package com.example.calmappscreens;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManagerBCPFragment extends Fragment {

    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    Button in;
    ImageView send,nok,ok;
   /* Employee emp;
    Emergency emer;*/
    DatabaseReference reff;
    Spinner dropd;
    TextView disp;
    long maxId = 0;
    String number[] = new String[1000];
    String num, A, fNumber, itemvalue, emergency, message, IV_Get;
    long a;
    int count, cntr, counting = 0, sendm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.manscreen_alert, container, false);
        dropd = (Spinner) view.findViewById(R.id.E_Drop);
        disp = view.findViewById(R.id.display);
        send = view.findViewById(R.id.Send);
        ok = view.findViewById(R.id.Ok);
        nok = view.findViewById(R.id.NOK);
        dropd = view.findViewById(R.id.E_Drop);

        Count();


        send.setVisibility(View.GONE);

        //DropDown

        List<String> list = new ArrayList<>();

        list.add("Choose Type of Emergency");
        list.add("Earthquake-Drill");
        list.add("Earthquake-Emergency");
        list.add("Fire-Drill");
        list.add("Fire-Emergency");
        list.add("Typhoon-Drill");
        list.add("Typhoon-Emergency");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropd.setAdapter(adapter);
        dropd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemvalue = parent.getItemAtPosition(position).toString();
                if (itemvalue == "Choose Type of Emergency") {
                    onClick();
                    IV_Get = "";
                    disp.setText("");
                }else if (itemvalue == "Earthquake-Drill") {
                    onClick();
                    IV_Get = "\"THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due to an earthquake that has hit mckinley hill.\"";
                    disp.setText("THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due to an earthquake that has hit mckinley hill.");
                } else if (itemvalue == "Earthquake-Emergency") {
                    onClick();
                    IV_Get = "\"EMERGENCY. We are triggering a site call tree drill due to an earthquake that has hit mckinley hill.\"";
                    disp.setText("EMERGENCY. We are triggering a site call tree drill due to an earthquake that has hit mckinley hill.");
                } else if (itemvalue == "Fire-Drill") {
                    onClick();
                    IV_Get = "\"THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due to a fire that started within the surrounding area of mckinley hill.\"";
                    disp.setText("THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due to a fire that started within the surrounding area of mckinley hill.");
                } else if (itemvalue == "Fire-Emergency") {
                    onClick();
                    IV_Get = "\"EMERGENCY. We are triggering a site call tree drill due to a fire that started within the surrounding area of mckinley hill.\"";
                    disp.setText("EMERGENCY. We are triggering a site call tree drill due to a fire that started within the surrounding area of mckinley hill");
                } else if (itemvalue == "Typhoon-Drill") {
                    onClick();
                    IV_Get = "\"THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due typhoon ondoy.\"";
                    disp.setText("THIS IS AN EXERCISE DRILL. We are triggering a site call tree drill due typhoon ondoy.");
                } else if (itemvalue == "Typhoon-Emergency") {
                    onClick();
                    IV_Get = "\"EMERGENCY. We are triggering a site call tree drill due typhoon ondoy.\"";
                    disp.setText("EMERGENCY. We are triggering a site call tree drill due typhoon ondoy.");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //

        send.setEnabled(false);
        if (checkPermission(Manifest.permission.SEND_SMS)) {
            send.setEnabled(true);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }
        return view;

    }


    public void onClick() {
        for(count = 0; count<=Integer.parseInt(A); count++){
            reff = FirebaseDatabase.getInstance().getReference().child("Employee").child("Employee " + count);

            reff.addValueEventListener(new ValueEventListener() {

                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(cntr=0; cntr<count; cntr++){
                        num = dataSnapshot.child("_num").getValue().toString();
                    }
                    number[counting] = num;
                    counting++;
                    Log.d("", number[0]+ " " + number[1]+ " "  + number[2]+ " "  + number[3]+ " "  + number[4]+ " "  + number[5] + " " + number[6] + " " + number[7]);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    reff = FirebaseDatabase.getInstance().getReference();
                //Send Via App
                    for(count = 0; count<=Integer.parseInt(A); count++) {
                        reff.child("Employee").child("Employee " + count).child("_mess").setValue(IV_Get);
                    }

                Toast.makeText(getActivity(), "Message Sent!", Toast.LENGTH_SHORT).show();


                //Text
                if(checkPermission(Manifest.permission.SEND_SMS)){
                    SmsManager smsManager = SmsManager.getDefault();
                    for(sendm=0; sendm<counting; sendm++){
                        smsManager.sendTextMessage(number[sendm], null, " " + IV_Get, null, null);
                    }
                    sendm=0;
                    Toast.makeText(getActivity(), "Message Sent!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    nok.setVisibility(View.GONE);
                    send.setVisibility(View.VISIBLE);
            }
        });

        nok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ok.setVisibility(View.GONE);
                send.setVisibility(View.VISIBLE);

            }
        });
    }



    public void Count(){
        reff = FirebaseDatabase.getInstance().getReference().child("Employee");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    a = snap.getChildrenCount();
                }
                A = "" + a;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }




    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(getActivity(), permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }
}


