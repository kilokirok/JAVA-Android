package com.example.module;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Missile extends Sprite{
    Paint p;
    protected Direction d;

    Missile(Direction direction, float screenWidth) {
        this.d = direction;
        float mis = screenWidth/30;

        if (direction == d.LEFT_FACING){

            velocity.set(-5,-20);
            bounds.set(0,0,mis,mis);

        } else if (direction == d.RIGHT_FACING) {
            velocity.set(5,-20);
            bounds.set(0,0,-mis,mis);

        }

        p = new Paint();
        p.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas q) {
        if (d == Direction.RIGHT_FACING){
            q.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, p);
        }
        else if (d == Direction.LEFT_FACING){
            q.drawLine(bounds.left, bounds.top, bounds.right, bounds.bottom, p);
        }

    }

    public float getm() {
        return bounds.top;
    }

    @Override
    public void tick() {
        this.move();
    }

}
