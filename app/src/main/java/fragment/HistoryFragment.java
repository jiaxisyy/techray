package fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hitek.serial.R;

import java.util.List;

import adapter.RecyclerViewAdapter;
import SQL.DataBaseHelper;
import bean.HistoryData;
import SQL.SqlManager;

/**
 * Created by zuheng.lv on 2016/6/10.
 */
public class HistoryFragment extends Fragment{
    private View view;
    private List<HistoryData> list;

    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private Button history_btn_search;
    private TextView history_startdate,history_starttime,history_stopdate,history_stoptime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        history_btn_search = (Button) view.findViewById(R.id.history_btn_search);
        history_startdate = (TextView)view.findViewById(R.id.history_startdate);
        history_starttime = (TextView)view.findViewById(R.id.history_starttime);
        history_stopdate = (TextView)view.findViewById(R.id.history_stopdate);
        history_stoptime = (TextView)view.findViewById(R.id.history_stoptime);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"history.db",null,1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor1 =  sqLiteDatabase.query("history",new String[]{"date","time"},null,null,null,null,"historyID","1");
        Cursor cursor2 =  sqLiteDatabase.query("history",new String[]{"date","time"},null,null,null,null,"historyID desc","1");
        if(cursor1.moveToFirst()){
            history_startdate.setText(cursor1.getString(0));
            history_starttime.setText(cursor1.getString(1));
        }
    if (cursor2.moveToFirst()){
        System.out.println(cursor2.getString(1));
        history_stopdate.setText(cursor2.getString(0));
        history_stoptime.setText(cursor2.getString(1));
    }
    }
}
