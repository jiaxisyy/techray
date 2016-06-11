package activity;



import java.util.Timer;
import java.util.TimerTask;

import com.hitek.serial.R;

import android.R.integer;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import utils.Constants;
import utils.PopUtils;
import utils.Pupwindow;


/**
 * ����ҳ��
 * Created by zuheng.lv on 2016/4/26.
 */
public class FlowActivity extends android.support.v4.app.Fragment implements View.OnClickListener, View.OnTouchListener {


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                        /**����дUI���º���*/
                    if(String.valueOf(msg.getData().getFloat("d264"))!=null && !String.valueOf(msg.getData().getFloat("d264")).equals("")){
                        flow_tv_totalflow.setText(String.valueOf((float) Math.round( msg.getData().getFloat("d264")* 10000) / 10000));
                    }
                    if(String.valueOf(msg.getData().getInt("d272"))!=null && !String.valueOf(msg.getData().getInt("d272")).equals("")){
                        flow_et_totalflow_correct.setText(String.valueOf(msg.getData().getInt("d272")));
                    }
                    break;
                case 2:

                    break;

            }
        }
    };

    private Button flow_btn_back,flow_btn_confirm,flow_btn_clean;
    private TextView flow_tv_totalflow;
    private TextView flow_et_totalflow_correct;
    private boolean flag = true;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.flow_layout,container,false);
        initView();
        initData();
        setData();
        getData();
        return view;
    }

    /**�ؼ���ʼ��*/
    public void initView(){
        flow_tv_totalflow= (TextView)view.findViewById(R.id.flow_tv_totalflow);
        flow_et_totalflow_correct = (TextView)view.findViewById(R.id.flow_et_totalflow_correct);
        flow_btn_confirm =(Button)view.findViewById(R.id.flow_btn_confirm);
        flow_btn_clean = (Button)view.findViewById(R.id.flow_btn_clean);
        flow_btn_back = (Button) view.findViewById(R.id.flow_btn_back);
        flow_et_totalflow_correct.setOnClickListener(this);
        flow_btn_back.setOnClickListener(this);
        flow_btn_clean.setOnClickListener(this);
        flow_btn_clean.setOnTouchListener(this);
        flow_btn_confirm.setOnClickListener(this);
        flow_btn_confirm.setOnTouchListener(this);
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
        new Thread(new Runnable() {
            @Override
            public void run() {

                /**����д���ݻ�ȡ�����ݴ�����*/
                while (flag) {
                    try {
                        float[] d264 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 264, 1);
                        int[] d272 = MyApplication.getInstance().mdbusreaddword(Constants.Define.OP_DWORD_D, 272, 1);
                        Bundle bundle = new Bundle();
                        Message msg = new Message();
                        bundle.putFloat("d264", d264[0]);
                        bundle.putInt("d272", d272[0]);
                        msg.setData(bundle);
                        msg.what = 1;
                        handler.sendMessage(msg);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**�����������*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.flow_btn_back:
//                Intent intent = new Intent(FlowActivity.this, MainActivity.class);
//                startActivity(intent);
//                break;
            case R.id.flow_et_totalflow_correct:
               int[] local=new int[2];
                v.getLocationInWindow(local);
                int[] str = {272};
                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_DWORD_D,str);
                break;
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.flow_btn_confirm:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m190 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m190,190,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m190 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m190,190,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                break;
            case R.id.flow_btn_clean:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m117 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m117 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                break;
        }
        return false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
    }

}
