package com.example.calmappscreens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeFragment extends Fragment {
    TextView mngr, alert;
    DatabaseReference reff;
    String empNum;
    String Message, mng, res, loc;
    TextView fstq, q;
    ImageView ok,nok,h,hl,i,il,e,el,t,tl,rep,wht;
    EditText Note;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mngr = view.findViewById(R.id.Name);
        alert = view.findViewById(R.id.alertmsg);
        fstq = view.findViewById(R.id.fstques);
        ok = view.findViewById(R.id.okwrk);
        nok = view.findViewById(R.id.nokwrk);
        q = view.findViewById(R.id.ques);
        h = view.findViewById(R.id.home);
        hl = view.findViewById(R.id.home_line);
        i = view.findViewById(R.id.ipc);
        il = view.findViewById(R.id.ipc_line);
        e = view.findViewById(R.id.etn);
        el = view.findViewById(R.id.etn_line);
        t = view.findViewById(R.id.trns);
        tl = view.findViewById(R.id.trns_line);
        rep = view.findViewById(R.id.reply);
        wht = view.findViewById(R.id.whitetxtbg);
        Note = view.findViewById(R.id.note);

        String Name = getActivity().getIntent().getExtras().getString("User");

        //Determine which Account is Logged in.
        if(Name.equals("11565884")){
            empNum = "0";
        }else if(Name.equals("11565703")){
            empNum = "2";
        }else if(Name.equals("11565500") ){
            empNum = "3";
        }else if(Name.equals("11565137")){
            empNum = "5";
        }else if(Name.equals("11565509")){
            empNum = "1";
        }else if(Name.equals("11566014")){
            empNum = "4";
        }else if(Name.equals("11566302")){
            empNum = "6";
        }else if(Name.equals("11546363")){
            empNum = "7";
        }



        //Buttons

            //First Sequence
                ok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        res = "Yes";
                        nok.setVisibility(View.GONE);
                        ok.setVisibility(View.GONE);
                        fstq.setVisibility(View.GONE);
                        reff.child("_res").setValue(res);

                        Note.setVisibility(View.VISIBLE);
                        rep.setVisibility(View.VISIBLE);
                        wht.setVisibility(View.VISIBLE);
                        q.setVisibility(View.VISIBLE);
                        h.setVisibility(View.VISIBLE);
                        i.setVisibility(View.VISIBLE);
                        e.setVisibility(View.VISIBLE);
                        t.setVisibility(View.VISIBLE);

                    }
                });

                nok.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        res = "No";
                        ok.setVisibility(View.GONE);
                        nok.setVisibility(View.GONE);
                        fstq.setVisibility(View.GONE);
                        reff.child("_res").setValue(res);

                        Note.setVisibility(View.VISIBLE);
                        rep.setVisibility(View.VISIBLE);
                        wht.setVisibility(View.VISIBLE);
                        q.setVisibility(View.VISIBLE);
                        h.setVisibility(View.VISIBLE);
                        i.setVisibility(View.VISIBLE);
                        e.setVisibility(View.VISIBLE);
                        t.setVisibility(View.VISIBLE);
                    }
                });

                h.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hl.setVisibility(View.VISIBLE);
                        il.setVisibility(View.GONE);
                        el.setVisibility(View.GONE);
                        tl.setVisibility(View.GONE);

                        loc = "Home";
                    }
                });

                i.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        il.setVisibility(View.VISIBLE);
                        hl.setVisibility(View.GONE);
                        el.setVisibility(View.GONE);
                        tl.setVisibility(View.GONE);

                        loc = "IPC";
                    }
                });

                e.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        el.setVisibility(View.VISIBLE);
                        il.setVisibility(View.GONE);
                        hl.setVisibility(View.GONE);
                        tl.setVisibility(View.GONE);

                        loc = "ETON";
                    }
                });

                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tl.setVisibility(View.VISIBLE);
                        il.setVisibility(View.GONE);
                        el.setVisibility(View.GONE);
                        hl.setVisibility(View.GONE);

                        loc = "Transit";
                    }
                });

                rep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reff.child("_loc").setValue(loc);
                        Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
                    }
                });



        //

        reff = FirebaseDatabase.getInstance().getReference().child("Employee").child("Employee " + empNum);
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mng = dataSnapshot.child("_manager").getValue().toString();
                Message = dataSnapshot.child("_mess").getValue().toString();
                mngr.setText(mng);
                alert.setText(Message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return  view;
    }
}
