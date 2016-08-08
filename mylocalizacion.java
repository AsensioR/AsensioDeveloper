package com.example.asensio.gps.localizacion;


import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class mylocalizacion extends Service implements LocationListener {

    private final Context cont;
   String txt;
    Location t;

    boolean gpsActivo;
    TextView texto;
    LocationManager manejar;

    public mylocalizacion() {
        super();
        this.cont = this.getApplication();
    }

    public mylocalizacion(Context c) {
        super();
        this.cont = c;
        getLocation();
    }

    public void setview(View v) {
        texto = (TextView) v;
        texto.setText(txt);

    }

    public void getLocation() {
        try {
            manejar = (LocationManager) this.cont.getSystemService(cont.LOCATION_SERVICE);
            gpsActivo = manejar.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {

        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            else
            {
                if (gpsActivo) {
                    manejar.requestLocationUpdates(manejar.GPS_PROVIDER, 1000 * 60, 10, this);
                    t= manejar.getLastKnownLocation(manejar.GPS_PROVIDER);
                    if(t!=null) {
                        txt = "Coordenadas Son: Altitud= " + t.getLongitude() + " Longitud= " + t.getLongitude();
                    }
                    else
                    {
                        Toast.makeText(cont,"Error de Localizacion",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        else
        {
            if (gpsActivo) {
                manejar.requestLocationUpdates(manejar.GPS_PROVIDER, 1000 * 60, 10, this);
                t= manejar.getLastKnownLocation(manejar.GPS_PROVIDER);
                if(t!=null) {
                    txt = "Coordenadas Son: Altitud= " + t.getLongitude() + " Longitud= " + t.getLongitude();
                }
                else
                {
                    Toast.makeText(cont,"Error de Localizacion",Toast.LENGTH_LONG).show();
                }
            }
        }



}
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(cont," Localizacion"+s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
