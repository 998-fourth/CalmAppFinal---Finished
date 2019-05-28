package com.example.calmappscreens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ImageView pic;
    private TextView myprofilename;
    private TextView myprofileposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Toolbar toolbars = findViewById(R.id.toolbar);
        setSupportActionBar(toolbars);


        drawer = findViewById(R.id.drawe_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbars,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();
        String Pass = getIntent().getExtras().getString("User");


        //Determine if the Account Exist in the DB
        if(Pass.equals("11565884") || Pass.equals("11565509") || Pass.equals("11565703") || Pass.equals("11565500") || Pass.equals("11566014") || Pass.equals("11565137") || Pass.equals("11566302") || Pass.equals("11546363")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
                Intent a = new Intent(this, HomeFragment.class);
                a.putExtra("User", Pass);
            navigationView.setCheckedItem(R.id.nav_home);
            }else if(Pass.equalsIgnoreCase("KitinMariano")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ManagerBCPFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;

            case R.id.nav_emergencycontacts:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EmergencyContactFragment()).commit();
                break;
            case R.id.nav_aboutus:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutUsFragment()).commit();
                break;
            case R.id.nav_faq:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FaqFragment()).commit();
                break;
            case R.id.nav_rep:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ReportFragment()).commit();
                break;
            case R.id.nav_site:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SiteFragment()).commit();
                break;
            case R.id.nav_rtm:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new RealTimeMonitorFragment()).commit();
                break;
            case R.id.nav_team:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OrgFragment()).commit();
                break;
            case R.id.nav_logout:
                Toast.makeText(this, "Logout",Toast.LENGTH_LONG).show();
                Intent Out = new Intent(this, MainActivity.class);
                startActivity(Out);
                break;
        }

        myprofilename = (TextView)findViewById(R.id.idname);
        myprofileposition = (TextView)findViewById(R.id.idposition);
        pic = (ImageView) findViewById(R.id.idprofile);
        ImageView myprofile = (ImageView)findViewById(R.id.idprofile);


        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyProfileFragment()).commit();
            }
        });

        myprofilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyProfileFragment()).commit();
            }
        });

        myprofileposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyProfileFragment()).commit();
            }
        });

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
