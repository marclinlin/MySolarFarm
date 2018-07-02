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

public class P7Activity extends AppCompatActivity {

    private EditText et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12;
    private String title, optimizador,carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p7activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        optimizador=bundle.getString("optimizador");
        carga=bundle.getString("carga");

        et1=findViewById(R.id.et71);
        et2=findViewById(R.id.et72);
        et3=findViewById(R.id.et73);
        et4=findViewById(R.id.et74);
        et5=findViewById(R.id.et75);
        et6=findViewById(R.id.et76);
        et7=findViewById(R.id.et77);
        et8=findViewById(R.id.et78);
        et9=findViewById(R.id.et79);
        et10=findViewById(R.id.et710);
        et11=findViewById(R.id.et711);
        et12=findViewById(R.id.et712);

    }

    public void siguiente (View view) {

        String a11 = et1.getText().toString();
        String a12 = et2.getText().toString();
        String a13 = et3.getText().toString();
        String a14 = et4.getText().toString();
        String a15 = et5.getText().toString();
        String a16 = et6.getText().toString();
        String a17 = et7.getText().toString();
        String a18 = et8.getText().toString();
        String a19 = et9.getText().toString();
        String a20 = et10.getText().toString();
        String a21 = et11.getText().toString();
        String a22 = et12.getText().toString();

        if (!TextUtils.isEmpty(a11) && !TextUtils.isEmpty(a12) && !TextUtils.isEmpty(a13) && !TextUtils.isEmpty(a14) && !TextUtils.isEmpty(a15) && !TextUtils.isEmpty(a16) && !TextUtils.isEmpty(a17) && !TextUtils.isEmpty(a18) && !TextUtils.isEmpty(a19) && !TextUtils.isEmpty(a20) && !TextUtils.isEmpty(a21) && !TextUtils.isEmpty(a22)) {
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            ContentValues registro = new ContentValues();
            registro.put("a11", a11);
            registro.put("a12", a12);
            registro.put("a13", a13);
            registro.put("a14", a14);
            registro.put("a15", a15);
            registro.put("a16", a16);
            registro.put("a17", a17);
            registro.put("a18", a18);
            registro.put("a19", a19);
            registro.put("a20", a20);
            registro.put("a21", a21);
            registro.put("a22", a22);
            bd.update("datos", registro, "nombre='" + title + "'", null);
            bd.close();
            if (optimizador.equals("si")){
                Intent intent = new Intent(P7Activity.this, P8Activity_a.class);
                intent.putExtra("nombre", title);
                intent.putExtra("carga",carga);
                startActivity(intent);
            }else {
                Intent intent = new Intent(P7Activity.this, P8Activity.class);
                intent.putExtra("nombre", title);
                intent.putExtra("carga", carga);
                startActivity(intent);
            }
        }else {
            Toast.makeText(this,"Rellene todos los campos", Toast.LENGTH_SHORT).show();
        }

    }
}
