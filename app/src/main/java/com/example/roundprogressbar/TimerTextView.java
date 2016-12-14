package com.example.roundprogressbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Victor on 2016/10/18.
 */
public class TimerTextView extends TextView implements Runnable {
    public TimerTextView(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    public long mday,mhour,mmin,msecond;
    private boolean run = false;
    private String strmmin,strmsecond;
    public void setTimes(long[] times){
        mday = times[0];
        mhour = times[1];
        mmin = times[2];
        msecond = times[3]+1;
    }
    private void ComputerTime(){
        msecond--;
        if (msecond<0){
            mmin--;
            msecond = 59;
            if (mmin<0){
                mhour--;
                mmin = 59;
                if (mhour<0){
                    mday--;
                    mhour = 23;
                    if (mday<0){
                        mday = 0;
                        mhour = 0;
                        mmin = 0;
                        msecond = 0;
                    }
                }
            }
        }
    }
    public boolean isRun(){
        return run;
    }
    public void run(){
        if (run){
            ComputerTime();
            if (mmin<10){
                strmmin = "0"+mmin;
            }else{
                strmmin = Long.toString(mmin);
            }
            if (msecond<10){
                strmsecond = "0"+msecond;
            }else {
                strmsecond = Long.toString(msecond);
            }
            String strTime = strmmin+":"+strmsecond;
            this.setText(strTime);

            postDelayed(this,1000);
        }else {
            removeCallbacks(this);
        }
    }
    public void beginRun(){
        this.run = true;
        run();
    }
    public void stopRun(){
        this.run = false;
    }
}
