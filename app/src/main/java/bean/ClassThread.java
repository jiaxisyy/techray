package bean;

/**
 * Created by zuheng.lv on 2016/6/2.
 */
public class ClassThread {
    private  SqlManager sqlManager;
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

}
