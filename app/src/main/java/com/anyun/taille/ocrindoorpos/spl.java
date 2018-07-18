package com.anyun.taille.ocrindoorpos;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;

public class spl extends Activity {

    private final int SPLASH_DISPLAY_LENGHT = 2000; // 两秒后进入系统
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spl);
        img = (ImageView)findViewById(R.id.imageView);
        int resId=this.getResources().getIdentifier("location","drawable",getPackageName());
        img.setImageResource(resId);


        new android.os.Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(spl.this,
                        MainActivity.class);
                spl.this.startActivity(mainIntent);
                spl.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }
}