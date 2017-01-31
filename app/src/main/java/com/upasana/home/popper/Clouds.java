package com.upasana.home.popper;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;


public class Clouds {

    Resources resource = MainActivity.resource;
    Context context = MainActivity.context;
    int screen_width = MainActivity.screen_width;
    int screen_height = MainActivity.screen_height;
    float sreen_dpi = MainActivity.screen_dpi;

    Bitmap pic, img;
    int cloud_x, cloud_y;
    int speed_x;
    int alpha;

    Clouds(int image) {
        pic = BitmapFactory.decodeResource(resource, image);
        img = pic;
        born();
    }

    public void born() {
        double n = Math.random();
        pic = Bitmap.createScaledBitmap(img, (int) (25 + n * img.getWidth()), (int) (25 + n * img.getHeight()), true);
        cloud_x = (int) (screen_width + screen_width * Math.random() * 0.5);     //Initital x position
        cloud_y = (int) (screen_height * Math.random() * 0.5);                 //Initial y position
        speed_x = (int) (3 + Math.random() * 5);
        alpha = 100 + (int) (Math.random() * 155);
    }

    public void move() {
        cloud_x -= speed_x;
        if (cloud_x < 20)
            fade();
    }

    public void draw(Canvas c) {
        Paint p = new Paint();
        p.setAlpha(alpha);
        c.drawBitmap(pic, cloud_x, cloud_y, p);
    }

    public void fade() {
        alpha -= 20;
        if (alpha < 20)
            born();
    }

}