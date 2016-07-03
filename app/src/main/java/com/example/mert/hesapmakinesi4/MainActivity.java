package com.example.mert.hesapmakinesi4;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.RelativeLayout.*;

public class MainActivity extends ActionBarActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0 ;
    EditText tx1,tx2,tx3;
    private float ilkDeger=0,sondeger=0;
    private double bul=0;
    private String islem;
    Spinner sp ;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    TextView isim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp =(Spinner)findViewById(R.id.spinner);
        tx1=(EditText) findViewById(R.id.sayi1);
        tx2=(EditText) findViewById(R.id.sayi2);
        tx3=(EditText)findViewById(R.id.son);
        sharedPreferences= getSharedPreferences("islem",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        isim=(TextView)findViewById(R.id.textView2);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(MenuItem item) {



                AlertEkle();




        return super.onOptionsItemSelected(item);
    }



    private void AlertEkle(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View layout = inflater.inflate(R.layout.kalem, null);
        final Button ekleBtnn = (Button) layout.findViewById(R.id.button1);
        final Button fotoBtnn = (Button) layout.findViewById(R.id.button2);
        final Button iptalBtnn= (Button) layout.findViewById(R.id.button3);
        final EditText ekleEdt=(EditText) layout.findViewById(R.id.degis);

        final AlertDialog.Builder built = new AlertDialog.Builder(this);
        built.setView(layout);
        final AlertDialog dialog1 = built.create();

        dialog1.show();

        iptalBtnn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog1.cancel();

            }
        });
        ekleBtnn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                String h = ekleEdt.getText().toString();
                isim.setText(h);
                dialog1.dismiss();

            }
        });


        fotoBtnn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
loadImagefromGallery(v);


                dialog1.dismiss();

            }
        });

    }



    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_OK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked

                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imageView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));



        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }



    public  void goster(View view){
        if (view.getId()==R.id.sonuc){

           String tmp = getSharedPreferences("islem",MODE_PRIVATE).getString("islem"," ");

           int id;
            id=sp.getSelectedItemPosition();
            if (id==1){
                ilkDeger=Float.parseFloat(tx1.getText().toString());
                sondeger=Float.parseFloat(tx2.getText().toString());
                bul=ilkDeger+sondeger;
                String ilk = String.valueOf(ilkDeger);
                String son = String.valueOf(sondeger);
                islem=ilk + "+"+son;
            }
            if (id==2){
                ilkDeger=Float.parseFloat(tx1.getText().toString());
                sondeger=Float.parseFloat(tx2.getText().toString());
                bul=ilkDeger-sondeger;
                String ilk = String.valueOf(ilkDeger);
                String son = String.valueOf(sondeger);
                islem=ilk+"-"+son;
            }
            if (id==3){
                ilkDeger=Float.parseFloat(tx1.getText().toString());
                sondeger=Float.parseFloat(tx2.getText().toString());
                bul=ilkDeger*sondeger;
                String ilk = String.valueOf(ilkDeger);
                String son = String.valueOf(sondeger);
                islem=ilk+"*"+son;
            }
            if (id==4){
                ilkDeger=Float.parseFloat(tx1.getText().toString());
                sondeger=Float.parseFloat(tx2.getText().toString());
                bul=ilkDeger/sondeger;
                String ilk = String.valueOf(ilkDeger);
                String son = String.valueOf(sondeger);
                islem=ilk+"/"+son;
            }

            String yaz = String.valueOf(bul);
            islem=islem+"="+yaz;
            tmp=tmp+"||"+islem;
            editor.putString("islem",tmp);
            editor.commit();
            tx3.setText(yaz);



        }
    }

public void gecmis(View view){
    if (view.getId()==R.id.gecmis){
        Intent i = new Intent(getApplicationContext(),gecmisActiviy.class);
        startActivity(i);


    }

}
    public  void GPS(View view){
        Intent i = new Intent(getApplicationContext(),gpsActivity.class);
        startActivity(i);
    }

}
