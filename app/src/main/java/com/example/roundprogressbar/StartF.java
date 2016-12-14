package com.example.roundprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.victor.paintboard.MainActivity;
import com.victor.paintboard.R;



public class StartF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start_f);
        Handler handler = new Handler();
        handler.postDelayed(new Start(),3000);
    }
    private class  Start implements Runnable{

        @Override
        public void run() {
            Intent intent=new Intent();
            intent.setClass(StartF.this,MainActivity.class);
            startActivity(intent);
            StartF.this.finish();
        }
    }
}
