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

public class P11Activity extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10, tv11;
    private String title, carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p11activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        carga = bundle.getString("carga");

        tv1=findViewById(R.id.textView161);
        tv2=findViewById(R.id.textView79);
        tv3=findViewById(R.id.textView82);
        tv4=findViewById(R.id.textView85);
        tv5=findViewById(R.id.textView88);
        tv6=findViewById(R.id.textView91);
        tv7=findViewById(R.id.tv2);
        tv8=findViewById(R.id.textView94);
        tv9=findViewById(R.id.textView98);
        tv10=findViewById(R.id.textView101);
        tv11=findViewById(R.id.textView104);


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
        if (cursor.moveToFirst()) {
            tv1.setText(cursor.getString(53));
            tv2.setText(cursor.getString(55));
            tv3.setText(cursor.getString(56));
            tv4.setText(cursor.getString(57));
            tv5.setText(cursor.getString(54));
            tv6.setText(cursor.getString(58));
            tv7.setText(cursor.getString(59));
            tv8.setText(cursor.getString(60));
            tv9.setText(cursor.getString(61));
            tv10.setText(cursor.getString(62));
            tv11.setText(cursor.getString(63));

        }else {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void siguiente (View view) {
        if (carga.equals("si")){
            Intent intent= new Intent(P11Activity.this,P12Activity_a.class);
            intent.putExtra("nombre",title);
            startActivity(intent);
        }else {
            Intent intent= new Intent(P11Activity.this,P12Activity.class);
            intent.putExtra("nombre",title);
            startActivity(intent);
        }
    }
}
