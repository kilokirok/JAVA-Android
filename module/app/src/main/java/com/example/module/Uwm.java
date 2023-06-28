package com.example.module;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;
// UWM
public class Uwm extends Enemy {

    public int point;
    Context c;

    public Uwm(Context c, float screenWidth, float screenHight) {
        super();
        this.screenWidth = screenWidth;
        this.screenHights = screenHight;
        Resources res = c.getResources();
        this.res = res;
        this.c = c;

        uwm();
        img = BitmapFactory.decodeResource(res, img1);
        img = Bitmap.createScaledBitmap(img, scale, scale, true);
        bounds.set(bounds.left, bounds.top, scale, scale);
    }

    public void uwm() {

        if (ant == Size.BIG){
            scale = (int) (screenWidth/6);
            if (drc == Direction.RIGHT_FACING){
                img1 = R.drawable.buwm;
            } else {
                img1 = R.drawable.big_submarine_flip;
            }
        }

        if (ant == Size.SMALL){
            scale = (int) (screenWidth/12);
            if (drc == Direction.RIGHT_FACING){
                img1 = R.drawable.suwm;
            } else {
                img1 = R.drawable.little_submarine_flip;
            }
        }

        if (ant == Size.MEDIUM){
            scale = (int) (screenWidth/9);
            if (drc == Direction.RIGHT_FACING){
                img1 = R.drawable.muwm;
            } else {
                img1 = R.drawable.medium_submarine_flip;
            }
        }

    }

    @Override
    protected float rw() {

        switch (size()) {
            case BIG:
                return 0.1f;
            case MEDIUM:
                return 0.075f;
            case SMALL:
            default:
                return 0.05f;
        }
    }

    @Override
    public void move() {
        super.move();
        rspeed = (int)(Math.random()*30);
        if (bounds.left > screenWidth) {
            float quincy = ((float) (Math.random() * screenHights/2) + screenHights/2);
            bounds.offsetTo(0, quincy);
        }
        if (bounds.right < 0 ) {
            float quincy = (float)((Math.random()*screenHights/2)+screenHights/2);
            bounds.offsetTo(screenWidth, quincy);
        }
        if (Math.random() < 0.5) {

            if (drc == Direction.RIGHT_FACING) {
                velocity.set(-rspeed,0);
            } else if(drc == Direction.LEFT_FACING) {
                velocity.set(rspeed,0);
            }
        }
    }
    @Override
    public void tick() {
        this.move();
    }


    public void explodeu() {

        exploding = true;
        img = BitmapFactory.decodeResource(res, R.drawable.submarine_explosion);
        velocity.set(0,0);
        rspeed = 0;

    }

    @Override
    public int point(){
        if (ant == Size.SMALL) {
            point = 150;
        } else if (ant == Size.MEDIUM) {
            point = 75;

        } else {
            point = 25;
        }
        return point;

    }

    @Override
    public void reset() {
        super.reset();
        uwm();
        img = BitmapFactory.decodeResource(res, img1);
        float quincy = (float) ((Math.random() * screenHights / 3) + screenHights / 3);

        if (drc == Direction.RIGHT_FACING) {

            bounds.offsetTo(0, quincy);

        } else {

            bounds.offsetTo(screenWidth, quincy);

        }
    }
}

