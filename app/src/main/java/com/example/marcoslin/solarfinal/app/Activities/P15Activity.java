package com.example.marcoslin.solarfinal.app.Activities;

import
        android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

import java.util.ArrayList;

public class P15Activity extends AppCompatActivity {

    private String title, resumen;
    private EditText et1,et2,et3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p15activity);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("nombre");

        et1=findViewById(R.id.et151);
        et2=findViewById(R.id.et152);
        et3=findViewById(R.id.et153);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'", null);
        if (cursor.moveToFirst()) {
            resumen=cursor.getString(82);

        }

        new AlertDialog.Builder(this)
                .setTitle("Enviar informe")
                .setMessage("Introduzca los correos de los destinatarios")
                .setPositiveButton("Ok",null)
                .show();


    }

    public void siguiente (View view) {

        ArrayList<String> correos =new ArrayList<>();

        correos.add(et1.getText().toString());
        correos.add(et2.getText().toString());
        correos.add(et3.getText().toString());

        String[] tcorreos = new String[ correos.size() ];
        correos.toArray( tcorreos );

        final Intent emailIntent = new Intent( android.content.Intent.ACTION_SEND);
        //emailIntent.setClassName("com.google.android.gm",
        //        "com.google.android.gm.ComposeActivityGmail");
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,tcorreos);
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                "Informe proyecto "+ title);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                resumen);
        startActivity(Intent.createChooser(
                emailIntent,"Elija cliente: "));
    }
}