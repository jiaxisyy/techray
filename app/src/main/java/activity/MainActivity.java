package activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
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
import bean.AlarmRecordData;
import bean.LoginErrorInfo;
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
import utils.Httputils;
import utils.Utils;


/**
 * ������
 * Created by zuheng.lv on 2016/4/26.
 */
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private List<String> parent = null;
    private Map<String, List<String>> map = null;
    private TextView tv_main_time;
    private TextView btn_main_login, pp_tv_login_cancel;
    private EditText pp_et_username, pp_et_password;
    private Button pp_btn_affirm, pp_btn_succeed_go_home;
    private CheckBox pp_login_ck_remember;
    private PopupWindow popupWindow, newPopupWindow;
    private String LOGIN_URL = "http://10.199.198.55:58010/userconsle/login";
    private final static int MSG_LOGIN_SUCCEED = 1;
    private final static int MSG_LOGIN_ERROR = 2;
    private ExpandableListView elv_mian_state;
    private View view, newView;
    private String loginInfo;
    private String errorInfo;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            switch (i) {

                case MSG_LOGIN_SUCCEED:
                    //登录成功
                    popupWindow.dismiss();
                    Log.d("JIAXI", "pop0");
//                    //创建新的弹窗
//                    newPopupWindow = getPopupWindow(R.layout.popupwindow_login_succeed);
//
//                    newPopupWindow.setAnimationStyle(R.style.AnimationPreview);
//
//                    newView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popupwindow_login_succeed, null);
//                    newPopupWindow.showAsDropDown(newView);
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    updataMainActivity();


                    break;
                case MSG_LOGIN_ERROR:
                    //登录失败
                    Toast.makeText(getApplicationContext(), errorInfo == null ? "登录失败" : errorInfo, Toast.LENGTH_SHORT).show();
                    break;
            }


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, MonitorFragment.class);
        setContentView(R.layout.main_layout);
        initialize();
        initData();
        startService(new Intent(this, Services.class));
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this));
        //取消线
//        elv_mian_state.setDivider(null);
        //去掉箭头
//        mainlistview.setGroupIndicator(null);

//elv_mian_state.setGroupIndicator(getResources().getDrawable(R.drawable.main_icon_home));
        elv_mian_state.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//关闭分组
//                elv_mian_state.collapseGroup(groupPosition);
                Log.d("JIAXI", groupPosition + "____" + childPosition);
                changeFragment(groupPosition, childPosition);

                return true;
            }
        });
        elv_mian_state.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                switch (groupPosition){
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
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSettingFragment.class);
                        break;
                    case 1:
                        //模拟量设置
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SimulaionFragment.class);
                        break;
                    case 2:
                        //历史报警记录
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, AlarmRecordFragment.class);
                        break;
                    case 3:
                        //系统设置
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SystemSettingFragment.class);
                        break;
                    case 4:
                        //特殊控制
                        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, TimeSeriesFragment.class);
                        break;
                    case 5:
                        //检测更新
                        UpdateApp updateApp = new UpdateApp(this);
                        updateApp.updateApk();

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
            pp_et_username.setText(Constants.Define.LOGIN_USERNAME);
        }
        popupWindow.showAsDropDown(view);

        pp_btn_affirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                //返回信息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Httputils httputils = new Httputils(getApplicationContext());
                        loginInfo = httputils.submitPostData(LOGIN_URL, user, "UTF-8");
                        Gson gson = new Gson();
                        if (loginInfo != null) {
                            if (!loginInfo.contains("\"error\"")) {
                                //登陆成功
                                Log.d("JIAXI", loginInfo);
                                Message message = Message.obtain();
                                message.what = MSG_LOGIN_SUCCEED;
                                handler.sendMessage(message);

                            } else {
                                //登陆失败
                                errorInfo = gson.fromJson(loginInfo, LoginErrorInfo.class).getError().toString();
                                Message message = new Message();
                                message.what = MSG_LOGIN_ERROR;
                                handler.sendMessage(message);

                            }
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
     * 更新主界面
     */
    private void updataMainActivity() {
        parent.add("设置");
        List<String> list4 = new ArrayList<String>();
        list4.add("时序设置");
        list4.add("模拟量设置");
        list4.add("历史报警记录");
        list4.add("系统设置");
        list4.add("特殊控制");
        list4.add("检测更新");
        map.put("设置", list4);
        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this));
        Log.d("JIAXI", "updataMainActivity");
    }

}





