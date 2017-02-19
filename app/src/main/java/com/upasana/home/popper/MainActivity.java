package com.upasana.home.popper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public static int screen_width, screen_height;
    public static float screen_dpi;
    public static Context context;
    public static Resources resource;
    public long mLastMove;
    public RefreshHandler mRedrawHandler = new RefreshHandler();
    SoundPool sp;
    MediaPlayer mp;
    Bitmap bg;
    Bitmap gameover;
    int gameover_y;
    Paint paint,p;
    int score;
    int life;
    Clouds cloud[];
    Baloons baloon[];
    Candy candy[];
    int image[] = new int[3];
    int temp[] = new int[3];
    int b_image[][] = new int[6][3];
    int goodies[] = new int[12];
    int sound[] = new int[5];
    int s_pool[] = new int[5];
    int danger;
    boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//========= SETTING FULLSCREEN ACTIVITY ============================================================================================

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(new myview(this));

//========= GETTING SCREEN RRESOLUTION & CONTEXT ====================================================================================

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        screen_width = displayMetrics.widthPixels;
        screen_height = displayMetrics.heightPixels;
        screen_dpi = getResources().getDisplayMetrics().density;
        context = getApplicationContext();
        resource = getResources();
        initialize();
        load_resources();

//========= GAME OVER SCREEN ==================================================================================================

        gameover = BitmapFactory.decodeResource(getResources(), R.drawable.gameover1);
        gameover = Bitmap.createScaledBitmap(gameover, (int) (screen_width / 1.5), (int) (screen_height / 2), true);
        gameover_y = -10;

//========= SETTING MEDIA PLAYERS ==================================================================================================

        mp = MediaPlayer.create(context, R.raw.bg_clip);
        mp.setLooping(true);
        //mp.start();

//========= CHOOSING THE UNITS ======================================================================================================

        for (int i = 0; i < cloud.length; i++)
            cloud[i] = new Clouds(image[(int) (Math.random() * 2)]);

        int j;
        for (int i = 0; i < baloon.length; i++) {
            j = (int) (Math.random() * 6);
            for (int k = 0; k < temp.length; k++)
                temp[k] = b_image[j][k];
            baloon[i] = new Baloons(temp);
        }

        for (int i = 0; i < candy.length; i++) {
            int s = (int) (Math.random() * goodies.length);
            if (s <= 7)
                danger = 0;
            else
                danger = 1;
            candy[i] = new Candy(goodies[s], danger);
        }

        sp = new SoundPool(12, AudioManager.STREAM_MUSIC, 0);
        for (int i = 0; i < 4; i++) {
            s_pool[i] = 0;
            s_pool[i] = sp.load(this, sound[i], 1);

        }

        running=true;
    }

    public void initialize() {
        score = 0;
        life = 10;

        bg = BitmapFactory.decodeResource(getResources(), R.drawable.bg1);
        bg = Bitmap.createScaledBitmap(bg, screen_width, screen_height, true);

        cloud = new Clouds[4];
        baloon = new Baloons[3];
        for(int i=0;i<baloon.length;i++)
            candy[i].clean();
        candy = new Candy[5];
        for(int i=0;i<candy.length;i++)
            candy[i].clean();
    }

    public void load_resources() // TO LOAD ALL RESOURCE FILES
    {
//============== PAINT INITIALIZATION ==============================================================================================
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize((int) (22 * screen_dpi));
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/myfont.TTF");
        paint.setTypeface(font);
        paint.setTextAlign(Paint.Align.RIGHT);

        p = new Paint();
        p.setColor(Color.BLACK);
        p.setAlpha(75);
        p.setTextSize((int) (50 * screen_dpi));
        p.setTypeface(font);
        p.setTextAlign(Paint.Align.CENTER);

//============== CLOUD ==============================================================================================
        image[0] = R.drawable.cloud3;
        image[1] = R.drawable.cloud2;
        image[2] = R.drawable.cloud1;
//=============== BALOON ============================================================================================
        b_image[0][0] = R.drawable.b1;
        b_image[0][1] = R.drawable.bp1;
        b_image[0][2] = R.drawable.bp11;
        b_image[1][0] = R.drawable.b2;
        b_image[1][1] = R.drawable.bp2;
        b_image[1][2] = R.drawable.bp22;
        b_image[2][0] = R.drawable.b3;
        b_image[2][1] = R.drawable.bp3;
        b_image[2][2] = R.drawable.bp33;
        b_image[3][0] = R.drawable.b4;
        b_image[3][1] = R.drawable.bp4;
        b_image[3][2] = R.drawable.bp44;
        b_image[4][0] = R.drawable.b5;
        b_image[4][1] = R.drawable.bp5;
        b_image[4][2] = R.drawable.bp55;
        b_image[5][0] = R.drawable.b6;
        b_image[5][1] = R.drawable.bp6;
        b_image[5][2] = R.drawable.bp66;
//=============== GOODIES    ========================================================================================
        goodies[0] = R.drawable.candy1;
        goodies[1] = R.drawable.candy2;
        goodies[2] = R.drawable.candy3;
        goodies[3] = R.drawable.candy4;
        goodies[4] = R.drawable.candy5;
        goodies[5] = R.drawable.candy6;
        goodies[6] = R.drawable.candy7;
        goodies[7] = R.drawable.candy8;
        goodies[8] = R.drawable.bomb1;
        goodies[9] = R.drawable.bomb2;
        goodies[10] = R.drawable.bomb3;
        goodies[11] = R.drawable.bomb4;
//=============== SOUND INITIALIZATION   ========================================================================================
        sound[0] = R.raw.pop;
        sound[1] = R.raw.catch_clip;
        sound[2] = R.raw.bomb;
        sound[3]= R.raw.gameover;
    }

    public void update() {
        long now = System.currentTimeMillis();
        if (now - mLastMove > 50) {
            setContentView(new myview(this));
            mLastMove = now;
        }

        mRedrawHandler.sleep((int) (25 / screen_dpi));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int pressX = (int) event.getX();
        int pressY = (int) event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {


            for (int i = 0; i < baloon.length; i++) {
                if (pressX > baloon[i].baloon_x &&
                        pressX < baloon[i].baloon_x + baloon[i].pic[0].getWidth() &&
                        pressY > baloon[i].baloon_y &&
                        pressY < baloon[i].baloon_y + baloon[i].pic[0].getHeight() && life > 0
                        ) {
                    if (s_pool[0] != 0)
                        sp.play(s_pool[0], 1, 1, 0, 0, 1);
                    baloon[i].poop();
                    score++;

                    if (Math.random() * 20 > 9) {
                        int n = (int) (Math.random() * candy.length);
                        candy[n].born(pressX, pressY);
                    }

                }
            }
            for (int i = 0; i < candy.length; i++) {
                if (pressX > candy[i].candy_x &&
                        pressX < candy[i].candy_x + candy[i].pic.getWidth() &&
                        pressY > candy[i].candy_y &&
                        pressY < candy[i].candy_y + candy[i].pic.getHeight() && life > 0
                        ) {
                    if (candy[i].danger == 0) {
                        sp.play(s_pool[1], 1, 1, 0, 0, 1);
                        score += 10;
                    } else {
                        sp.play(s_pool[2], 1, 1, 0, 0, 1);
                        life--;
                        if(life<=0)
                            sp.play(s_pool[3], 1, 1, 0, 0, 1);

                    }

                    candy[i].poop();
                    break;
                }
            }
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        mp.stop();
        mp.release();
        sp.release();

        Intent intent = new Intent(this, StartUp.class);
        startActivity(intent);
        running=false;
        finish();

    }

    class myview extends View {


        public myview(Context context) {
            super(context);
        }

        @Override
        public void onDraw(Canvas canvas) {
            if(running) {
                canvas.drawBitmap(bg, 0, 0, null);

                for (int i = 0; i < cloud.length; i++) {
                    cloud[i].move();
                    cloud[i].draw(canvas);
                }

                if (life <= 0) {
                    if (gameover_y < screen_height / 3) {
                        gameover_y += 5;
                    }
                    canvas.drawBitmap(gameover, (int) (screen_width / 6), gameover_y, null);
                    canvas.drawText(String.valueOf(score), (int) (screen_width / 2), (int) (gameover_y + screen_height / 3.8), p);
                } else {


                    for (int i = 0; i < candy.length; i++) {
                        candy[i].move();
                        candy[i].draw(canvas);
                    }
                    for (int i = 0; i < baloon.length; i++) {
                        baloon[i].move();
                        baloon[i].draw(canvas);
                    }
                }
                float text_x = screen_width - (20 * screen_dpi);
                float text_y = 60;
                canvas.drawText(String.valueOf(score), text_x, text_y, paint);
                text_y += 20 * screen_dpi;
                canvas.drawText("LIFE      " + life, text_x, text_y, paint);

                update();
            }
        }
    }

    public class RefreshHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            MainActivity.this.update();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }

    }

    @Override
    public void finish() {
        //android.os.Process.killProcess(android.os.Process.myPid());

        super.finish();
        if(bg!=null)
        {
            bg.recycle();
            bg=null;
        }
        for(int i=0;i<candy.length;i++)
        {
            candy[i].clean();
        }

    }

}







