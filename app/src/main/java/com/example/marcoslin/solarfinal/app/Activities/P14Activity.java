package com.example.marcoslin.solarfinal.app.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

import java.util.Locale;

public class P14Activity extends AppCompatActivity {

    private String title, nombpla, nombbat, nombreg, nombinv, costot, van, tir, inc;
    private Double  pserie, pparal, batserie, batparal;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p14activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");

        tv1=findViewById(R.id.textView25);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'", null);
        if (cursor.moveToFirst()) {
            nombpla=cursor.getString(25);
            inc=cursor.getString(26);
            nombbat=cursor.getString(35);
            pserie= Double.parseDouble(cursor.getString(56));
            pparal= Double.parseDouble(cursor.getString(57));
            batserie= Double.parseDouble(cursor.getString(62));
            batparal= Double.parseDouble(cursor.getString(63));
            nombinv=cursor.getString(64);
            nombreg=cursor.getString(66);
            costot=cursor.getString(76);
            van=cursor.getString(79);
            tir=cursor.getString(80);

        }


        int placastotales= (int) Math.ceil(pserie*pparal);
        int bateriastotales= (int) Math.ceil(batparal*batserie);

        String placas= String.valueOf(placastotales);
        String baterias=String.valueOf(bateriastotales);

        String resumen = "Serán necesarios para la instalación los siguientes materiales: \n\n" + placas +
                " placas fotovoltaicas " + nombpla + "\n" + baterias + " baterías " + nombbat + "\nUn inversor " + nombinv + " y un regulador " + nombreg +
                "\nLos módulos deberán instalarse con una inclinación de " + inc +" grados"+ "\n\nLa inversión tendrá un coste total de " +costot+ " euros, un VAN de "+
                van + " euros y un TIR de " + tir+"%";

        tv1.setText(resumen);

        ContentValues registro = new ContentValues();
            registro.put("a82", resumen);
        bd.update("datos", registro, "nombre='" + title + "'", null);
        bd.close();
    }

    public void grafica (View view){
        Intent intent =new Intent(P14Activity.this,P14Activity_a.class);
        intent.putExtra("nombre", title);
        startActivity(intent);
    }

    public void siguiente (View view) {
        Intent intent= new Intent(P14Activity.this,P15Activity.class);
        intent.putExtra("nombre",title);
        startActivity(intent);
    }

    public void inicio (View view) {
        Intent intent= new Intent(P14Activity.this,P3Activity.class);
        startActivity(intent);
    }
}
