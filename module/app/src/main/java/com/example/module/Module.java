package com.example.module;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.content.DialogInterface;
import android.app.Activity;
import android.app.AlertDialog;
import android.media.MediaPlayer;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;


public class Module extends View implements TickListener {

    private Heli sh, mh, bh;
    private Uwm suwm, muwm, buwm;
    private Bitmap water;
    private Battleship ms;
    private boolean initialized;
    public static Timer time = new Timer();
    Paint tc;
    Paint quincy;
    int h;
    int w;
    long now;
    long yet;
    int gametime = 20;
    ArrayList<DepthCharge> dc = new ArrayList<>();
    ArrayList<DepthCharge> dcm = new ArrayList<>();
    ArrayList<Missile> missiles = new ArrayList<>();
    ArrayList<Missile> missilesm = new ArrayList<>();
    ArrayList<Heli> helis = new ArrayList<>();
    ArrayList<Uwm> uwms = new ArrayList<>();
    int bs;
    String message1;
    boolean uu;
    int min;
    int sec;
    MediaPlayer uex;
    MediaPlayer hex;
    MediaPlayer s1;
    MediaPlayer s2;
    MediaPlayer sde;



    public Module(Context c) {

        super(c);
        readfile();
        quincy = new Paint();
        water = BitmapFactory.decodeResource(getResources(), R.drawable.water);

        uex = MediaPlayer.create(c, R.raw.uex);
        uex.setLooping(false);

        hex = MediaPlayer.create(c, R.raw.hex);
        hex.setLooping(false);

        s1 = MediaPlayer.create(c, R.raw.s1);
        s1.setLooping(false);

        s2 = MediaPlayer.create(c, R.raw.s2);
        s2.setLooping(false);

        sde = MediaPlayer.create(c, R.raw.sde);
        sde.setLooping(false);

        initialized = false;

    }

    public void readfile() {
        try (Scanner o = new Scanner(getContext().openFileInput("scores.txt"))){

            bs = o.nextInt();

        } catch (FileNotFoundException g) {
            bs = 0;
        }
    }




    @Override
    public void onDraw(Canvas c) {

        tick();

        h = c.getHeight();
        w = c.getWidth();


        if (initialized == false) {
            final int wW = w / 50;


            water = Bitmap.createScaledBitmap(water, wW, wW, true);
            sh = new Heli(getContext(), w, h);
            float shX = w * 0.1f;
            float shY = h * 0.1f;
            sh.setPosition(shX, shY);
            time.register(sh);
            mh = new Heli(getContext(), w, h);
            float mhX = w * 0.45f;
            float mhY = h * 0.22f;
            mh.setPosition(mhX, mhY);
            time.register(mh);
            bh = new Heli(getContext(), w, h);
            float bhX = w * 0.8f;
            float bhY = h * 0.3f;
            bh.setPosition(bhX, bhY);
            time.register(bh);
            suwm = new Uwm(getContext(), w, h);
            float suwmX = w * 0.22f;
            float suwmY = h * 0.8f;
            suwm.setPosition(suwmX, suwmY);
            time.register(suwm);
            muwm = new Uwm(getContext(), w, h);
            float muwmX = w * 0.52f;
            float muwmY = h * 0.7f;
            muwm.setPosition(muwmX, muwmY);
            time.register(muwm);
            buwm = new Uwm(getContext(), w, h);
            float buwmX = w * 0.8f;
            float buwmY = h * 0.9f;
            buwm.setPosition(buwmX, buwmY);
            time.register(buwm);
            ms = Battleship.getInstance(getResources(), w);
            float msX = w / 3;
            float msY = h * 0.4f;
            ms.setPosition(msX, msY);


            helis.add(sh);
            helis.add(mh);
            helis.add(bh);
            uwms.add(suwm);
            uwms.add(muwm);
            uwms.add(buwm);

            initialized = true;

        }
        for (var qq : dc) {
            qq.draw(c);
            dcm.add(qq);
        }

        for (var ww : dcm) {
            if (ww.getdd() > h) {
                dc.remove(ww);
                time.deregister(ww);
            }
        }
        for (var ee : missiles) {
            ee.draw(c);
            missilesm.add(ee);
        }
        for (var rr : missilesm) {
            if (rr.getm() < 0) {
                missiles.remove(rr);
                time.deregister(rr);
            }
        }

        ms.draw(c);
        sh.draw(c);
        mh.draw(c);
        bh.draw(c);
        suwm.draw(c);
        muwm.draw(c);
        buwm.draw(c);
        float waterX = 0;
        tc = new Paint();
        while (waterX < w) {
            c.drawBitmap(water, waterX, h / 2, null);
            waterX += water.getWidth();
        }

        tc.setColor(Color.BLACK);
        tc.setTextSize(60);
        c.drawText(String.valueOf(R.string.score) + score, 65, 300, tc);
        String tt = String.format("%01d", sec);
        if (sec >= 10) {
            c.drawText(String.valueOf(R.string.time) + min + " : " + sec, 600, 300, tc);
        } else {
            c.drawText(String.valueOf(R.string.time) + min + " : 0" + sec, 600, 300, tc);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            float x = m.getX();
            float y = m.getY();

            if (Prefs.rd(getContext())) {
                if (y > h / 2) {
                    DepthCharge dc1 = new DepthCharge(getResources(), w);
                     dc1.setPosition(w * 0.45f, h * 0.5f);
                    dc.add(dc1);
                    time.register(dc1);
                    sde.start();
                }
            }
            if (Prefs.rmi(getContext())) {
                if (x < (w / 2) && y < (h / 2)){
                    Missile mis = new Missile(Direction.LEFT_FACING, w);
                    mis.setPosition(w * 0.35f, h * 0.4f);
                    missiles.add(mis);
                    time.register(mis);
                    s1.start();
                } else {
                   Missile mis = new Missile(Direction.RIGHT_FACING, w);
                   mis.setPosition(w * 0.65f, h * 0.4f);
                   missiles.add(mis);
                   time.register(mis);
                   s2.start();
                }
        }
    }
        return true;
    }

    int score;

    public void detectCollisions() {


        for (var shooting : missiles) {
            for (var everyheli : helis) {
                if (shooting.overlaps(everyheli)) {
                    everyheli.explodeh();
                    missiles.remove(shooting);
                    hex.start();
                    score += everyheli.point();
                }
            }
        }
        for (var shooting : dc) {
            for (var everyuwm : uwms) {
                if (shooting.overlaps(everyuwm)) {
                    everyuwm.explodeu();
                    dc.remove(shooting);
                    uex.start();
                    score += everyuwm.point();
                }
            }
        }
    }



    @Override
    public void tick() {

        detectCollisions();

        min = gametime / 60;
        sec = gametime % 60;


        now = System.currentTimeMillis();
        if (now - yet >= 1000) {
            gametime -= 1;
            yet = now;
        }
        if (gametime == 0) {
            gametime = 0;
            if (uu == false) {
                time.removeMessages(0);
                if (score > bs) {
                    try(var fos = getContext().openFileOutput("score.txt", Context.MODE_PRIVATE)) {
                        fos.write((" " + score).getBytes());
                    } catch (FileNotFoundException g) {
                        g.printStackTrace();
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    AlertDialog.Builder h = new AlertDialog.Builder(getContext());
                    h.setTitle(String.valueOf(R.string.gameover))
                            .setMessage(R.string.finscore + score + "'")
                            .setCancelable(false)
                            .setPositiveButton(R.string.newgame, (d,i) -> restart())
                            .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface d, int i) {
                                    try (var fos = getContext().openFileOutput("score.txt", Context.MODE_PRIVATE)) {
                                        fos.write(("" + score).getBytes());
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    ((Activity) getContext()).finish();
                                }
                            });

                    AlertDialog box = h.create();
                    box.show();
                } else {
                    AlertDialog.Builder b = new AlertDialog.Builder(getContext());
                    b.setTitle(String.valueOf(R.string.gameover))
                            .setMessage(R.string.finscore + score + ","+ R.string.best + bs)
                            .setCancelable(false)
                            .setPositiveButton(R.string.newgame, (d, i) -> restart())
                            .setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface d, int i) {
                                    try (var fos = getContext().openFileOutput("score.txt", Context.MODE_PRIVATE)) {
                                        fos.write(("" + score).getBytes());
                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    ((Activity) getContext()).finish();
                                }
                            });
                    AlertDialog box = b.create();
                    box.show();
                }
                uu = true;
            }
        }
        invalidate();
    }

    public void restart() {

        time.sendMessageDelayed(Message.obtain(), 1000);
        gametime = 20;
        score = 0;
        uu = false;

    }

    public static void pauseapp() {
        if (time != null){
            time.paused();
        }
    }

    public static void restartapp(){
        if (time != null) {
            time.unpaused();
        }
    }
}


