package activity;



import com.hitek.serial.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;


/**
 *  ������
 * Created by zuheng.lv on 2016/4/26.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnLongClickListener {

    private Button main_btn_monitor,main_btn_parameter,main_btn_status,main_btn_alarm,main_btn_pruessure,main_btn_analog,main_btn_time,main_btn_valve,main_btn_flow;
    private Intent intent;
    private ImageView logo_test;
    private int flag=0;
    private LinearLayout parent_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initView();
//        startService(new Intent(this,Services.class));
    }


    /**�ؼ���ʼ��*/
    public void initView(){
    	parent_layout = (LinearLayout)findViewById(R.id.parent_layout);
        main_btn_monitor = (Button)findViewById(R.id.main_btn_monitor);
        main_btn_parameter = (Button)findViewById(R.id.main_btn_parameter);
        main_btn_status = (Button)findViewById(R.id.main_btn_status);
        main_btn_alarm = (Button)findViewById(R.id.main_btn_alarm);
        main_btn_pruessure = (Button)findViewById(R.id.main_btn_pruessure);
        main_btn_analog = (Button)findViewById(R.id.main_btn_analog);
        main_btn_time = (Button)findViewById(R.id.main_btn_time);
        main_btn_valve = (Button)findViewById(R.id.main_btn_valve);
        main_btn_flow = (Button)findViewById(R.id.main_btn_flow);
       logo_test =(ImageView) findViewById(R.id.logo_test);
        main_btn_monitor.setOnClickListener(this);
        main_btn_parameter.setOnClickListener(this);
        main_btn_status.setOnClickListener(this);
        main_btn_alarm.setOnClickListener(this);
        main_btn_pruessure.setOnClickListener(this);
        main_btn_analog.setOnClickListener(this);
        main_btn_time.setOnClickListener(this);
        main_btn_valve.setOnClickListener(this);
        main_btn_flow.setOnClickListener(this);
       logo_test.setOnClickListener(this);
       logo_test.setOnLongClickListener(this);
       parent_layout.setOnClickListener(this);
    }

    /**������������*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_btn_monitor:
//                intent = new Intent(MainActivity.this,MonitorActivity.class);
//                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_parameter:
                intent = new Intent(MainActivity.this,ParameterActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_status:
                intent = new Intent(MainActivity.this,AMachineStatusActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_alarm:
                intent = new Intent(MainActivity.this,AlarmActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_pruessure:
                intent = new Intent(MainActivity.this,PressureActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_analog:
                intent = new Intent(MainActivity.this,AnalogActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_time:
                intent = new Intent(MainActivity.this,ATimingActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_valve:
                intent = new Intent(MainActivity.this,AValveActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.main_btn_flow:
                intent = new Intent(MainActivity.this,FlowActivity.class);
                startActivity(intent);
//                finish();
                break;
            case R.id.logo_test:
            	flag++;
           	break;
           	case R.id.parent_layout:
           		flag=0;
           		break;
        }
    }


	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.logo_test:
			flag=flag+3;
			if(flag>=8){
//		    	intent = new Intent(MainActivity.this,TestActivity.class);
	               startActivity(intent);
	               flag=0;
			}
	               break;
		}
		return false;
	}



}
