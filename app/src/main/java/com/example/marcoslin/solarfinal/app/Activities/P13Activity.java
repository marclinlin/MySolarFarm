package com.example.marcoslin.solarfinal.app.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class P13Activity extends AppCompatActivity {

    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12;
    private Float totpla, totbat, preinv, prereg, costman;

    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p13activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");

        tv1=findViewById(R.id.textView126);
        tv2=findViewById(R.id.textView129);
        tv3=findViewById(R.id.textView132);
        tv4=findViewById(R.id.textView135);
        tv5=findViewById(R.id.textView138);
        tv6=findViewById(R.id.textView141);
        tv7=findViewById(R.id.textView144);
        tv8=findViewById(R.id.textView147);
        tv9=findViewById(R.id.textView150);
        tv10=findViewById(R.id.textView153);
        tv11=findViewById(R.id.textView156);

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor =bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
        // Para saber como se pone texto en un tv, mirar la pagina 30 del curso de android
        if (cursor.moveToFirst()) {
            tv1.setText(cursor.getString(69));
            tv2.setText(cursor.getString(70));
            tv3.setText(cursor.getString(71));
            tv4.setText(cursor.getString(72));
            tv5.setText(cursor.getString(73));
            tv6.setText(cursor.getString(76));
            tv7.setText(cursor.getString(77));
            tv8.setText(cursor.getString(78));
            tv9.setText(cursor.getString(79));
            tv10.setText(cursor.getString(80));
            tv11.setText(cursor.getString(81));
        }else {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }
        bd.close();

        PieChart pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setUsePercentValues(true);

        totpla=Float.parseFloat(tv1.getText().toString());
        totbat=Float.parseFloat(tv2.getText().toString());
        preinv=Float.parseFloat(tv3.getText().toString());
        prereg=Float.parseFloat(tv4.getText().toString());
        costman=Float.parseFloat(tv5.getText().toString());

        ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        yvalues.add(new PieEntry(totpla, "Módulos"));
        yvalues.add(new PieEntry(totbat, "Baterías"));
        yvalues.add(new PieEntry(preinv, "Inversor"));
        yvalues.add(new PieEntry(prereg, "Regulador"));
        yvalues.add(new PieEntry(costman, "Mano de obra"));

        Description description= new Description();
        description.setText("Costes");
        pieChart.setDescription(description);
        PieDataSet set = new PieDataSet(yvalues,"");
        PieData data = new PieData(set);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setTransparentCircleRadius(25f);
        pieChart.setHoleRadius(25f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.invalidate(); // refresh

        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);


    }

    public void siguiente (View view) {
        Intent intent= new Intent(P13Activity.this,P14Activity.class);
        intent.putExtra("nombre",title);
        startActivity(intent);
    }
}
