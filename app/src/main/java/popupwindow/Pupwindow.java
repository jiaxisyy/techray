package popupwindow;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.hitek.serial.R;

import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/5/12.
 */
public class Pupwindow extends Activity implements View.OnClickListener {
    private PopupWindow  popupWindow;
    private View contentView;
    private EditText pup_et;
    private int type;
    private int[] address;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public Pupwindow(Context context, View view, float x, float y, int type, int[] address){
        this.type = type;
        this.address = address;
        contentView =LayoutInflater.from(context).inflate(R.layout.popupwindow_layout, null);
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        pup_et = (EditText) contentView.findViewById(R.id.pup_et);
        pup_et.setWidth(view.getWidth());
        pup_et.setHeight(view.getHeight());
        pup_et.setBackground(view.getBackground());
        Button pup_comfirm = (Button)contentView.findViewById(R.id.pup_comfirm);
        Button pup_cancel = (Button)contentView.findViewById(R.id.pup_cancel);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, (int) x, (int) y);
        pup_comfirm.setOnClickListener(this);
        pup_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pup_comfirm:
                if(pup_et.getText().toString()!=null && !pup_et.getText().toString().equals("")){
                    String[] input ={pup_et.getText().toString()};
                    new ReadAndWrite().WriteJni(type,address,input);
                }
                popupWindow.dismiss();
                break;
            case R.id.pup_cancel:
                popupWindow.dismiss();
                break;
        }
    }
}