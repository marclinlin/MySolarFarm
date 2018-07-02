package com.example.marcoslin.solarfinal.app.Activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

public class P8Activity extends AppCompatActivity {

    private String title, carga;
    private EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15,et16, et17,et18;
    private Spinner spinner2, spinner11, spinner14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p8activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        carga=bundle.getString("carga");

        String []voltaje={"12","24"};


        et1=findViewById(R.id.editText11);
        spinner2=findViewById(R.id.spinner3);
        et3=findViewById(R.id.et81);
        et4=findViewById(R.id.editText16);
        et5=findViewById(R.id.editText17);
        et6=findViewById(R.id.editText12);
        et7=findViewById(R.id.editText14);
        et8=findViewById(R.id.et87);
        et9=findViewById(R.id.et82);
        et10=findViewById(R.id.editText19);
        spinner11=findViewById(R.id.spinner4);
        et12=findViewById(R.id.editText26);
        et13=findViewById(R.id.et83);
        spinner14=findViewById(R.id.spinner5);
        et15=findViewById(R.id.editText21);
        et16=findViewById(R.id.editText22);
        et17=findViewById(R.id.editText24);
        et18=findViewById(R.id.editText25);

        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,voltaje);
        spinner2.setAdapter(adapter);
        spinner11.setAdapter(adapter);
        spinner14.setAdapter(adapter);


    }

    public void cargadatos (View view) {

        if (title!=null) {

            final String a23 = et1.getText().toString();
            final String a24 = spinner2.getSelectedItem().toString();
            final String a25 = et3.getText().toString();
            final String a26 = et4.getText().toString();
            final String a27 = et5.getText().toString();
            final String a28 = et6.getText().toString();
            final String a29 = et7.getText().toString();
            final String a30 = et8.getText().toString();
            final String a31 = et9.getText().toString();
            final String a32 = et10.getText().toString();
            final String a33 = spinner11.getSelectedItem().toString();
            final String a34 = et12.getText().toString();
            final String a35 = et13.getText().toString();
            final String a36 = spinner14.getSelectedItem().toString();
            final String a37 = et15.getText().toString();
            final String a38 = et16.getText().toString();
            final String a39 = et17.getText().toString();
            final String a40 = et18.getText().toString();

            if (!TextUtils.isEmpty(a23)&&!TextUtils.isEmpty(a24)&&!TextUtils.isEmpty(a25)&&!TextUtils.isEmpty(a26)&&!TextUtils.isEmpty(a27)&&!TextUtils.isEmpty(a28)&&!TextUtils.isEmpty(a29)&&!TextUtils.isEmpty(a30)&&!TextUtils.isEmpty(a31)&&!TextUtils.isEmpty(a32)&&!TextUtils.isEmpty(a33)&&!TextUtils.isEmpty(a34)&&!TextUtils.isEmpty(a35)&&!TextUtils.isEmpty(a36)&&!TextUtils.isEmpty(a37)&&!TextUtils.isEmpty(a38)&&!TextUtils.isEmpty(a39)) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("a23",a23);
                registro.put("a24",a24);
                registro.put("a25",a25);
                registro.put("a26",a26);
                registro.put("a27",a27);
                registro.put("a28",a28);
                registro.put("a29",a29);
                registro.put("a30",a30);
                registro.put("a31",a31);
                registro.put("a32",a32);
                registro.put("a33",a33);
                registro.put("a34",a34);
                registro.put("a35",a35);
                registro.put("a36",a36);
                registro.put("a37",a37);
                registro.put("a38",a38);
                registro.put("a39",a39);
                registro.put("a40",a40);
                bd.update("datos", registro, "nombre='" + title + "'", null);
                bd.close();
                Intent intent = new Intent(P8Activity.this, P9Activity.class);
                intent.putExtra("nombre", title);
                intent.putExtra("carga",carga);
                startActivity(intent);
            }else {
                Toast.makeText(getBaseContext(), "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            }
        }
    }

