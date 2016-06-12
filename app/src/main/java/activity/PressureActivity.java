//package activity;
//
//
//
//import com.hitek.serial.R;
//
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.Nullable;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import utils.Constants;
//import popupwindow.Pupwindow;
//import utils.ReadAndWrite;
//
//
///**
// * ѹ������ҳ��
// * Created by zuheng.lv on 2016/4/26.
// */
//public class PressureActivity extends android.support.v4.app.Fragment implements View.OnClickListener {
//
//
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 1:
//                    /**����дUI���º���*/
//                    if(msg.getData().getString("0")!=null && !msg.getData().getString("0").equals("")){
//                        pressure_et_max1.setText(msg.getData().getString("0"));
//                        Log.d("*****************",msg.getData().getString("0"));
//                    }
//                    if(msg.getData().getString("1")!=null && !msg.getData().getString("1").equals("")){
//                        pressure_et_min1.setText(msg.getData().getString("1"));
//                    }
//                    if(msg.getData().getString("2")!=null && !msg.getData().getString("2").equals("")){
//                        pressure_et_max2.setText(msg.getData().getString("2"));
//                    }
//                    if(msg.getData().getString("3")!=null && !msg.getData().getString("3").equals("")){
//                        pressure_et_min2.setText(msg.getData().getString("3"));
//                    }
//
//                    break;
//            }
//        }
//    };
//
//
//    private TextView pressure_et_max1,pressure_et_max2,pressure_et_min1,pressure_et_min2;
//    private boolean flag=true;
//    private  Thread myThread;
//    private int[] local;
//    private int[] str;
//    private View view;
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.pressure_layout,container,false);
//        initView();
//        initData();
//        setData();
//        myThread.start();
//        return view;
//    }
//    /**�ؼ���ʼ��*/
//    public void initView(){
//        pressure_et_max1 = (TextView)view.findViewById(R.id.pressure_et_max1);
//        pressure_et_max2 = (TextView)view.findViewById(R.id.pressure_et_max2);
//        pressure_et_min1 = (TextView)view.findViewById(R.id.pressure_et_min1);
//        pressure_et_min2 = (TextView)view.findViewById(R.id.pressure_et_min2);
//        pressure_et_max1.setOnClickListener(this);
//        pressure_et_max2.setOnClickListener(this);
//        pressure_et_min1.setOnClickListener(this);
//        pressure_et_min2.setOnClickListener(this);
//    }
//    /**���ݳ�ʼ��*/
//    public void initData(){
//
//
//    }
//
//    /**���ݻ�ȡ*/
//    public void getData(){
//
//
//    }
//    /**���ݴ洢*/
//    public void saveData(){
//
//    }
//    /**���ݸ���*/
//    public void setData(){
//        myThread =  new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(flag){
//                    try{
//                        int[] address = {330,334,338,342};
//                        String[] read = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,address);
//                        Bundle bundle = new Bundle();
//                        for(int i =0;i<read.length;i++){
//                            bundle.putString(String.valueOf(i),read[i]);
//                        }
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.setData(bundle);
//                        handler.sendMessage(msg);
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//
//    /**�����������*/
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.pressure_et_max1:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{330};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
//                break;
//            case R.id.pressure_et_max2:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{338};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
//                break;
//            case R.id.pressure_et_min1:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{334};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
//                break;
//            case R.id.pressure_et_min2:
//                local=new int[2];
//                v.getLocationInWindow(local);
//                str = new int[]{342};
//                new Pupwindow(view.getContext(),v,local[0],local[1],Constants.Define.OP_REAL_D,str);
//                break;
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        flag = false;
//    }
//}
