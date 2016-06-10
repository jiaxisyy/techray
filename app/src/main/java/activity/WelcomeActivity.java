package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hitek.serial.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by shuang.xiang on 2016/6/9.
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);
        //休息两秒进入主界面

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //进入主界面
                enterMainActivity();
            }
        };
        timer.schedule(task, 2000);

    }

    private void enterMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        //跳转到主界面
        startActivity(intent);
        //关闭界面
        finish();

    }
}
