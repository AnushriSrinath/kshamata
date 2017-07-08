package cononymous.spark.com.myapplication;

import android.app.Dialog;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Location_activity extends AppCompatActivity  implements GoogleApiClient.ConnectionCallbacks ,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleMap mMap;
    private static final int ERROR_DIALOG_REQUEST = 703;
    GoogleApiClient locationClient;
    LocationListener locListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(servicesOK()){
            setContentView(R.layout.activity_location_activity);
            if(initMap()){
                locationClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                        .addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
                locationClient.connect();
            }else {
                Toast.makeText(getApplicationContext(),"Map not connected" , Toast.LENGTH_SHORT).show();
            }
        }else{
            setContentView(R.layout.activity_location_activity);
        }
    }

    public boolean servicesOK(){
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if(isAvailable == ConnectionResult.SUCCESS){
            return true;
        }else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this , ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(getApplicationContext() , "Google play Services Not Available" , Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public boolean initMap(){
        if(mMap == null){
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();
        }
        return (mMap != null);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public void cur_loc_give(View view) {
        locListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Double lat = location.getLatitude();
                Double lon = location.getLongitude();
                LatLng myLocationLatitude = new LatLng(lat,lon);
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myLocationLatitude,15);
                mMap.animateCamera(update);
            }
        };
    }
}
