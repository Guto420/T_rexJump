package com.example.gustavo.t_rexjump;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.Random;

/**
 * Created by gustavo on 13/06/2017.
 */

public class Obstaculos extends GameObject{
        private Bitmap sprite;
        private int x;
        private int y;
        private int speed;

        private int MaxX;
        private int MinX;

        private int MaxY;
        private int MinY;

    public Obstaculos(Context contex, int screenX, int screenY)
    {
        sprite = BitmapFactory.decodeResource(contex.getResources(), R.drawable.obst1);

        MaxX = screenX;
        MaxY = screenY;
        MinX = 0;
        MinY = 0;

        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(MaxY) - sprite.getHeight();

    }

    public void update(int playerSpeed)
    {
        x -= playerSpeed;
        x -= speed;

        if (x < MinX - sprite.getWidth())
        {

            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = MaxX;
            y = generator.nextInt(MaxY) - sprite.getWidth();
        }

    }

    public Bitmap getBitmap() {
        return sprite;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }


}
