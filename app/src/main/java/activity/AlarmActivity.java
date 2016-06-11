package activity;




import com.hitek.serial.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import utils.Constants;
import popupwindow.Pupwindow;
import utils.ReadAndWrite;

/**
 * 报警设置界面
 * Created by zuheng.lv on 2016/4/26.
 */
public class AlarmActivity extends android.support.v4.app.Fragment implements View.OnClickListener {
    private TextView alarm_et_oxy_max, alarm_et_oxy_min, alarm_et_oxy_flow, alarm_et_oxy_concentration, alarm_et_temp_max, alarm_et_temp_min;
    private Button alarm_btn_back;
    private LinearLayout alarm_layout_parent;
    private Thread myThread;
    private static boolean flag = true;
    private int[] local,str;
    private View view;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**这里写UI更新函数*/
                    if(msg.getData().getString("0")!=null &&!msg.getData().getString("0").isEmpty()){
                        alarm_et_oxy_max.setText(String.valueOf(msg.getData().getFloat("d280")));
                    }
                    if(msg.getData().getString("1")!=null &&!msg.getData().getString("1").isEmpty()){
                        alarm_et_oxy_min.setText(String.valueOf(msg.getData().getFloat("d284")));
                    }
                    if(msg.getData().getString("2")!=null &&!msg.getData().getString("2").isEmpty()){
                        alarm_et_oxy_concentration.setText(String.valueOf(msg.getData().getFloat("d296")));
                    }
                    if(msg.getData().getString("3")!=null &&!msg.getData().getString("3").isEmpty()){
                        alarm_et_oxy_flow.setText(String.valueOf(msg.getData().getFloat("d300")));
                    }
                    if(msg.getData().getString("4")!=null &&!msg.getData().getString("4").isEmpty()){
                        alarm_et_temp_max.setText(String.valueOf(msg.getData().getFloat("d304")));
                    }
                    if(msg.getData().getString("5")!=null &&!msg.getData().getString("5").isEmpty()){
                        alarm_et_temp_min.setText(String.valueOf(msg.getData().getFloat("d308")));
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view  = inflater.inflate(R.layout.alarm_layout,container,false);
        initView();
        initData();
        setData();
        return view;
    }


    /**
     * 控件初始化
     */
    public void initView() {
        //氧气压力上限
        alarm_et_oxy_max = (TextView) view.findViewById(R.id.alarm_et_oxy_max);
        // 氧气压力下限
        alarm_et_oxy_min = (TextView) view.findViewById(R.id.alarm_et_oxy_min);
        //氧气流量上限
        alarm_et_oxy_flow = (TextView) view.findViewById(R.id.alarm_et_oxy_flow);
        //氧气浓度下限
        alarm_et_oxy_concentration = (TextView) view.findViewById(R.id.alarm_et_oxy_concentration);
        //露点/温度上限
        alarm_et_temp_max = (TextView) view.findViewById(R.id.alarm_et_temp_max);
        //露点/温度上下限

        alarm_et_temp_min = (TextView) view.findViewById(R.id.alarm_et_temp_min);
        alarm_btn_back=(Button) view.findViewById(R.id.alarm_btn_back);
        alarm_btn_back.setOnClickListener(this);
        alarm_et_oxy_min.setOnClickListener(this);
        alarm_et_oxy_flow.setOnClickListener(this);
        alarm_et_oxy_concentration.setOnClickListener(this);
        alarm_et_temp_max.setOnClickListener(this);
        alarm_et_temp_min.setOnClickListener(this);
        alarm_et_oxy_max.setOnClickListener(this);
    }
    /**
     * 数据初始化
     */
    public void initData() {}
    /**
     * 数据获取
     */
    public void getData() {}
    /**
     * 数据存储
     */
    public void saveData() {}
    /**
     * 数据跟新
     */
    public void setData() {
         myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    try {
                        /**这里写数据获取与数据处理函数*/
                        Bundle bundle = new Bundle();
                        int[] address = {280,284,296,300,304,308};
                        String[] input = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,address);
                        for(int i = 0 ;i <input.length;i++){
                            bundle.putString(String.valueOf(i),input[i]);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        myThread.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alarm_btn_back:
//                startActivity(new Intent(AlarmActivity.this,MainActivity.class));
//                finish();
                break;
            case R.id.alarm_et_oxy_max:
                local=new int[2];
                v.getLocationInWindow(local);
                 str = new int[]{280};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.alarm_et_oxy_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{284};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.alarm_et_oxy_flow:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{296};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.alarm_et_oxy_concentration:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{300};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.alarm_et_temp_max:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{304};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.alarm_et_temp_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{308};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
