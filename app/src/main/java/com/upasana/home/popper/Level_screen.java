package com.upasana.home.popper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Level_screen extends AppCompatActivity implements View.OnClickListener{

    Button b1,b2,b3,b4,b5,b6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//========= SETTING FULLSCREEN ACTIVITY ============================================================================================

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        b1=(Button)findViewById(R.id.l1);
        b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.l2);
        b2.setOnClickListener(this);
        b3=(Button)findViewById(R.id.l3);
        b3.setOnClickListener(this);
        b4=(Button)findViewById(R.id.l4);
        b4.setOnClickListener(this);
        b5=(Button)findViewById(R.id.l5);
        b5.setOnClickListener(this);
        b6=(Button)findViewById(R.id.l6);
        b6.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {

        if(v.equals(b1))
        {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        if(v.equals(b2))
        {
            Intent intent = new Intent(this, Level_2.class);
            this.startActivity(intent);

        }

        if(v.equals(b3))
        {
            Intent intent = new Intent(this, Level_3.class);
            this.startActivity(intent);

        }


        if(v.equals(b4))
        {
            Intent intent = new Intent(this, Level_4.class);
            this.startActivity(intent);

        }


        if(v.equals(b5))
        {
            Intent intent = new Intent(this, Level_5.class);
            this.startActivity(intent);

        }


        if(v.equals(b6))
        {
            Intent intent = new Intent(this, Level_6.class);
            this.startActivity(intent);

        }



    }
}
