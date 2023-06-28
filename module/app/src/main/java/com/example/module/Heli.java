package com.example.module;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;

// Heli
public class Heli extends Enemy {

    public int point;
    Resources res;
    int rspeed;
    Context c;


    public Heli(Context c, float screenWidth, float screenHight) {
        super();
        z = Prefs.hdir(c);
        reset();
        this.screenWidth = screenWidth;
        this.screenHights = screenHight;
        res = c.getResources();
        this.c = c;
        this.res = res;

        heli();
        img = BitmapFactory.decodeResource(res, img1);
        img = Bitmap.createScaledBitmap(img, scale, scale, true);
        bounds.set(bounds.left, bounds.top, scale, scale);
    }

    public void heli() {

        if (ant == Size.BIG) {
            scale = (int) (screenWidth / 6);
            if (drc == Direction.RIGHT_FACING) {
                img1 = R.drawable.bh;
            } else {
                img1 = R.drawable.big_airplane_flip;
            }
        }

        if (ant == Size.SMALL) {
            scale = (int) (screenWidth / 12);
            if (drc == Direction.RIGHT_FACING) {
                img1 = R.drawable.sh;
            } else {
                img1 = R.drawable.little_airplane_flip;
            }
        }

        if (ant == Size.MEDIUM) {
            scale = (int) (screenWidth / 9);
            if (drc == Direction.RIGHT_FACING) {
                img1 = R.drawable.mh;
            } else {
                img1 = R.drawable.medium_airplane_flip;
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
            float quincy = ((float) (Math.random() * screenHights / 3));
            bounds.offsetTo(0, quincy);
        }
        if (bounds.right < 0) {
            float quincy = (float) ((Math.random() * screenHights / 3));
            bounds.offsetTo(screenWidth, quincy);
        }
        if (Math.random() < 0.5) {

            if (drc == Direction.RIGHT_FACING) {
                velocity.set(-rspeed, 0);
            } else if (drc == Direction.LEFT_FACING) {
                velocity.set(rspeed, 0);
            }
        }
    }

    @Override
    public void tick() {
        this.move();
    }


    public void explodeh() {

        exploding = true;
        if(exploding) {
            img = BitmapFactory.decodeResource(res, R.drawable.airplane_explosion);
            velocity.set(0, 0);
            rspeed = 0;
        }

    }

    @Override
    public int point() {
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
        heli();
        img = BitmapFactory.decodeResource(res, img1);
        float quincy = (float) ((Math.random() * screenHights / 2) + screenHights / 2);

        if (drc == Direction.RIGHT_FACING) {

            bounds.offsetTo(0, quincy);

        } else {

            bounds.offsetTo(screenWidth, quincy);

        }
    }
}



