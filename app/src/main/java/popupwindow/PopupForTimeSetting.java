package popupwindow;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hitek.serial.R;
import com.koushikdutta.ion.builder.Builders;

import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
//public class PopupForTimeSetting implements View.OnClickListener {

//    private EditText ATiming_1, ATiming_2, ATiming_3, ATiming_4, ATiming_5, ATiming_6, ATiming_7, ATiming_8, ATiming_9, ATiming_10, ATiming_11, ATiming_12, ATiming_13;
//    private Button time_btn_change,time_btn_cancel;
//    private View contentView;
//    private PopupWindow popupWindow;
//    private int type;
//    private int[] address;
//    private View view;
//    public PopupForTimeSetting(Context context, View view, int type, int[] address){
//        this.view = view;
//        this.type =type;
//        this.address = address;
//        contentView = LayoutInflater.from(context).inflate(R.layout.time_layout,null);
//        initView();
//        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
//        popupWindow.setWidth(contentView.getWidth());
//        System.out.println(view.getWidth());
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//    }
//
//    private void initView() {
//        ATiming_1 = (EditText) contentView.findViewById(R.id.ATiming_1);
//        ATiming_2 = (EditText) contentView.findViewById(R.id.ATiming_2);
//        ATiming_3 = (EditText) contentView.findViewById(R.id.ATiming_3);
//        ATiming_4 = (EditText) contentView.findViewById(R.id.ATiming_4);
//        ATiming_5 = (EditText) contentView.findViewById(R.id.ATiming_5);
//        ATiming_6 = (EditText) contentView.findViewById(R.id.ATiming_6);
//        ATiming_7 = (EditText) contentView.findViewById(R.id.ATiming_7);
//        ATiming_8 = (EditText) contentView.findViewById(R.id.ATiming_8);
//        ATiming_9 = (EditText) contentView.findViewById(R.id.ATiming_9);
//        ATiming_10 = (EditText) contentView.findViewById(R.id.ATiming_10);
//        ATiming_11 = (EditText) contentView.findViewById(R.id.ATiming_11);
//        ATiming_12 = (EditText) contentView.findViewById(R.id.ATiming_12);
//        ATiming_13 = (EditText) contentView.findViewById(R.id.ATiming_13);
//        ATiming_1.setClickable(true);
//        ATiming_2.setClickable(true);
//        ATiming_3.setClickable(true);
//        ATiming_4.setClickable(true);
//        ATiming_5.setClickable(true);
//        ATiming_6.setClickable(true);
//        ATiming_7.setClickable(true);
//        ATiming_8.setClickable(true);
//        ATiming_9.setClickable(true);
//        ATiming_10.setClickable(true);
//        ATiming_11.setClickable(true);
//        ATiming_12.setClickable(true);
//        ATiming_13.setClickable(true);
//
//        time_btn_change.setText("确定");
//        time_btn_cancel.setVisibility(View.VISIBLE);
//        time_btn_cancel.setOnClickListener(this);
//        time_btn_change.setOnClickListener(this);
//    }
//
//    public void showPopupWindow(){
//        if(!popupWindow.isShowing()){
//            popupWindow.showAtLocation(view, Gravity.FILL_VERTICAL,0,0);
//        }
//    }
//    public void stopPopupWindow(){
//
//        if(popupWindow.isShowing()){
//            popupWindow.dismiss();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.systemsetting_btn_change:
//                String[] input ={ATiming_1.getText().toString(),ATiming_2.getText().toString(),ATiming_3.getText().toString(),ATiming_4.getText().toString(),ATiming_5.getText().toString(),ATiming_6.getText().toString(),ATiming_7.getText().toString(),ATiming_8.getText().toString(),ATiming_9.getText().toString(),ATiming_10.getText().toString(),ATiming_11.getText().toString(),ATiming_12.getText().toString(),ATiming_13.getText().toString()};
//                new ReadAndWrite().WriteJni(type,address,input);
//                popupWindow.dismiss();
//                break;
//            case R.id.systemsetting_btn_cancel:
//                popupWindow.dismiss();
//                break;
//        }
//    }
//}
