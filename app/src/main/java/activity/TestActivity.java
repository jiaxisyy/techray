//package activity;
//
//
//import com.hitek.serial.R;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//
//
///**
// * Created by zuheng.lv on 2016/4/29.
// */
//public class TestActivity extends Activity implements View.OnClickListener, View.OnLongClickListener {
//
//	Handler handle = new Handler(){
//		public void handleMessage(android.os.Message msg) {
//			switch(msg.what){
//			case 1:
//				test_tv_m_read_input.setText(msg.obj.toString());
//				break;
//			case 2 :
//
//				break;
//			case 3 :
//
//				break;
//			case 4 :
//
//				break;
//
//			}
//		};
//	};
//
//    private TextView test_tv_m_read_input,test_tv_vw_read_input,test_tv_vd_read_input,test_tv_f_read_input;
//    private Button test_btn_m_write,test_btn_m_read,test_btn_vw_write,test_btn_vw_read,test_btn_vd_write,test_btn_vd_read,test_btn_f_write,test_btn_f_read;
//    private EditText test_et_m_write_address,test_et_m_read_address,test_et_m_write_input,test_et_vw_write_address,test_et_vw_write_input,test_et_vw_read_address,test_et_vd_write_address,test_et_vd_write_input,test_et_vd_read_address,test_et_f_write_address,test_et_f_write_input,test_et_f_read_address;
//    private boolean flag_m_read=true;
//    private boolean flag_m_write=true;
//    private boolean flag_vw_read=true;
//    private boolean flag_vw_write=true;
//    private boolean flag_vd_read=true;
//    private boolean flag_vd_write=true;
//    private boolean flag_f_read=true;
//    private boolean flag_f_write=true;
//    private EditText test_et_m_write_type,test_et_m_read_type,test_et_vw_write_type,test_et_vw_read_type,test_et_vd_write_type,test_et_vd_read_type,test_et_f_write_type,test_et_f_read_type;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_layout);
//        initView();
//    }
//
//        public void initView(){
//            test_et_m_write_type=(EditText) findViewById(R.id.test_et_m_write_type);
//            test_et_m_read_type=(EditText) findViewById(R.id.test_et_m_read_type);
//            test_et_vw_write_type=(EditText) findViewById(R.id.test_et_vw_write_type);
//            test_et_vw_read_type=(EditText) findViewById(R.id.test_et_vw_read_type);
//            test_et_vd_write_type=(EditText) findViewById(R.id.test_et_vd_write_type);
//            test_et_vd_read_type=(EditText) findViewById(R.id.test_et_vd_read_type);
//            test_et_f_write_type=(EditText) findViewById(R.id.test_et_f_write_type);
//            test_et_f_read_type=(EditText) findViewById(R.id.test_et_f_read_type);
//
//
//            test_et_m_write_address=(EditText) findViewById(R.id.test_et_m_write_address);
//            test_et_m_read_address=(EditText) findViewById(R.id.test_et_m_read_address);
//            test_et_m_write_input=(EditText) findViewById(R.id.test_et_m_write_input);
//            test_et_vw_write_address=(EditText) findViewById(R.id.test_et_vw_write_address);
//            test_et_vw_write_input=(EditText) findViewById(R.id.test_et_vw_write_input);
//            test_et_vw_read_address=(EditText) findViewById(R.id.test_et_vw_read_address);
//            test_et_vd_write_address=(EditText) findViewById(R.id.test_et_vd_write_address);
//            test_et_vd_write_input=(EditText) findViewById(R.id.test_et_vd_write_input);
//            test_et_vd_read_address=(EditText) findViewById(R.id.test_et_vd_read_address);
//            test_et_f_write_address=(EditText) findViewById(R.id.test_et_f_write_address);
//            test_et_f_write_input=(EditText) findViewById(R.id.test_et_f_write_input);
//            test_et_f_read_address=(EditText) findViewById(R.id.test_et_f_read_address);
//            test_btn_m_write = (Button) findViewById(R.id.test_btn_m_write);
//            test_btn_m_read = (Button) findViewById(R.id.test_btn_m_read);
//            test_btn_vw_write = (Button) findViewById(R.id.test_btn_vw_write);
//            test_btn_vw_read = (Button) findViewById(R.id.test_btn_vw_read);
//            test_btn_vd_write = (Button) findViewById(R.id.test_btn_vd_write);
//            test_btn_vd_read = (Button) findViewById(R.id.test_btn_vd_read);
//            test_btn_f_write = (Button) findViewById(R.id.test_btn_f_write);
//            test_btn_f_read = (Button) findViewById(R.id.test_btn_f_read);
//            test_tv_m_read_input=(TextView) findViewById(R.id.test_tv_m_read_input);
//            test_tv_vw_read_input=(TextView) findViewById(R.id.test_tv_vw_read_input);
//            test_tv_vd_read_input=(TextView) findViewById(R.id.test_tv_vd_read_input);
//            test_tv_f_read_input=(TextView) findViewById(R.id.test_tv_f_read_input);
//            test_btn_m_write.setOnClickListener(this);
//            test_btn_m_write.setOnLongClickListener(this);
//            test_btn_m_read.setOnClickListener(this);
//            test_btn_m_read.setOnLongClickListener(this);
//            test_btn_vw_write.setOnClickListener(this);
//            test_btn_vw_write.setOnLongClickListener(this);
//            test_btn_vw_read.setOnClickListener(this);
//            test_btn_vw_read.setOnLongClickListener(this);
//            test_btn_vd_write.setOnClickListener(this);
//            test_btn_vd_write.setOnLongClickListener(this);
//            test_btn_vd_read.setOnClickListener(this);
//            test_btn_vd_read.setOnLongClickListener(this);
//            test_btn_f_write.setOnClickListener(this);
//            test_btn_f_write.setOnLongClickListener(this);
//            test_btn_f_read.setOnClickListener(this);
//            test_btn_f_read.setOnLongClickListener(this);
//        }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.test_btn_m_write:
//                int address_m_write = Integer.parseInt(test_et_m_write_address.getText().toString());
//                int type_m_write = Integer.parseInt(test_et_m_write_type.getText().toString());
//                byte[] input_m = test_et_m_write_input.getText().toString().getBytes();
//                if(input_m!=null && input_m.length>0){
//                    MyApplication.getInstance().mdbuswritebyte(type_m_write,input_m,address_m_write,1);
//                }
//                break;
//            case R.id.test_btn_m_read:
//                int address_m_read = Integer.parseInt(test_et_m_read_address.getText().toString());
//                int type_m_read = Integer.parseInt(test_et_m_read_type.getText().toString());
//                byte[] read_m =  MyApplication.getInstance().mdbusreadbyte(type_m_read,address_m_read,1);
//                if(read_m!=null && read_m.length>0){
//                    test_tv_m_read_input.setText(String.valueOf(read_m[0]));
//                }
//                break;
//            case R.id.test_btn_vw_write:
//                int address_vw_write = Integer.parseInt(test_et_vw_write_address.getText().toString());
//                int type_vw_write = Integer.parseInt(test_et_vw_write_type.getText().toString());
//                 short[] input_vw ={new Short(test_et_vw_write_input.getText().toString())};
//                if(input_vw!=null && input_vw.length>0){
//                    MyApplication.getInstance().mdbuswriteword(type_vw_write,input_vw,address_vw_write,1);
//                }
//                break;
//            case R.id.test_btn_vw_read:
//                int address_vw_read = Integer.parseInt(test_et_vw_read_address.getText().toString());
//                int type_vw_read = Integer.parseInt(test_et_vw_read_type.getText().toString());
//
//                short[] read_vw =  MyApplication.getInstance().mdbusreadword(type_vw_read,address_vw_read,1);
//                if(read_vw!=null && read_vw.length>0){
//                    test_tv_vw_read_input.setText(String.valueOf(read_vw[0]));
//                }
//                break;
//            case R.id.test_btn_vd_write:
//                int address_vd_write = Integer.parseInt(test_et_vd_write_address.getText().toString());
//                int type_vd_write = Integer.parseInt(test_et_vd_write_type.getText().toString());
//                int[] input_vd ={ Integer.parseInt(test_et_vd_write_input.getText().toString())};
//                if(input_vd!=null && input_vd.length>0){
//                    MyApplication.getInstance().mdbuswritedword(type_vd_write,input_vd,address_vd_write,1);
//                }
//                break;
//            case R.id.test_btn_vd_read:
//                int address_vd_read=Integer.parseInt(test_et_vd_read_address.getText().toString());
//                int type_vd_read=Integer.parseInt(test_et_vd_read_type.getText().toString());
//               int[]read_vd = MyApplication.getInstance().mdbusreaddword(type_vd_read,address_vd_read,1);
//                if(read_vd!=null && read_vd.length>0){
//                    test_tv_vd_read_input.setText(String.valueOf(read_vd[0]));
//                }
//                break;
//            case R.id.test_btn_f_write:
//                int address_f_write=Integer.parseInt(test_et_f_write_address.getText().toString());
//                int type_f_write=Integer.parseInt( test_et_f_write_type.getText().toString());
//                float[] input_f = {new Float(test_et_f_write_input.getText().toString())};
//                if(input_f!=null && input_f.length>0) {
//                    MyApplication.getInstance().mdbuswritereal(type_f_write, input_f, address_f_write, 1);
//                }
//                break;
//            case R.id.test_btn_f_read:
//                int address_f_read=Integer.parseInt(test_et_f_read_address.getText().toString());
//                int type_f_read=Integer.parseInt(test_et_f_read_type.getText().toString());
//                float[] read_f= MyApplication.getInstance().mdbusreadreal(type_f_read,address_f_read,1);
//                if(read_f!=null && read_f.length>0){
//                    test_tv_f_read_input.setText(String.valueOf(read_f[0]));
//                }
//                break;
//        }
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
////        switch (v.getId()){
////            case R.id.test_btn_m_write:
////              if(test_btn_m_write.getText().toString().equals("д��")){
////                  test_btn_m_write.setText("ֹͣ");
////                  new Thread(new Runnable() {
////                      @Override
////                      public void run() {
////
////                          while(flag_m_write){
////                              try {
////                                  Thread.sleep(500);
////                              } catch (InterruptedException e) {
////                                  e.printStackTrace();
////                              }
////                              int address_m_write = Integer.parseInt(test_et_m_write_address.getText().toString());
////                              int type_m_write = Integer.parseInt(test_et_m_write_type.getText().toString());
////                              byte[] input_m = test_et_m_write_input.getText().toString().getBytes();
////                              if(input_m!=null && input_m.length>0){
////                                  MyApplication.getInstance().mdbuswritebyte(type_m_write,input_m,address_m_write,1);
////                              }
////                          }
////                          Thread.interrupted();
////                      }
////                  }).start();
////              }else{
////                  test_btn_m_write.setText("д��");
////                  flag_m_write=false;
////              }
////
////                break;
////            case R.id.test_btn_m_read:
////                if(test_btn_m_read.getText().toString().equals("����")){
////                    test_btn_m_read.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_m_read){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_m_read = Integer.parseInt(test_et_m_read_address.getText().toString());
////                                int type_m_read = Integer.parseInt(test_et_m_read_type.getText().toString());
////                                byte[] read_m =  MyApplication.getInstance().mdbusreadbyte(type_m_read,address_m_read,1);
////                                if(read_m!=null && read_m.length>0){
////                                	Message msg = new Message();
////                                	msg.what=1;
////                                	msg.obj = String.valueOf(read_m[0]);
////
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_m_read.setText("����");
////                    flag_m_read=false;
////                }
////                break;
////            case R.id.test_btn_vw_write:
////                if(test_btn_vw_write.getText().toString().equals("д��")){
////                    test_btn_vw_write.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_vw_write){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_vw_write = Integer.parseInt(test_et_vw_write_address.getText().toString());
////                                int type_vw_write = Integer.parseInt(test_et_vw_write_type.getText().toString());
////                                short[] input_vw ={new Short(test_et_vw_write_input.getText().toString())};
////                                if(input_vw!=null && input_vw.length>0){
////                                    MyApplication.getInstance().mdbuswriteword(type_vw_write,input_vw,address_vw_write,1);
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_vw_write.setText("д��");
////                    flag_vw_write=false;
////                }
////                break;
////            case R.id.test_btn_vw_read:
////                if(test_btn_vw_read.getText().toString().equals("����")){
////                    test_btn_vw_read.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_vw_read){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_vw_read = Integer.parseInt(test_et_vw_read_address.getText().toString());
////                                int type_vw_read = Integer.parseInt(test_et_vw_read_type.getText().toString());
////
////                                short[] read_vw =  MyApplication.getInstance().mdbusreadword(type_vw_read,address_vw_read,1);
////                                if(read_vw!=null && read_vw.length>0){
////                                	Message msg = new Message();
////                                	msg.what=1;
////
////
////                                    test_tv_vw_read_input.setText(String.valueOf(read_vw[0]));
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_vw_read.setText("����");
////                    flag_vw_read=false;
////                }
////                break;
////            case R.id.test_btn_vd_write:
////                if(test_btn_vd_write.getText().toString().equals("д��")){
////                    test_btn_vd_write.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_vd_write){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_vd_write = Integer.parseInt(test_et_vd_write_address.getText().toString());
////                                int type_vd_write = Integer.parseInt(test_et_vd_write_type.getText().toString());
////                                int[] input_vd ={ Integer.parseInt(test_et_vd_write_input.getText().toString())};
////                                if(input_vd!=null && input_vd.length>0){
////                                    MyApplication.getInstance().mdbuswritedword(type_vd_write,input_vd,address_vd_write,1);
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_vd_write.setText("����");
////                    flag_vd_write=false;
////                }
////                break;
////            case R.id.test_btn_vd_read:
////                if(test_btn_vd_read.getText().toString().equals("����")){
////                    test_btn_vd_read.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_vd_read){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_vd_read=Integer.parseInt(test_et_vd_read_address.getText().toString());
////                                int type_vd_read=Integer.parseInt(test_et_vd_read_type.getText().toString());
////                                int[]read_vd = MyApplication.getInstance().mdbusreaddword(type_vd_read,address_vd_read,1);
////                                if(read_vd!=null && read_vd.length>0){
////                                    test_tv_vd_read_input.setText(String.valueOf(read_vd[0]));
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_vd_read.setText("����");
////                    flag_vd_read=false;
////                }
////                break;
////            case R.id.test_btn_f_write:
////                if(test_btn_f_write.getText().toString().equals("д��")){
////                    test_btn_f_write.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_f_write){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_f_write=Integer.parseInt(test_et_f_write_address.getText().toString());
////                                int type_f_write=Integer.parseInt( test_et_f_write_type.getText().toString());
////                                float[] input_f = {new Float(test_et_f_write_input.getText().toString())};
////                                if(input_f!=null && input_f.length>0) {
////                                    MyApplication.getInstance().mdbuswritereal(type_f_write, input_f, address_f_write, 1);
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_f_write.setText("����");
////                    flag_f_write=false;
////                }
////                break;
////            case R.id.test_btn_f_read:
////                if(test_btn_f_read.getText().toString().equals("����")){
////                    test_btn_f_read.setText("ֹͣ");
////                    new Thread(new Runnable() {
////                        @Override
////                        public void run() {
////
////                            while(flag_f_read){
////                                try {
////                                    Thread.sleep(500);
////                                } catch (InterruptedException e) {
////                                    e.printStackTrace();
////                                }
////                                int address_f_read=Integer.parseInt(test_et_f_read_address.getText().toString());
////                                int type_f_read=Integer.parseInt(test_et_f_read_type.getText().toString());
////                                float[] read_f= MyApplication.getInstance().mdbusreadreal(type_f_read,address_f_read,1);
////                                if(read_f!=null && read_f.length>0){
////                                    test_tv_f_read_input.setText(String.valueOf(read_f[0]));
////                                }
////                            }
////                            Thread.interrupted();
////                        }
////                    }).start();
////                }else{
////                    test_btn_f_read.setText("����");
////                    flag_f_read=false;
////                }
////                break;
////        }
////        return false;
//    }
//}
