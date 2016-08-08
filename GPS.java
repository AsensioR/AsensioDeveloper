package com.example.asensio.gps;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import com.example.asensio.gps.localizacion.mylocalizacion;


public class GPS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);
mylocalizacion ml=new mylocalizacion(getApplication().getApplicationContext());


       ml.setview(findViewById(R.id.uno));

    }

}
