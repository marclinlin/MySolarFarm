package com.example.marcoslin.solarfinal.app.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

import java.util.Locale;

public class P12Activity extends AppCompatActivity {

    private EditText et1,et2,et3, et4, et5, et6, et7;
    private TextView tv1, tv2, tv3;
    private String title;
    private double totpla, totbat, ahorro, enec, pnec, emevit, van, tir, payback, costot,con1,con2,con3,con4,con5,con6,con7,con8,con9,con10,con11,con12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p12activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");

        et1=findViewById(R.id.tv122);
        et2=findViewById(R.id.editText25);
        et3=findViewById(R.id.et121);
        et4=findViewById(R.id.editText26);
        et5=findViewById(R.id.editText27);
        et6=findViewById(R.id.editText28);
        et7=findViewById(R.id.editText29);

        tv1=findViewById(R.id.textView108);
        tv2=findViewById(R.id.textView112);
        tv3=findViewById(R.id.textView115);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
        if (cursor.moveToFirst()) {
            tv1.setText(cursor.getString(65));
            tv2.setText(cursor.getString(67));
            tv3.setText(cursor.getString(68));
        }
        bd.close();

    }

    public void siguiente (View view) {
        String a64 = et1.getText().toString();
        String a71 = et2.getText().toString();
        String a66 = et3.getText().toString();
        String a72 = et4.getText().toString();
        String a73 = et5.getText().toString();
        String a74 = et6.getText().toString();
        String a75 = et7.getText().toString();


        if (!TextUtils.isEmpty(a64)&&!TextUtils.isEmpty(a71)&&!TextUtils.isEmpty(a66)&&!TextUtils.isEmpty(a72)&&!TextUtils.isEmpty(a73)&&!TextUtils.isEmpty(a74)&&!TextUtils.isEmpty(a75)) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
            if (cursor.moveToFirst()) {
                con1=Double.parseDouble(cursor.getString(11));
                con2=Double.parseDouble(cursor.getString(12));
                con3=Double.parseDouble(cursor.getString(13));
                con4=Double.parseDouble(cursor.getString(14));
                con5=Double.parseDouble(cursor.getString(15));
                con6=Double.parseDouble(cursor.getString(16));
                con7=Double.parseDouble(cursor.getString(17));
                con8=Double.parseDouble(cursor.getString(18));
                con9=Double.parseDouble(cursor.getString(19));
                con10=Double.parseDouble(cursor.getString(20));
                con11=Double.parseDouble(cursor.getString(21));
                con12=Double.parseDouble(cursor.getString(22));
                pnec=Double.parseDouble(cursor.getString(23));
                totpla=Double.parseDouble(cursor.getString(69));
                totbat=Double.parseDouble(cursor.getString(70));
            }

            double preinv = Double.parseDouble(a71);
            double prereg = Double.parseDouble(a72);
            double costman = Double.parseDouble(a73);
            double tret= Double.parseDouble(a74)/100;
            double tamort= Double.parseDouble(a75);

            enec=(con1+con2+con3+con4+con5+con6+con7+con8+con9+con10+con11+con12)/12;

            // coste total
            double costot= totpla+totbat+preinv+prereg+costman;

            // Ahorro anual
            ahorro=(pnec*41.13+0.1219*enec*365)*1.05113*1.21;

            //Emisiones evitadas
            emevit=enec*365*0.24;

            //VAN
            double a = (1+tret);
            double b = (-tamort);
            double raise = Math.pow(a,b);
            van=-costot+(ahorro*(1-raise))/tret;

            //TIR
            double error = 1;
            double tir = 0.001;
            while (error>0.001){
                double raise1 = Math.pow((1+tir),-tamort);
                double f = -costot+(ahorro*(1-raise1))/tir;
                double raise2= Math.pow((1+tir),-tamort-1);
                double fd= (20*ahorro*raise2*tir-(ahorro-ahorro*raise1))/(tir*tir);
                double xn=tir-f/fd;
                error=Math.abs(xn-tir);
                tir=xn;

            }
            tir= tir*100;

            //Payback
            payback= costot/ahorro;

            //Formateo de variables
            String a76= String.format(Locale.US,"%.2f", costot);
            String a77= String.format(Locale.US,"%.2f", ahorro);
            String a78= String.format(Locale.US,"%.2f", emevit);
            String a79= String.format(Locale.US,"%.2f", van);
            String a80= String.format(Locale.US,"%.2f", tir);
            String a81= String.format(Locale.US,"%.2f", payback);


            ContentValues registro = new ContentValues();
            registro.put("a64",a64);
            registro.put("a66",a66);
            registro.put("a71",a71);
            registro.put("a72",a72);
            registro.put("a73",a73);
            registro.put("a74",a74);
            registro.put("a75",a75);
            registro.put("a76",a76);
            registro.put("a77",a77);
            registro.put("a78",a78);
            registro.put("a79",a79);
            registro.put("a80",a80);
            registro.put("a81",a81);
            bd.update("datos", registro, "nombre='" + title + "'", null);
            bd.close();
            Intent intent = new Intent(P12Activity.this, P13Activity.class);
            intent.putExtra("nombre", title);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Rellene todos los campos",Toast.LENGTH_SHORT).show();

        }
    }
}
