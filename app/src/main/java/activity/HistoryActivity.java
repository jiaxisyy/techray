package activity;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitek.serial.R;

import java.util.List;

import adapter.RecyclerViewAdapter;
import bean.HistoryData;
import SQL.SqlManager;

/**
 * Created by zuheng.lv on 2016/6/3.
 */
public class HistoryActivity extends Fragment{

    private View view;
    private List<HistoryData> list;

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_layout,container,false);
        initData();
        return view;
    }

    public void initData(){
        list = new SqlManager(getContext(),"history.db",null,1).searchHistory();
        recyclerView = (RecyclerView) view.findViewById(R.id.history_recycle);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(),list);
        recyclerView.setAdapter(recyclerViewAdapter );
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
