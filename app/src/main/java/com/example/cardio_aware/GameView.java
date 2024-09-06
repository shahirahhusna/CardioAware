package com.example.cardio_aware;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    Bitmap background, ground, rabbit;
    Rect rectBackground, rectGround;
    Context context;
    android.os.Handler handler;
    final long UPDATE_MILLIS = 30;
    Runnable runnable;
    Paint textPaint = new Paint();
    Paint healthPaint = new Paint();
    float TEXT_SIZE = 120;
    int points = 0;
    int life = 3;
    static int dWidth, dHeight;
    Random random;
    float rabbitX, rabbitY;
    float oldX;
    float oldRabbitX;
    ArrayList<JunkFood> junkFoods;
    ArrayList<Explosion> explosions;

    public GameView(Context context) {
        super(context);
        this.context = context;
        background = BitmapFactory.decodeResource(getResources(), R.drawable.gamebg);
        ground = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        rabbit = BitmapFactory.decodeResource(getResources(), R.drawable.bunny);
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        dWidth = size.x;
        dHeight = size.y;
        rectBackground = new Rect(0, 0, dWidth, dHeight);
        rectGround = new Rect(0, dHeight - ground.getHeight(), dWidth, dHeight);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
        textPaint.setColor(Color.rgb(255, 165, 0));
        textPaint.setTextSize(TEXT_SIZE);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTypeface(ResourcesCompat.getFont(context, R.font.days_one));
        healthPaint.setColor(Color.GREEN);
        random = new Random();
        rabbitX = dWidth / 2 - rabbit.getWidth() / 2;
        rabbitY = dHeight - ground.getHeight() - rabbit.getHeight() / 2 + 50;
        junkFoods = new ArrayList<>();
        explosions = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            JunkFood junkFood = new JunkFood(context);
            junkFoods.add(junkFood);
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(background, null, rectBackground, null);
        canvas.drawBitmap(ground, null, rectGround, null);
        canvas.drawBitmap(rabbit, rabbitX, rabbitY, null);
        for (int i = 0; i < junkFoods.size(); i++) {
            canvas.drawBitmap(junkFoods.get(i).getJunkFood(junkFoods.get(i).junkFoodFrame), junkFoods.get(i).junkFoodX, junkFoods.get(i).junkFoodY, null);
            junkFoods.get(i).junkFoodFrame++;
            if (junkFoods.get(i).junkFoodFrame > 2) {
                junkFoods.get(i).junkFoodFrame = 0;
            }
            junkFoods.get(i).junkFoodY += junkFoods.get(i).junkFoodVelocity;
            if (junkFoods.get(i).junkFoodY + junkFoods.get(i).getJunkFoodHeight() >= dHeight - ground.getHeight()) {
                points += 10;
                Explosion explosion = new Explosion(context);
                explosion.explosionX = junkFoods.get(i).junkFoodX;
                explosion.explosionY = junkFoods.get(i).junkFoodY;
                explosions.add(explosion);
                junkFoods.get(i).resetPosition();
            }
        }

        for (int i = 0; i < junkFoods.size(); i++) {
            if (junkFoods.get(i).junkFoodX + junkFoods.get(i).getJunkFoodWidth() >= rabbitX
                    && junkFoods.get(i).junkFoodX <= rabbitX + rabbit.getWidth()
                    && junkFoods.get(i).junkFoodY + junkFoods.get(i).getJunkFoodWidth() >= rabbitY
                    && junkFoods.get(i).junkFoodY + junkFoods.get(i).getJunkFoodWidth() <= rabbitY + rabbit.getHeight()) {
                life--;
                junkFoods.get(i).resetPosition();
                if (life == 0) {
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            }
        }

        for (int i = 0; i < explosions.size(); i++) {
            Explosion explosion = explosions.get(i);
            if (explosion.explosionFrame < explosion.getExplosionCount()) {
                canvas.drawBitmap(explosion.getExplosion(explosion.explosionFrame), explosion.explosionX, explosion.explosionY, null);
                explosion.explosionFrame++;
            } else {
                explosions.remove(i);
                i--; // Adjust index after removal
            }
        }
        if(life == 2){
            healthPaint.setColor(Color.YELLOW);
        } else if(life == 1){
            healthPaint.setColor(Color.RED);
        }
        canvas.drawRect(dWidth-200, 30, dWidth-200+60*life, 80, healthPaint);
        canvas.drawText(""+points, 20, TEXT_SIZE, textPaint);
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        if (touchY>= rabbitY){
            int action = event.getAction();
            if (action == MotionEvent.ACTION_DOWN){
                oldX = event.getX();
                oldRabbitX = rabbitX;
            }
            if (action == MotionEvent.ACTION_MOVE){
                float shift = oldX - touchX;
                float newRabbitX = oldRabbitX - shift;
                if (newRabbitX <= 0)
                    rabbitX = 0;
                else if (newRabbitX >= dWidth - rabbit.getWidth())
                    rabbitX = dWidth - rabbit.getWidth();
                else
                    rabbitX = newRabbitX;
            }
        }
        return true;
    }
}
