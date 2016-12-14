package com.victor.paintboard;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.roundprogressbar.FindPc;
import com.example.roundprogressbar.useStep;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private long exitTime;
    private LinearLayout main_ll_xunlian,main_ll_pc,main_ll_help;
    private ImageView namelogo;
    private MediaPlayer completed = new MediaPlayer();
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_ll_xunlian = (LinearLayout)findViewById(R.id.main_xunlian);
        main_ll_pc = (LinearLayout)findViewById(R.id.main_chaxun);
        main_ll_help = (LinearLayout)findViewById(R.id.main_help);
        namelogo = (ImageView)findViewById(R.id.namelogo);
        completed = MediaPlayer.create(this,R.raw.completed);

        languageSetting(Locale.getDefault().getLanguage());
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        main_ll_help.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, useStep.class);
                startActivityForResult(intent,3);
            }
        });
        main_ll_xunlian.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,1);
            }
        });
        main_ll_pc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, FindPc.class);
                startActivityForResult(intent,2);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), R.string.back_again, Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void languageSetting(String flag){
        if (flag.equals("zh")){
           namelogo.setImageResource(R.mipmap.namelogo2x);
        }else {
            namelogo.setImageResource(R.mipmap.en_namelogo2x);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (completed!=null){
            completed.stop();
            completed.release();
        }
    }
    public class MyViewPagerAdpter extends PagerAdapter{
        @Override
        public int getCount(){
            return viewList.size();
        }
        @Override
        public void destroyItem(ViewGroup container,int position,Object object){
            container.removeView(viewList.get(position));
        }
        @Override
        public Object instantiateItem(ViewGroup container,int position){
            container.addView(viewList.get(position));

            return viewList.get(position);
        }
        @Override
        public boolean isViewFromObject(View arg0,Object arg1){
            return arg0 == arg1;
        }

    }
}
