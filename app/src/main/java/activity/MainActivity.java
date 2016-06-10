package activity;


import android.content.Intent;
import android.os.Bundle;
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
import bean.LoginErrorInfo;
import fragment.AnimationFragment;
import fragment.HistoryFragment;
import fragment.IntroduceFragment;
import fragment.MonitorFragment;
import fragment.SimulaionFragment;
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
    private PopupWindow popupWindow;
    private String LOGIN_URL = "http://kawakp.chinclouds.com:58010/userconsle/login";
    private ExpandableListView elv_mian_state;
    private View view;
    private String loginInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.replace(getSupportFragmentManager(), R.id.frameLayout_main, SimulaionFragment.class);
        setContentView(R.layout.main_layout);

        startService(new Intent(this, Services.class));

        initialize();
        initData();

        elv_mian_state.setAdapter(new MainExpandableListViewAdapter(map, parent, this));
        //取消线
//        elv_mian_state.setDivider(null);
        //去掉箭头
//        mainlistview.setGroupIndicator(null);

        elv_mian_state.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {


//关闭分组
//                elv_mian_state.collapseGroup(groupPosition);


                return true;
            }
        });

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
        list2.add("child2-1");
        list2.add("child2-2");
        list2.add("child2-3");
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

                pp_tv_login_cancel.setOnClickListener(this);
                pp_btn_affirm.setOnClickListener(this);

                break;

            case R.id.pp_btn_affirm:
//                确认按钮
                String username = pp_et_username.getText().toString();
                String password = pp_et_password.getText().toString();
                boolean checked = pp_login_ck_remember.isChecked();
                CacheUtils.putBoolean(this, Constants.Define.IS_LOGIN_USER_REMEMBER, checked);
                //是否保存用户名
                if (checked) {

                    CacheUtils.putString(this, Constants.Define.LOGIN_USERNAME, username);
                } else {
                    CacheUtils.putString(this, Constants.Define.LOGIN_USERNAME, "");

                }
                //网络请求
                final Map<String, String> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
                //返回信息
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        loginInfo = Httputils.submitPostData(LOGIN_URL, user, "UTF-8");


                    }


                }).start();
                Gson gson = new Gson();
                if (loginInfo != null) {
                    if (!loginInfo.contains("\"error\"")) {
                        //登陆成功
                        Log.d("JIAXI", loginInfo);
                        popupWindow.dismiss();
                        Log.d("JIAXI", "pop0");
                        //创建新的弹窗
                        popupWindow = getPopupWindow(R.layout.popupwindow_login_succeed);
                        Log.d("JIAXI", "pop1");
                        popupWindow.setAnimationStyle(R.style.AnimationPreview);
                        Log.d("JIAXI", "pop2");
                        view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.popupwindow_login_succeed, null);
                        Log.d("JIAXI", "pop3");
                        pp_btn_succeed_go_home = (Button) view.findViewById(R.id.pp_btn_succeed_go_home);
                        pp_btn_succeed_go_home.setOnClickListener(this);
                        popupWindow.showAsDropDown(view);


                    } else {
                        //登陆失败
                        Toast.makeText(getApplicationContext(), gson.fromJson(loginInfo, LoginErrorInfo.class).getError().toString(), Toast.LENGTH_SHORT).show();
                    }

                    break;


                }
            case R.id.pp_tv_login_cancel:
                //取消按钮，关闭弹窗

                popupWindow.dismiss();
                popupWindow.setAnimationStyle(R.style.AnimationPreview);

                break;
            case R.id.pp_btn_succeed_go_home:
//                回到主页
                popupWindow.dismiss();
                parent.add("设置");
                Log.d("JIAXI",parent.get(3).toString());
                parent.notify();
                Log.d("JIAXI","notify");

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

    }


}





