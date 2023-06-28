package com.example.module;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Bitmap;


public abstract class Sprite implements TickListener {
    Bitmap img;
    RectF bounds;
    float screenWidth;
    float screenHights;
    public PointF velocity;




    public Sprite(){
        velocity = new PointF();
        bounds = new RectF();
    }

    public void setPosition(float x, float y){

        bounds.offsetTo(x,y);
    }

    public void draw(Canvas c){

        c.drawBitmap(img,bounds.left,bounds.top,null);
    }

    public void move() {

        bounds.offset(velocity.x,velocity.y);
    }

    public boolean overlaps(Sprite other){

        return RectF.intersects(this.bounds, other.bounds);
    }
}




