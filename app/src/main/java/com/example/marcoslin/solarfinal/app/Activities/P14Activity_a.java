package com.example.marcoslin.solarfinal.app.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

public class P14Activity_a extends AppCompatActivity {


    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;
    String title;
    double con1,con2,con3,con4,con5,con6,con7,con8,con9,con10,con11,con12;
    double mes1,mes2,mes3,mes4,mes5,mes6,mes7,mes8,mes9,mes10,mes11,mes12;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p14activity_a);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'", null);
        if (cursor.moveToFirst()) {
            con1=Float.parseFloat(cursor.getString(11));
            con2=Float.parseFloat(cursor.getString(12));
            con3=Float.parseFloat(cursor.getString(13));
            con4=Float.parseFloat(cursor.getString(14));
            con5=Float.parseFloat(cursor.getString(15));
            con6=Float.parseFloat(cursor.getString(16));
            con7=Float.parseFloat(cursor.getString(17));
            con8=Float.parseFloat(cursor.getString(18));
            con9=Float.parseFloat(cursor.getString(19));
            con10=Float.parseFloat(cursor.getString(20));
            con11=Float.parseFloat(cursor.getString(21));
            con12=Float.parseFloat(cursor.getString(22));
            mes1=Float.parseFloat(cursor.getString(41));
            mes2=Float.parseFloat(cursor.getString(42));
            mes3=Float.parseFloat(cursor.getString(43));
            mes4=Float.parseFloat(cursor.getString(44));
            mes5=Float.parseFloat(cursor.getString(45));
            mes6=Float.parseFloat(cursor.getString(46));
            mes7=Float.parseFloat(cursor.getString(47));
            mes8=Float.parseFloat(cursor.getString(48));
            mes9=Float.parseFloat(cursor.getString(49));
            mes10=Float.parseFloat(cursor.getString(50));
            mes11=Float.parseFloat(cursor.getString(51));
            mes12=Float.parseFloat(cursor.getString(52));

        }
        bd.close();


        barWidth = 0.30f;
        barSpace = 0f;
        groupSpace = 0.4f;

        BarChart chart= (BarChart)findViewById(R.id.chart);
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 12;

        ArrayList xVals = new ArrayList();

        xVals.add("Ene");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");
        xVals.add("Jul");
        xVals.add("Ago");
        xVals.add("Sep");
        xVals.add("Oct");
        xVals.add("Nov");
        xVals.add("Dic");


        ArrayList yVals1 = new ArrayList();
        ArrayList yVals2 = new ArrayList();

        yVals1.add(new BarEntry(1, (float) con1));
        yVals2.add(new BarEntry(1, (float) mes1));
        yVals1.add(new BarEntry(2, (float) con2));
        yVals2.add(new BarEntry(2, (float) mes2));
        yVals1.add(new BarEntry(3, (float) con3));
        yVals2.add(new BarEntry(3, (float) mes3));
        yVals1.add(new BarEntry(4, (float) con4));
        yVals2.add(new BarEntry(4, (float) mes4));
        yVals1.add(new BarEntry(5, (float) con5));
        yVals2.add(new BarEntry(5, (float) mes5));
        yVals1.add(new BarEntry(6, (float) con6));
        yVals2.add(new BarEntry(6, (float) mes6));
        yVals1.add(new BarEntry(7, (float) con7));
        yVals2.add(new BarEntry(7, (float) mes7));
        yVals1.add(new BarEntry(8, (float) con8));
        yVals2.add(new BarEntry(8, (float) mes8));
        yVals1.add(new BarEntry(9, (float) con9));
        yVals2.add(new BarEntry(9, (float) mes9));
        yVals1.add(new BarEntry(10, (float) con10));
        yVals2.add(new BarEntry(10, (float) mes10));
        yVals1.add(new BarEntry(11, (float) con11));
        yVals2.add(new BarEntry(11, (float) mes11));
        yVals1.add(new BarEntry(12, (float) con12));
        yVals2.add(new BarEntry(12, (float) mes12));

        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "Consumo(kWh/día)");
        set1.setColor(Color.RED);
        set2 = new BarDataSet(yVals2, "Producción(kWh/día)");
        set2.setColor(Color.BLUE);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        //chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(5f);
        l.setXOffset(10f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(0f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(true);
        xAxis.setAxisMaximum(12);
        xAxis.setLabelCount(12);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        //Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);
    }
}
