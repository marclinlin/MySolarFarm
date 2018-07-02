package com.example.marcoslin.solarfinal.app.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

public class P6Activity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_CODE = 101;
    private EditText et1,et2;
    private LocationManager locationManager;
    private Location currentLocation;
    private Marker marker;
    private CameraPosition camera;
    private String title, optimizador, carga;
    private String latitude;
    private String altitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p6activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        optimizador=bundle.getString("optimizador");
        carga=bundle.getString("carga");

        et1=findViewById(R.id.editText10);
        et2=findViewById(R.id.editText28);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            // ask permissions here using below code
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        }
    }

    public void onResume() {
        super.onResume();

    }

    public void Autolocalizar(View view) {
        if (!chekisgpsisenabled()) {
            showinfoalert();
        }else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;

            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location == null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            currentLocation = location;

            if (currentLocation != null) {
                createOrUpdateMarkerByLocation(location);
                zoomToLocation(location);
                double lat = marker.getPosition().latitude;
                double alt = currentLocation.getAltitude();
                latitude=String.format(Locale.US,"%.2f",lat);
                altitude=String.format(Locale.US,"%.2f",alt);
                et1.setText(latitude);
                et2.setText(altitude);

            }
        }

    }


    private void createOrUpdateMarkerByLocation(Location location) {
        if (marker == null) {
            marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).draggable(true));
        } else {
            marker.setPosition(new LatLng(location.getLatitude(), location.getLongitude()));
        }

    }

    private void zoomToLocation(Location location) {
        camera = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(15)           // limit -> 21
                .bearing(0)         // 0 - 365º
                .tilt(30)           // limit -> 90
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camera));

    }


    private boolean chekisgpsisenabled() {
        try {
            int gpsSignal = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);

            if (gpsSignal == 0) {
                return false;
            } else {
                return true;
            }

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showinfoalert() {
        new AlertDialog.Builder(this)
                .setTitle("Señal GPS")
                .setMessage("La señal GPS está desactivada. ¿Quiere activar la señal GPS ahora?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

    }

    public void cargadatos (View view) {

        latitude= et1.getText().toString();
        altitude= et2.getText().toString();

        if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(latitude)&&!TextUtils.isEmpty(altitude)) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("a9", latitude);
            registro.put("a10",altitude);
            bd.update("datos", registro, "nombre='" + title + "'", null);
            bd.close();
            Intent intent= new Intent(P6Activity.this,P7Activity.class);
            intent.putExtra("nombre",title);
            intent.putExtra("optimizador",optimizador);
            intent.putExtra("carga",carga);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Introduzca la latitud y la altitud de su localización",Toast.LENGTH_SHORT).show();
        }
    }

}
