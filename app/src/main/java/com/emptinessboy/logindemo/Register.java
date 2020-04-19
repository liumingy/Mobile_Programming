package com.emptinessboy.logindemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.emptinessboy.logindemo.MainActivity.USER_POOL;

public class Register extends AppCompatActivity {

    public static void closeKeybord(Activity activity) {    //关闭软键盘
        InputMethodManager imm =  (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(imm != null) {
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent data = getIntent();//获取传递值
        int regmode = data.getIntExtra("mode",0);
        TextView textViewAccount = findViewById(R.id.textViewAccount);
        if(regmode==1)
            textViewAccount.setText("邮 箱");

        Button reg = findViewById(R.id.buttonReg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextEmail = findViewById(R.id.editTextEmail);
                EditText editTextPassword = findViewById(R.id.editTextPassword);
                closeKeybord(Register.this);
                try {
                    String u = editTextEmail.getText().toString();
                    String p = editTextPassword.getText().toString();
                    if (u.length()<3||p.length()<6){
                        //Toast.makeText(Register.this,"用户名或密码太短",Toast.LENGTH_SHORT).show();

                        View regView = LayoutInflater.from(Register.this).inflate(R.layout.popup_regfail,null,false);
                        TextView err = regView.findViewById(R.id.textViewReg);
                        err.setText("注册失败,用户名或密码太短");
                        final PopupWindow regResult = new PopupWindow(regView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                        regResult.setTouchable(true);
                        regResult.setAnimationStyle(R.style.pop_anim);
                        regResult.showAtLocation(v, Gravity.BOTTOM,0, 0);

                    }else{
                        USER_POOL.AddUser(u,p); //新增用户名密码

                        View regView = LayoutInflater.from(Register.this).inflate(R.layout.popup_regsuccess,null,false);
                        final PopupWindow regResult = new PopupWindow(regView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                        regResult.setTouchable(true);
                        regResult.setAnimationStyle(R.style.pop_anim);
                        regResult.showAtLocation(v, Gravity.BOTTOM,0, 0);

                        //倒计时函数
                        CountDownTimer timer = new CountDownTimer(2000, 10) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                            }
                            @Override
                            public void onFinish() {
                                finish();   //自动回到登录界面
                            }
                        };

                        timer.start();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    View regView = LayoutInflater.from(Register.this).inflate(R.layout.popup_regfail,null,false);
                    TextView err = regView.findViewById(R.id.textViewReg);
                    err.setText("内部错误！");

                    final PopupWindow regResult = new PopupWindow(regView, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT,true);
                    regResult.setTouchable(true);
                    regResult.setAnimationStyle(R.style.pop_anim);
                    regResult.showAtLocation(v, Gravity.BOTTOM,0, 0);
                }
            }
        });
    }

    public boolean dispatchTouchEvent(MotionEvent ev){
        HideKeyBroadUtils.hide(this,ev);
        return super.dispatchTouchEvent(ev);
    }
}