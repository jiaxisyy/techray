package activity;



import com.hitek.serial.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;


/**
 * ��������ҳ��
 * Created by zuheng.lv on 2016/4/26.
 */
public class RunActivity extends Activity implements View.OnClickListener {



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    /**����дUI���º���*/
                    break;
            }
        }
    };

    private Button run_btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.run_layout);
        initView();
        initData();
    }

    /**�ؼ���ʼ��*/
    public void initView(){
        run_btn_back = (Button) findViewById(R.id.run_btn_back);
        run_btn_back.setOnClickListener(this);

    }
    /**���ݳ�ʼ��*/
    public void initData(){


    }

    /**���ݻ�ȡ*/
    public void getData(){


    }

    /**���ݸ���*/
    public void setData(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                /**���д���ݻ�ȡ�����ݴ�����*/
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();
    }

    /**�����������*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.run_btn_back:
                Intent intent =new Intent(RunActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }
    }


}
