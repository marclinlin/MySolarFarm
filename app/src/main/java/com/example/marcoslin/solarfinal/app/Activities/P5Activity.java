package com.example.marcoslin.solarfinal.app.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;


import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

public class P5Activity extends AppCompatActivity {

    private EditText et1, et2, et3, et4, et5, et6, et7, et8, et9;
    private String title, optimizador, carga;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p5activity);

        Bundle bundle = getIntent().getExtras();
        title = bundle.getString("nombre");
        optimizador=bundle.getString("optimizador");
        carga=bundle.getString("carga");

        double a = 0.8;
        double b = 5;
        double c = 5;
        double d = 5;
        double e = 1;
        double f = 1;
        double g = 1;
        double h = 1;

        String aa = Double.toString(a);
        String bb = Double.toString(b);
        String cc = Double.toString(c);
        String dd = Double.toString(d);
        String ee = Double.toString(e);
        String ff = Double.toString(f);
        String gg = Double.toString(g);
        String hh = Double.toString(h);


        et1 = findViewById(R.id.editText);
        et2 = findViewById(R.id.editText2);
        et3 = findViewById(R.id.editText3);
        et4 = findViewById(R.id.editText4);
        et5 = findViewById(R.id.editText5);
        et6 = findViewById(R.id.editText6);
        et7 = findViewById(R.id.editText7);
        et8 = findViewById(R.id.editText8);

        et1.setText(aa);
        et2.setText(bb);
        et3.setText(cc);
        et4.setText(dd);
        et5.setText(ee);
        et6.setText(ff);
        et7.setText(gg);
        et8.setText(hh);

    }

    public void cargadatos(View v) {
        String a1 = et1.getText().toString();
        String a2 = et2.getText().toString();
        String a3 = et3.getText().toString();
        String a4 = et4.getText().toString();
        String a5 = et5.getText().toString();
        String a6 = et6.getText().toString();
        String a7 = et7.getText().toString();
        String a8 = et8.getText().toString();

        if (!TextUtils.isEmpty(a1) && !TextUtils.isEmpty(a2) && !TextUtils.isEmpty(a3) && !TextUtils.isEmpty(a4) && !TextUtils.isEmpty(a5) && !TextUtils.isEmpty(a6) && !TextUtils.isEmpty(a7) && !TextUtils.isEmpty(a8)) {
            double aaa = Double.parseDouble(a1);
            if (aaa <= 1 && aaa >= 0) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                registro.put("nombre", title);
                registro.put("a1", a1);
                registro.put("a2", a2);
                registro.put("a3", a3);
                registro.put("a4", a4);
                registro.put("a5", a5);
                registro.put("a6", a6);
                registro.put("a7", a7);
                registro.put("a8", a8);
                bd.insert("datos", null, registro);
                bd.close();
                Intent intent = new Intent(P5Activity.this, P6Activity.class);
                intent.putExtra("nombre", title);
                intent.putExtra("optimizador",optimizador);
                intent.putExtra("carga",carga);
                startActivity(intent);
            } else {
                Toast.makeText(this, "El factor de simultaneidad debe valer entre 0 y 1", Toast.LENGTH_SHORT).show();
            }
        }else{
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                }
            }


        }