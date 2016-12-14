package com.example.roundprogressbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.victor.paintboard.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class useStep extends SwipeBackActivity {
    private ImageView us_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_step);

        us_back = (ImageView)findViewById(R.id.step_back);
        us_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useStep.this.finish();
            }
        });
    }
}
