package com.app.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView proximitySensor;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senser);
        proximitySensor=findViewById(R.id.senserfirst);
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager!=null){
            Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            if(proximitySensor!=null){
                sensorManager.registerListener(this,proximitySensor,SensorManager.SENSOR_DELAY_NORMAL);
            }
        }else{
            Toast.makeText(this,"Sensor nao encontrado!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        String sensorValue="Proximity Value: "+sensorEvent.values[0];
        proximitySensor.setText(sensorValue);
        if(sensorEvent.values[0]>0){
            Toast.makeText(this,"O Objeto esta longe", Toast.LENGTH_SHORT).show();
        }else{
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                //v.vibrate();
                v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            }else{
                v.vibrate(500);
            }
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),notification);
            r.play();
            Toast.makeText(this,"O Objeto esta proximo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}