package com.example.roundprogressbar;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.paintboard.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class FindPc extends SwipeBackActivity {

    private TextView findpc_pic;
    String gifpath1 = "file:///android_asset/find1.gif";
    String gifpath2 = "file:///android_asset/find2.gif";
    String gifpath3 = "file:///android_asset/find3.gif";
    String gifpath4 = "file:///android_asset/find4.gif";
    private String[] imgs =new String[]{gifpath1,gifpath2,gifpath3,gifpath4};
    String data1 = "<HTML><Div align=\"center\" margin=\"0px\"><BODY style=\"margin: 0px;\"/><IMG width=\"328\" height=\"260\" src=\"" + gifpath1 + "\" margin=\"0px\"/></Div>";
    String data2 = "<HTML><Div align=\"center\" margin=\"0px\"><BODY style=\"margin: 0px;\"/><IMG width=\"328\" height=\"260\" src=\"" + gifpath2 + "\" margin=\"0px\"/></Div>";
    String data3 = "<HTML><Div align=\"center\" margin=\"0px\"><BODY style=\"margin: 0px;\"/><IMG width=\"328\" height=\"260\" src=\"" + gifpath3 + "\" margin=\"0px\"/></Div>";
    String data4 = "<HTML><Div align=\"center\" margin=\"0px\"><BODY style=\"margin: 0px;\"/><IMG width=\"328\" height=\"260\" src=\"" + gifpath4 + "\" margin=\"0px\"/></Div>";
    private String[] data = new String[]{data1,data2,data3,data4};
    private ImageView imageView,backView;
    private WebView webView;
    private TextView text1;
    int currentImg = 0;
    int lp = 0;
    final int RIGHT = 0;
    final int LEFT = 1;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pc);

        gestureDetector = new GestureDetector(FindPc.this,onGestureListener);
        backView = (ImageView)findViewById(R.id.main_back);
        text1 = (TextView)findViewById(R.id.text1);
        webView = (WebView)findViewById(R.id.webView);
        findpc_pic = (TextView)findViewById(R.id.findpc_pic);
        findpc_pic.bringToFront();

        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);


        webView.loadDataWithBaseURL(imgs[0], data[0], "text/html", "utf-8", null);
        backView.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View view){
                FindPc.this.finish();
            }
        });

    }
    protected void settext(){
        switch (lp){
            case 0:
                text1.setText(R.string.pc_text1);
                findpc_pic.setText("1/4");
                break;
            case 1:
                text1.setText(R.string.pc_text2);
                findpc_pic.setText("2/4");
                break;
            case 2:
                text1.setText(R.string.pc_text3);
                findpc_pic.setText("3/4");
                break;
            case 3:
                text1.setText(R.string.pc_text4);
                findpc_pic.setText("4/4");
                break;
        }
    }
    private GestureDetector.OnGestureListener onGestureListener =
            new GestureDetector.SimpleOnGestureListener(){
                @Override
            public boolean onFling(MotionEvent e1,MotionEvent e2,float velocityX,float velocityY){
                    float x = e2.getX()-e1.getX();
                    float y = e2.getY()-e2.getY();

                    if (x>0){
                        doResult(RIGHT);
                    }else if(x<0){
                       doResult(LEFT);
                    }
                    return true;
                }
            };
    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        webView.onTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }
    public void doResult(int action){
        switch (action){
            case RIGHT:
                if (currentImg > 0) {
                    currentImg -=1;
                    webView.loadDataWithBaseURL(imgs[currentImg % imgs.length],data[currentImg%data.length],"text/html","utf-8",null);
                    lp -=1;
                    settext();
                } else {
                    webView.loadDataWithBaseURL(imgs[0], data[0], "text/html", "utf-8", null);
                }

                break;
            case LEFT:
                if (currentImg < 3) {
                    currentImg +=1;
                    webView.loadDataWithBaseURL(imgs[currentImg%imgs.length],data[currentImg%data.length],"text/html","utf-8",null);
                    lp +=1;
                    settext();
                } else {
                    webView.loadDataWithBaseURL(imgs[3],data[3],"text/html","utf-8",null);
                }

                break;
        }
    }

}
