package com.example.module;

import android.os.Message;
import android.os.Handler;
import java.util.ArrayList;

public class Timer extends Handler {

    public boolean paused;

    private final ArrayList<TickListener> observers = new ArrayList<>();


    public void notifyobservers() {
        for (TickListener q: observers){
            q.tick();
        }
    }

    public Timer() {

        paused = false;


        sendMessageDelayed(obtainMessage(), 1000);

    }

    public void register(TickListener q) {

        observers.add(q);
    }
    public void deregister(TickListener q) {

        observers.remove(q);
    }

    @Override
    public void handleMessage(Message m) {
        for (var qq : observers) {
            qq.tick();
        }
        if (!paused ) {
            notifyobservers();
            sendMessageDelayed(obtainMessage(), 100);
        }

        notifyobservers();
        sendMessageDelayed(obtainMessage(), 100);

    }

    public void paused() {
        paused = true;
    }

    public void unpaused() {
        paused = false;
        sendMessageDelayed(obtainMessage(), 100);
    }
}
