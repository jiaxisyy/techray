package popupwindow;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.hitek.serial.R;

import java.util.List;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
public class PopupForSimulaion {

        private View contentView;

    public PopupForSimulaion(Context context, View view){
        PopupWindow popupWindow = new PopupWindow(context);
        contentView = LayoutInflater.from(context).inflate(R.layout.popupforsimulaion_layout,null);
        popupWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT,false);
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
        popupWindow.showAtLocation(view, Gravity.LEFT|Gravity.TOP,0,0);
    }
}
