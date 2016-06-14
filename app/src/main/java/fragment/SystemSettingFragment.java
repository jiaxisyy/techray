package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hitek.serial.R;

import utils.Constants;
import popupwindow.Pupwindow;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/10.
 */
public class SystemSettingFragment extends Fragment implements View.OnClickListener {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /**����дUI���º���*/
                    if(msg.getData().getString("0")!=null && !msg.getData().getString("0").equals("")){

                        float v = Float.parseFloat(msg.getData().getString("0"));
                        pressure_et_max1.setText(String.valueOf((float) Math.round(v * 100) / 100));

                    }
                    if(msg.getData().getString("1")!=null && !msg.getData().getString("1").equals("")){

                        float v = Float.parseFloat(msg.getData().getString("1"));
                        pressure_et_min1.setText(String.valueOf((float) Math.round(v * 100) / 100));

                    }
                    if(msg.getData().getString("2")!=null && !msg.getData().getString("2").equals("")){

                        float v = Float.parseFloat(msg.getData().getString("2"));
                        pressure_et_max2.setText(String.valueOf((float) Math.round(v * 100) / 100));

                    }
                    if(msg.getData().getString("3")!=null && !msg.getData().getString("3").equals("")){
                        float v = Float.parseFloat(msg.getData().getString("3"));
                        pressure_et_min2.setText(String.valueOf((float) Math.round(v * 100) / 100));
                    }

                    break;
            }
        }
    };



    private TextView pressure_et_max1,pressure_et_max2,pressure_et_min1,pressure_et_min2;
    private boolean flag=true;
    private  Thread myThread;
    private int[] local;
    private int[] str;
    private View view;
    private Pupwindow pupwindow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.systemsetting_layout,container,false);
        initView();
        initData();
        setData();
        pupwindow = new Pupwindow(getContext());
        myThread.start();
        return view;
    }
    /**�ؼ���ʼ��*/
    public void initView(){
        pressure_et_max1 = (TextView)view.findViewById(R.id.pressure_et_max1);
        pressure_et_max2 = (TextView)view.findViewById(R.id.pressure_et_max2);
        pressure_et_min1 = (TextView)view.findViewById(R.id.pressure_et_min1);
        pressure_et_min2 = (TextView)view.findViewById(R.id.pressure_et_min2);
        pressure_et_max1.setOnClickListener(this);
        pressure_et_max2.setOnClickListener(this);
        pressure_et_min1.setOnClickListener(this);
        pressure_et_min2.setOnClickListener(this);
    }
    /**���ݳ�ʼ��*/
    public void initData(){


    }

    /**���ݻ�ȡ*/
    public void getData(){


    }
    /**���ݴ洢*/
    public void saveData(){

    }
    /**���ݸ���*/
    public void setData(){
        myThread =  new Thread(new Runnable() {
            @Override
            public void run() {
                while(flag){
                    try{
                        int[] address = {620,626,330,334};
                        String[] read = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,address);
                        Bundle bundle = new Bundle();
                        for(int i =0;i<read.length;i++){
                            bundle.putString(String.valueOf(i),read[i]);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**�����������*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pressure_et_max1:
                pupwindow.showPopupWindow(view,Constants.Define.OP_REAL_D,new int[]{620});
                break;
            case R.id.pressure_et_min1:
                pupwindow.showPopupWindow(view,Constants.Define.OP_REAL_D,new int[]{626});
                break;
            case R.id.pressure_et_max2:
                pupwindow.showPopupWindow(view,Constants.Define.OP_REAL_D,new int[]{330});
                break;
            case R.id.pressure_et_min2:
                pupwindow.showPopupWindow(view,Constants.Define.OP_REAL_D,new int[]{334});
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pupwindow.stopPopupWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
