package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hitek.serial.R;

import activity.MyApplication;
import popupwindow.PopupForTimeSetting;
import popupwindow.Pupwindow;
import utils.Constants;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
public class TimeSettingFragment extends Fragment implements View.OnClickListener {


    private TextView ATiming_1, ATiming_2, ATiming_3, ATiming_4, ATiming_5, ATiming_6, ATiming_7, ATiming_8, ATiming_9, ATiming_10, ATiming_11, ATiming_12, ATiming_13;
    private int[] local,str;
    private Button time_btn_change;
    private PopupForTimeSetting pupwindow;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
//                    Log.d("LOG",String.valueOf(msg.getData().getShort("d400")));
                    /**����дUI���º���*/
                    if(String.valueOf(msg.getData().getShort("d400"))!=null && !String.valueOf(msg.getData().getShort("d400")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d400")));
                        ATiming_1.setText(String.valueOf((float) Math.round(v * 10) / 10));


                    }
                    if(String.valueOf(msg.getData().getShort("d402"))!=null && !String.valueOf(msg.getData().getShort("d402")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d402")));
                        ATiming_2.setText(String.valueOf((float) Math.round(v * 10) / 10));



                    }
                    if(String.valueOf(msg.getData().getShort("d404"))!=null && !String.valueOf(msg.getData().getShort("d404")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d404")));
                        ATiming_3.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_3.setText(String.valueOf(msg.getData().getShort("d404")));

                    }
                    if(String.valueOf(msg.getData().getShort("d406"))!=null && !String.valueOf(msg.getData().getShort("d406")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d406")));
                        ATiming_4.setText(String.valueOf((float) Math.round(v * 10) / 10));


//                        ATiming_4.setText(String.valueOf(msg.getData().getShort("d406")));

                    }
                    if(String.valueOf(msg.getData().getShort("d408"))!=null && !String.valueOf(msg.getData().getShort("d408")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d408")));
                        ATiming_5.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_5.setText(String.valueOf(msg.getData().getShort("d408")));

                    }
                    if(String.valueOf(msg.getData().getShort("d410"))!=null && !String.valueOf(msg.getData().getShort("d410")).equals("")){

                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d410")));
                        ATiming_6.setText(String.valueOf((float) Math.round(v * 10) / 10));




                    }
                    if(String.valueOf(msg.getData().getShort("d412"))!=null && !String.valueOf(msg.getData().getShort("d412")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d412")));
                        ATiming_7.setText(String.valueOf((float) Math.round(v * 10) / 10));



                    }
                    if(String.valueOf(msg.getData().getShort("d414"))!=null && !String.valueOf(msg.getData().getShort("d414")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d414")));
                        ATiming_8.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_8.setText(String.valueOf(msg.getData().getShort("d414")));

                    }
                    if(String.valueOf(msg.getData().getShort("d416"))!=null && !String.valueOf(msg.getData().getShort("d416")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d416")));
                        ATiming_9.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_9.setText(String.valueOf(msg.getData().getShort("d416")));

                    }
                    if(String.valueOf(msg.getData().getShort("d418"))!=null && !String.valueOf(msg.getData().getShort("d418")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d418")));
                        ATiming_10.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_10.setText(String.valueOf(msg.getData().getShort("d418")));

                    }
                    if(String.valueOf(msg.getData().getShort("d420"))!=null && !String.valueOf(msg.getData().getShort("d420")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d420")));
                        ATiming_11.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_11.setText(String.valueOf(msg.getData().getShort("d420")));

                    }
                    if(String.valueOf(msg.getData().getShort("d422"))!=null && !String.valueOf(msg.getData().getShort("d422")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d422")));
                        ATiming_12.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_12.setText(String.valueOf(msg.getData().getShort("d422")));

                    }
                    if(String.valueOf(msg.getData().getShort("d424"))!=null && !String.valueOf(msg.getData().getShort("d424")).equals("")){
                        float v = Float.parseFloat(String.valueOf(msg.getData().getShort("d424")));
                        ATiming_13.setText(String.valueOf((float) Math.round(v * 10) / 10));

//                        ATiming_13.setText(String.valueOf(msg.getData().getShort("d424")));

                    }

                    break;
            }
        }
    };

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.time_layout,container,false);
        initView();
        initData();
        setData();
        return view;
    }

    /**
     * �ؼ���ʼ��
     */
    public void initView() {
        pupwindow = new PopupForTimeSetting(getContext());

        ATiming_1 = (TextView) view.findViewById(R.id.ATiming_1);
        ATiming_2 = (TextView) view.findViewById(R.id.ATiming_2);
        ATiming_3 = (TextView) view.findViewById(R.id.ATiming_3);
        ATiming_4 = (TextView) view.findViewById(R.id.ATiming_4);
        ATiming_5 = (TextView) view.findViewById(R.id.ATiming_5);
        ATiming_6 = (TextView) view.findViewById(R.id.ATiming_6);
        ATiming_7 = (TextView) view.findViewById(R.id.ATiming_7);
        ATiming_8 = (TextView) view.findViewById(R.id.ATiming_8);
        ATiming_9 = (TextView) view.findViewById(R.id.ATiming_9);
        ATiming_10 = (TextView) view.findViewById(R.id.ATiming_10);
        ATiming_11 = (TextView) view.findViewById(R.id.ATiming_11);
        ATiming_12 = (TextView) view.findViewById(R.id.ATiming_12);
        ATiming_13 = (TextView) view.findViewById(R.id.ATiming_13);
        ATiming_1.setOnClickListener(this);
        ATiming_2.setOnClickListener(this);
        ATiming_3.setOnClickListener(this);
        ATiming_4.setOnClickListener(this);
        ATiming_5.setOnClickListener(this);
        ATiming_6.setOnClickListener(this);
        ATiming_7.setOnClickListener(this);
        ATiming_8.setOnClickListener(this);
        ATiming_9.setOnClickListener(this);
        ATiming_10.setOnClickListener(this);
        ATiming_11.setOnClickListener(this);
        ATiming_12.setOnClickListener(this);
        ATiming_13.setOnClickListener(this);
    }

    /**
     * ���ݳ�ʼ��
     */
    public void initData() {


    }

    /**
     * ���ݻ�ȡ
     */
    public void getData() {


    }

    /**
     * ���ݴ洢
     */
    public void saveData() {

    }

    /**
     * ���ݸ���
     */
    public void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {

                        /**����д���ݻ�ȡ�����ݴ�����*/
                        short[] d400 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 400, 1);
                        short[] d402 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 402, 1);
                        short[] d404 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 404, 1);
                        short[] d406 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 406, 1);
                        short[] d408 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 408, 1);
                        short[] d410 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 410, 1);
                        short[] d412 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 412, 1);
                        short[] d414 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 414, 1);
                        short[] d416 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 416, 1);
                        short[] d418 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 418, 1);
                        short[] d420 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 420, 1);
                        short[] d422 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 422, 1);
                        short[] d424 =	MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 424, 1);
                        Bundle bundle = new Bundle();
                        bundle.putShort("d400", d400[0]);
                        bundle.putShort("d402", d402[0]);
                        bundle.putShort("d404", d404[0]);
                        bundle.putShort("d406", d406[0]);
                        bundle.putShort("d408", d408[0]);
                        bundle.putShort("d410", d410[0]);
                        bundle.putShort("d412", d412[0]);
                        bundle.putShort("d414", d414[0]);
                        bundle.putShort("d416", d416[0]);
                        bundle.putShort("d418", d418[0]);
                        bundle.putShort("d420", d420[0]);
                        bundle.putShort("d422", d422[0]);
                        bundle.putShort("d424", d424[0]);

                        Message msg = new Message();
                        msg.setData(bundle);
                        msg.what = 1;
                        handler.sendMessage(msg);

                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * �����������
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ATiming_1:
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{400});
                break;
            case R.id.ATiming_2:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{402});
                break;
            case R.id.ATiming_3:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{404});
                break;
            case R.id.ATiming_4:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{406});
                break;
            case R.id.ATiming_5:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{408});
                break;
            case R.id.ATiming_6:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{410});
                break;
            case R.id.ATiming_7:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{412});
                break;
            case R.id.ATiming_8:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{414});
                break;
            case R.id.ATiming_9:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{416});
                break;
            case R.id.ATiming_10:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{418});
                break;
            case R.id.ATiming_11:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{420});
                break;
            case R.id.ATiming_12:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{422});
                break;
            case R.id.ATiming_13:
                local=new int[2];
                v.getLocationInWindow(local);
                pupwindow.showPopupWindow(v,Constants.Define.OP_WORD_D,new int[]{424});
                break;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        pupwindow.stopPopupWindow();
    }
}
