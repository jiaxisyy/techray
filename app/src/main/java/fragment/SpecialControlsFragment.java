package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hitek.serial.R;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class SpecialControlsFragment extends Fragment {

    private TextView tvidrevise;
    private EditText edsystemsettingrevise;
    private TextView tvidprotect;
    private EditText edsystemsettingprotect;
    private TextView tvplcyear;
    private TextView tvplcmonth;
    private TextView tvplcday;
    private TextView tvplchour;
    private TextView tvplcminute;
    private TextView tvplcsecond;
    private Button btntimeseriesaffirm;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_special_controls, container, false);


        return view;
    }

    private void initialize() {

        tvidrevise = (TextView) view.findViewById(R.id.tv_id_revise);
        edsystemsettingrevise = (EditText) view.findViewById(R.id.ed_system_setting_revise);
        tvidprotect = (TextView) view.findViewById(R.id.tv_id_protect);
        edsystemsettingprotect = (EditText) view.findViewById(R.id.ed_system_setting_protect);
        tvplcyear = (TextView) view.findViewById(R.id.tv_plc_year);
        tvplcmonth = (TextView) view.findViewById(R.id.tv_plc_month);
        tvplcday = (TextView) view.findViewById(R.id.tv_plc_day);
        tvplchour = (TextView) view.findViewById(R.id.tv_plc_hour);
        tvplcminute = (TextView) view.findViewById(R.id.tv_plc_minute);
        tvplcsecond = (TextView) view.findViewById(R.id.tv_plc_second);
        btntimeseriesaffirm = (Button) view.findViewById(R.id.btn_time_series_affirm);
    }
}
