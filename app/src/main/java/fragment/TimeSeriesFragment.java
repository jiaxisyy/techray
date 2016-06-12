package fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hitek.serial.R;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import activity.MyApplication;
import popupwindow.Pupwindow;
import utils.CacheUtils;
import utils.Constants;
import utils.ReadAndWrite;
import wheel.StrericWheelAdapter;
import wheel.WheelView;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class TimeSeriesFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

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
                        flow_tv_safe.setText(String.valueOf(msg.getData().getInt("d272")));
                    }
                    if(msg.getData().getStringArray("data").length>0){
                        series_year.setText(msg.getData().getShortArray("data")[0]);
                        series_month.setText(msg.getData().getShortArray("data")[1]);
                        series_day.setText(msg.getData().getShortArray("data")[2]);
                        series_hour.setText(msg.getData().getShortArray("data")[3]);
                        series_minute.setText(msg.getData().getShortArray("data")[4]);
                        series_second.setText(msg.getData().getShortArray("data")[5]);
                        series_week.setText(msg.getData().getShortArray("data")[6]);
                    }
                    break;
                case 2:

                    break;

            }
        }
    };

    private Button flow_btn_confirm,btn_time_series_affirm,flow_btn_open,flow_btn_close;
    private TextView flow_tv_totalflow,flow_tv_safe;
    private TextView series_year,series_month,series_day,series_hour,series_minute,series_second,series_week;
    private boolean flag = true;
    private View view;
    private Pupwindow popupWindow;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_series,container,false);
        initView();
        initData();
        setData();
        getData();
        return view;
    }

    /**�ؼ���ʼ��*/
    public void initView(){
        flow_tv_totalflow= (TextView)view.findViewById(R.id.flow_tv_totalflow);
        flow_tv_safe=(TextView) view.findViewById(R.id.flow_tv_safe);
        flow_btn_confirm =(Button)view.findViewById(R.id.flow_btn_confirm);
        btn_time_series_affirm = (Button) view.findViewById(R.id.btn_time_series_affirm);
        flow_btn_open=(Button)view.findViewById(R.id.flow_btn_open);
        flow_btn_close=(Button)view.findViewById(R.id.flow_btn_close);

        series_year = (TextView) view.findViewById(R.id.series_year);
        series_month = (TextView) view.findViewById(R.id.series_month);
        series_day = (TextView) view.findViewById(R.id.series_day);
        series_hour = (TextView) view.findViewById(R.id.series_hour);
        series_minute = (TextView) view.findViewById(R.id.series_minute);
        series_second = (TextView) view.findViewById(R.id.series_second);
        series_week = (TextView) view.findViewById(R.id.series_week);

        series_year.setOnTouchListener(this);
        series_month.setOnTouchListener(this);
        series_day.setOnTouchListener(this);
        series_hour.setOnTouchListener(this);
        series_minute.setOnTouchListener(this);
        series_second.setOnTouchListener(this);
        series_week.setOnTouchListener(this);

        flow_tv_safe.setOnClickListener(this);
        flow_tv_totalflow.setOnClickListener(this);
        flow_btn_confirm.setOnTouchListener(this);
        btn_time_series_affirm.setOnTouchListener(this);
        flow_btn_open.setOnClickListener(this);
        flow_btn_close.setOnClickListener(this);
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
                        String[] data = ReadAndWrite.ReadJni(Constants.Define.OP_DWORD_D,new int[]{610,611,612,613,614,615,616});

                        Bundle bundle = new Bundle();
                        Message msg = new Message();
                        bundle.putStringArray("data",data);
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
            case R.id.flow_tv_totalflow:
                int[] str = {264};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_DWORD_D,str);
                popupWindow.showPopupWindow();
                break;
            case R.id.flow_tv_safe:
                int[] ste = {272};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_DWORD_D,ste);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_year:
                int[] year = {600};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,year);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_month:
                int[] month = {601};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,month);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_day:
                int[] day = {602};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,day);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_hour:
                int[] hour = {603};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,hour);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_minute:
                int[] minute = {604};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,minute);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_second:
                int[] second = {605};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,second);
                popupWindow.showPopupWindow();
                break;
            case R.id.series_week:
                int[] week = {606};
                popupWindow = new Pupwindow(getContext(),v,Constants.Define.OP_WORD_D,week);
                popupWindow.showPopupWindow();
                break;
            case R.id.flow_btn_open:
                MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,new byte[]{1},58,1);
                break;
            case R.id.flow_btn_close:
                MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,new byte[]{0},58,1);
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
            case R.id.btn_time_series_affirm:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m56 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m56,56,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m56 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m56,56,1);
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
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
    }
}
