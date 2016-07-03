package com.example.mert.hesapmakinesi4;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class gecmisActiviy extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView tx ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecmis_activiy);


        sharedPreferences= getPreferences(MODE_PRIVATE);
        editor=sharedPreferences.edit();
        tx= (TextView)findViewById(R.id.bilgi);

        String tmp = getSharedPreferences("islem",MODE_PRIVATE).getString("islem"," ");


        tx.setText(tmp);


    }
}
