package com.example.mert.hesapmakinesi4;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class gpsActivity extends AppCompatActivity {
    TextView konumText;
    final String gpsAcildi = "GPS Acıldı";
    final String gpsKapatildi = "GPS Kapalı";
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        konumText = (TextView) findViewById(R.id.ygps);

       startTime();

    }
    private void startTime() {
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 1000);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            // buraya ne yapmak istiyorsan o kodu yaz.. Kodun sonlandıktan sonra 1 saniye sonra tekrar çalışacak şekilde handler tekrar çalışacak.

            LocationManager konumYoneticisi = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            LocationListener konumDinleyicisi = new LocationListener() {


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {
                    Toast.makeText(getApplicationContext(), gpsAcildi, Toast.LENGTH_SHORT).show();
                    konumText.setText("GPS Veri bilgileri Alınıyor...");
                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(getApplicationContext(), gpsKapatildi, Toast.LENGTH_SHORT).show();
                    konumText.setText("GPS Bağlantı Bekleniyor...");
                }

                @Override
                public void onLocationChanged(Location loc) {
                    loc.getLatitude();
                    loc.getLongitude();

                    String Text = "Bulunduğunuz konum bilgileri : \n" + "Latitud = " + loc.getLatitude() + "\nLongitud = " + loc.getLongitude();
                    konumText.setText(Text);
                }
            };


            if (ActivityCompat.checkSelfPermission(gpsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(gpsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            konumYoneticisi.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, konumDinleyicisi);
            mHandler.postDelayed(this, 10000);
            konumText.setText("yeni değer");
        }
    };
}
