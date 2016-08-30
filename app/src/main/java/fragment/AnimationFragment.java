package fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hitek.serial.R;

import activity.MyApplication;
import bean.AnimationSingleView;
import utils.Constants;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class AnimationFragment extends Fragment{
    private View view;
    private Button animation_btn_switch;
    private Boolean flag=true;

    Handler handler = new Handler(){
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.getData().getShort("d700")==0){
//                System.out.println(msg.getData().getShort("d700"));
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.stop_lamp));
            }else if(msg.getData().getShort("d700")==1){
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.running_lamp));
            }else if(msg.getData().getShort("d700")==2){
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.waitting_lamp));
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.animation_single_layout,container,false);
        initView();
        setData();
        return view;
    }
    private void initView(){
        animation_btn_switch=(Button) view.findViewById(R.id.animation_btn_switch);
    }

    private void setData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    short[] d700 =    MyApplication.getInstance().mdbusreadword(Constants.Define.OP_WORD_D, 700, 1);
                    Bundle bundle = new Bundle();
                    if(d700.length>0){
                        bundle.putShort("d700",d700[0]);
                    }
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onPause() {
        super.onPause();
        onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
