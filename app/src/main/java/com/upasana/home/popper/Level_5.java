package com.upasana.home.popper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by home on 1/18/2017.
 */

public class Level_5 extends MainActivity{

    @Override
    public void initialize()
    {
        score=0;
        life=2;

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg5);
        bg = Bitmap.createScaledBitmap(bg, screen_width, screen_height, true);

        cloud = new Clouds[4];
        baloon = new Baloons[12];
        candy = new Candy[8];
    }
}
