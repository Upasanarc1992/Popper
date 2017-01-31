package com.upasana.home.popper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by home on 1/18/2017.
 */

public class Level_6 extends MainActivity{

    @Override
    public void initialize()
    {
        score=0;
        life=1;

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg6);
        bg = Bitmap.createScaledBitmap(bg, screen_width, screen_height, true);

        cloud = new Clouds[6];
        baloon = new Baloons[15];
        candy = new Candy[10];
    }
}
