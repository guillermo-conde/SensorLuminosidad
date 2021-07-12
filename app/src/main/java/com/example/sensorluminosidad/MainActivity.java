package com.example.sensorluminosidad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button iniciar;
    Intent accion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar = (Button) findViewById(R.id.btnIniciar);

        //Código para ir a la segunda interfaz.
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accion = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(accion);
            }
        });
    }

}