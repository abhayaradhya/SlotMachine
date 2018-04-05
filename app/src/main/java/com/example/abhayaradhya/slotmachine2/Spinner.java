package com.example.abhayaradhya.slotmachine2;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by abhayaradhya on 3/27/18.
 */

public class Spinner {

    private ImageView img;
    private Handler handler;
    private Update update;
    private boolean stopped;
    private int imageIndex;
    private int rate;
    private int[] colors;
    private int baseRate;

    public Spinner(ImageView img, int rate, int[] colors){
        this.img = img;
        stopped = true;
        handler = new Handler();
        update = new Update();
        imageIndex = 0;
        this.rate = rate;
        baseRate = rate;
        //images = new int[]{R.drawable};
        this.colors = colors;
        img.setBackgroundColor(colors[0]);
    }

    public boolean isStopped(){
        return stopped;
    }

    public int getColorIndex(){
        return imageIndex%colors.length;
    }

    public void startSpinning(){
        if(stopped==true){
            handler.postDelayed(update, rate);
            stopped = false;
        }
    }

    public void setImageIndex(int i){
        imageIndex = i;
        img.setBackgroundColor(colors[imageIndex%colors.length]);
    }

    public void setRate(int r){
        rate = r;
    }

    public int getBaseRate(){
        return baseRate;
    }

    public void stopSpinning(){
        if(stopped == false) {
            handler.removeCallbacks(update);
            stopped = true;
        }
    }

    private class Update implements Runnable {
        @Override
        public void run() {
            imageIndex++;
            img.setBackgroundColor(colors[imageIndex%colors.length]);
            handler.postDelayed(update, rate);
        }
    }
}
