package com.example.module;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.res.Resources;

// Battle ship
public class Battleship extends Sprite {

    private static Battleship ui;

    public Battleship(Resources res, float screenWidth) {

        super();
        int msSize = (int) (screenWidth / 3);
        img = BitmapFactory.decodeResource(res, R.drawable.ms);
        img = Bitmap.createScaledBitmap(img, msSize, msSize, true);
        bounds.set(bounds.left, bounds.top, msSize, msSize);
    }

    public static Battleship getInstance(Resources res, float screenWidth) {

        if (ui == null) {
            ui = new Battleship(res, screenWidth);
            return ui;
        }
        return ui;
    }

    @Override
    public void tick() {
        this.move();
    }
}