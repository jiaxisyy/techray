package popupwindow;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.renderscript.ScriptGroup;
import android.renderscript.Type;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hitek.serial.R;

import utils.ReadAndWrite;

/**
 * Created by shuang.xiang on 2016/6/13.
 */
public class PopupForWeek implements View.OnClickListener {
    private PopupWindow popupWindow;
    private View contentView;
    private EditText pup_et;
    private int type;
    private int[] address;
    private View view;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public PopupForWeek(Context context){
        contentView = LayoutInflater.from(context).inflate(R.layout.pp_input, null);
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT,false);
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setAnimationStyle(R.style.AnimationPreview);
        pup_et = (EditText) contentView.findViewById(R.id.pup_et);
        Button pup_comfirm = (Button)contentView.findViewById(R.id.pup_comfirm);
        TextView pup_cancel = (TextView)contentView.findViewById(R.id.pup_cancel);
        pup_comfirm.setOnClickListener(this);
        pup_cancel.setOnClickListener(this);
    }
    public void showPopupWindow( View view, int type, int[] address){
        this.view = view;
        this.type = type;
        this.address = address;
        if(!popupWindow.isShowing()){
            pup_et.setText("");
            pup_et.setInputType(InputType.TYPE_CLASS_TEXT);
            popupWindow.showAtLocation(contentView, Gravity.FILL,0,0);
        }
    }
    public void stopPopupWindow(){

        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pup_comfirm:
                String[] input ;
                if(pup_et.getText().toString()!=null && !pup_et.getText().toString().equals("")){
                    if(pup_et.getText().toString().equals("一") || pup_et.getText().toString()=="1"|| pup_et.getText().toString().equals("1")){
                         input = new String[]{"1"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("二") || pup_et.getText().toString()=="2"|| pup_et.getText().toString().equals("2")){
                        input = new String[]{"2"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("三") || pup_et.getText().toString()=="3"|| pup_et.getText().toString().equals("3")){
                        input = new String[]{"3"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("四") || pup_et.getText().toString()=="4"|| pup_et.getText().toString().equals("4")){
                        input = new String[]{"4"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("五") || pup_et.getText().toString()=="5"|| pup_et.getText().toString().equals("5")){
                        input = new String[]{"5"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("六") || pup_et.getText().toString()=="6"|| pup_et.getText().toString().equals("6")){
                        input = new String[]{"6"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                    if(pup_et.getText().toString().equals("天") || pup_et.getText().toString()=="7"|| pup_et.getText().toString().equals("7")){
                        input = new String[]{"0"};
                        new ReadAndWrite().WriteJni(type,address,input);
                    }
                }
                popupWindow.dismiss();
                break;
            case R.id.pup_cancel:
                popupWindow.dismiss();
                break;
        }
    }
}
