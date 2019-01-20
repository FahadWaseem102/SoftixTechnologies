package com.example.fahad.softixtechnologies.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.fahad.softixtechnologies.R;

public class SplashActivity extends AppCompatActivity {

    ImageView imgView ;
    Animation animatiomFromtop ;

    public static int SPLASH_TIME_OUT= 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imgView = findViewById(R.id.imageView) ;
        animatiomFromtop = AnimationUtils.loadAnimation(this, R.anim.splash_anim) ;
        imgView.setAnimation(animatiomFromtop);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
