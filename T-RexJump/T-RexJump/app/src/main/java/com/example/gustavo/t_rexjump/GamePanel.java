package com.example.gustavo.t_rexjump;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


import java.sql.Time;
import java.util.ArrayList;


/**
 * Created by gustavo on 11/06/2017.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static final int MOVESPEED = -15;
    public MainThread thread;
    private Background bg;
    private Player player;
    Context c;
    public static float score;
    private Obstaculos[] obstaculos;
    private int obstaculosCount = 3;
    private Paint paint;



    public GamePanel(Context context) {
        super(context);
        c=context;
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);

        /*obstaculos = new Obstaculos[obstaculosCount];
        for(int i=0; i<obstaculosCount; i++){
            obstaculos[i] = new Obstaculos(context, screenX, screenY);
        }*/

    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while(retry && counter<1000)
        {
            counter++;
            try {thread.setRunning(false);
                 thread.join();
                 retry = false;

            }catch (InterruptedException e) {e.printStackTrace();}

        }

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        player = new Player(BitmapFactory.decodeResource(getResources(),R.drawable.dino24), 65, 25, 3);
        bg = new Background(BitmapFactory.decodeResource(getResources(),R.drawable.bg1));
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            player.y -= 90;

            if(!player.getPlaying())
           {
               player.setPlaying(true);
           }
           else{
               player.setUp(true);
           }
            return true;
        }

        return super.onTouchEvent(event);
    }

    public void update()
    {
        if(player.getPlaying()) {
            bg.update();
            player.update();
        }

        score+=0.30;

        if(score >= 250)
        {
            Intent i = new Intent("Forca");
            i.addCategory("ForcaGame");
            i.putExtra("game", "Jogo5");
            c.startActivity(i);
        }

        for(int i=0; i<obstaculosCount; i++){
            obstaculos[i].update(player.y);
        }
    }


    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if (canvas!=null)
        {
            canvas.drawText(Float.toString(score), 0, 0, new Paint());
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }

        for (int i = 0; i < obstaculosCount; i++) {
            canvas.drawBitmap(
                    obstaculos[i].getBitmap(),
                    obstaculos[i].getX(),
                    obstaculos[i].getY(),
                    paint);
        }


    }

}


