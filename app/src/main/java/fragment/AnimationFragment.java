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

import com.hitek.serial.R;

import bean.AnimationSingleView;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class AnimationFragment extends Fragment{
    private View view;
    private Button animation_btn_switch;
    private AnimationSingleView animationView_single;
    private Boolean flag=true;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.getData().getString("d700").equals("0")){
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.stop_lamp));
                animationView_single.stopDraw();
            }else if(msg.getData().getString("d700").equals("1")){
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.running_lamp));
                animationView_single.startDraw();
            }else if(msg.getData().getString("d700").equals("2")){
                animation_btn_switch.setBackground(getResources().getDrawable(R.drawable.waitting_lamp));
                animationView_single.stopDraw();
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.animation_single_layout,container,false);
        initView();
        setData();
        return view;
    }
    private void initView(){
        animation_btn_switch=(Button) view.findViewById(R.id.animation_btn_switch);
        animationView_single = (AnimationSingleView) view.findViewById(R.id.animationView_single);
    }

    private void setData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag){
                    String[]D700 = ReadAndWrite.ReadJni(1,new int[]{700});
                    Bundle bundle = new Bundle();
                    bundle.putString("1",D700[0]);
                    Message msg = new Message();
                    msg.setData(bundle);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
