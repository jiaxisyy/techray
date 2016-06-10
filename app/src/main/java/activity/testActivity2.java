package activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.hitek.serial.R;

import fragment.AnimationFragment;
import fragment.MonitorFragment;
import service.Services;
import utils.Utils;

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
        Utils.replace(getSupportFragmentManager(), R.id.main_fragment, MonitorFragment.class);
        startService(new Intent(this, Services.class));
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment, MonitorFragment.class);
                break;
            case R.id.main_btn2:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment, ATimingActivity.class);
                break;
            case R.id.main_btn3:
                Utils.replace(getSupportFragmentManager(),R.id.main_fragment,testActivity3.class);
                break;
        }
    }
}
