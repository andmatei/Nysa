package nysa.nysa_20.service.connectivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nysa.nysa_20.model.LocationDataKeeper;
import nysa.nysa_20.service.utilitary.PermissionService;

public class LocationService  {
    private Activity activity;
    private LocationManager locationManager;
    private static double latitude;
    private static double longitude;
    private static String locality ="";
    private static String country="";



    public LocationService (Activity activity){
        this.activity = activity;
        PermissionService.checkFineLocationPermission(activity);
        PermissionService.checkCoarseLocation(activity);
        locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    Geocoder geocoder = new Geocoder(activity);
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);

                        locality = addressList.get(0).getLocality();
                        country = addressList.get(0).getCountryName();

                        System.out.println("------------"+latitude+"----"+longitude+"-----"+locality+"-------"+country);
                        LocationDataKeeper.setLatitude(new Double(latitude));
                        LocationDataKeeper.setLongitude(new Double(longitude));
                        LocationDataKeeper.setLocality(new String(locality));
                        LocationDataKeeper.setCountry(new String(country));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }else
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        Geocoder geocoder = new Geocoder(activity);
                        try {
                            List<Address> addressList = geocoder.getFromLocation(latitude,longitude,1);
                            locality = addressList.get(0).getLocality();
                            country = addressList.get(0).getCountryName();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });


            }

    }

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static  String getLocality() {
        return locality;
    }

    public static String getCountry() {
        return country;
    }
}
