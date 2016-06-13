package fragment;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hitek.serial.R;

import java.util.ArrayList;
import java.util.List;

import SQL.DataBaseHelper;
import SQL.SqlManager;
import adapter.AlarmRecordRecycleViewAdapter;
import bean.AlarmRecordData;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
public class AlarmRecordFragment extends android.support.v4.app.Fragment{

    private View view ;
    private AlarmRecordRecycleViewAdapter alarmRecordRecycleViewAdapter ;
    private RecyclerView alarmrecord_recycle;
    private TextView alarmrecord_startdate,alarmrecord_starttime,alarmrecord_stopdate,alarmrecord_stoptime;
    private List<AlarmRecordData> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.alarmrecord_layout,container,false);
        initView();
        initData();
        return view;
    }
    public void initView(){
        alarmrecord_recycle = (RecyclerView)view.findViewById(R.id.alarmrecord_recycle);
        alarmrecord_startdate = (TextView)view.findViewById(R.id.alarmrecord_startdate);
        alarmrecord_starttime = (TextView)view.findViewById(R.id.alarmrecord_starttime);
        alarmrecord_stopdate = (TextView)view.findViewById(R.id.alarmrecord_stopdate);
        alarmrecord_stoptime = (TextView)view.findViewById(R.id.alarmrecord_stoptime);
    }
    public void initData(){
        list = new ArrayList<>();
        SqlManager sqlManager = new SqlManager(getContext(),"history.db",null,1);
        list = sqlManager.searchAlarmRecord();
        System.out.println(list.get(0).getType());
        alarmRecordRecycleViewAdapter =new AlarmRecordRecycleViewAdapter(getContext(),list);
        alarmrecord_recycle.setAdapter(alarmRecordRecycleViewAdapter);
        alarmrecord_recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getContext(),"history.db",null,1);
        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor1 =  sqLiteDatabase.query("alarm",new String[]{"date","time"},null,null,null,null,"alarmID","1");
        Cursor cursor2 =  sqLiteDatabase.query("alarm",new String[]{"date","time"},null,null,null,null,"alarmID desc","1");
        if(cursor1.moveToFirst()){
            alarmrecord_startdate.setText(cursor1.getString(0));
            alarmrecord_starttime.setText(cursor1.getString(1));
        }
        if (cursor2.moveToFirst()){
            System.out.println(cursor2.getString(1));
            alarmrecord_stopdate.setText(cursor2.getString(0));
            alarmrecord_stoptime.setText(cursor2.getString(1));
        }
    }
}
