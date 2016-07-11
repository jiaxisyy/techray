package fragment;

import android.annotation.TargetApi;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.support.v4.app.Fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import utils.ReadAndWrite;
import utils.Utils;
import view.NoDoubleClickListener;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class MonitorFragment extends Fragment implements View.OnTouchListener {
    private Handler handler = new Handler() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    /**����дUI���º���*/
                    pressureData = msg.getData().getString("i1");
                    concentrationData = msg.getData().getString("j1");
                    flowData = msg.getData().getString("k1");
                    totalflowData = msg.getData().getString("l1");
                    if (msg.getData().getShort("d700") == 0) {
                        momitor_btn_machine_a.setBackground(getResources().getDrawable(R.drawable.stop_lamp));
                    } else if (msg.getData().getShort("d700") == 1) {
                        momitor_btn_machine_a.setBackground(getResources().getDrawable(R.drawable.running_lamp));
                    } else if (msg.getData().getShort("d700") == 2) {
                        momitor_btn_machine_a.setBackground(getResources().getDrawable(R.drawable.waitting_lamp));
                    }
                    momitor_btn_machine_a.setText(msg.getData().getString("d458") + "时");
                    if (msg.getData().getStringArray("main")[0] != null && !msg.getData().getStringArray("main")[0].equals("")) {
                        pressure_main.setText(msg.getData().getStringArray("main")[0]);
                    }
                    if (msg.getData().getStringArray("main")[1] != null && !msg.getData().getStringArray("main")[1].equals("")) {
                        totalflow_main.setText(msg.getData().getStringArray("main")[1]);
                    }
                    if (msg.getData().getStringArray("main")[2] != null && !msg.getData().getStringArray("main")[2].equals("")) {
                        flow_main.setText(msg.getData().getStringArray("main")[2]);
                    }
                    if (msg.getData().getStringArray("main")[3] != null && !msg.getData().getStringArray("main")[3].equals("")) {
                        concentration_main.setText(msg.getData().getStringArray("main")[3]);
                        concentration_main_biaopan.setText(msg.getData().getStringArray("main")[3]);
                        if(Float.parseFloat(msg.getData().getStringArray("main")[3])<=50){
                            animotion((float) ((Float.parseFloat(msg.getData().getStringArray("main")[3])/100-0.5)*150));
                        }
                    }
                    break;
                case 2:
                    //
                    if (stringBuffer.length() > 0) {
                        //有报警信息
                        ll_main_warning.setVisibility(View.VISIBLE);
                        tv_main_warning.setText(stringBuffer.toString());
                    } else if (stringBuffer.length() == 0) {
                        ll_main_warning.setVisibility(View.GONE);
                        ll_main_warning.layout(ll_main_warning.getLeft(), ll_main_warning.getTop(), ll_main_warning.getRight(), ll_main_warning.getBottom());
                    }
                    break;
            }
        }
    };

    static Logger logger = Logger.getLogger("com.hitek.serial");
    static boolean is_run_flag = false;
    private Boolean btn_flag = true;

    //ѭ����־
    private boolean flag = true;
    private Button momitor_btn_machine_a, momitor_btn_start, momitor_btn_stop;
    private View view;
    private MainRecycleViewAdapter mainRecycleView1, mainRecycleView2;
    private RecyclerView recyclerView1, recyclerView2;
    private List<MainRecycleViewItem> list1;
    private List<MainRecycleViewItem> list2;
    private String pressureData, concentrationData, flowData, totalflowData;
    private LinearLayout ll_main_warning;
    private TextView tv_main_warning;
    private StringBuffer stringBuffer;
    private TextView pressure_main, concentration_main, flow_main, totalflow_main,concentration_main_biaopan;
    private ImageView needle;
   private long begin=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.monitor_layout, container, false);
        initData();
        initView();
        setData();
        return view;
    }


    /**
     * �ؼ���ʼ��
     */
    public void initView() {
//        recyclerView1 = (RecyclerView) view.findViewById(R.id.main_recycleview_normal);
//        recyclerView2 = (RecyclerView) view.findViewById(R.id.main_recycleview_big);
//        recyclerView1.setLayoutManager(new GLayoutManager(getContext(), 3));
//        recyclerView2.setLayoutManager(new GLayoutManager(getContext(), 1));
//        mainRecycleView1 = new MainRecycleViewAdapter(getContext(), list1, true);
//        mainRecycleView2 = new MainRecycleViewAdapter(getContext(), list2, false);
//        recyclerView1.setAdapter(mainRecycleView1);
//        recyclerView2.setAdapter(mainRecycleView2);
        ll_main_warning = (LinearLayout) view.findViewById(R.id.ll_main_warning);
        tv_main_warning = (TextView) view.findViewById(R.id.tv_main_warning);
        pressure_main = (TextView) view.findViewById(R.id.pressure_main);
        concentration_main = (TextView) view.findViewById(R.id.concentration_main);
        flow_main = (TextView) view.findViewById(R.id.flow_main);
        totalflow_main = (TextView) view.findViewById(R.id.totalflow_main);
        concentration_main_biaopan=(TextView)view.findViewById(R.id.concentration_main_biaopan);
        needle = (ImageView) view.findViewById(R.id.needle);
//
        momitor_btn_machine_a = (Button) view.findViewById(R.id.momitor_btn_machine_a);
        momitor_btn_start = (Button) view.findViewById(R.id.momitor_btn_start);
        momitor_btn_stop = (Button) view.findViewById(R.id.momitor_btn_stop);
        momitor_btn_stop.setOnTouchListener(this);
        momitor_btn_start.setOnTouchListener(this);
//
//        mainRecycleView1.setOnItemClickLitener(new MainRecycleViewAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, final int position) {
//
//                if (Utils.isValidClick()) {
//                    List<MainRecycleViewItem> saveList = new ArrayList<MainRecycleViewItem>();
//                    saveList.add(list2.get(0));
//                    saveList.add(list1.get(position));
//                    mainRecycleView2.removeData(saveList.get(1));
//                    mainRecycleView1.removeData(position, saveList.get(0));
//                }
//
//
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//
//            }
//        });

    }

    /**
     * ���ݳ�ʼ��
     */

    public void initData() {
//        list1 = new ArrayList<MainRecycleViewItem>();
//        list2 = new ArrayList<MainRecycleViewItem>();
//        MainRecycleViewItem mPressure = new MainRecycleViewItem();
//        MainRecycleViewItem mConcentration = new MainRecycleViewItem();
//        MainRecycleViewItem mFlow = new MainRecycleViewItem();
//        MainRecycleViewItem mTotalFlow = new MainRecycleViewItem();
//        mPressure.setBackground(getResources().getDrawable(R.drawable.pressure_normal));
//        mPressure.setBackgroundBig(getResources().getDrawable(R.drawable.pressure_big));
//        mPressure.setType("当前压力");
//        mPressure.setData("1111");
//        mPressure.setUnit("kg/cm²");
//        mPressure.setStr("212");
//
//        mConcentration.setBackground(getResources().getDrawable(R.drawable.concentration_normal));
//        mConcentration.setBackgroundBig(getResources().getDrawable(R.drawable.concentration_big));
//        mConcentration.setType("当前浓度");
//        mConcentration.setData("2222");
//        mConcentration.setUn it("%");
//
//        mConcentration.setStr("244");
//        mFlow.setBackground(getResources().getDrawable(R.drawable.flow_normal));
//        mFlow.setBackgroundBig(getResources().getDrawable(R.drawable.flow_big));
//        mFlow.setType("当前流量");
//        mFlow.setData("333");
//        mFlow.setUnit("L/min");
//
//        mFlow.setStr("228");
//        mTotalFlow.setBackground(getResources().getDrawable(R.drawable.totalflow_nromal));
//        mTotalFlow.setBackgroundBig(getResources().getDrawable(R.drawable.totalflow_big));
//        mTotalFlow.setType("累积流量");
//        mTotalFlow.setData("4444");
//        mTotalFlow.setUnit("m³");
//
//        mTotalFlow.setStr("264");
//        list1.add(mPressure);
//        list1.add(mConcentration);
//        list1.add(mTotalFlow);
//        list2.add(mFlow);
    }

    /**
     * ���ݻ�ȡ
     */
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

//                /**����д���ݻ�ȡ�����ݴ�����*/
//                Log.d("TAG", "run");

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
                        int[] strID = {212, 333, 264, 244};
                        String[] main = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D, strID);


                        short[] d700 = MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 700, 1);
                        Bundle bundle = new Bundle();
                        int[] d458 = MyApplication.getInstance().mdbusreaddword(Constants.Define.OP_DWORD_D, 458, 1);

                        bundle.putString("i1", String.valueOf(i1));
                        bundle.putString("j1", String.valueOf(j1));
                        bundle.putString("k1", String.valueOf(k1));
                        bundle.putString("l1", String.valueOf(l1));
                        bundle.putString("d458", String.valueOf(d458[0]));
                        bundle.putStringArray("main", main);
                        if (d700.length > 0) {
                            bundle.putShort("d700", d700[0]);
                        }
                        final int[] ints = {50, 51, 52, 53, 54, 55, 57};
                        final String[] strings = new String[]{"氧气压力过高;", "氧气压力过低;", "氧气浓度过低;", "氧气流量过高;", "露点/温度过高;", "露点/温度过低;", "设备锁机;"};

                        // stringBuffer.append("\"氧气压力过高;\", \"氧气压力过低;\", \"氧气浓度过低;\", \"氧气流量过高;\", \"露点/温度过高;\", \"露点/温度过低;\"");
                        String[] str = ReadAndWrite.ReadJni(Constants.Define.OP_BIT_M, ints);
                        stringBuffer = new StringBuffer();
                        if (str != null) {
                            int length = str.length;
                            for (int p = 0; p < length; p++) {
                                int index = Integer.parseInt(str[p]);
                                if (index == 1) {
                                    stringBuffer.append(strings[p]);
                                }
                            }
                            Message obtain = Message.obtain();
                            obtain.what = 2;
                            handler.sendMessage(obtain);
                        }
                        Message msg = new Message();
                        msg.what = 1;
                        msg.setData(bundle);
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
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
        flag = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {

            case R.id.momitor_btn_start:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_flag = false;
                    momitor_btn_start.setSelected(btn_flag);

                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, b, 10, 1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_flag = true;
                    momitor_btn_start.setSelected(btn_flag);

                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, b, 10, 1);

                }
                break;
            case R.id.momitor_btn_stop:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_flag = false;
                    momitor_btn_stop.setSelected(btn_flag);
                    TimerTask timerTask = new TimerTask() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            byte[] b = {0};
                            MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, b, 101, 1);
                        }
                    };
                    Timer time = new Timer();
                    time.schedule(timerTask, 500);

                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_flag = true;
                    momitor_btn_stop.setSelected(btn_flag);
                    byte[] b = {1};
                    MyApplication.getInstance().mdbuswritebyte(Constants.Define.OP_BIT_M, b, 101, 1);
                }
                break;
        }
        return false;
    }

    private void animotion(float degrees) {
        AnimationSet animationSet = new AnimationSet(true);
        /**
         * 前两个参数定义旋转的起始和结束的度数，后两个参数定义圆心的位置
         */
        RotateAnimation rotateAnimation = new RotateAnimation(begin, degrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.88f);
        rotateAnimation.setDuration(1000);
        animationSet.addAnimation(rotateAnimation);
        needle.startAnimation(animationSet);
        begin = (long) degrees;
    }
}
