package fragment;

import android.annotation.TargetApi;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hitek.serial.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import activity.MyApplication;
import adapter.MainRecycleViewAdapter;
import bean.GLayoutManager;
import bean.MainRecycleViewItem;
import utils.Constants;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class MonitorFragment extends Fragment implements  View.OnTouchListener {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**����дUI���º���*/
                    pressureData =  msg.getData().getString("i1");
                    concentrationData =  msg.getData().getString("j1");
                    flowData =  msg.getData().getString("k1");
                    totalflowData =  msg.getData().getString("l1");
                    momitor_btn_machine_a.setSelected(msg.getData().getBoolean("m11"));
                    break;
            }
        }
    };

    static Logger logger = Logger.getLogger("com.hitek.serial");
    static boolean is_run_flag = false;
    private Boolean  btn_flag = true;

    //ѭ����־
    private boolean flag = true;
    private Button momitor_btn_machine_a,momitor_btn_start,momitor_btn_stop;
    private View view;
    private MainRecycleViewAdapter mainRecycleView1,mainRecycleView2;
    private RecyclerView recyclerView1,recyclerView2;
    private List<MainRecycleViewItem> list1;
    private List<MainRecycleViewItem> list2;
    private String pressureData,concentrationData,flowData,totalflowData;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.monitor_layout,container,false);
        initData();
        initView();
        setData();
        return view;
    }



    /**
     * �ؼ���ʼ��
     */
    public void initView() {
        recyclerView1 = (RecyclerView) view.findViewById(R.id.main_recycleview_normal);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.main_recycleview_big);
        recyclerView1.setLayoutManager(new GLayoutManager(getContext(),3));
        recyclerView2.setLayoutManager(new GLayoutManager(getContext(),1));
        mainRecycleView1 = new MainRecycleViewAdapter(getContext(),list1,true);
        mainRecycleView2 = new MainRecycleViewAdapter(getContext(),list2,false);
        recyclerView1.setAdapter(mainRecycleView1);
        recyclerView2.setAdapter(mainRecycleView2);

        momitor_btn_machine_a = (Button) view.findViewById(R.id.momitor_btn_machine_a);
        momitor_btn_start= (Button) view.findViewById(R.id.momitor_btn_start);
        momitor_btn_stop = (Button) view.findViewById(R.id.momitor_btn_stop);
        momitor_btn_stop.setOnTouchListener(this);
        momitor_btn_start.setOnTouchListener(this);
        mainRecycleView1.setOnItemClickLitener(new MainRecycleViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                List<MainRecycleViewItem> saveList = new ArrayList<MainRecycleViewItem>();
                saveList.add(list2.get(0));
                saveList.add(list1.get(position));
                mainRecycleView2.removeData(saveList.get(1));
                mainRecycleView1.removeData(position, saveList.get(0));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    /**
     * ���ݳ�ʼ��
     */

    public void initData() {
        list1 = new ArrayList<MainRecycleViewItem>();
        list2 = new ArrayList<MainRecycleViewItem>();
        MainRecycleViewItem mPressure = new MainRecycleViewItem();
        MainRecycleViewItem mConcentration = new MainRecycleViewItem();
        MainRecycleViewItem mFlow = new MainRecycleViewItem();
        MainRecycleViewItem mTotalFlow = new MainRecycleViewItem();
        mPressure.setBackground(getResources().getDrawable(R.drawable.pressure_normal));
        mPressure.setBackgroundBig(getResources().getDrawable(R.drawable.pressure_big));
        mPressure.setType("当前压力");
        mPressure.setData("1111");
        mPressure.setUnit("kg/cm²");
        mPressure.setStr("212");
        mPressure.setHeight(100);
        mConcentration.setBackground(getResources().getDrawable(R.drawable.concentration_normal));
        mConcentration.setBackgroundBig(getResources().getDrawable(R.drawable.concentration_big));
        mConcentration.setType("当前浓度");
        mConcentration.setData("2222");
        mConcentration.setStr("%");
        mConcentration.setHeight(100);
        mConcentration.setStr("244");
        mFlow.setBackground(getResources().getDrawable(R.drawable.flow_normal));
        mFlow.setBackgroundBig(getResources().getDrawable(R.drawable.flow_normal));
        mFlow.setType("当前流量");
        mFlow.setData("333");
        mFlow.setStr("L/min");
        mFlow.setHeight(100);
        mFlow.setStr("228");
        mTotalFlow.setBackground(getResources().getDrawable(R.drawable.totalflow_nromal));
        mTotalFlow.setBackgroundBig(getResources().getDrawable(R.drawable.totalflow_big));
        mTotalFlow.setType("累积流量");
        mTotalFlow.setData("4444");
        mTotalFlow.setStr("m³");
        mTotalFlow.setHeight(220);
        mTotalFlow.setStr("264");
        list1.add(mPressure);
        list1.add(mConcentration);
        list1.add(mFlow);
        list2.add(mTotalFlow);
    }

    /**
     * ���ݻ�ȡ
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void getData() {

    }

    /**
     * ���ݸ���
     */
    public void setData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
//                float   a  =   123.2334f;
//                float  b   =  (float)(Math.round(a*100))/100;(�����100����2λС����,���Ҫ����λ,��4λ,��������100�ĳ�10000)

                /**����д���ݻ�ȡ�����ݴ�����*/
                Log.d("TAG", "run");
                while (flag) {
                    try {
                        //����ѹ��ֵ
                        float[] i = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 212, 1);
                        float i1 = (float) (Math.round(i[0] * 10)) / 10;
                        //����ͳ��
                        float[] j = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 264, 1);
                        float j1 = (float) (Math.round(j[0] * 100)) / 100;
                        //��������
                        float[] k = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 228, 1);
                        float k1 = (float) (Math.round(k[0] * 10000)) / 10000;
                        //����Ũ��
                        float[] l = MyApplication.getInstance().mdbusreadreal(Constants.Define.OP_REAL_D, 244, 1);
                        float l1 = (float) (Math.round(l[0] * 100)) / 100;
                        //A������״̬
                        byte[]m11 =  MyApplication.getInstance().mdbusreadbyte(Constants.Define.OP_BIT_M,11,1);

                        Bundle bundle = new Bundle();

                        bundle.putString("i1", String.valueOf(i1));
                        bundle.putString("j1", String.valueOf(j1));
                        bundle.putString("k1", String.valueOf(k1));
                        bundle.putString("l1", String.valueOf(l1));

                        if(m11[0] == 1){
                            bundle.putBoolean("m11",true);
                        }else{
                            bundle.putBoolean("m11",false);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {

            case R.id.momitor_btn_start:
                if(event.getAction()==MotionEvent.ACTION_UP){
                    btn_flag=false;
                    momitor_btn_start.setSelected(btn_flag);

                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,10,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btn_flag=true;
                    momitor_btn_start.setSelected(btn_flag);

                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,10,1);

                }
                break;
            case R.id.momitor_btn_stop:
                if(event.getAction()==MotionEvent.ACTION_UP){
                    btn_flag=false;
                    momitor_btn_stop.setSelected(btn_flag);
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,101,1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);

                }
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    btn_flag=true;
                    momitor_btn_stop.setSelected(btn_flag);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M,b,101,1);
                }
                break;
        }
        return false;
    }
}
