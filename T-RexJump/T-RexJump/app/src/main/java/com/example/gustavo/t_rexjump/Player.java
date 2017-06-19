package com.example.gustavo.t_rexjump;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


/**
 * Created by gustavo on 13/06/2017.
 */

public class Player extends GameObject {
    private Bitmap bm;
    private boolean up;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;


    public Player(Bitmap res, int w, int h, int numFrames) {
        x = 100;
        y = GamePanel.HEIGHT / 2;

        height = h;
        width = w;
        bm = res;

        Bitmap[] image = new Bitmap[numFrames];

        startTime = System.nanoTime();
    }

    public void setUp (boolean b)
    {
        up = b;
    }

    public void update()
    {
        //Gravity
        y+= 7;

    }


    public void draw(Canvas canvas)

    {
        canvas.drawBitmap(bm, x, y, null);
    }


    public boolean getPlaying()
    {
        return playing;
    }

    public void setPlaying(boolean b)
    {
        playing = b;
    }

    public void resetY()
    {
        y = 0;
    }

    public void resetScore()
    {
        GamePanel.score = 0;
    }

}
