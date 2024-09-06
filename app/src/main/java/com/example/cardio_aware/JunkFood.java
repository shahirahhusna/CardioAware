package com.example.cardio_aware;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class JunkFood {
    Bitmap junkFood[] = new Bitmap[5];
    int junkFoodFrame = 0;
    int junkFoodX, junkFoodY, junkFoodVelocity;
    Random random;

    public JunkFood(Context context){
        Bitmap originalBurger = BitmapFactory.decodeResource(context.getResources(), R.drawable.burger);
        Bitmap originalPizza = BitmapFactory.decodeResource(context.getResources(), R.drawable.pizza);
        Bitmap originalSoda = BitmapFactory.decodeResource(context.getResources(), R.drawable.soda);
        Bitmap originalDonut = BitmapFactory.decodeResource(context.getResources(), R.drawable.donut);
        Bitmap originalFries = BitmapFactory.decodeResource(context.getResources(), R.drawable.fries);

        int desiredWidth = originalBurger.getWidth() / 2;
        int desiredHeight = originalBurger.getHeight() / 2;

        junkFood[0] = Bitmap.createScaledBitmap(originalBurger, desiredWidth, desiredHeight, false);
        junkFood[1] = Bitmap.createScaledBitmap(originalPizza, desiredWidth, desiredHeight, false);
        junkFood[2] = Bitmap.createScaledBitmap(originalSoda, desiredWidth, desiredHeight, false);
        junkFood[3] = Bitmap.createScaledBitmap(originalDonut, desiredWidth, desiredHeight, false);
        junkFood[4] = Bitmap.createScaledBitmap(originalFries, desiredWidth, desiredHeight, false);

        random = new Random();
        resetPosition();
    }

    public Bitmap getJunkFood(int junkFoodFrame){
        return junkFood[junkFoodFrame];
    }
    public int getJunkFoodWidth(){
        return junkFood[0].getWidth();
    }
    public int getJunkFoodHeight(){
        return junkFood[0].getHeight();
    }
    public void resetPosition() {
        junkFoodX = random.nextInt(GameView.dWidth - getJunkFoodWidth());
        junkFoodY = -200 + random.nextInt(600) * -1;
        junkFoodVelocity = 35 + random.nextInt(16);
    }
}
