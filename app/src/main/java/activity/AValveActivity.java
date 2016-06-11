package activity;



import com.hitek.serial.R;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import utils.Constants;


/**
 * A机阀门检验页面
 * Created by zuheng.lv on 2016/4/26.
 */
public class AValveActivity extends android.support.v4.app.Fragment implements View.OnClickListener, View.OnTouchListener  {

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /**这里写UI更新函数*/
                    avalve_btn_valve1.setSelected(msg.getData().getBoolean("y3"));
                    avalve_btn_valve2.setSelected(msg.getData().getBoolean("y4"));
                    avalve_btn_valve3.setSelected(msg.getData().getBoolean("y5"));
                    avalve_btn_valve4.setSelected(msg.getData().getBoolean("y6"));

                    avalve_btn_valve5.setSelected(msg.getData().getBoolean("y10"));
                    avalve_btn_valve6.setSelected(msg.getData().getBoolean("y11"));
                    avalve_btn_valve7.setSelected(msg.getData().getBoolean("y12"));
                    avalve_btn_valve_in.setSelected(msg.getData().getBoolean("y2"));
                    avalve_btn_valve9.setSelected(msg.getData().getBoolean("y13"));
                    avalve_btn_valve_born.setSelected(msg.getData().getBoolean("y7"));
                    avalve_btn_valve11.setSelected(msg.getData().getBoolean("y0"));
                    avalve_btn_valve12.setSelected(msg.getData().getBoolean("y1"));
//                	Log.d("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++",  String.valueOf(avalve_btn_valve1.isSelected()));
                    break;
            }
        }
    };

    private Button valve_btn_back;
    private Button avalve_btn_valve1,avalve_btn_valve2,avalve_btn_valve3,avalve_btn_valve4,avalve_btn_valve5,avalve_btn_valve6,avalve_btn_valve7,avalve_btn_valve9,avalve_btn_valve11,avalve_btn_valve12,avalve_btn_valve_in,avalve_btn_valve_born;
    private Button avalve_btn1,avalve_btn2,avalve_btn3,avalve_btn4,avalve_btn5,avalve_btn6,avalve_btn7,avalve_btn9,avalve_btn11,avalve_btn12,avalve_btn_in,avalve_btn_born;
    private Boolean flag = true;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.avalve_layout,container,false);
        return view;
    }


    /**�ؼ���ʼ��*/
    public void initView(){

        avalve_btn_valve1= (Button)view.findViewById(R.id.avalve_btn_valve1);
        avalve_btn_valve2= (Button)view.findViewById(R.id.avalve_btn_valve2);
        avalve_btn_valve3= (Button)view.findViewById(R.id.avalve_btn_valve3);
        avalve_btn_valve4= (Button)view.findViewById(R.id.avalve_btn_valve4);
        avalve_btn_valve5= (Button)view.findViewById(R.id.avalve_btn_valve5);
        avalve_btn_valve6= (Button)view.findViewById(R.id.avalve_btn_valve6);
        avalve_btn_valve7= (Button)view.findViewById(R.id.avalve_btn_valve7);
        avalve_btn_valve_in= (Button)view.findViewById(R.id.avalve_btn_valve_in);
        avalve_btn_valve9= (Button)view.findViewById(R.id.avalve_btn_valve9);
        avalve_btn_valve_born= (Button)view.findViewById(R.id.avalve_btn_valve_born);
        avalve_btn_valve11= (Button)view.findViewById(R.id.avalve_btn_valve11);
        avalve_btn_valve12= (Button)view.findViewById(R.id.avalve_btn_valve12);
        avalve_btn1 = (Button) view.findViewById(R.id.avalve_btn1);
        avalve_btn2 = (Button) view.findViewById(R.id.avalve_btn2);
        avalve_btn3 = (Button) view.findViewById(R.id.avalve_btn3);
        avalve_btn4 = (Button) view.findViewById(R.id.avalve_btn4);
        avalve_btn5 = (Button) view.findViewById(R.id.avalve_btn5);
        avalve_btn6 = (Button) view.findViewById(R.id.avalve_btn6);
        avalve_btn7 = (Button)view.findViewById(R.id.avalve_btn7);
        avalve_btn_in = (Button) view.findViewById(R.id.avalve_btn_in);
        avalve_btn9 = (Button) view.findViewById(R.id.avalve_btn9);
        avalve_btn_born = (Button) view.findViewById(R.id.avalve_btn_born);
        avalve_btn11 = (Button) view.findViewById(R.id.avalve_btn11);
        avalve_btn12 = (Button) view.findViewById(R.id.avalve_btn12);
        valve_btn_back = (Button)view.findViewById(R.id.valve_btn_back);
        avalve_btn1.setOnTouchListener(this);
        avalve_btn2.setOnTouchListener(this);
        avalve_btn3.setOnTouchListener(this);
        avalve_btn4.setOnTouchListener(this);
        avalve_btn5.setOnTouchListener(this);
        avalve_btn6.setOnTouchListener(this);
        avalve_btn7.setOnTouchListener(this);
        avalve_btn_in.setOnTouchListener(this);
        avalve_btn9.setOnTouchListener(this);
        avalve_btn_born.setOnTouchListener(this);
        avalve_btn11.setOnTouchListener(this);
        avalve_btn12.setOnTouchListener(this);
        valve_btn_back.setOnClickListener(this);
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
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //�ܽ�����
                    byte[] y2 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 2, 1);
                    //A������
                    byte[] y3 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 3, 1);
                    //B������
                    byte[] y4 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 4, 1);
                    //A������
                    byte[] y5 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 5, 1);
                    //B������
                    byte[] y6 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 6, 1);
                    //�ܲ�����
                    byte[] y7 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 7, 1);
                    //A������
                    byte[] y10 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 8, 1);
                    //B������
                    byte[] y11 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y,9, 1);
                    //��ѹ��
                    byte[] y12 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 10, 1);
                    //������
                    byte[] y13 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 11, 1);
                    //��ѹ��
                    byte[] y0 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 0, 1);
                    //��ɻ�
                    byte[] y1 = MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_Y, 1, 1);

                    Bundle bundle = new Bundle();
                    if(y0[0]==0){
                        bundle.putBoolean("y0", false);
                    }else if(y0[0]==1){
                        bundle.putBoolean("y0", true);
                    }
                    if(y1[0]==0){
                        bundle.putBoolean("y1", false);
                    }else if(y1[0]==1){
                        bundle.putBoolean("y1", true);
                    }
                    if(y2[0]==0){
                        bundle.putBoolean("y2", false);
                    }else if(y2[0]==1){
                        bundle.putBoolean("y2", true);
                    }
                    if(y3[0]==0){
                        bundle.putBoolean("y3", false);
                    }else if(y3[0]==1){
                        bundle.putBoolean("y3", true);
                    }
                    if(y4[0]==0){
                        bundle.putBoolean("y4", false);
                    }else if(y4[0]==1){
                        bundle.putBoolean("y4", true);
                    }
                    if(y5[0]==0){
                        bundle.putBoolean("y5", false);
                    }else if(y5[0]==1){
                        bundle.putBoolean("y5", true);
                    }
                    if(y6[0]==0){
                        bundle.putBoolean("y6", false);
                    }else if(y6[0]==1){
                        bundle.putBoolean("y6", true);
                    }
                    if(y7[0]==0){
                        bundle.putBoolean("y7", false);
                    }else if(y7[0]==1){
                        bundle.putBoolean("y7", true);
                    }
                    if(y10[0]==0){
                        bundle.putBoolean("y10", false);
                    }else if(y10[0]==1){
                        bundle.putBoolean("y10", true);
                    }
                    if(y11[0]==0){
                        bundle.putBoolean("y11", false);
                    }else if(y11[0]==1){
                        bundle.putBoolean("y11", true);
                    }
                    if(y12[0]==0){
                        bundle.putBoolean("y12", false);
                    }else if(y12[0]==1){
                        bundle.putBoolean("y12", true);
                    }
                    if(y13[0]==0){
                        bundle.putBoolean("y13", false);
                    }else if(y13[0]==1){
                        bundle.putBoolean("y13", true);
                    }
                    Message msg = new Message();
                    msg.setData(bundle);
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    /**�����������*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.valve_btn_back:
//                Intent intent = new Intent(AValveActivity.this,MainActivity.class);
//                startActivity(intent);
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.avalve_btn1:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn1.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,153,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn1.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,153,1);
                }
                break;
            case R.id.avalve_btn2:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn2.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,154,1);

                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn2.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,154,1);

                }
                break;
            case R.id.avalve_btn3:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn3.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,155,1);


                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn3.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,155,1);
                }
                break;
            case R.id.avalve_btn4:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn4.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,156,1);

                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn4.setPressed(false);

                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,156,1);
                }
                break;
            case R.id.avalve_btn5:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn5.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,160,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn5.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,160,1);
                }
                break;
            case R.id.avalve_btn6:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn6.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,161,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn6.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,161,1);
                }
                break;
            case R.id.avalve_btn7:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn7.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,162,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn7.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,162,1);
                }
                break;
            case R.id.avalve_btn_in:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn_in.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,152,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn_in.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,152,1);
                }
                break;
            case R.id.avalve_btn9:
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    avalve_btn9.setPressed(true);
                    byte[] b = {1};

                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,163,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn9.setPressed(false);
                    byte[] b = {0};

                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,163,1);
                }
                break;
            case R.id.avalve_btn_born:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn_born.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,157,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn_born.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,157,1);
                }
                break;
            case R.id.avalve_btn11:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn11.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,150,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn11.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,150,1);
                }
                break;
            case R.id.avalve_btn12:
                if(event.getAction()== MotionEvent.ACTION_DOWN) {
                    avalve_btn12.setPressed(true);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,151,1);
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    avalve_btn12.setPressed(false);
                    byte[] b = {0};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,151,1);

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
