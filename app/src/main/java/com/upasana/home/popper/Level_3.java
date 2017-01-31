package com.upasana.home.popper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by home on 1/18/2017.
 */

public class Level_3 extends MainActivity{

    @Override
    public void initialize()
    {
        score=0;
        life=5;

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg4);
        bg = Bitmap.createScaledBitmap(bg, screen_width, screen_height, true);

        cloud = new Clouds[4];
        baloon = new Baloons[9];
        candy = new Candy[9];
    }
}
