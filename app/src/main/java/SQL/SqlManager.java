package SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.AlarmRecordData;
import bean.HistoryData;
import utils.Constants;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/2.
 */
public class SqlManager extends DataBaseHelper {

    private SQLiteDatabase db;
    public SqlManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        db = getWritableDatabase();
    }

    /**
     * 存储历史记录
     */
    public void insertHistory(){
        ContentValues values = new ContentValues();
        int str[]={212,264,228,244};
        String[]num = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,str);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        values.put("date", df1.format(new Date()));
        values.put("time", df2.format(new Date()));
        values.put("pressure",num[0]);
        values.put("concentration",num[3]);
        values.put("flow",num[2]);
        values.put("totalflow",num[1]);
        db.insert("history",null,values);
    }
    /**
     * 删除历史记录
     */
    public void deleteHistory(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        db.execSQL("DELETE FROM history WHERE date<"+df.format(new Date()));
        db.delete("history","date<?",new String[]{"Select CONVERT(varchar(100), dateadd(day,-30,GETDATE()), 24)"});
        System.out.println("delete");
    }

    /**
     * 查询历史记录
     */
    public List<HistoryData> searchHistory(){
        Cursor cursor =  db.query("history",null,null,null,null,null,"historyID desc");
        List<HistoryData> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                HistoryData historyData = new HistoryData();
               //ID
                historyData.setKeyId(cursor.getInt(0));
                //日期
                historyData.setDate(cursor.getString(1));
               //时间
                historyData.setTime(cursor.getString(2));
               //压力
                historyData.setPressure(cursor.getString(3));
               //浓度
                historyData.setConcentration(cursor.getString(4));
              //流量
                historyData.setFlow(cursor.getString(5));
              //总流量
                historyData.setTotalflow(cursor.getString(6));
                list.add(historyData);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public List<AlarmRecordData> searchAlarmRecord(){
        Cursor cursor =  db.query("alarm",null,null,null,null,null,"alarmID desc");
        List<AlarmRecordData> list = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                AlarmRecordData alarmRecordData = new AlarmRecordData();
                //ID
                alarmRecordData.setKeyId(cursor.getInt(0));
                //日期
                alarmRecordData.setDate(cursor.getString(1));
                //时间
                alarmRecordData.setTime(cursor.getString(2));
                //类型
                alarmRecordData.setType(cursor.getString(3));
                //报警值
                alarmRecordData.setData(cursor.getString(4));
                //报警说明
                alarmRecordData.setExplain(cursor.getString(5));
                //消报时间
                alarmRecordData.setCancel(cursor.getString(6));
                list.add(alarmRecordData);
            }while (cursor.moveToNext());
        }
        return list;
    }
    /**
     * 存储报警记录
     */
    public long insertAlarmRecord(String type,String data,String explain ){
        ContentValues values = new ContentValues();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        values.put("date", df1.format(new Date()));
        values.put("time", df2.format(new Date()));
        values.put("type",type);
        values.put("data",data);
        values.put("explain",explain);
        long ID = db.insert("alarm",null,values);
        return ID;
    }
    public void upDateAlarmRecord(long ID){
        ContentValues values = new ContentValues();
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
        values.put("cancel", df1.format(new Date())+df2.format(new Date()));
        db.update("alarm",values,"alarmID =?",new String[]{String.valueOf(ID)});
    }
}
