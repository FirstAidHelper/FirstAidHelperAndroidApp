package com.ibm.firstaidhelper;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton turnOnSOSAlerts;
    private ImageButton turnOffSOSAlerts;
    private Button mainMap;
    private Location location;
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button userProfile = findViewById(R.id.nav_person);
        turnOnSOSAlerts = (ImageButton) findViewById(R.id.turn_on_al_button);
        turnOffSOSAlerts = (ImageButton) findViewById(R.id.turn_off_al_button);
        ImageButton sosButton = (ImageButton) findViewById(R.id.mainFirstButton);
        mainMap = (Button) findViewById(R.id.nav_map);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //CODE
        checkVerified();
        turnOnSOSAlerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOnSOSAlerts.setVisibility(View.GONE);
                turnOffSOSAlerts.setVisibility(View.VISIBLE);
            }
        });

        turnOffSOSAlerts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turnOffSOSAlerts.setVisibility(View.GONE);
                turnOnSOSAlerts.setVisibility(View.VISIBLE);
            }
        });

        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addCurrentLocationMarker();
            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_person) {
            startActivity(new Intent(MainActivity.this, UserProfileScrollingActivity.class));
        } else if (id == R.id.nav_map) {
            startActivity(new Intent(MainActivity.this, MapsActivity.class));

        } else if (id == R.id.nav_info) {
            startActivity(new Intent(MainActivity.this, SignUp.class));
        }else if (id == R.id.nav_help){
            startActivity(new Intent(MainActivity.this, LogIn.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkVerified(){

        int certVerified = GlobalSettings.getCertVerified();

        if (certVerified == 0){
            turnOffSOSAlerts.setVisibility(View.GONE);
            turnOnSOSAlerts.setVisibility(View.GONE);
            //mainMap.setVisibility(View.GONE);
        }else {
            turnOnSOSAlerts.setVisibility(View.VISIBLE);
            turnOffSOSAlerts.setVisibility(View.GONE);
            //mainMap.setVisibility(View.VISIBLE);
        }
    }

    private void addCurrentLocationMarker(){
        LatLng currentUserLocation = GlobalSettings.getCurrentUserLocation(location);
        mMap.addMarker(new MarkerOptions().position(currentUserLocation).title("HEEEEELP"));
    }


}
