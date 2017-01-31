package com.upasana.home.popper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by home on 1/18/2017.
 */

public class Level_4 extends MainActivity{

    @Override
    public void initialize()
    {
        score=0;
        life=4;

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg3);
        bg = Bitmap.createScaledBitmap(bg, screen_width, screen_height, true);

        cloud = new Clouds[4];
        baloon = new Baloons[10];
        candy = new Candy[6];
    }
}
