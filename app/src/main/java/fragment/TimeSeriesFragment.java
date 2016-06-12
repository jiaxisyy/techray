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
                    break;
                case 2:

                    break;

            }
        }
    };

    private Button flow_btn_confirm,flow_btn_clean;
    private TextView flow_tv_totalflow,flow_tv_safe;
    private TextView flow_et_totalflow_correct,tv_plc_year;
    private boolean flag = true;
    private View view;
    private Pupwindow popupWindow;
    private TextView plc_setting;
    //时间选择器
    private WheelView yearWheel,monthWheel,dayWheel,hourWheel,minuteWheel,secondWheel;
    public static String[] yearContent=null;
    public static String[] monthContent=null;
    public static String[] dayContent=null;
    public static String[] hourContent = null;
    public static String[] minuteContent=null;
    public static String[] secondContent=null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_time_series,container,false);
        initView();
        initData();
        setData();
        getData();
        initTimeParaData();
        initContent();
        return view;
    }

    /**�ؼ���ʼ��*/
    public void initView(){
        flow_tv_totalflow= (TextView)view.findViewById(R.id.flow_tv_totalflow);
        flow_tv_safe=(TextView) view.findViewById(R.id.flow_tv_safe);
        flow_btn_confirm =(Button)view.findViewById(R.id.flow_btn_confirm);
        plc_setting = (TextView) view.findViewById(R.id.plc_setting);

        flow_tv_safe.setOnClickListener(this);
        flow_tv_totalflow.setOnClickListener(this);
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
//            case R.id.flow_btn_clean:
//                if(event.getAction()== MotionEvent.ACTION_DOWN){
//                    byte[] m117 = {1};
//                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
//                }else if(event.getAction()== MotionEvent.ACTION_UP){
//                    TimerTask timerTask = new TimerTask() {
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            byte[] m117 = {0};
//                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,m117,117,1);
//                        }
//                    };
//                    Timer time = new Timer();
//                    time.schedule(timerTask, 500);
//                }
//                break;
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


    public void initTimeParaData(){
        plc_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = ((LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.time_picker, null);
                Calendar calendar = Calendar.getInstance();
                int curYear = calendar.get(Calendar.YEAR);
                int curMonth= calendar.get(Calendar.MONTH)+1;
                int curDay = calendar.get(Calendar.DAY_OF_MONTH);
                int curHour = calendar.get(Calendar.HOUR_OF_DAY);
                int curMinute = calendar.get(Calendar.MINUTE);
                int curSecond = calendar.get(Calendar.SECOND);

                yearWheel = (WheelView)view.findViewById(R.id.yearwheel);
                monthWheel = (WheelView)view.findViewById(R.id.monthwheel);
                dayWheel = (WheelView)view.findViewById(R.id.daywheel);
                hourWheel = (WheelView)view.findViewById(R.id.hourwheel);
                minuteWheel = (WheelView)view.findViewById(R.id.minutewheel);
                secondWheel = (WheelView)view.findViewById(R.id.secondwheel);


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(view);

                yearWheel.setAdapter(new StrericWheelAdapter(yearContent));
                yearWheel.setCurrentItem(curYear-2013);
                yearWheel.setCyclic(true);
                yearWheel.setInterpolator(new AnticipateOvershootInterpolator());


                monthWheel.setAdapter(new StrericWheelAdapter(monthContent));

                monthWheel.setCurrentItem(curMonth-1);

                monthWheel.setCyclic(true);
                monthWheel.setInterpolator(new AnticipateOvershootInterpolator());

                dayWheel.setAdapter(new StrericWheelAdapter(dayContent));
                dayWheel.setCurrentItem(curDay-1);
                dayWheel.setCyclic(true);
                dayWheel.setInterpolator(new AnticipateOvershootInterpolator());

                hourWheel.setAdapter(new StrericWheelAdapter(hourContent));
                hourWheel.setCurrentItem(curHour);
                hourWheel.setCyclic(true);
                hourWheel.setInterpolator(new AnticipateOvershootInterpolator());

                minuteWheel.setAdapter(new StrericWheelAdapter(minuteContent));
                minuteWheel.setCurrentItem(curMinute);
                minuteWheel.setCyclic(true);
                minuteWheel.setInterpolator(new AnticipateOvershootInterpolator());

                secondWheel.setAdapter(new StrericWheelAdapter(secondContent));
                secondWheel.setCurrentItem(curSecond);
                secondWheel.setCyclic(true);
                secondWheel.setInterpolator(new AnticipateOvershootInterpolator());

                builder.setTitle("123");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        StringBuffer sb = new StringBuffer();
                        sb.append(yearWheel.getCurrentItemValue()).append("-")
                                .append(monthWheel.getCurrentItemValue()).append("-")
                                .append(dayWheel.getCurrentItemValue());

                        sb.append(" ");
                        sb.append(hourWheel.getCurrentItemValue())
                                .append(":").append(minuteWheel.getCurrentItemValue())
                                .append(":").append(secondWheel.getCurrentItemValue());
                        plc_setting.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }
    public void initContent()
    {
        yearContent = new String[10];
        for(int i=0;i<10;i++)
            yearContent[i] = String.valueOf(i+2013);

        monthContent = new String[12];
        for(int i=0;i<12;i++)
        {
            monthContent[i]= String.valueOf(i+1);
            if(monthContent[i].length()<2)
            {
                monthContent[i] = "0"+monthContent[i];
            }
        }

        dayContent = new String[31];
        for(int i=0;i<31;i++)
        {
            dayContent[i]=String.valueOf(i+1);
            if(dayContent[i].length()<2)
            {
                dayContent[i] = "0"+dayContent[i];
            }
        }
        hourContent = new String[24];
        for(int i=0;i<24;i++)
        {
            hourContent[i]= String.valueOf(i);
            if(hourContent[i].length()<2)
            {
                hourContent[i] = "0"+hourContent[i];
            }
        }

        minuteContent = new String[60];
        for(int i=0;i<60;i++)
        {
            minuteContent[i]=String.valueOf(i);
            if(minuteContent[i].length()<2)
            {
                minuteContent[i] = "0"+minuteContent[i];
            }
        }
        secondContent = new String[60];
        for(int i=0;i<60;i++)
        {
            secondContent[i]=String.valueOf(i);
            if(secondContent[i].length()<2)
            {
                secondContent[i] = "0"+secondContent[i];
            }
        }
    }
}
