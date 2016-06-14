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
import popupwindow.PopupForWeek;
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

                    if(String.valueOf(msg.getData().getStringArray("data")[0]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[0] ).equals("")){
                        series_year.setText(String.valueOf(msg.getData().getStringArray("data")[0]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[1]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[1] ).equals("")){
                        series_month.setText(String.valueOf(msg.getData().getStringArray("data")[1]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[2]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[2] ).equals("")){
                        series_day.setText(String.valueOf(msg.getData().getStringArray("data")[2]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[3]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[3] ).equals("")){
                        series_hour.setText(String.valueOf(msg.getData().getStringArray("data")[3]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[4]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[4] ).equals("")){
                        series_minute.setText(String.valueOf(msg.getData().getStringArray("data")[4]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[5]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[5] ).equals("")){
                        series_second.setText(String.valueOf(msg.getData().getStringArray("data")[5]));
                    }
                    if(String.valueOf(msg.getData().getStringArray("data")[6]) !=null &&!String.valueOf(msg.getData().getStringArray("data")[6] ).equals("")){
                        switch (msg.getData().getStringArray("data")[6]){
                            case "0":
                                series_week.setText("天");
                                break;
                            case "1":
                                series_week.setText("一");
                                break;
                            case "2":
                                series_week.setText("二");
                                break;
                            case "3":
                                series_week.setText("三");
                                break;
                            case "4":
                                series_week.setText("四");
                                break;
                            case "5":
                                series_week.setText("五");
                                break;
                            case "6":
                                series_week.setText("六");
                                break;
                        }

                    }
                    break;
                case 2:

                    break;

            }
        }
    };

    private Button flow_btn_confirm,btn_time_series_affirm,flow_btn_open;
    private TextView flow_tv_totalflow,flow_tv_safe;
    private TextView series_year,series_month,series_day,series_hour,series_minute,series_second,series_week;
    private boolean flag = true;
    private View view;
    private Pupwindow popupWindow;
    private PopupForWeek popupForWeek;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_special_controls,container,false);
        initView();
        initData();
        setData();
        getData();
        return view;
    }

    /**�ؼ���ʼ��*/
    public void initView(){
        popupWindow = new Pupwindow(getContext());
        popupForWeek = new PopupForWeek(getContext());
        flow_tv_totalflow= (TextView)view.findViewById(R.id.flow_tv_totalflow);
        flow_tv_safe=(TextView) view.findViewById(R.id.flow_tv_safe);
        flow_btn_confirm =(Button)view.findViewById(R.id.flow_btn_confirm);
        btn_time_series_affirm = (Button) view.findViewById(R.id.btn_time_series_affirm);
        flow_btn_open=(Button)view.findViewById(R.id.flow_btn_open);

        series_year = (TextView) view.findViewById(R.id.series_year);
        series_month = (TextView) view.findViewById(R.id.series_month);
        series_day = (TextView) view.findViewById(R.id.series_day);
        series_hour = (TextView) view.findViewById(R.id.series_hour);
        series_minute = (TextView) view.findViewById(R.id.series_minute);
        series_second = (TextView) view.findViewById(R.id.series_second);
        series_week = (TextView) view.findViewById(R.id.series_week);

        series_year.setOnClickListener(this);
        series_month.setOnClickListener(this);
        series_day.setOnClickListener(this);
        series_hour.setOnClickListener(this);
        series_minute.setOnClickListener(this);
        series_second.setOnClickListener(this);
        series_week.setOnClickListener(this);

        flow_tv_safe.setOnClickListener(this);
        flow_tv_totalflow.setOnClickListener(this);
        flow_btn_confirm.setOnTouchListener(this);
        btn_time_series_affirm.setOnTouchListener(this);
        flow_btn_open.setOnTouchListener(this);
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
                popupWindow.showPopupWindow(v,Constants.Define.OP_DWORD_D,str);
                break;
            case R.id.flow_tv_safe:
                int[] ste = {628};
                popupWindow.showPopupWindow(v,Constants.Define.OP_REAL_D,ste);
                break;
            case R.id.series_year:
                int[] year = {600};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,year);
                break;
            case R.id.series_month:
                int[] month = {601};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,month);
                break;
            case R.id.series_day:
                int[] day = {602};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,day);
                break;
            case R.id.series_hour:
                int[] hour = {603};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,hour);
                break;
            case R.id.series_minute:
                int[] minute = {604};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,minute);
                break;
            case R.id.series_second:
                int[] second = {605};
                popupWindow.showPopupWindow(v,Constants.Define.OP_WORD_D,second);
                break;
            case R.id.series_week:
                int[] week = {606};
                popupForWeek.showPopupWindow(v,Constants.Define.OP_WORD_D,week);
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
            case R.id.flow_btn_open:
                if(event.getAction()== MotionEvent.ACTION_DOWN){
                    byte[] m58 = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m58,58,1);
                }else if(event.getAction()== MotionEvent.ACTION_UP){
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] m58 = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m58,58,1);
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
        popupWindow.stopPopupWindow();
        popupForWeek.stopPopupWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
    }
}
