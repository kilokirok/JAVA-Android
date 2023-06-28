package com.example.module;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends Activity {

    private ImageView imgv;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        imgv = new ImageView(this);
        imgv.setImageResource(R.drawable.splash);
        imgv.setScaleType(ImageView.ScaleType.FIT_XY);
        setContentView(imgv);
    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        var w = imgv.getWidth();
        var h = imgv.getHeight();
        System.out.println(w);
        System.out.println(h);
        System.out.println(m.getX());
        System.out.println(m.getY());
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            var x = m.getX();
            var y = m.getY();
            if (x < w*0.15f && y < h*0.15f) {
                //user touched settings button
                //TODO launch a Preferences activity
                Intent tent = new Intent(this, MyPrefsActivity.class);
                startActivity(tent);
            } else if (x > w*0.4f && y > h*0.5f && x < w*0.6f && y < h*0.6f) {
                Intent tent = new Intent(this, MainActivity.class);
                startActivity(tent);
                finish();
            } else if (x > w*0.78f && y < h*0.2f){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Game Start!")
                        .setCancelable(false)
                        .setPositiveButton("OK", (dialog,id) -> new Splash());
                AlertDialog alert = builder.create();
                alert.show();
            }
        }
        return true;
    }

}
