package com.upasana.home.popper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Level_screen extends AppCompatActivity {

    public static int screen_width, screen_height;


    Bitmap level_btn[], background;
    int b_x, b_y;
    int b1_x, b2_x;
    int b1_y;
    SoundPool sp;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//========= SETTING FULLSCREEN ACTIVITY ============================================================================================

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new Level_screen.myview(this));

//========= GETTING SCREEN RRESOLUTION & CONTEXT ====================================================================================

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        screen_width = displayMetrics.widthPixels;
        screen_height = displayMetrics.heightPixels;

//========= SETTING BITMAPS AND BUTTON POSITIONS ====================================================================================


        level_btn=new Bitmap[6];

        if(background!=null) {
            background.recycle();
            background = null;
        }

        background = BitmapFactory.decodeResource(getResources(), R.drawable.l1);
        background = StartUp.getResizedBitmap(background,screen_height, screen_width);

        level_btn[0] = BitmapFactory.decodeResource(getResources(), R.drawable.l11);
        level_btn[1] = BitmapFactory.decodeResource(getResources(), R.drawable.l2);
        level_btn[2] = BitmapFactory.decodeResource(getResources(), R.drawable.l3);
        level_btn[3] = BitmapFactory.decodeResource(getResources(), R.drawable.l4);
        level_btn[4] = BitmapFactory.decodeResource(getResources(), R.drawable.l5);
        level_btn[5] = BitmapFactory.decodeResource(getResources(), R.drawable.l6);


        for (int i = 0; i < level_btn.length; i++)
            level_btn[i] = Bitmap.createScaledBitmap(level_btn[i], (int) (2 * screen_width / 9), (int) (2* screen_height / 15), true);

        b_x = (int) (screen_width / 9);
        b1_x=b_x+(int)(5*screen_width/18);
        b2_x=b1_x+(int)(5*screen_width/18);
        b_y = (int) (12 * screen_height / 30);
        b1_y= (int) (17 * screen_height / 30);


        id=R.raw.press;
        sp = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        id=sp.load(this,id,1);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pressX = (int) event.getX();
        int pressY = (int) event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {

            if (pressX > b_x &&
                    pressX < b_x + level_btn[0].getWidth() &&
                    pressY > b_y &&
                    pressY < b_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            if (pressX > b1_x &&
                    pressX < b1_x + level_btn[0].getWidth() &&
                    pressY > b_y &&
                    pressY < b_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, Level_2.class);
                startActivity(intent);
                finish();
            }
            if (pressX > b2_x &&
                    pressX < b2_x + level_btn[0].getWidth() &&
                    pressY > b_y &&
                    pressY < b_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, Level_3.class);
                startActivity(intent);
                finish();
            }
            if (pressX > b_x &&
                    pressX < b_x + level_btn[0].getWidth() &&
                    pressY > b1_y &&
                    pressY < b1_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, Level_4.class);
                startActivity(intent);
                finish();
            }
            if (pressX > b1_x &&
                    pressX < b1_x + level_btn[0].getWidth() &&
                    pressY > b1_y &&
                    pressY < b1_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, Level_5.class);
                startActivity(intent);
                finish();
            }
            if (pressX > b2_x &&
                    pressX < b2_x + level_btn[0].getWidth() &&
                    pressY > b1_y &&
                    pressY < b1_y + level_btn[0].getHeight()
                    ) {
                sp.play(id, 1, 1, 0, 0, 1);
                Intent intent = new Intent(this, Level_6.class);
                startActivity(intent);
                finish();
            }



        }
        return false;
    }


//============== CLICKING PLAW NOW BUTTON ==============================================================================================

    class myview extends View {


        public myview(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {

            canvas.drawBitmap(background, 0, 0, null);
            canvas.drawBitmap(level_btn[0], b_x, b_y, null);
            canvas.drawBitmap(level_btn[1], b1_x, b_y, null);
            canvas.drawBitmap(level_btn[2], b2_x, b_y, null);
            canvas.drawBitmap(level_btn[3], b_x, b1_y, null);
            canvas.drawBitmap(level_btn[4], b1_x, b1_y, null);
            canvas.drawBitmap(level_btn[5], b2_x, b1_y, null);

        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, StartUp.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void finish() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.finish();
        if(background!=null)
        {
            background.recycle();
            background=null;
        }
    }
}
