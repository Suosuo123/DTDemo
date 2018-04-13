package com.dhgate.dt.demo.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import com.dhgate.dt.demo.R;

/**
 * Created by flora on 2018/4/13.
 */

public class SplashActivity extends BaseActivity {

    private final int SPLASH_DISPLAY_LENGHT = 1500;
     private Handler handler;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate() {
        super.onCreate();

        fullScreen();

        handler = new Handler();
        // 延迟SPLASH_DISPLAY_LENGHT时间然后跳转到MainActivity
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MyAccountActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGHT);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return  true;
        }
        return  super.onKeyDown(keyCode, event);
    }
}
