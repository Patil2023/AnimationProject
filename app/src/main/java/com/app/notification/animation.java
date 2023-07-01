package com.app.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class animation extends AppCompatActivity {
    Button  Blink,Fade,Move,Rotate,Slide,Zoom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Blink=findViewById(R.id.Btn1);
        Fade=findViewById(R.id.Btn2);
        Move=findViewById(R.id.Btn3);
        Rotate=findViewById(R.id.Btn4);
        Slide=findViewById(R.id.Btn5);
        Zoom=findViewById(R.id.Btn6);
        Blink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_animation);
                Blink.startAnimation(animation);

                Intent intent=new Intent(getApplicationContext(),BrodcastReceiver.class);
                startActivity(intent);


            }
        });
        Fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_animation);
                Fade.startAnimation(animation2);

            }
        });
        Move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_animation);
                Move.startAnimation(animation3);


            }
        });
        Rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_animation);
                Rotate.startAnimation(animation4);

            }
        });

        Zoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation5 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_animation);
                Slide.startAnimation(animation5);

            }
        });
    }
}