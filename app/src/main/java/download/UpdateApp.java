package download;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hitek.serial.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;

import java.io.File;

import bean.ApkInfo;
import utils.Httputils;

/**
 * Created by shuang.xiang on 2016/6/11.
 */
public class UpdateApp {

    private static String localUrl = "http://10.199.198.161:8081/JavaWebApp/ver.json";
    private static String url = "http://10.199.198.55:58010/userconsle/clientApps/aaa";
    private static String fileUrl = "http://10.199.198.55:58010:58010/userconsle/clientApps/aaa/file";
    private static String filePath;
    private static Context context;
    private static int NEW = 1;
    private static int OLD = 2;

    private static Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == NEW) {
                //为最新,不需要更新
                Toast.makeText(context, "当前版本为最新版本", Toast.LENGTH_SHORT).show();


            }
            if (msg.what == OLD) {

                View view = LayoutInflater.from(context).inflate(R.layout.popupwindow_update, null);
                PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                popupWindow.setAnimationStyle(R.style.AnimationPreview);
                popupWindow.showAsDropDown(view);



              /*  final ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.show();
                //需要更新
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        Ion.with(context)
                                .load(fileUrl)
//// have a ProgressBar get updated automatically with the percent
//                                .progressBar(progressBar)
// and a ProgressDialog

                                .progressDialog(progressDialog)
// can also use a custom callback
                                .progress(new ProgressCallback() {
                                    @Override
                                    public void onProgress(long downloaded, long total) {
                                        System.out.println("" + downloaded + " / " + total);
                                    }
                                })
                                .write(new File(Environment.getExternalStorageDirectory() + filePath))
                                .setCallback(new FutureCallback<File>() {
                                    @Override
                                    public void onCompleted(Exception e, File file) {
                                        // download done...
                                        // do stuff with the File or error
                                        progressDialog.dismiss();
                                        Log.d("*************", "*****************ok");
                                    }
                                });

                    }
                }).start();*/


            }


        }
    };

    public UpdateApp(Context context) {
        this.context = context;
    }

    /**
     * 更新apk文件
     */
    public static void updateApk() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Httputils httputils = new Httputils(context);
                byte[] bytes = httputils.loadByteFromURL(localUrl, context);
                if (bytes != null) {
                    String s = new String(bytes);
                    Gson gson = new Gson();
                    ApkInfo apkInfo = gson.fromJson(s, ApkInfo.class);
                    filePath = apkInfo.getFilePath();
                    int verCode = getVerCode(context);
                    int versionCode = apkInfo.getVersionCode();
                    if (versionCode > verCode) {
                        //需要更新
                        Message obtain = Message.obtain();
                        obtain.what = OLD;
                        handler.sendMessage(obtain);

                    } else {
                        //提示不需要更新,是最新版本
                        Message obtain = Message.obtain();
                        obtain.what = NEW;
                        handler.sendMessage(obtain);


                    }


                }


            }
        }).start();


    }

    public static String getApplicationName(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName =
                (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    /**
     * 获取版本名
     *
     * @param context
     * @return
     */
    public static String getVerName(Context context) {
        String versionName = "";
        String packageName = context.getPackageName();

        try {
            versionName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }

    /**
     * 获取本地版本
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {

        int verCode = -1;
        //包名
        String packageName = context.getPackageName();

        try {
            verCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        return verCode;
    }
}
