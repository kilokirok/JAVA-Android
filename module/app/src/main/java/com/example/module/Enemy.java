package com.example.module;


import android.content.res.Resources;
import android.graphics.Canvas;

public abstract class Enemy extends Sprite {
    protected Size ant;
    protected Direction drc;
    int img1;
    Resources res;
    int rspeed;
    int scale;
    protected boolean exploding;
    int z;






// Provide ramdom place to enemies

    public Enemy() {
        super();
        reset();
    }

    public void rsize(){

        double rand = Math.random();

        if (rand<0.333){
            ant = Size.BIG; }
        else if (rand < 0.667 && rand >0.333){
            ant = Size.MEDIUM;}
        else {
            ant = Size.SMALL;}

    }

    public void rfacing() {

        double ard = Math.random();
        if (ard < 0.5){
            drc = Direction.RIGHT_FACING;
        } else {
            drc = Direction.LEFT_FACING;
        }
    }


    public void reset() {

        exploding = false;
        rsize();
        rfacing();


    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        if (exploding == true){
            reset();
        }

    }


    protected Size size() {
        return ant;
    }

    abstract int point();

    protected abstract float rw();
}
