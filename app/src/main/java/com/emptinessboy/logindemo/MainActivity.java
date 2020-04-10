package com.emptinessboy.logindemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.buttonLogin);    //主按钮
        loginButton.setOnClickListener(new View.OnClickListener() { //添加监听事件
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);
                String u = editTextEmail.getText().toString();
                String p = editTextPassword.getText().toString();
                Switch remenber = findViewById(R.id.switchRem); //切换按钮
//                if(remenber.isChecked()){
//                    //记住我
//                }
                int result =loginCheck(u,p);
                if(result==0){
                    Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                }
                else if(result==1){
                    Toast.makeText(MainActivity.this,"用户名未填写",Toast.LENGTH_SHORT).show();
                }
                else if(result==2){
                    Toast.makeText(MainActivity.this,"密码未填写",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //注册按钮
        Button buttonReg = findViewById(R.id.buttonReg);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View regView = LayoutInflater.from(MainActivity.this).inflate(R.layout.popup_content,null,false);
                PopupWindow regWindow = new PopupWindow(regView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                regWindow.setTouchable(true);
                regWindow.setAnimationStyle(R.style.pop_anim);
                regWindow.showAtLocation(v, Gravity.BOTTOM,0, 0);
                //绑定popupWindow的视图对象
                Button buttonUseEmail = regView.findViewById(R.id.buttonUseEmail);
                buttonUseEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //调用customDialog
                        regTos();
                    }
                });
                Button buttonUseName = regView.findViewById(R.id.buttonUseName);
                buttonUseName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        regTos();
                    }
                });
            }
        });
        //退出按钮
        ImageButton quit = findViewById(R.id.buttonClose);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建警告窗体实例
                AlertDialog quitConfirm = new AlertDialog.Builder(MainActivity.this).create();
                //设置内容
                quitConfirm.setIcon(R.drawable.close);
                quitConfirm.setMessage("您确定要离开么？");
                quitConfirm.setButton(AlertDialog.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //确认按钮后执行的内容
                        android.os.Process.killProcess(android.os.Process.myPid()); //退出程序代码
                    }
                });
                quitConfirm.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消后执行的代码
                    }
                });
                quitConfirm.show();
            }
        });

    }

    int loginCheck(String u,String p){
        String users,password;
        users="hxf";password="123456";   //用户名密码
        if(u.isEmpty()){
            return 1;   //用户名没填
        }
        else if(p.isEmpty()){
            return 2;   //密码没填
        }
        else if(users.equals(u)){
            if(password.equals(p)){
                return 0;    //匹配则登录成功
            }
            else{
                return 3;   //密码错误
            }
        }
        else{
            return 4;   //用户名错误
        }
    }

    void regTos(){
        //调用customDialog
        customDialog dialog = new customDialog(MainActivity.this, new tosConfirm());
        dialog.show();
    }

    public class tosConfirm implements customDialog.onCustomDialogListener{
        @Override
        public void buttonConfirmTosClicked(boolean isRead) {
            if(isRead){
                Toast.makeText(MainActivity.this,"注册还没开放哦！",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this,"需要先同意协议才能注册哦！",Toast.LENGTH_SHORT).show();
            }
        }
    }

}

