package com.example.gustavo.t_rexjump;

import android.graphics.Rect;

/**
 * Created by gustavo on 12/06/2017.
 */

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int height;
    protected int width;

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }


}
