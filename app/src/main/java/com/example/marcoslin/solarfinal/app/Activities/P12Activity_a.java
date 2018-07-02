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

public class P12Activity_a extends AppCompatActivity {

    private TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p12activity_a);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");


        tv1=findViewById(R.id.textView108);
        tv2=findViewById(R.id.tv122);
        tv3=findViewById(R.id.editText25);
        tv4=findViewById(R.id.textView112);
        tv5=findViewById(R.id.textView115);
        tv6=findViewById(R.id.et121);
        tv7=findViewById(R.id.editText26);
        tv8=findViewById(R.id.editText27);
        tv9=findViewById(R.id.editText28);
        tv10=findViewById(R.id.editText29);


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
        if (cursor.moveToFirst()) {
            tv1.setText(cursor.getString(65));
            tv2.setText(cursor.getString(64));
            tv3.setText(cursor.getString(71));
            tv4.setText(cursor.getString(67));
            tv5.setText(cursor.getString(68));
            tv6.setText(cursor.getString(66));
            tv7.setText(cursor.getString(72));
            tv8.setText(cursor.getString(73));
            tv9.setText(cursor.getString(74));
            tv10.setText(cursor.getString(75));

        }
        bd.close();

    }

    public void siguiente (View view) {
        Intent intent = new Intent(P12Activity_a.this, P13Activity.class);
        intent.putExtra("nombre", title);
        startActivity(intent);
        }
    }