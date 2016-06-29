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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.hitek.serial.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import SQL.DataBaseHelper;
import SQL.SqlManager;
import adapter.AlarmRecordRecycleViewAdapter;
import adapter.RecyclerViewAdapter;
import bean.AlarmRecordData;
import bean.DoubleDatePickerDialog;

/**
 * Created by zuheng.lv on 2016/6/11.
 */
public class AlarmRecordFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    private View view ;
    private AlarmRecordRecycleViewAdapter alarmRecordRecycleViewAdapter ;
    private RecyclerView alarmrecord_recycle;
    private TextView alarmrecord_startdate,alarmrecord_starttime,alarmrecord_stopdate,alarmrecord_stoptime;
    private Button alarmrecord_btn_search;
    private List<AlarmRecordData> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.alarmrecord_layout,container,false);
        initView();
//        initData();
        return view;
    }

    @Override
    public void onStart() {

        super.onStart();
        initData();
    }

    public void initView(){
        alarmrecord_recycle = (RecyclerView)view.findViewById(R.id.alarmrecord_recycle);
        alarmrecord_startdate = (TextView)view.findViewById(R.id.alarmrecord_startdate);
        alarmrecord_starttime = (TextView)view.findViewById(R.id.alarmrecord_starttime);
        alarmrecord_stopdate = (TextView)view.findViewById(R.id.alarmrecord_stopdate);
        alarmrecord_stoptime = (TextView)view.findViewById(R.id.alarmrecord_stoptime);
        alarmrecord_btn_search = (Button) view.findViewById(R.id.alarmrecord_btn_search);
        alarmrecord_btn_search.setOnClickListener(this);
    }
    public void initData(){
        list = new ArrayList<>();
        SqlManager sqlManager = new SqlManager(getContext(),"history.db",null,1);
        list = sqlManager.searchAlarmRecord(null,null,null,null,null,"alarmID desc",null);
//        System.out.println(list.get(0).getType());
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
//            System.out.println(cursor2.getString(1));
            alarmrecord_stopdate.setText(cursor2.getString(0));
            alarmrecord_stoptime.setText(cursor2.getString(1));
        }
    }

    @Override
    public void onClick(View v) {
        Calendar c = Calendar.getInstance();
        new DoubleDatePickerDialog(getContext(),0, new DoubleDatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
                                  int endDayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    String startDate = simpleDateFormat.format(simpleDateFormat.parse(startYear+"-"+(startMonthOfYear+1)+"-"+startDayOfMonth));
                    String endDate =  simpleDateFormat.format(simpleDateFormat.parse(endYear+"-"+(endMonthOfYear+1)+"-"+endDayOfMonth));
                    System.out.println(simpleDateFormat.format(simpleDateFormat.parse(startYear+"-"+(startMonthOfYear+1)+"-"+startDayOfMonth)));
                    System.out.println(simpleDateFormat.format(simpleDateFormat.parse(endYear+"-"+(endMonthOfYear+1)+"-"+endDayOfMonth)));
                    list.clear();
                    list = new SqlManager(getContext(),"history.db",null,1).searchAlarmRecord(null,"date BETWEEN ? AND ?",new String[]{startDate,endDate},null,null,"historyID desc",null);
                    AlarmRecordRecycleViewAdapter alarmRecordRecycleViewAdapter = new AlarmRecordRecycleViewAdapter(getContext(),list);
                    alarmrecord_recycle.setAdapter(alarmRecordRecycleViewAdapter);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), true).show();
    }
}
