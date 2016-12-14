package com.lazytech.canvasdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Victor on 2016/10/9.
 */
public class PaintBoard extends View {
    public PaintBoard(Context context,AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int width = this.getWidth();
        int height = this.getHeight();

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.rgb(0xe9, 0xe9, 0xe9));
        canvas.drawColor(Color.TRANSPARENT);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF();
        rectF.left =8;
        rectF.top = 8;
        rectF.right =width -8;
        rectF.bottom = height-8;

        canvas.drawArc(rectF, -90, 360, false, paint);
        paint.setColor(Color.rgb(0xf8, 0x60, 0x30));
        canvas.drawArc(rectF, -90, 300, false, paint);

        paint.setStrokeWidth(10);
        String text = 80 +"%";
        int textheight = height / 4;
        paint.setTextSize(textheight);
        int textwidth = (int)paint.measureText(text,0,text.length());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, width / 2 - textwidth / 2, height / 2 + textheight / 2, paint);

        String text1 = "击败了";
        paint.setColor(Color.rgb(0xe9, 0xe9, 0x30));
        paint.setTextSize(textheight*2/3);
        int text1width = (int)paint.measureText(text1,0,text1.length());
        canvas.drawText(text1,width/2-text1width/2,height/3,paint);

    }
}
