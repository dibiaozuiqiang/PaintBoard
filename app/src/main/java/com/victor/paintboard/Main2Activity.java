package com.victor.paintboard;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.roundprogressbar.Pickerview;
import com.example.roundprogressbar.RoundProgressBar;
import com.example.roundprogressbar.RoundProgressBar01;
import com.example.roundprogressbar.TimerTextView;

import java.util.ArrayList;
import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class Main2Activity extends SwipeBackActivity {

    private final static String TAG = "MainActivity";

    private TimerTextView timerTextView;
    private ImageView xl_back, xl_set, xl_start, xl_pause;
    private TextView xl_qx, xl_qd, cs_text, ss_b_text, fs_b_text, cs_b_text;
    private LinearLayout xl_qq, xl_sp, xl_pickerview, xl_b_view;
    private Pickerview fs_pv, cs_pv, second_pv;
    public long long_second = 30, long_fs = 30, long_cs = 30, long_timeS, long_timeM, cs;
    private int i = 0;
    private String j;

    private RoundProgressBar01 roundProgressBar_01 = null;
    private RoundProgressBar roundProgressBar_02 = null;
    private RoundProgressBar roundProgressBar_03 = null;
    private int TIME_TICKER = 0;
    private int PROGRESS = 1;

    private int time = 0;
    private int progress = 0;
    private final int UPDATE_UI_01 = 1;
    private final int UPDATE_UI_02 = 2;

    private MediaPlayer completed = new MediaPlayer();
    private MediaPlayer relax = new MediaPlayer();
    private MediaPlayer flex = new MediaPlayer();
    private MediaPlayer contract = new MediaPlayer();
    private MediaPlayer contract1 = new MediaPlayer();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);

            switch (msg.what) {
                case UPDATE_UI_01:
                    Log.i(TAG, "UPDATE_UI_01");
                    progress++;
                    long lcs = long_cs-(progress/cs);
                    if (lcs<10){
                        cs_text.setText("0"+lcs);
                    }else {
                        cs_text.setText(Long.toString(lcs));
                    }
                    if ((progress%(long_second+long_fs)==0)&&(progress<roundProgressBar_01.getMax())){
                        contract.start();
                    }else if (progress%(long_second+long_fs)==long_second){
                        contract1.start();
                    }
                    if (progress >= roundProgressBar_01.getMax()) {
                        progress = roundProgressBar_01.getMax();
                        completed.start();
                        xl_pause.setVisibility(View.GONE);
                        xl_start.setVisibility(View.VISIBLE);
                        timerTextView.stopRun();
                        handler.sendEmptyMessage(UPDATE_UI_02);

                    }else {
                    handler.sendEmptyMessageDelayed(UPDATE_UI_01, 1000);
                    }
                    updateUIRoundProgressBar_01(progress);
                    break;
                case UPDATE_UI_02:
                    Log.i(TAG, "UPDATE_UI_02");
                    handler.removeMessages(UPDATE_UI_01);
                    // progress = 0;

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        second_pv = (Pickerview) findViewById(R.id.second_view);
        fs_pv = (Pickerview) findViewById(R.id.fs_view);
        cs_pv = (Pickerview) findViewById(R.id.cs_view);
        xl_back = (ImageView) findViewById(R.id.xl_back);
        xl_set = (ImageView) findViewById(R.id.xl_set);
        xl_qq = (LinearLayout) findViewById(R.id.xl_qq);
        xl_sp = (LinearLayout) findViewById(R.id.xl_sp);
        xl_b_view = (LinearLayout) findViewById(R.id.xl_b_view);
        xl_pickerview = (LinearLayout) findViewById(R.id.xl_pickerview);
        xl_qx = (TextView) findViewById(R.id.xl_qx);
        xl_start = (ImageView) findViewById(R.id.xl_start);
        xl_pause = (ImageView) findViewById(R.id.xl_pause);
        xl_qd = (TextView) findViewById(R.id.xl_qd);
        cs_text = (TextView) findViewById(R.id.cs_text);
        ss_b_text = (TextView) findViewById(R.id.ss_b_text);
        fs_b_text = (TextView) findViewById(R.id.fs_b_text);
        cs_b_text = (TextView) findViewById(R.id.cs_b_text);
        completed = MediaPlayer.create(this,R.raw.completed);
        relax = MediaPlayer.create(this,R.raw.relax);
        flex = MediaPlayer.create(this,R.raw.flex);
        contract = MediaPlayer.create(this,R.raw.contract);
        contract1 = MediaPlayer.create(this,R.raw.contract1);

        ss_b_text.setText("0");
        fs_b_text.setText("0");
        cs_b_text.setText("0");

        List<String> data = new ArrayList<String>();

        for (i = 0; i < 60; i++) {
            j = Integer.toString(i);
            data.add(j);
        }

        second_pv.setData(data);
        fs_pv.setData(data);
        cs_pv.setData(data);


//        xl_start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xl_start.setVisibility(View.GONE);
//                xl_pause.setVisibility(View.VISIBLE);
//
//            }
//        });
        xl_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xl_qq.setVisibility(View.VISIBLE);
                xl_sp.setVisibility(View.GONE);
                xl_b_view.setVisibility(View.GONE);
                xl_pickerview.setVisibility(View.VISIBLE);
            }
        });

//        xl_qd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                xl_qq.setVisibility(View.GONE);
//                xl_pickerview.setVisibility(View.GONE);
//                xl_sp.setVisibility(View.VISIBLE);
//                long_timeM = 0;
//                long_timeS = 0;
//                long_timeS = long_fs + long_second;
//                if (long_timeS>59){
//                    long_timeM = long_timeS/60;
//                    long_timeS = long_timeS-(long_timeM*60);
//                }
//            }
//        });
        xl_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xl_qq.setVisibility(View.GONE);
                xl_sp.setVisibility(View.VISIBLE);
                xl_b_view.setVisibility(View.VISIBLE);
                xl_pickerview.setVisibility(View.GONE);
            }
        });
        xl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Main2Activity.this.finish();
            }
        });

        cs_pv.setOnSelectListener(new Pickerview.onSelectListener() {
            @Override
            public void onSelect(String text) {
                long_cs = Long.parseLong(text);
            }
        });
        fs_pv.setOnSelectListener(new Pickerview.onSelectListener() {
            @Override
            public void onSelect(String text) {
                long_fs = Long.parseLong(text);
            }
        });

        second_pv.setOnSelectListener(new Pickerview.onSelectListener() {
            @Override
            public void onSelect(String text) {
                long_second = Long.parseLong(text);
             //   Toast.makeText(Main2Activity.this, "选择了" + text + "秒", Toast.LENGTH_SHORT).show();

            }
        });
//        minute_pv.setOnSelectListener(new Pickerview.onSelectListener() {
//
//            @Override
//            public void onSelect(String text) {
//                long_minute = Long.parseLong(text);
//                Toast.makeText(Main2Activity.this, "选择了 " + text + "分",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });

//        minute_pv.setSelected(0);// 设置默认值


        init();
        //handler.sendEmptyMessageDelayed(UPDATE_UI_01, 1000);

        long[] times = {0, 1, 0, 0};
        timerTextView = (TimerTextView) findViewById(R.id.timer_text_view);
        timerTextView.setTimes(times);


        Button startBtn = (Button) findViewById(R.id.main_start_btn);
        Button stopBtn = (Button) findViewById(R.id.main_stop_btn);
//        Button continueBtn = (Button)findViewById(R.id.main_continue_btn);

        roundProgressBar_01.setMax((int) (timerTextView.msecond + timerTextView.mmin * 60 + timerTextView.mhour * 360 + timerTextView.mday * 24));
//        continueBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                if (!timerTextView.isRun()){
//                    timerTextView.beginRun();
//
//                    handler.sendEmptyMessageDelayed(UPDATE_UI_01,1000);
//                }
//            }
//        });
        xl_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xl_qq.setVisibility(View.GONE);
                xl_pickerview.setVisibility(View.GONE);
                xl_sp.setVisibility(View.VISIBLE);
                xl_b_view.setVisibility(View.VISIBLE);
                ss_b_text.setText(Long.toString(long_second));
                fs_b_text.setText(Long.toString(long_fs));
                cs_b_text.setText(Long.toString(long_cs));
                cs = 0;
                long_timeM = 0;
                long_timeS = 0;
                long_timeS = long_fs + long_second;
                cs = long_timeS;
                long_timeS = (long_fs + long_second) * long_cs;
                if (long_timeS > 59) {
                    long_timeM = long_timeS / 60;
                    long_timeS = long_timeS - (long_timeM * 60);
                }
                long[] times = {0, 0, long_timeM, long_timeS};
                progress = 0;
                timerTextView.setTimes(times);
                roundProgressBar_01.setMax((int) (timerTextView.msecond + timerTextView.mmin * 60 + timerTextView.mhour * 360 + timerTextView.mday * 24-1));
            }

        });
        xl_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long[] times = {0, 0, long_timeM, long_timeS};
                if (progress>=roundProgressBar_01.getMax()){
                    progress = 0;
                    timerTextView.setTimes(times);
                }
                if (!timerTextView.isRun()) {
                    timerTextView.beginRun();

                    handler.sendEmptyMessageDelayed(UPDATE_UI_01, 1000);
                    xl_pause.setVisibility(View.VISIBLE);
                    xl_start.setVisibility(View.GONE);
                }
                contract.start();
            }
        });
        xl_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timerTextView.isRun()) {
                    timerTextView.stopRun();
                    handler.sendEmptyMessage(UPDATE_UI_02);
                    xl_pause.setVisibility(View.GONE);
                    xl_start.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void init() {
        // TODO Auto-generated method stub
        roundProgressBar_01 = (RoundProgressBar01) findViewById(R.id.roundProgressBar_01);
        roundProgressBar_01.setStyle(TIME_TICKER);
        roundProgressBar_01.setProgress(0);
        progress = 0;

//        roundProgressBar_02 = (RoundProgressBar)findViewById(R.id.roundProgressBar_02);
//        roundProgressBar_02.setStyle(PROGRESS);

//        roundProgressBar_03 = (RoundProgressBar)findViewById(R.id.roundProgressBar_03);
//        roundProgressBar_03.setStyle(TIME_TICKER);
//        roundProgressBar_03.setTime("test:"+progress, progress);
    }

    //    private void updateUI_02(int time){
//        //roundProgressBar_02.setTime("test"+time,time);
//    }
//    private void updateUI_03(String time,int progress){
//        roundProgressBar_03.setTime("test"+time, progress);
//    }
    private void updateUIRoundProgressBar_01(int progress) {
        roundProgressBar_01.setProgress(progress);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (completed!=null){
            completed.stop();
            completed.release();
        }
    }
}