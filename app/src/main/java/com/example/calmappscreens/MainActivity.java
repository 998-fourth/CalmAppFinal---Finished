package com.example.calmappscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference reff;
    int count, cntr, counting;
    String A, num;
    long a;
    private EditText Email;
    private EditText EmployeeId;
    Employee emp;
    Emergency emer;
    String number[] = new String[1000];
    //private Button Login;
    private TextView CountText;
    private int ctr = 5;
    public String Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.email);
        EmployeeId = (EditText) findViewById(R.id.eId);
        //Login = (ImageView)findViewById(R.id.logbtn);
        CountText = (TextView) findViewById(R.id.counter);
        ImageView Login = (ImageView) findViewById(R.id.logbtn);
        emp = new Employee();
        emer = new Emergency();
        Count();


        CountText.setText("No of attempts remaining 5");

        //Log in
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int B = 0; B <=Integer.parseInt(A); B++) {
                    reff = FirebaseDatabase.getInstance().getReference().child("Employee").child(("Employee " + B));
                    reff.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for(int C=0; C<Integer.parseInt(A); C++){
                                num = dataSnapshot.child("_empid").getValue().toString();
                            }
                            number[counting] = num;
                            counting++;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //Check User
                String username = Email.getText().toString();
                String password = EmployeeId.getText().toString();
                if ((username.equalsIgnoreCase(number[0]) && password.equals("1234") ) || (username.equalsIgnoreCase(number[1])  && password.equals("1234") ) || (username.equalsIgnoreCase(number[2])  && password.equals("1234") ) || ((username.equalsIgnoreCase(number[3])  && password.equals("1234") ) && password.equals("1234") ) || ((username.equalsIgnoreCase(number[4])  && password.equals("1234") )  && password.equals("1234") ) || (username.equalsIgnoreCase(number[5])  && password.equals("1234") ) || (username.equalsIgnoreCase(number[6])  && password.equals("1234") ) || (username.equalsIgnoreCase(number[7])  && password.equals("1234") )) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("User", username);
                    startActivity(intent);
                } else if (username.equalsIgnoreCase("KitinMariano") && password.equals("1234") ) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("User", username);
                    startActivity(intent);
                } else {
                    ctr--;
                    Toast.makeText(MainActivity.this, "Wrong Email or Password!", Toast.LENGTH_SHORT).show();
                    Email.setText("");
                    EmployeeId.setText("");
                    CountText.setText("No of attempts remaining" + String.valueOf(ctr));
                }
            }
        });
    }

    //Get Count of the Employee in DB
    public void Count(){
        reff = FirebaseDatabase.getInstance().getReference("/Employee");
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
}
