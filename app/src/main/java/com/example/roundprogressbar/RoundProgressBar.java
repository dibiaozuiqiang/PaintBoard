package com.example.roundprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.victor.paintboard.R;

/**
 * Created by Victor on 2016/10/17.
 */
public class RoundProgressBar extends View{

    private final static String TAG = "RoundProgressBar";

    private Paint paint ;
    private int roundColor;
    private int roundProgressColor;
    private int textColor;
    private float textSize;
    private float roundWidth;
    private int max;
    private float progress;
    private boolean textIsDisplayable;
    private int style;
    private int digree1 = 0;
    private String time ="";
    private int lastTime = 0;
    private int pointWidth = 4;
    public static final int TIME_TICKER = 0;
    public static final int PROGRESS = 1;
    private float progressStep = (float) 0.1;

    public RoundProgressBar(Context context) {
        this(context,null);
    }

    public RoundProgressBar(Context context,AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RoundProgressBar(Context context,AttributeSet attrs, int defStyle) {
        super(context,attrs,defStyle);
        // TODO Auto-generated constructor stub
        init(context,attrs);
        //tickerHandler.sendMessageDelayed(tickerHandler.obtainMessage(1), 1000);
    }

    private void init(Context context, AttributeSet attrs) {
        // TODO Auto-generated method stub
        paint =new Paint();
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        roundColor = mTypeArray.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = mTypeArray.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
        textColor = mTypeArray.getColor(R.styleable.RoundProgressBar_textColor,Color.GREEN);
        textSize = mTypeArray.getDimension(R.styleable.RoundProgressBar_textSize, 15);
        roundWidth = mTypeArray.getDimension(R.styleable.RoundProgressBar_roundWidth,5);
        max = mTypeArray.getInteger(R.styleable.RoundProgressBar_max, 60);
        textIsDisplayable = mTypeArray.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, true);
        style = mTypeArray.getInt(R.styleable.RoundProgressBar_style, 0);
        progress = 0.0f;
        mTypeArray.recycle();
    }

//  Handler tickerHandler = new Handler(){
//      @Override
//      public void handleMessage(Message msg) {
//      // TODO Auto-generated method stub
//          super.handleMessage(msg);
//          switch(msg.what){
//              case 1:
//              invalidate();
//              break;
//              case 2:
//              invalidate();
//              break;
//          }
//      }
//  };

    @Override
    protected void onDraw(Canvas canvas){
        Log.i(TAG, "RoundProgressBar--onDraw");
        int centre = getWidth()/2;
        int padding = 10;
        int radius = (int)(centre-roundWidth/2)-padding;
        Rect rect = new Rect();

        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(roundWidth);
        paint.setAntiAlias(true);
        paint.setTextSize(textSize);
        paint.setAlpha(180);


        switch(style){
            case TIME_TICKER:
            {
                float tl = paint.measureText(time)+padding;
                float arc = (float)Math.asin(tl/2/radius);
                float arc_b = (float)Math.toDegrees(arc);
                RectF oval_1 = new RectF(centre-radius,centre-radius,centre+radius,centre+radius);
                //canvas.drawArc(oval_1, (-90+arc_b), (360-2*arc_b), false, paint);
                canvas.drawArc(oval_1, 0, 360, false, paint);
                //paint.setStrokeWidth(roundWidth);
                paint.setColor(roundProgressColor);
                //paint.setAlpha(0);
                if(lastTime == (int)progress)
                    progress += progressStep;
                if(progress > 100)
                    progress = 0;
                RectF oval = new RectF(centre-radius,centre-radius,centre+radius,centre+radius);
                //canvas.drawArc(oval, (-90+arc_b), (360-2*arc_b)*progress/max, false, paint);
                canvas.drawArc(oval, -90, 270*progress/max, false, paint);
                paint.setStrokeWidth(0);
                paint.setColor(textColor);
                paint.setTextSize(textSize);
                paint.setTypeface(Typeface.DEFAULT_BOLD);
                float textWidth = paint.measureText(time);
                paint.getTextBounds(time, 0, time.length(), rect);
                int textheight = rect.height();
                canvas.drawText(time, centre-textWidth/2,centre+textheight/2,paint);
                this.lastTime = (int)progress;
                //tickerHandler.sendMessageDelayed(tickerHandler.obtainMessage(1), 100);
                invalidate();
                break;
            }
            case PROGRESS:
            {
                int x1,y1;
                canvas.drawCircle(centre, centre, radius, paint);
                if (digree1 > 360)
                    digree1 = 0;
                else if(digree1>335)
                    digree1 +=3;
                else if(digree1>305)
                    digree1+=2;
                else if(digree1 >270)
                    digree1 +=1;
                else if(digree1>225)
                    digree1 +=3;
                else if(digree1 >180)
                    digree1 +=4;
                else if(digree1>135 )
                    digree1 +=7;
                else if(digree1 >90)
                    digree1 +=10;
                else if(digree1>45)
                    digree1 +=10;
                else
                    digree1 +=7;
                x1 =(int)(centre+radius*Math.cos(digree1*Math.PI/180));
                y1 = (int)(centre+radius*Math.sin(digree1*Math.PI/180));
                paint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(x1, y1, roundWidth+pointWidth, paint);
                invalidate();
                break;
            }
        }
    }

    public synchronized void setTime(String time,int progress){
        this.time = time;
        this.progress = progress;
    }

    public synchronized void setPointWidth(int pointWidth){
        this.pointWidth = pointWidth;
    }

    public synchronized void setStyle(int style){
        this.style = style;
    }

    public synchronized int getMax(){
        return max;
    }
    public synchronized void setMax(int max){
        if(max<0){
            throw new IllegalArgumentException("max not less than 0");
        }
        this.max = max;
    }
    public synchronized float getProgress(){
        return progress;
    }
    public synchronized void setProgress(int progress){
        if(progress<0){
            throw new IllegalArgumentException("progress not less than 0");
        }
        if(progress>max){
            progress = max;
        }
        if(progress<=max){
            this.progress = progress;
            postInvalidate();
        }
    }
    public int getCricelColor(){
        return roundColor;
    }
    public int getCricelProgressColor(){
        return roundProgressColor;
    }
    public void setCircleProgressColor(int cricleProgressColor){
        this.roundProgressColor = cricleProgressColor;
    }
    public int getTextColor(){
        return textColor;
    }
    public void setTextColor(int textColor){
        this.textColor = textColor;
    }
    public float getTextSize(){
        return textSize;
    }
    public void setTextSize(float textSize){
        this.textSize = textSize;
    }
    public void setProgressStep(float progressStep){
        this.progressStep = progressStep;
    }

    public float getRoundWidth(){
        return roundWidth;
    }
    public void setRoundWidth(float roundWidth){
        this.roundWidth = roundWidth;
    }
}
