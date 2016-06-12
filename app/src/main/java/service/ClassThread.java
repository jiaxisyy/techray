package service;

import SQL.SqlManager;
import utils.Constants;
import utils.ReadAndWrite;

/**
 * Created by zuheng.lv on 2016/6/2.
 */
public class ClassThread {
    private SqlManager sqlManager;
    private String[] alarmstr={"氧气压力过高","氧气压力过低","氧气浓度过低","氧气流量过高","露点/温度过高","露点/温度过低"};
    private int[] adress={212,212,244,228,260,260};
    private String[] unit={"kg/cm²","kg/cm²","%","m³","°C","°C"};
    private String[] explain ={"低于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{280}),
            "高于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{284}),
            "低于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{296}),
            "高于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{300}),
            "高于于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{304}),
            "低于"+ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D,new int[]{308})};
    private long[] id =new long[6];

    private boolean[] flag= {true,true,true,true,true,true};
    public ClassThread(SqlManager sqlManager){
        this.sqlManager = sqlManager;
    }
    /**
     * 每15分钟保存历史数据
     */
    public void historySave(){
     new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    sqlManager.insertHistory();
                    try {
                        Thread.sleep(1000*60*15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    /**
     * 删除一个月以前的历史记录
     */
    public void historyDelete(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    sqlManager.deleteHistory();
                    try {
                        Thread.sleep(1000*60*60*24);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void saveAlarmRecord(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    String[] str = ReadAndWrite.ReadJni(Constants.Define.OP_BIT_M,new int[]{50,51,52,53,54,55});
                    for(int i =0;i<str.length;i++){
                        if(str[i].equals("1") && flag[i]){
                          String[] data =   ReadAndWrite.ReadJni(Constants.Define.OP_BIT_M,new int[]{adress[i]});
                            sqlManager.insertAlarmRecord(alarmstr[i],data[0]+unit[i],explain[i]);
                            flag[i]=false;
                        }
                        if(str[i].equals("0") && flag[i]==false){
                            sqlManager.upDateAlarmRecord(id[i]);
                            flag[i]=true;
                        }
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
