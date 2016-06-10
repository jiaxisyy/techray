package activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.hitek.serial.R;
import utils.Utils;
/**
 * Created by zuheng.lv on 2016/6/3.
 */
public class testActivity3 extends android.support.v4.app.Fragment implements View.OnClickListener {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.second_fragment_layout,container,false);
        Button btn1 = (Button)view.findViewById(R.id.btn_frag1);
        btn1.setOnClickListener(this);
        Button btn2 = (Button)view.findViewById(R.id.btn_frag2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button)view.findViewById(R.id.btn_frag3);
        btn3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onStart() {
        Utils.replace(getFragmentManager(),R.id.frag2,ATimingActivity.class);
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_frag1:
                Utils.replace(getFragmentManager(),R.id.frag2,ATimingActivity.class);
                break;
            case R.id.btn_frag2:
                Utils.replace(getFragmentManager(),R.id.frag2,AValveActivity.class);
                break;
            case R.id.btn_frag3:
                Utils.replace(getFragmentManager(),R.id.frag2, HistoryActivity.class);
                break;
        }
    }
}
