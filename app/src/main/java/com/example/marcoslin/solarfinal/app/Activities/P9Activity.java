package com.example.marcoslin.solarfinal.app.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.marcoslin.solarfinal.R;
import com.example.marcoslin.solarfinal.app.SQLHelper.AdminSQLiteOpenHelper;

import java.util.Arrays;
import java.util.Locale;

public class P9Activity extends AppCompatActivity {

    private ProgressBar progressBar;
    private asynctask asynctask;
    private String title, carga, nombpla, nombbat;
    private double pi, mes1, mes2, mes3, mes4, mes5, mes6, mes7, mes8, mes9, mes10, mes11, mes12, con1, con2,con3,con4,con5,con6,con7,con8,con9,con10,con11,con12;
    private double[] dia, gon, dec, pasoang, ws, pasohoras, energiadiaria;
    private double latitud, orientacion, inclinacion, filpan;
    private double[][] costheta, coszs, tb,td,g,energia,w, nmod;
    private int i,j;
    private double term1, term2, term3, term4, term5;
    private double a0,a1,k,a0i,a0v,a1i,a1v,ki,kv, altitude,lat, ori, inc, pcabl, pconv, pbat, enec, etperd, pplaca, cplaca, vsis, pserie,pparal, pinst, lvert,lhori, dauto,dhor, sup, pdes;
    private double vbat, capbat, cbat, batserie, batparal, pinv, pnec, cinv, fsim, pvol, pint, creg, vplaca, prepla, prebat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p9activity);

        Bundle bundle =getIntent().getExtras();
        title = bundle.getString("nombre");
        carga=bundle.getString("carga");

        progressBar = findViewById(R.id.pb);
        asynctask =new  asynctask();
        asynctask.execute();




    }

    private class asynctask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getBaseContext(), "datos", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor cursor = bd.rawQuery("SELECT * FROM datos WHERE nombre='" + title + "'",null);
            if (cursor.moveToFirst()) {
                fsim=Double.parseDouble(cursor.getString(1));
                pcabl=Double.parseDouble(cursor.getString(2));
                pconv=Double.parseDouble(cursor.getString(3));
                pbat=Double.parseDouble(cursor.getString(4));
                cbat=Double.parseDouble(cursor.getString(5));
                cplaca=Double.parseDouble(cursor.getString(6));
                creg=Double.parseDouble(cursor.getString(7));
                cinv = Double.parseDouble(cursor.getString(8));
                lat = Double.parseDouble(cursor.getString(9));
                altitude =Double.parseDouble(cursor.getString(10));
                con1=Double.parseDouble(cursor.getString(11));
                con2=Double.parseDouble(cursor.getString(12));
                con3=Double.parseDouble(cursor.getString(13));
                con4=Double.parseDouble(cursor.getString(14));
                con5=Double.parseDouble(cursor.getString(15));
                con6=Double.parseDouble(cursor.getString(16));
                con7=Double.parseDouble(cursor.getString(17));
                con8=Double.parseDouble(cursor.getString(18));
                con9=Double.parseDouble(cursor.getString(19));
                con10=Double.parseDouble(cursor.getString(20));
                con11=Double.parseDouble(cursor.getString(21));
                con12=Double.parseDouble(cursor.getString(22));
                pnec=Double.parseDouble(cursor.getString(23));
                vsis=Double.parseDouble(cursor.getString(24));
                nombpla=cursor.getString(25);
                inc=Double.parseDouble(cursor.getString(26));
                ori=Double.parseDouble(cursor.getString(27));
                pplaca=Double.parseDouble(cursor.getString(28));
                lvert=Double.parseDouble(cursor.getString(29));
                lhori=Double.parseDouble(cursor.getString(30));
                filpan=Double.parseDouble(cursor.getString(31));
                pint=Double.parseDouble(cursor.getString(32));
                vplaca=Double.parseDouble(cursor.getString(33));
                prepla=Double.parseDouble(cursor.getString(34));
                nombbat=cursor.getString(35);
                vbat=Double.parseDouble(cursor.getString(36));
                pdes=Double.parseDouble(cursor.getString(37));
                capbat=Double.parseDouble(cursor.getString(38));
                dauto=Double.parseDouble(cursor.getString(39));
                prebat=Double.parseDouble(cursor.getString(40));

            }
            pplaca=pplaca/1000;

            double con[] = new double[12];
            con[0] = con1;
            con[1] = con2;
            con[2] = con3;
            con[3] = con4;
            con[4] = con5;
            con[5] = con6;
            con[6] = con7;
            con[7] = con8;
            con[8] = con9;
            con[9] = con10;
            con[10] = con11;
            con[11] = con12;



            pi= Math.PI;
            dia= new double[365];
            gon=new double[365];
            dec=new double[365];
            latitud=Math.toRadians(lat);
            orientacion=Math.toRadians(ori);
            inclinacion=Math.toRadians(inc);
            ws=new double[365];
            pasoang=new double[365];
            pasohoras=new double[365];
            costheta=new double[365][501];
            coszs=new double[365][501];
            tb=new double[365][501];
            td=new double[365][501];
            g=new double[365][501];
            energia=new double[365][501];
            energiadiaria=new double[365];
            w=new double[365][501];
            nmod= new double [12][21];
            a0i=1.03*(0.4237-0.00821*(6-altitude/1000)*(6-altitude/1000));
            a0v=0.97*(0.4237-0.00821*(6-altitude/1000)*(6-altitude/1000));
            a1i=1.01*(0.5055+0.00595*(6.5-altitude/1000)*(6.5-altitude/1000));
            a1v=0.99*(0.5055+0.00595*(6.5-altitude/1000)*(6.5-altitude/1000));
            ki=1*(0.2711+0.01858*(2.5-altitude/1000)*(2.5-altitude/1000));
            kv=1*(0.2711+0.01858*(2.5-altitude/1000)*(2.5-altitude/1000));

            onProgressUpdate(20);



            //Calculo de la energia diaria
            for (int i = 0; i <= 364; i++) {
                dia[i] = i + 1;
            }

            for (i = 0; i <= 364; i++) {

                gon[i] = 1367 * (1 + 0.033 * Math.cos(2 * pi * (dia[i] / 365)));
                dec[i] = Math.toRadians(23.45 * Math.sin(2 * pi * (284 + dia[i]) / 365));
            }


            for (i = 0; i <= 364; i++) {

                ws[i] = Math.acos(Math.tan(latitud) * Math.tan(-dec[i]));
            }

            for (i = 0; i <= 364; i++) {

                pasoang[i] = (2 * ws[i]) / 500;
            }

            for (i = 0; i <= 364; i++) {

                pasohoras[i] = pasoang[i] / (15 * (pi / 180));
            }

            for (i = 0; i <= 364; i++) {
                w[i][0] = -ws[i];
                for (j = 1; j <= 500; j++) {
                    w[i][j] = w[i][j - 1] + pasoang[i];
                }
            }
            onProgressUpdate(40);

            for (i = 0; i <= 364; i++) {

                term1 = Math.sin(dec[i]) * Math.sin(latitud) * Math.cos(inclinacion);
                term2 = Math.sin(dec[i]) * Math.cos(latitud) * Math.sin(inclinacion) * Math.cos(orientacion);
                term4 = Math.cos(dec[i]) * Math.sin(latitud) * Math.sin(inclinacion) * Math.cos(orientacion);

                for (j = 0; j <= 500; j++) {

                    term3 = Math.cos(dec[i]) * Math.cos(latitud) * Math.cos(inclinacion) * Math.cos(w[i][j]);
                    term5 = Math.cos(dec[i]) * Math.sin(inclinacion) * Math.sin(orientacion) * Math.sin(w[i][j]);
                    costheta[i][j] = term1 - term2 + term3 + term4 + term5;
                    coszs[i][j] = Math.sin(dec[i]) * Math.sin(latitud) + Math.cos(dec[i]) * Math.cos(latitud) * Math.cos(w[i][j]);

                    if (i <= 79) {
                        a0 = a0i;
                        a1 = a1i;
                        k = ki;
                    } else if (i > 80 && i <= 263) {
                        a0 = a0v;
                        a1 = a1v;
                        k = kv;
                    } else {
                        a0 = a0i;
                        a1 = a1i;
                        k = ki;
                    }
                    tb[i][j] = a0 + a1 * Math.exp(-k / coszs[i][j]);
                    if (Double.isNaN(tb[i][j])) {
                        tb[i][j] = 0;
                    }
                    if (Double.isInfinite(tb[i][j])) {
                        tb[i][j] = 1;
                    }
                    td[i][j] = 0.271 - 0.294 * tb[i][j];
                    g[i][j] = (gon[i] * costheta[i][j]) * (tb[i][j] + td[i][j]);
                    energia[i][j] = g[i][j] * pasohoras[i];

                }

            }

            onProgressUpdate(60);
            for (i = 0; i <= 364; i++) {
                for (j = 0; j <= 500; j++) {
                    if (energia[i][j] < 0) {
                        energia[i][j] = 0;
                    }

                }
            }

            for (i = 0; i <= 364; i++) {
                for (j = 0; j <= 500; j++) {
                    energiadiaria[i] = energiadiaria[i] + energia[i][j];

                }
            }

            for (i = 0; i <= 30; i++) {
                mes1 = mes1 + energiadiaria[i] / 31;

            }

            for (i = 31; i <= 58; i++) {
                mes2 = mes2 + energiadiaria[i] / 28;

            }

            for (i = 59; i <= 90; i++) {
                mes3 = mes3 + energiadiaria[i] / 31;

            }

            for (i = 91; i <= 119; i++) {
                mes4 = mes4 + energiadiaria[i] / 30;

            }

            for (i = 120; i <= 150; i++) {
                mes5 = mes5 + energiadiaria[i] / 31;

            }

            for (i = 151; i <= 180; i++) {
                mes6 = mes6 + energiadiaria[i] / 30;

            }

            for (i = 181; i <= 211; i++) {
                mes7 = mes7 + energiadiaria[i] / 31;

            }

            for (i = 212; i <= 242; i++) {
                mes8 = mes8 + energiadiaria[i] / 31;

            }

            for (i = 243; i <= 272; i++) {
                mes9 = mes9 + energiadiaria[i] / 30;

            }

            for (i = 273; i <= 303; i++) {
                mes10 = mes10 + energiadiaria[i] / 31;

            }

            for (i = 304; i <= 333; i++) {
                mes11 = mes11 + energiadiaria[i] / 30;

            }

            for (i = 334; i <= 364; i++) {
                mes12 = mes12 + energiadiaria[i] / 31;

            }


            onProgressUpdate(80);
            mes1 = mes1 / 1000;
            mes2 = mes2 / 1000;
            mes3 = mes3 / 1000;
            mes4 = mes4 / 1000;
            mes5 = mes5 / 1000;
            mes6 = mes6 / 1000;
            mes7 = mes7 / 1000;
            mes8 = mes8 / 1000;
            mes9 = mes9 / 1000;
            mes10 = mes10 / 1000;
            mes11 = mes11 / 1000;
            mes12 = mes12 / 1000;


            int g;
            double energiamasperd[]= new double[12];
            for (g=0;g<=11;g++){
                energiamasperd[g]=(con[g])/(1-pbat/100-pcabl/100-pconv/100);
            }



            //HSP del mes mas desfavorable
            double meses[]= new double[12];
            meses[0]=mes1;
            meses[1]=mes2;
            meses[2]=mes3;
            meses[3]=mes4;
            meses[4]=mes5;
            meses[5]=mes6;
            meses[6]=mes7;
            meses[7]=mes8;
            meses[8]=mes9;
            meses[9]=mes10;
            meses[10]=mes11;
            meses[11]=mes12;


            //Numero de paneles

            int p;
            double numeropaneles []=new double[12];
            double minhsp=0;
            double npan=0;
            for (p=0;p<=11;p++){
                numeropaneles[p]=(energiamasperd[p]*cplaca)/(meses[p]*pplaca);
                if (numeropaneles[p]>npan){
                    npan=numeropaneles[p];
                    minhsp=meses[p];
                    etperd=energiamasperd[p];
                }
            }

            if (vsis==vplaca){
                pserie=1;
                pparal=Math.ceil(npan);
            }else {
                pserie=2;
                pparal=Math.ceil(npan/2);
            }
            //Energia producida
            double etpan= (minhsp)*pserie*pparal*pplaca;

            //potencia total instalada
            pinst=pplaca*pserie*pparal;

            onProgressUpdate(60);

            //Superficie necesaria
            dhor=(lvert*Math.sin(inclinacion))/Math.tan(Math.toRadians(61)-latitud);
            sup=(filpan-1)*dhor*lhori*Math.ceil(npan/filpan)+npan*lhori*lvert*Math.cos(inclinacion);


            //Capacidad necesaria
            double capnec=Math.ceil(etperd*dauto*1000)/(vsis*pdes/100);

            //Numero de baterias
            double nbat= (capnec*cbat)/(capbat);



            if (vsis==vbat){
                 batserie=1;
                 batparal=Math.ceil(nbat);
            }else {
                 batserie=2;
                 batparal=Math.ceil(nbat/2);
            }

            //Potencia inversor
            pinv=pnec*fsim*cinv;

            //Voltaje e intensidad minimo del regulador
            String vreg=String.format(Locale.US,"%.2f",vsis);
            double ireg= (pparal*pint)*creg;

            // Coste de placas y baterías
            double totpla= pserie*pparal*prepla;
            double totbat= batserie*batparal*prebat;






            //Formatear variables
            String a41=String.format(Locale.US,"%.2f",mes1);
            String a42=String.format(Locale.US,"%.2f",mes2);
            String a43=String.format(Locale.US,"%.2f",mes3);
            String a44=String.format(Locale.US,"%.2f",mes4);
            String a45=String.format(Locale.US,"%.2f",mes5);
            String a46=String.format(Locale.US,"%.2f",mes6);
            String a47=String.format(Locale.US,"%.2f",mes7);
            String a48=String.format(Locale.US,"%.2f",mes8);
            String a49=String.format(Locale.US,"%.2f",mes9);
            String a50=String.format(Locale.US,"%.2f",mes10);
            String a51=String.format(Locale.US,"%.2f",mes11);
            String a52=String.format(Locale.US,"%.2f",mes12);
            String a53= String.format(Locale.US,"%.2f",etperd);
            String a54= String.format(Locale.US,"%.2f",etpan);
            String a55=String.format(Locale.US,"%.2f",minhsp);
            String a56=String.format(Locale.US,"%.2f",pserie);
            String a57=String.format(Locale.US,"%.2f",pparal);
            String a58=String.format(Locale.US,"%.2f",pinst);
            String a59=String.format(Locale.US,"%.2f",dhor);
            String a60=String.format(Locale.US,"%.2f",sup);
            String a61=String.format(Locale.US,"%.2f",capnec);
            String a62=String.format(Locale.US,"%.2f",batserie);
            String a63=String.format(Locale.US,"%.2f",batparal);
            String a65=String.format(Locale.US,"%.2f",pinv);
            String a67=String.format(Locale.US,"%.2f",ireg);
            String a68=vreg;
            String a69=String.format(Locale.US, "%.2f", totpla);
            String a70=String.format(Locale.US, "%.2f", totbat);





            // registro de datos
            ContentValues registro = new ContentValues();
            registro.put("a41", a41);
            registro.put("a42", a42);
            registro.put("a43", a43);
            registro.put("a44", a44);
            registro.put("a45", a45);
            registro.put("a46", a46);
            registro.put("a47", a47);
            registro.put("a48", a48);
            registro.put("a49", a49);
            registro.put("a50", a50);
            registro.put("a51", a51);
            registro.put("a52", a52);
            registro.put("a53", a53);
            registro.put("a54", a54);
            registro.put("a55", a55);
            registro.put("a56", a56);
            registro.put("a57", a57);
            registro.put("a58", a58);
            registro.put("a59", a59);
            registro.put("a60", a60);
            registro.put("a61", a61);
            registro.put("a62", a62);
            registro.put("a63", a63);
            registro.put("a65", a65);
            registro.put("a67", a67);
            registro.put("a68", a68);
            registro.put("a69", a69);
            registro.put("a70", a70);

            bd.update("datos", registro, "nombre='" + title + "'", null);
            bd.close();



            onProgressUpdate(100);

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();

            progressBar.setProgress(progreso);
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(100);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Intent intent = new Intent(P9Activity.this, P10Activity.class);
                intent.putExtra("nombre", title);
                intent.putExtra("carga",carga);
                startActivity(intent);
            }
        }
    }
}
