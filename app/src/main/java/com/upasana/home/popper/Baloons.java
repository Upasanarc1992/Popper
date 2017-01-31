package com.upasana.home.popper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.Toast;

public class Baloons {

    Resources resource = MainActivity.resource;
    Context context = MainActivity.context;
    int screen_width = MainActivity.screen_width;
    int screen_height = MainActivity.screen_height;
    float sreen_dpi = MainActivity.screen_dpi;

    Bitmap pic[]=new Bitmap[3];
    Bitmap img[]=new Bitmap[3];
    int baloon_x, baloon_y;
    int speed_y;
    int alpha, count;
    double scale;
    boolean popped;

    Baloons(int image[]) {
        for (int i = 0; i < image.length; i++) {
            pic[i] = BitmapFactory.decodeResource(resource, image[i]);
            img[i] = pic[i];
             }

        born();
    }

    public void born() {
        double n=5+Math.random()*2;
        scale=Double.valueOf(screen_width)/Double.valueOf(n*img[0].getWidth());

        for (int i = 0; i < img.length; i++) {
            pic[i]=Bitmap.createScaledBitmap(img[i],5+(int)(scale*img[i].getWidth()),5+(int)(scale*img[i].getHeight()),true);
        }
        baloon_x = (int) (10 + screen_width * Math.random() * 0.75);                             //Initial x position
        baloon_y = (int) (screen_height*0.75 - screen_height * Math.random() * 0.3);                  //Initial y position
        speed_y = (int) (3 + Math.random() * 8 * sreen_dpi);
        alpha = 0;
        count = 0;
        popped = false;

    }

    public void move() {
        baloon_y -= speed_y;
        if (alpha < 255)
            alpha += 15;
        if (popped) {
            fade();
            baloon_y += speed_y;
        }
        if (baloon_y < -50 || (popped && alpha < 0))
            born();
    }

    public void fade() {
        alpha -= 30;
        if (alpha < 0)
            born();
    }

    public void draw(Canvas c) {
        Paint p = new Paint();
        p.setAlpha(alpha);
        if (popped && count<2)
            count++;

        c.drawBitmap(pic[count], baloon_x, baloon_y, p);
    }

    public void poop() {
        if(alpha>250)
        popped = true;
    }
}
