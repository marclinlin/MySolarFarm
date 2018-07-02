package com.example.marcoslin.solarfinal.app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

public class P4Activity extends AppCompatActivity {

    private EditText etp41;
    private View btnp41;
    private String title, optimizador, carga;
    private boolean repeticion;
    private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.p4activity);

        Bundle bundle= getIntent().getExtras();
        carga= bundle.getString("carga");

        btnp41 = findViewById(R.id.btnp41);
        etp41 = findViewById(R.id.etp41);
        checkBox =findViewById(R.id.checkBox);
        optimizador="no";

    }


    public void savename (View v) {
        String title=etp41.getText().toString();
        if(!TextUtils.isEmpty(title)){
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
            int k;
            repeticion=false;
            for (k=0; k < proyectos.length; k++) {

                if (proyectos[k].equals(title)) {
                    repeticion=true;
                    Toast.makeText(P4Activity.this,"Ya existe un proyecto con ese nombre", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
            if (!repeticion){
                if (checkBox.isChecked()==true){
                    optimizador="si";
                }
                Intent intent = new Intent(P4Activity.this, P5Activity.class);
                intent.putExtra("nombre", title);
                intent.putExtra("optimizador", optimizador);
                intent.putExtra("carga", carga);
                startActivity(intent);
            }

        }else
            Toast.makeText(P4Activity.this,"Introduzca un nombre para el proyecto", Toast.LENGTH_SHORT).show();
    }
}


