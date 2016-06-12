//package activity;
//
//
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import com.hitek.serial.R;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.View.OnTouchListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.TextView;
//
//import utils.Constants;
//import popupwindow.Pupwindow;
//
//
///**
// * �������ý���
// * Created by zuheng.lv on 2016/4/26.
// */
//public class ParameterActivity extends android.support.v4.app.Fragment implements View.OnClickListener, OnTouchListener{
//
//    private TextView para_et_opendelay, para_et_startdelay,momitor_tv_runningtime;
//    private Button para_btn_original,para_btn_setting,para_btn_factory;
//    private int[] local,str;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 1:
//                    /**����дUI���º���*/
//                    if(String.valueOf(msg.getData().getShort("d476"))!=null && !String.valueOf(msg.getData().getShort("d476")).equals("")){
//                        para_et_opendelay.setText(String.valueOf(msg.getData().getShort("d476")));
//                    }
//                    if(String.valueOf(msg.getData().getShort("d478"))!=null && !String.valueOf(msg.getData().getShort("d478")).equals("")){
//                        para_et_startdelay.setText(String.valueOf(msg.getData().getShort("d478")));
//                    }
//                    if(msg.getData().getIntArray("ints").length>0){
//                        momitor_tv_runningtime.setText( msg.getData().getIntArray("ints")[0]+"");
//
//                    }
//                    break;
//            }
//        }
//    };
//
//    private Button para_btn_back;
//    private View view;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.parameter_layout,container,false);
//        initView();
//        initData();
//        setData();
//        return view;
//    }
//
//
//
//    /**
//     * �ؼ���ʼ��
//     */
//    public void initView() {
//
//        //A������ʱ��
//        momitor_tv_runningtime = (TextView) view.findViewById(R.id.momitor_tv_runningtime);
//
//        para_btn_back = (Button) view.findViewById(R.id.para_btn_back);
//        para_btn_original = (Button) view.findViewById(R.id.para_btn_original);
//        para_btn_factory = (Button) view.findViewById(R.id.para_btn_factory);
//        para_btn_setting = (Button) view.findViewById(R.id.para_btn_setting);
//        para_btn_back.setOnClickListener(this);
//        para_btn_original.setOnTouchListener(this);
//        para_btn_factory.setOnTouchListener(this);
//        para_btn_setting.setOnTouchListener(this);
//        //��ѹ��������ʱ
//        para_et_opendelay = (TextView) view.findViewById(R.id.para_et_opendelay);
//        //����������ʱ
//        para_et_startdelay = (TextView) view.findViewById(R.id.para_et_startdelay);
//        para_et_opendelay.setOnClickListener(this);
//        para_et_startdelay.setOnClickListener(this);
//    }
//
//    /**
//     * ���ݳ�ʼ��
//     */
//    public void initData() {
//
//
//    }
//
//    /**
//     * ���ݻ�ȡ
//     */
//    public void getData() {
//
//
//    }
//
//    /**
//     * ���ݴ洢
//     */
//    public void saveData() {
//
//    }
//
//    /**
//     * ���ݸ���
//     */
//    public void setData() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while(true){
//                    try {
//                        /**����д���ݻ�ȡ�����ݴ�����*/
//                        //A������ʱ��
//                        int[] ints = MyApplication.getInstance().mdbusreaddword(Constants.Define.OP_DWORD_D, 458, 1);
//                        short[]d476 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 476, 1);
//                        short[]d478 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 478, 1);
//                        Bundle bundle = new Bundle();
//                        bundle.putIntArray("ints", ints);
//                        bundle.putShort("d476", d476[0]);
//                        bundle.putShort("d478", d478[0]);
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.setData(bundle);
//                        handler.sendMessage(msg);
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
//    }
//
//    /**
//     * �����������
//     */
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.para_btn_back:
////                Intent intent = new Intent(ParameterActivity.this, MainActivity.class);
////                startActivity(intent);
////                finish();
//                break;
//            case R.id.para_et_opendelay:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{476};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_WORD_D,str);
//                break;
//            case R.id.para_et_startdelay:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{478};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_WORD_D,str);
//                break;
//        }
//    }
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        // TODO Auto-generated method stub
//        switch(v.getId()){
//            case R.id.para_btn_original:
//                if(event.getAction() ==MotionEvent.ACTION_DOWN){
//                    byte[]m95 = {1};
//                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m95, 95, 1);
//                }else if(event.getAction() ==MotionEvent.ACTION_UP){
//                    TimerTask timerTask = new TimerTask() {
//
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            byte[]m95 = {0};
//                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m95, 95, 1);
//                        }
//                    };
//                    Timer time = new Timer();
//                    time.schedule(timerTask, 500);
//                }
//                break;
//            case R.id.para_btn_setting:
//                if(event.getAction() ==MotionEvent.ACTION_DOWN){
//                    byte[]m192 = {1};
//                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m192, 192, 1);
//                }else if(event.getAction() ==MotionEvent.ACTION_UP){
//                    TimerTask timerTask = new TimerTask() {
//
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            byte[]m192 = {0};
//                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m192, 192, 1);
//                        }
//                    };
//                    Timer time = new Timer();
//                    time.schedule(timerTask, 500);
//                }
//                break;
//            case R.id.para_btn_factory:
//                if(event.getAction() ==MotionEvent.ACTION_DOWN){
//                    byte[]m193 = {1};
//                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m193, 193, 1);
//
//                }else if(event.getAction() ==MotionEvent.ACTION_UP){
//
//
//                    TimerTask timerTask = new TimerTask() {
//                        @Override
//                        public void run() {
//                            // TODO Auto-generated method stub
//                            byte[]m193 = {0};
//                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, m193, 193, 1);
//                        }
//                    };
//                    Timer time = new Timer();
//                    time.schedule(timerTask, 500);
//                }
//                break;
//        }
//
//        return false;
//    }
//}
