package com.ibm.firstaidhelper;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

class GlobalSettings {


    private static int stateOfUser = 0;
    private static int certVerified = 0;


    static int getStateOfUser() {
        return stateOfUser;
    }

    static void setStateOfUser(int stateOfUser) {
        GlobalSettings.stateOfUser = stateOfUser;
    }



    static int getCertVerified() {
        return certVerified;
    }

    static void setCertVerified(int certVerified) {
        GlobalSettings.certVerified = certVerified;
    }


    static LatLng getCurrentUserLocation(Location location) {
        LatLng currentUserLocation = new LatLng(100,0);
        if (location!= null){
            currentUserLocation = new LatLng(location.getLatitude(), location.getLongitude());
        }
        return currentUserLocation;
    }
}
