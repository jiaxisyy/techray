package download;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * Created by shuang.xiang on 2016/6/11.
 */
public class UpdadeService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UpdateApp.updateApk();



    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
