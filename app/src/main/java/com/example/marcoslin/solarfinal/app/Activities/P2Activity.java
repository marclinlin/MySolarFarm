package com.example.marcoslin.solarfinal.app.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.marcoslin.solarfinal.R;

public class P2Activity extends AppCompatActivity {

    private View btnp21;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2activity);

        btnp21 = findViewById(R.id.btnp21);
        WebView view=findViewById(R.id.textView20);

        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "My Solar farm <br><br>" +
                "Esta aplicación está diseñada para que con solo introducir unos parámetros necesarios para el diseño y los equipos que se quieran utilizar para una instalación fotovoltaica doméstica de autoconsumo, calcule el ahorro que proporcionaría e indica si sería una buena inversión dicha instalación fotovoltaica. También incluye un servicio de autolocalización que extrae los valores de latitud y de la altitud de la localización actual a través del GPS del dispositivo móvil para facilitar los cálculos del recurso solar.<br><br>" +
        "My Solar Farm, también permite guardar y gestionar a través de una base de datos los resultados de todos los proyectos que se hayan calculado previamente en la aplicación." +
                "<br><br>Por Marcos Lin Lin"+"<br>Correo: linlin.marcos@gmail.com"+"<br>Universidad Politécnica de Madrid";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");


    btnp21.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent= new Intent(P2Activity.this,P3Activity.class);
            startActivity(intent);
            }
        });


    }
}
