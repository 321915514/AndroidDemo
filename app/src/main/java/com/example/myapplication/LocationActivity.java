package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.EOFException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private static final int LOCATION_CODE = 301;
    private String  locationProvider = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        getLocation();
    }
    public void getLocation(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
            locationProvider = LocationManager.GPS_PROVIDER;

        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(this,"no service",Toast.LENGTH_SHORT).show();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            // 获取权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                // 请求

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},LOCATION_CODE);

            }else {
                // 获取指
                Location location = locationManager.getLastKnownLocation(locationProvider);
                if(location != null){
                    Toast.makeText(this,location.getLongitude()+""+location.getLatitude()+"",Toast.LENGTH_SHORT).show();
                    Log.v("TAG","获取经纬度："+location.getLongitude()+""+location.getLatitude());
                    getAddress(location);
                }else {
                    locationManager.requestLocationUpdates(locationProvider,3000,1,locationListener);
                }
            }
        }
    }
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            if(location != null){
                // 重新显示地理位置经纬度
                Toast.makeText(LocationActivity.this,location.getLongitude()+""+location.getLatitude()+"",Toast.LENGTH_SHORT).show();
                Log.v("TAG","监视地理位置经纬度："+location.getLongitude()+""+location.getLatitude());}
        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
        // enable 时
        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }
    };
    // 请求权限
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_CODE:
                if (grantResults.length > 0 && grantResults[0] == getPackageManager().PERMISSION_GRANTED && grantResults[1] == getPackageManager().PERMISSION_GRANTED) {
                    Toast.makeText(this, "request permision", Toast.LENGTH_SHORT).show();
                    try {
                        List<String> providers = locationManager.getProviders(true);
                        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                            // network
                            locationProvider = LocationManager.NETWORK_PROVIDER;
                        } else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                            locationProvider = LocationManager.GPS_PROVIDER;
                        }
                        Location location = locationManager.getLastKnownLocation(locationProvider);
                        if (location != null) {
                            // 重新显示地理位置经纬度
                            Toast.makeText(this, location.getLongitude() + "" + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                            Log.v("TAG", "获取上次的经纬度：" + location.getLongitude() + "" + location.getLatitude());
                        } else {
                            locationManager.requestLocationUpdates(locationProvider, 0, 0, locationListener);
                        }
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(this,"require permission",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }


    }

    public List<Address> getAddress(Location location){
        List<Address> result = null;
        try {
            if(location != null){
                Geocoder gc = new Geocoder(this, Locale.getDefault());
                result = gc.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                Toast.makeText(this,"获取地址信息"+result.toString(),Toast.LENGTH_SHORT).show();
                Log.v("TAG","获取地址信息"+result.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
    }
}
