package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hitek.serial.R;

import activity.MyApplication;
import popupwindow.PopupForSimulaion;
import utils.Constants;

/**
 * Created by zuheng.lv on 2016/6/10.
 */
public class SimulaionFragment extends Fragment implements View.OnClickListener {



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /**����дUI���º���*/
                    if(String.valueOf(msg.getData().getShortArray("d10")[0])!=null && !String.valueOf(msg.getData().getShortArray("d10")[0]).equals("")){
                        analog_et_oxy_original.setText(String.valueOf(msg.getData().getShortArray("d10")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d12")[0])!=null && !String.valueOf(msg.getData().getShortArray("d12")[0]).equals("")){
                        analog_et_flow_original.setText(String.valueOf(msg.getData().getShortArray("d12")[0]));

                    }
                    if(String.valueOf(msg.getData().getShortArray("d14")[0])!=null && !String.valueOf(msg.getData().getShortArray("d14")[0]).equals("")){

                        analog_et_concentration_original.setText(String.valueOf(msg.getData().getShortArray("d14")[0]));
                    }
                    if(String.valueOf(msg.getData().getShortArray("d16")[0])!=null && !String.valueOf(msg.getData().getShortArray("d16")[0]).equals("")){
                        analog_et_temp_original.setText(String.valueOf(msg.getData().getShortArray("d16")[0]));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d212")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d212")[0]).equals("")){

                        analog_et_oxy_current.setText(String.valueOf(msg.getData().getFloatArray("d212")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d228")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d228")[0]).equals("")){
                        analog_et_flow_current.setText(String.valueOf(msg.getData().getFloatArray("d228")[0]));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d244")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d244")[0]).equals("")){

                        analog_et_concentration_current.setText(String.valueOf(msg.getData().getFloatArray("d244")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d260")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d260")[0]).equals("")){

                        analog_et_temp_current.setText(String.valueOf(msg.getData().getFloatArray("d260")[0]));

                    }
                    break;
                case 2://(float)(Math.round(totalPrice*100)/100);
                    if(String.valueOf(msg.getData().getFloatArray("d200")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d200")[0]).equals("")){
                        analog_et_oxy_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d200")[0]*100))/100));


                    }
                    if(String.valueOf(msg.getData().getFloatArray("d204")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d204")[0]).equals("")){
                        analog_et_oxy_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d204")[0]*100))/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d208")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d208")[0]).equals("")){

                        analog_et_oxy_correction.setText(String.valueOf(msg.getData().getFloatArray("d208")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d216")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d216")[0]).equals("")){
                        analog_et_flow_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d216")[0]*100))/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d220")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d220")[0]).equals("")){

                        analog_et_flow_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d220")[0]*100))/100));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d224")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d224")[0]).equals("")){
                        analog_et_flow_correction.setText(String.valueOf(msg.getData().getFloatArray("d224")[0]));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d232")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d232")[0]).equals("")){
                        analog_et_concentration_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d232")[0]*100))/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d236")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d236")[0]).equals("")){

                        analog_et_concentration_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d236")[0]*100))/100));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d240")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d240")[0]).equals("")){

                        analog_et_concentration_correction.setText(String.valueOf(msg.getData().getFloatArray("d240")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d248")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d248")[0]).equals("")){
                        analog_et_temp_max.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d248")[0]*100))/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d252")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d252")[0]).equals("")){
                        analog_et_temp_min.setText(String.valueOf((float)(Math.round(msg.getData().getFloatArray("d252")[0]*100))/100));

                    }
                    if(String.valueOf(msg.getData().getFloatArray("d256")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d256")[0]).equals("")){

                        analog_et_temp_correction.setText(String.valueOf(msg.getData().getFloatArray("d256")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d620")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d620")[0]).equals("")){

                        analog_et_oxy_alarm.setText(String.valueOf(msg.getData().getFloatArray("d620")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d622")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d622")[0]).equals("")){

                        analog_et_flow_alarm.setText(String.valueOf(msg.getData().getFloatArray("d622")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d626")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d626")[0]).equals("")){

                        analog_et_concentration_alarm.setText(String.valueOf(msg.getData().getFloatArray("d626")[0]));
                    }
                    if(String.valueOf(msg.getData().getFloatArray("d624")[0])!=null && !String.valueOf(msg.getData().getFloatArray("d624")[0]).equals("")){

                        analog_et_temp_alarm.setText(String.valueOf(msg.getData().getFloatArray("d624")[0]));
                    }
                    break;


            }
        }
    };

    private TextView analog_et_oxy_original,analog_et_flow_original,analog_et_concentration_original,analog_et_temp_original,analog_et_oxy_current,analog_et_flow_current,analog_et_concentration_current,analog_et_temp_current;
    private TextView analog_et_oxy_max,analog_et_flow_max,analog_et_concentration_max,analog_et_temp_max;
    private TextView analog_et_oxy_min,analog_et_flow_min,analog_et_concentration_min,analog_et_temp_min;
    private TextView analog_et_oxy_correction,analog_et_flow_correction,analog_et_concentration_correction,analog_et_temp_correction;
    private TextView analog_et_oxy_alarm,analog_et_flow_alarm,analog_et_concentration_alarm,analog_et_temp_alarm;
    private boolean flag=true;
    private int[] local,str;
    private View view;
    private PopupForSimulaion popupForSimulaion;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.simulation_layout,container,false);
        initView();
        initData();
        setData();
        getData();
        return view;
    }


    /**�ؼ���ʼ��*/
    public void initView(){
        popupForSimulaion =  new PopupForSimulaion(getContext());

        analog_et_oxy_alarm=(TextView)view.findViewById(R.id.analog_et_oxy_alarm_max);
        analog_et_flow_alarm=(TextView)view.findViewById(R.id.analog_et_flow_alarm_min);
        analog_et_concentration_alarm=(TextView)view.findViewById(R.id.analog_et_concentration_alarm_min);
        analog_et_temp_alarm=(TextView)view.findViewById(R.id.analog_et_temp_alarm_min);

        analog_et_oxy_original=(TextView)view.findViewById(R.id.analog_et_oxy_original);
        analog_et_flow_original=(TextView)view.findViewById(R.id.analog_et_flow_original);
        analog_et_concentration_original=(TextView)view.findViewById(R.id.analog_et_concentration_original);
        analog_et_temp_original=(TextView)view.findViewById(R.id.analog_et_temp_original);

        analog_et_oxy_current=(TextView)view.findViewById(R.id.analog_et_oxy_current);
        analog_et_flow_current=(TextView)view.findViewById(R.id.analog_et_flow_current);
        analog_et_concentration_current=(TextView)view.findViewById(R.id.analog_et_concentration_current);
        analog_et_temp_current=(TextView)view.findViewById(R.id.analog_et_temp_current);

        analog_et_oxy_max=(TextView)view.findViewById(R.id.analog_et_oxy_max);
        analog_et_flow_max=(TextView)view.findViewById(R.id.analog_et_flow_max);
        analog_et_concentration_max=(TextView)view.findViewById(R.id.analog_et_concentration_max);
        analog_et_temp_max=(TextView)view.findViewById(R.id.analog_et_temp_max);

        analog_et_oxy_min=(TextView)view.findViewById(R.id.analog_et_oxy_min);
        analog_et_flow_min=(TextView)view.findViewById(R.id.analog_et_flow_min);
        analog_et_concentration_min=(TextView)view.findViewById(R.id.analog_et_concentration_min);
        analog_et_temp_min=(TextView)view.findViewById(R.id.analog_et_temp_min);

        analog_et_oxy_correction=(TextView)view.findViewById(R.id.analog_et_oxy_correction);
        analog_et_flow_correction=(TextView)view.findViewById(R.id.analog_et_flow_correction);
        analog_et_concentration_correction=(TextView)view.findViewById(R.id.analog_et_concentration_correction);
        analog_et_temp_correction=(TextView)view.findViewById(R.id.analog_et_temp_correction);

        analog_et_oxy_max.setOnClickListener(this);
        analog_et_flow_max.setOnClickListener(this);
        analog_et_concentration_max.setOnClickListener(this);
        analog_et_temp_max.setOnClickListener(this);

        analog_et_oxy_min.setOnClickListener(this);
        analog_et_flow_min.setOnClickListener(this);
        analog_et_concentration_min.setOnClickListener(this);
        analog_et_temp_min.setOnClickListener(this);

        analog_et_oxy_correction.setOnClickListener(this);
        analog_et_flow_correction.setOnClickListener(this);
        analog_et_concentration_correction.setOnClickListener(this);
        analog_et_temp_correction.setOnClickListener(this);

        analog_et_oxy_alarm.setOnClickListener(this);
        analog_et_flow_alarm.setOnClickListener(this);
        analog_et_concentration_alarm.setOnClickListener(this);
        analog_et_temp_alarm.setOnClickListener(this);


    }
    /**���ݳ�ʼ��*/
    public void initData(){
    }
    /**���ݻ�ȡ*/
    public void getData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true){
                    try {
                        //压力最大值
                        float[]d200 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 200, 1);

                        //压力最小值
                        float[]d204 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 204, 1);
                        // 压力修正值
                        float[]d208 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 208, 1);

                        //流量最大值
                        float[] d216 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 216, 1);
                        //流量最小值
                        float[] d220 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 220, 1);
                        //流量修正值
                        float[] d224 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 224, 1);

                        //浓度最大值
                        float[] d232 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 232, 1);
                        //浓度最小值
                        float[] d236 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 236, 1);
                        //浓度修正值
                        float[] d240 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 240, 1);

                        //温度最大值
                        float[] d248 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 248, 1);
                        //温度最小值
                        float[] d252 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 252, 1);
                        //温度修正值
                        float[] d256 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 256, 1);


                        //氧气压力报警值
                        float[]d620 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D,620, 1);
                        //氧气流量报警值
                        float[]d622 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 622, 1);
                        //氧气温度报警值
                        float[]d624 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 624, 1);
                        //氧气浓度报警值
                        float[]d626 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 626, 1);

                        Bundle bundle = new Bundle();
                        bundle.putFloatArray("d200", d200);
                        bundle.putFloatArray("d204", d204);
                        bundle.putFloatArray("d208", d208);
                        bundle.putFloatArray("d216", d216);
                        bundle.putFloatArray("d220", d220);
                        bundle.putFloatArray("d224", d224);
                        bundle.putFloatArray("d232", d232);
                        bundle.putFloatArray("d236", d236);
                        bundle.putFloatArray("d240", d240);
                        bundle.putFloatArray("d248", d248);
                        bundle.putFloatArray("d252", d252);
                        bundle.putFloatArray("d256", d256);
                        bundle.putFloatArray("d620", d620);
                        bundle.putFloatArray("d622", d622);
                        bundle.putFloatArray("d624", d624);
                        bundle.putFloatArray("d626", d626);
                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 2;
                        handler.sendMessage(msg);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();

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
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //氧气压力原始值
                    short[] d10 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 10, 1);
                    //氧气流量原始值
                    short[] d12 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 12, 1);
                    // 氧气浓度原始值
                    short[] d14 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 14, 1);
                    //氧气温度原始值
                    short[] d16 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 16, 1);

                    //氧气压力当前值
                    float[] d212 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 212, 1);

                    //当前值流量
                    float[] d228 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 228, 1);

                    //当前值流量
                    float[] d244 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 244, 1);

                    //温度当前值
                    float[] d260 = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 260, 1);
                    Bundle bundle = new Bundle();
                    bundle.putShortArray("d10", d10);
                    bundle.putShortArray("d12", d12);
                    bundle.putShortArray("d14", d14);
                    bundle.putShortArray("d16", d16);

                    bundle.putFloatArray("d212", d212);

                    bundle.putFloatArray("d228", d228);

                    bundle.putFloatArray("d244", d244);

                    bundle.putFloatArray("d260", d260);
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
            case R.id.analog_et_oxy_max:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{200};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_flow_max:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{216};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_concentration_max:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{232};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_temp_max:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{248};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_oxy_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{204};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_flow_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{220};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_concentration_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{236};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_temp_min:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{252};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id. analog_et_oxy_correction:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{208};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_flow_correction:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{224};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_concentration_correction:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{240};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_temp_correction:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{256};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;

            case R.id.analog_et_oxy_alarm:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{620};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_flow_alarm:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{622};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_concentration_alarm:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{626};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
            case R.id.analog_et_temp_alarm:
                local=new int[2];
                v.getLocationInWindow(local);
                str = new int[]{624};
                popupForSimulaion.showPopupWindow(v,local[0],local[1],Constants.Define.OP_REAL_D,str);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        popupForSimulaion.stopPopupWindow();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
