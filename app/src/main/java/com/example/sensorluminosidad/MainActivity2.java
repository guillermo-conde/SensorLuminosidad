package com.example.sensorluminosidad;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    //Variables necesarias
    Button salir;
    SensorManager manager;
    Sensor sensor;
    SensorEventListener eventListener;
    TextView mostrar;
    float valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Instanciación del botón de salida.
        salir = (Button)findViewById(R.id.btnSalir);
        //Instanciación de objetos relacionados al sensor.
        manager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //Instanciación del textview para mostrar el porcentaje.
        mostrar = (TextView)findViewById(R.id.mostrarPorcentaje);

        //Salir de la segunda interfaz
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Comprobación de que el dispositivo cuenta con sensor de luz.
        if(sensor == null){
            Toast.makeText(this, "Tu dispositivo no cuenta con sensor de luz :(", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            //Instanciación de un escuchador de cambios de valor en el sensor
            eventListener = new SensorEventListener() {
                //Función que detecta cada cambio.
                @Override
                public void onSensorChanged(SensorEvent event) {
                    valor = event.values[0];
                    //Se muestra como decimal mas no como porcentaje.
                    mostrar.setText(String.format("%.2f",valor));
                }
                //Función para calibrar los valores del sensor (No usada en esta práctica, pero necesaria para el funcionamiento).
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {
                }
            };
        }
    }

    //Función que detiene el sensor cuando se sale de la interfaz.
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(eventListener);
    }

    //Función que dispara el funcionamiento del sensor en la segunda interfaz
    @Override
    protected void onResume() {
        super.onResume();
        manager.registerListener(eventListener, sensor, manager.SENSOR_DELAY_NORMAL);
    }
}