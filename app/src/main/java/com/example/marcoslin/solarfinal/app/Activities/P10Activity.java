package com.example.marcoslin.solarfinal.app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

public class P10Activity extends AppCompatActivity {

    private TextView tv0,tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;
    private String title, carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p10activity);
        tv0=findViewById(R.id.tv102);
        tv1=findViewById(R.id.textView54);
        tv2=findViewById(R.id.textView56);
        tv3=findViewById(R.id.textView58);
        tv4=findViewById(R.id.textView60);
        tv5=findViewById(R.id.textView62);
        tv6=findViewById(R.id.textView64);
        tv7=findViewById(R.id.textView66);
        tv8=findViewById(R.id.textView68);
        tv9=findViewById(R.id.textView70);
        tv10=findViewById(R.id.textView72);
        tv11=findViewById(R.id.textView74);
        tv12=findViewById(R.id.textView76);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        carga=bundle.getString("carga");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
        if (cursor.moveToFirst()) {
            tv0.setText(cursor.getString(26));
            tv1.setText(cursor.getString(41));
            tv2.setText(cursor.getString(42));
            tv3.setText(cursor.getString(43));
            tv4.setText(cursor.getString(44));
            tv5.setText(cursor.getString(45));
            tv6.setText(cursor.getString(46));
            tv7.setText(cursor.getString(47));
            tv8.setText(cursor.getString(48));
            tv9.setText(cursor.getString(49));
            tv10.setText(cursor.getString(50));
            tv11.setText(cursor.getString(51));
            tv12.setText(cursor.getString(52));
        }else {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void siguiente (View view) {
        Intent intent= new Intent(P10Activity.this,P11Activity.class);
        intent.putExtra("nombre",title);
        intent.putExtra("carga",carga);
        startActivity(intent);
    }

}
