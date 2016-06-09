package activity;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hitek.serial.R;

import bean.Services;
import utils.Utils;
import view.AlarmLamp;

/**
 * Created by zuheng.lv on 2016/6/3.
 */
public class testActivity2 extends FragmentActivity implements View.OnClickListener {

    private AlarmActivity alarmActivity;
    private AMachineStatusActivity aMachineStatusActivity;
    private AnalogActivity analogActivity;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main_layout);
        Button btn1 = (Button) findViewById(R.id.main_btn);
        Button btn2 = (Button) findViewById(R.id.main_btn2);
        Button btn3 = (Button) findViewById(R.id.main_btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        analogActivity = new AnalogActivity();
        alarmActivity = new AlarmActivity();
        aMachineStatusActivity = new AMachineStatusActivity();
        Utils.replace(getSupportFragmentManager(), R.id.main_fragment, AlarmActivity.class);
        startService(new Intent(this, Services.class));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment,MonitorActivity.class);
                break;
            case R.id.main_btn2:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment,AnalogActivity.class);
                break;
            case R.id.main_btn3:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment,testActivity3.class);
                break;
        }
    }
}
