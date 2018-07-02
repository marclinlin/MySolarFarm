package com.example.marcoslin.solarfinal.app.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

public class P3Activity_a extends AppCompatActivity {

    private Spinner spinner;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p3activity_a);

        spinner=findViewById(R.id.spinner2);

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
    }

    public void borrarproyecto (View view){
       final String title=spinner.getSelectedItem().toString();
       if (title==null){
           Toast.makeText(this, "No se ha seleccionado ningun proyecto", Toast.LENGTH_SHORT).show();
       }else {
           new AlertDialog.Builder(this)
                   .setTitle("Borrar proyecto")
                   .setMessage("Se va a borrar el proyecto " + title + " y no será posible recuperar sus datos. ¿Quiere continuar?")
                   .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
                           SQLiteDatabase bd = admin.getWritableDatabase();
                           bd.delete("datos", "nombre='" + title + "'", null);
                           Cursor cursor = bd.rawQuery("SELECT * FROM datos", null);
                           final int size = cursor.getCount();
                           String proyectos[] = new String[size];

                           if (cursor.moveToFirst()) {
                               i = 0;
                               do {
                                   proyectos[i] = cursor.getString(0);
                                   i++;
                               } while (cursor.moveToNext());
                           }
                           bd.close();
                           ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, proyectos);
                           spinner.setAdapter(adapter);
                           Toast.makeText(getBaseContext(), "El proyecto " + title + " ha sido eliminado", Toast.LENGTH_SHORT).show();

                       }
                   })
                   .setNegativeButton("Cancelar", null)
                   .show();
       }
    }


    public void borrartodos(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Borrar todos")
                .setMessage("Se van a borrar todos los proyectos y no será posible recuperarlos ¿Quiere continuar?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
                        SQLiteDatabase bd = admin.getWritableDatabase();
                        bd.delete("datos",null,null);
                        Cursor cursor = bd.rawQuery("SELECT * FROM datos",null);
                        final int size= cursor.getCount();
                        String proyectos[]=new String[size];

                        if (cursor.moveToFirst()) {
                            i = 0;
                            do {
                                proyectos[i] = cursor.getString(0);
                                i++;
                            } while (cursor.moveToNext());
                        }
                        bd.close();
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,proyectos);
                        spinner.setAdapter(adapter);
                        Toast.makeText(getBaseContext(),"Todos los proyectos han sido eliminados",Toast.LENGTH_SHORT).show();

                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void anterior (View view){
        Intent intent =new Intent(P3Activity_a.this,P3Activity.class);
        startActivity(intent);
    }

}
