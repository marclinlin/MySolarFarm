package com.example.marcoslin.solarfinal.app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;

import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;


public class P3Activity extends AppCompatActivity {

    private View  btnp31;
    private RadioButton rb1, rb2;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p3activity);

        rb1= findViewById(R.id.rb1);
        rb2= findViewById(R.id.rb2);
        btnp31= findViewById(R.id.btnp31);
        spinner=findViewById(R.id.spinner);

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"datos",null,1);
        SQLiteDatabase bd=admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM datos",null);
        final int size= cursor.getCount();
        String proyectos[]=new String[size];

        if (cursor.moveToFirst()) {
            int i = 0;
            do {
                proyectos[i] = cursor.getString(0);
                i++;
            } while (cursor.moveToNext());
        }
        bd.close();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,proyectos);
        spinner.setAdapter(adapter);

    btnp31.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(rb1.isChecked()) {
                String carga="no";
                Intent intent= new Intent(P3Activity.this,P4Activity.class);
                intent.putExtra("carga",carga);
                startActivity(intent);
            }else {
                if (rb2.isChecked()) {
                    String carga="si";
                    String title=spinner.getSelectedItem().toString();
                    Intent intent = new Intent(P3Activity.this, P10Activity.class);
                    intent.putExtra("nombre",title);
                    intent.putExtra("carga",carga);
                    startActivity(intent);
                } else {
                    Toast.makeText(P3Activity.this, "No se ha seleccionado ninguna opción", Toast.LENGTH_SHORT).show();
                }
            }

        }
    });}

    public void gestionarproyectos (View view) {
        Intent intent =new Intent(P3Activity.this,P3Activity_a.class);
        startActivity(intent);

    }
    }


