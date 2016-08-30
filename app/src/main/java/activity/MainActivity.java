package activity;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;

import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.hitek.serial.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import adapter.MainExpandableListViewAdapter;
import bean.ApkInfo;
import bean.LoginErrorInfo;
import bean.LoginSucceedInfo;
import download.UpdateApp;
import fragment.AlarmRecordFragment;
import fragment.AnimationFragment;
import fragment.HistoryFragment;
import fragment.IntroduceFragment;
import fragment.MonitorFragment;
import fragment.OxygenStateFragment;
import fragment.SimulaionFragment;
import fragment.SystemSettingFragment;
import fragment.TimeSeriesFragment;
import fragment.TimeSettingFragment;
import service.Services;
import utils.CacheUtils;
import utils.Constants;
import utils.CustomToast;
import utils.Httputils;
import utils.ReadAndWrite;
import utils.Utils;


/**
 * ������
 * Created by zuheng.lv on 2016/4/26.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "TAG";
    private List<String> parent = null;
    private Map<String, List<String>> map = null;
    private TextView tv_main_time;
    private TextView btn_main_login, pp_tv_login_cancel;
    private EditText pp_et_username, pp_et_password;
    private Button pp_btn_affirm, pp_btn_succeed_go_home;
    private CheckBox pp_login_ck_remember;
    private PopupWindow popupWindow, newPopupWindow;
    private String LOGIN_URL = "http://kawakp.chinclouds.com:58010/userconsle/login";
    private String USERID_ONE = "51158022c9e1436a9f66d9c30484a597";
    private String USERID_TWO = "4aa4c968e01448ba8ac123234203c883";
    private String USERID_THREE = "09f92289861b497aa369bced8715793f";
    private boolean gradeFlag1 = false;
    private boolean gradeFlag2 = false;
    private boolean gradeFlag3 = false;
    private final int MSG_LOGIN_SUCCEED = 1;
    private final int MSG_LOGIN_ERROR = 2;
    private final int TIME = 3;
    private final int NOINTERNET = 4;
    private final int ONE = 5;
    private final int TWO = 6;
    private List<Integer> ions;
    private ExpandableListView elv_mian_state;
    private View view, newView;
    private String loginInfo;
    private String errorInfo, str;
    private StringBuffer sb;

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            switch (i) {

                case MSG_LOGIN_SUCCEED:
                    //登录成功
                    gradeFlag3 = true;
                    popupWindow.dismiss();
                    CustomToast.showToast(MainActivity.this, "登录成功", Toast.LENGTH_SHORT);
                    btn_main_login.setText("注销");
                    updateMainActivity();
                    btn_main_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn_main_login.setText("登录");
                            parent.clear();
                            map.clear();
                            ions.clear();
                            gradeFlag3 = false;
                            initData();
                            MainExpandableListViewAdapter mainExpandableListViewAdapter = new MainExpandableListViewAdapter(map, parent, getApplicationContext(), ions);
                            elv_mian_state.setAdapter(mainExpandableListViewAdapter);
                            showLoginPopupWindow();


                        }
                    });


                    break;
                case MSG_LOGIN_ERROR:
                    //登录失败

                    CustomToast.showToast(MainActivity.this, errorInfo == null ? "登录失败" : errorInfo, Toast.LENGTH_SHORT);
                    break;
                case TIME:
                    tv_main_time.setText(sb.toString());
                    break;
                case NOINTERNET:
                    //没有网络

                    CustomToast.showToast(MainActivity.this, "网络异常,请检查网络状态", Toast.LENGTH_SHORT);
                    break;

                case ONE:
                    //一级
                    //登录成功
                    gradeFlag1 = true;
                    popupWindow.dismiss();
                    CustomToast.showToast(MainActivity.this, "登录成功", Toast.LENGTH_SHORT);
                    btn_main_login.setText("注销");
                    updateOne();
//                    updateMainActivity();
                    btn_main_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn_main_login.setText("登录");
                            parent.clear();
                            map.clear();
                            ions.clear();
                            gradeFlag1 = false;
                            initData();
                            MainExpandableListViewAdapter mainExpandableListViewAdapter = new MainExpandableListViewAdapter(map, parent, getApplicationContext(), ions);
                            elv_mian_state.setAdapter(mainExpandableListViewAdapter);
                            showLoginPopupWindow();


                        }
                    });

                    break;
                case TWO:
                    //二级
                    //登录成功
                    gradeFlag2 = true;
                    popupWindow.dismiss();
                    CustomToast.showToast(MainActivity.this, "登录成功", Toast.LENGTH_SHORT);
                    btn_main_login.setText("注销");
                    updateTwo();
//                    updateMainActivity();
                    btn_main_login.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            btn_main_login.setText("登录");
                            parent.clear();
                            map.clear();
                            ions.clear();
                            gradeFlag2 = false;
                            initData();
                            MainExpandableListViewAdapter mainExpandableListViewAdapter = new MainExpandableListViewAdapter(map, parent, getApplicationContext(), ions);
                            elv_mian_state.setAdapter(mainExpandableListViewAdapter);
                            showLoginPopupWindow();


                        }
                    });

                    break;
            }


        }
    };
    private Message message = Message.obtain();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, MonitorFragment.class);
        setContentView(R.layout.main_layout);
        initialize();
        initData();

        startService(new Intent(this, Services.class));
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this, ions));

//取消箭头
        elv_mian_state.setGroupIndicator(null);

        elv_mian_state.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


                changeFragment(groupPosition, childPosition);

                return true;
            }
        });
        elv_mian_state.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                switch (groupPosition) {
                    //主页
                    case 0:

                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, MonitorFragment.class);
                        break;
                    //公司介绍
                    case 2:

                        //介绍
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, IntroduceFragment.class);
                        break;

                }


                return false;
            }
        });

    }

    /**
     * 改变fragment布局
     *
     * @param groupPosition
     * @param childPosition
     */
    private void changeFragment(int groupPosition, int childPosition) {
        switch (groupPosition) {
            //选择组
            case 0:

//                Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, MonitorFragment.class);
                break;
            //************************
            case 1:
                switch (childPosition) {
                    case 0:
                        //运行状态
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, AnimationFragment.class);
                        break;
                    case 1:
                        //制氧机状态
                        //介绍
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, OxygenStateFragment.class);

                        break;
                    case 2:
                        //历史记录
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, HistoryFragment.class);
                        break;
                }
                break;
            //************************
            case 2:


                break;
            //************************
            case 3:
                switch (childPosition) {
                    case 0:
                        //时序设置

                        if (gradeFlag1) {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SystemSettingFragment.class);
                        }
//                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSettingFragment.class);
                        //二级用户
                        else if (gradeFlag2) {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSettingFragment.class);
                        } else {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSettingFragment.class);
                        }

                        break;
                    case 1:
                        if (gradeFlag1) {

                            if (isNetworkConnected()) {
                                UpdateApp updateApp = new UpdateApp(this);
                                updateApp.updateApk();
                            } else {
                                CustomToast.showToast(this, "网络异常,请检查网络状态", Toast.LENGTH_SHORT);
                            }
                        } else if (gradeFlag2) {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SimulaionFragment.class);
                        } else {
                            //模拟量设置
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SimulaionFragment.class);
                        }

                        break;
                    case 2:
                        if (gradeFlag2) {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, AlarmRecordFragment.class);
                        } else {

                            //历史报警记录
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, AlarmRecordFragment.class);
                        }

                        break;
                    case 3:
                        if (gradeFlag2) {
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SystemSettingFragment.class);
                        } else {

                            //系统设置
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SystemSettingFragment.class);
                        }


                        break;
                    case 4:
                        if (gradeFlag2) {
                            if (isNetworkConnected()) {
                                UpdateApp updateApp = new UpdateApp(this);
                                updateApp.updateApk();
                            } else {
                                CustomToast.showToast(this, "网络异常,请检查网络状态", Toast.LENGTH_SHORT);
                            }
                        } else {
                            //特殊控制
                            Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSeriesFragment.class);

                        }

                        break;
                    case 5:
                        //检测更新

                        if (isNetworkConnected()) {
                            UpdateApp updateApp = new UpdateApp(this);
                            updateApp.updateApk();
                        } else {
                            CustomToast.showToast(this, "网络异常,请检查网络状态", Toast.LENGTH_SHORT);
                        }


                        break;
                }

                break;

        }

    }

    public void initData() {
        parent = new ArrayList<String>();
        parent.add("首页");
        parent.add("状态");
        parent.add("介绍");

        map = new HashMap<String, List<String>>();

        List<String> list1 = new ArrayList<String>();
        map.put("首页", list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("运行状态");
        list2.add("制氧机状态");
        list2.add("历史记录");
        map.put("状态", list2);
        List<String> list3 = new ArrayList<String>();
        map.put("介绍", list3);
        ions = new ArrayList<>();
        ions.add(R.drawable.main_icon_home);
        ions.add(R.drawable.main_icon_status);
        ions.add(R.drawable.main_icon_introduce);
        //    readWarning();
        // updataMainActivity();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm ");
//                        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
//                        str = formatter.format(curDate);
                        String[] time = ReadAndWrite.ReadJni(Constants.Define.OP_WORD_D, new int[]{610, 611, 612, 613, 614});
                        int length = time.length;
                        if (time != null && length > 0) {
                            sb = new StringBuffer();
                            sb.append(time[0]);
                            sb.append("年");
                            sb.append(time[1]);
                            sb.append("月");
                            sb.append(time[2]);
                            sb.append("日");
                            sb.append("    ");
                            sb.append(time[3]);
                            sb.append(":");
                            sb.append(time[4]);
                            Message obtain = Message.obtain();
                            obtain.what = TIME;
                            handler.sendMessage(obtain);
                        }

                        Thread.sleep(1000 * 30);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }


    private void initialize() {

        tv_main_time = (TextView) findViewById(R.id.tv_main_time);
        btn_main_login = (TextView) findViewById(R.id.btn_main_login);
        elv_mian_state = (ExpandableListView) findViewById(R.id.elv_mian_state);
        btn_main_login.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_main_login:
                showLoginPopupWindow();
                break;

        }
    }


    /**
     * 获取弹窗
     *
     * @return
     */

    private PopupWindow getPopupWindow(int layoutId) {
        view = LayoutInflater.from(this).inflate(layoutId, null);
        PopupWindow popupWindow = new PopupWindow(view, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        return popupWindow;
    }

    /**
     * 登陆弹窗
     */
    private void showLoginPopupWindow() {


        popupWindow = getPopupWindow(R.layout.popupwindow_login);
        popupWindow.setAnimationStyle(R.style.AnimationPreview);

        //用户名
        pp_et_username = (EditText) view.findViewById(R.id.pp_et_username);
//        密码
        pp_et_password = (EditText) view.findViewById(R.id.pp_et_password);
        //确认按钮

        pp_btn_affirm = (Button) view.findViewById(R.id.pp_btn_affirm);
//        保存按钮
        pp_login_ck_remember = (CheckBox) view.findViewById(R.id.pp_login_ck_remember);
        //取消按钮

        pp_tv_login_cancel = (TextView) view.findViewById(R.id.pp_tv_login_cancel);
        if (CacheUtils.getBoolean(this, Constants.Define.IS_LOGIN_USER_REMEMBER, false)) {
            pp_et_username.setText(CacheUtils.getString(this, Constants.Define.LOGIN_USERNAME));
        }
        popupWindow.showAsDropDown(view);

        pp_btn_affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // updataMainActivity();
                String username = pp_et_username.getText().toString();
                String password = pp_et_password.getText().toString();
                boolean checked = pp_login_ck_remember.isChecked();
                CacheUtils.putBoolean(getApplicationContext(), Constants.Define.IS_LOGIN_USER_REMEMBER, checked);
                //是否保存用户名
                if (checked) {

                    CacheUtils.putString(getApplicationContext(), Constants.Define.LOGIN_USERNAME, username);
                } else {
                    CacheUtils.putString(getApplicationContext(), Constants.Define.LOGIN_USERNAME, "");
                }
                //网络请求
                final Map<String, String> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
      

//                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
//                    if (password.equals("admin2") && username.equals("admin1")) {
//                        //一级用户
//                        Message message = Message.obtain();
//                        message.what = ONE;
//                        handler.sendMessage(message);
//
//                    }
//                    if (password.equals("admin5") && username.equals("admin2")) {
//                        //二级用户
//                        Message message = Message.obtain();
//                        message.what = TWO;
//                        handler.sendMessage(message);
//                    }
//                    if (password.equals("admin10") && username.equals("admin3")) {
//                        //三级用户
//                        Message message = Message.obtain();
//                        message.what = MSG_LOGIN_SUCCEED;
//                        handler.sendMessage(message);
//                    }
//
//                }
                //返回信息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Httputils httputils = new Httputils(getApplicationContext());
                        //判断网络
                        if (isNetworkConnected()) {

                            //有网络
                            loginInfo = httputils.submitPostData(LOGIN_URL, user, "UTF-8");
                            Log.d(TAG, loginInfo);
                            Gson gson = new Gson();
                            if (loginInfo != null) {
                                if (!loginInfo.contains("\"error\"")) {
                                    //登陆成功
//                                    Message message = Message.obtain();
//                                    message.what = MSG_LOGIN_SUCCEED;
//                                    handler.sendMessage(message);
                                    String id = gson.fromJson(loginInfo, LoginSucceedInfo.class).getRoles().get(0).getId().toString();
                                    if (!TextUtils.isEmpty(id) && id.equals(USERID_ONE)) {
                                        message.what = ONE;
                                        handler.sendMessage(message);
                                    } else if (!TextUtils.isEmpty(id) && id.equals(USERID_TWO)) {
                                        message.what = TWO;
                                        handler.sendMessage(message);

                                    } else if (!TextUtils.isEmpty(id) && id.equals(USERID_THREE)) {
                                        message.what = MSG_LOGIN_SUCCEED;
                                        handler.sendMessage(message);
                                    }
                                } else {
                                    //登陆失败
                                    errorInfo = gson.fromJson(loginInfo, LoginErrorInfo.class).getError().toString();
                                    message.what = MSG_LOGIN_ERROR;
                                    handler.sendMessage(message);
                                }
                            }
                        } else {
                            //没有网络
                            Message message = Message.obtain();
                            message.what = NOINTERNET;
                            handler.sendMessage(message);


                        }

                    }


                }).start();

            }
        });
        pp_tv_login_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取消按钮，关闭弹窗
                popupWindow.dismiss();
                popupWindow.setAnimationStyle(R.style.AnimationPreview);
            }
        });
    }

    /**
     * 判断网络问题
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    /**
     * 一级用户
     */
    private void updateOne() {
        parent.add("设置");
        List<String> list4 = new ArrayList<String>();
//        list4.add("时序设置");
//        list4.add("模拟量设置");
//        list4.add("历史报警记录");
        list4.add("系统设置");
//        list4.add("特殊控制");
        list4.add("检测更新");
        map.put("设置", list4);
        ions.add(R.drawable.main_icon_setting);
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this, ions));

    }

    /**
     * 二级用户
     */
    private void updateTwo() {
        parent.add("设置");
        List<String> list4 = new ArrayList<String>();
        list4.add("时序设置");
        list4.add("模拟量设置");
        list4.add("历史报警记录");
        list4.add("系统设置");
//        list4.add("特殊控制");
        list4.add("检测更新");
        map.put("设置", list4);
        ions.add(R.drawable.main_icon_setting);
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this, ions));

    }


    /**
     * 更新主界面
     */
    private void updateMainActivity() {
        parent.add("设置");
        List<String> list4 = new ArrayList<String>();
        list4.add("时序设置");
        list4.add("模拟量设置");
        list4.add("历史报警记录");
        list4.add("系统设置");
        list4.add("特殊控制");
        list4.add("检测更新");
        map.put("设置", list4);
        ions.add(R.drawable.main_icon_setting);
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this, ions));
//        Log.d("JIAXI", "updataMainActivity");
    }


}





