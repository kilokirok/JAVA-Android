package com.example.module;

import android.graphics.BitmapFactory;
import android.content.res.Resources;
import android.graphics.Bitmap;


public class DepthCharge extends Sprite {


    public DepthCharge(Resources res, float screenWidth) {
        super();
        int depthSize = (int) (screenWidth / 50 );
        img = BitmapFactory.decodeResource(res, R.drawable.depth_charge);
        img = Bitmap.createScaledBitmap(img, depthSize, depthSize, true);
        bounds.set(bounds.left, bounds.top, depthSize, depthSize);
        velocity.set(0, 20);
    }
    public float getdd() {
        return bounds.top;
    }

    @Override
    public void tick() {
        this.move();
    }
}
