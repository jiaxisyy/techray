package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitek.serial.R;

/**
 * Created by zuheng.lv on 2016/6/9.
 */
public class TimeSeriesFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time_series,container,false);
        return view;
    }
}
