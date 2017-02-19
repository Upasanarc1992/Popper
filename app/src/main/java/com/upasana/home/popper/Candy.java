package com.upasana.home.popper;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.SoundPool;

public class Candy {

    Resources resource = MainActivity.resource;
    Context context = MainActivity.context;
    int screen_width = MainActivity.screen_width;
    int screen_height = MainActivity.screen_height;
    float sreen_dpi = MainActivity.screen_dpi;

    Bitmap pic, img;
    int candy_x, candy_y;
    int speed_y;
    int is_born;
    int danger;
    float scale;

    Candy(int image,int danger) {
        pic = BitmapFactory.decodeResource(resource, image);
        img = pic;
        is_born = 0;
        this.danger=danger;

        scale=Float.valueOf(screen_width)/Float.valueOf(6*img.getWidth());


    }

    public void born(int x, int y) {
        pic=Bitmap.createScaledBitmap(img,(int)(scale*img.getWidth()),(int)(scale*img.getHeight()),true);

        candy_x = x;                                               //Initital x position
        candy_y = y;                                               //Initial y position
        speed_y = (int) (3 + Math.random() * 5);
        is_born = 1;
    }

    public void move() {
        if (is_born == 1) {
            candy_y += speed_y;
            if (candy_y > screen_height)
                is_born = 0;
        }

    }

    public void poop() {
        is_born=0;
        candy_y = screen_height+20;
        }


    public void draw(Canvas c) {
        if (is_born == 1) {

            Paint p = new Paint();
            c.drawBitmap(pic, candy_x, candy_y, p);
        }
    }

    public void clean()
    {
        if(pic!=null)
        {
            pic.recycle();
            pic=null;
        }
    }


}
