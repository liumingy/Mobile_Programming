package com.emptinessboy.logindemo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class customDialog extends Dialog {

    private onCustomDialogListener CustomDialogListener;    //将实例化的对象保存

    public customDialog(@NonNull Context context,onCustomDialogListener CustomDialogListener) { //第二个参数用来传入已经实例化的对象
        super(context); //生成的构造函数
        this.CustomDialogListener = CustomDialogListener;
    }

    //用于调用的接口
    public interface onCustomDialogListener {
        //定义回调事件
        //按钮点击事件
        public void buttonConfirmTosClicked(boolean isRead);

    }

    @Override
    //onCreate()用作对话框的初始化
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //生成的方法重载

        setContentView(R.layout.custom_dialog); //调用样式

        //这段代码定义了宽度
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = 1800;
        this.getWindow().setAttributes(lp);

        EditText tos = findViewById(R.id.Tos);
        tos.setFocusable(false);//不可编辑

        Button buttonConfirmTos = findViewById(R.id.buttonConfirmTos);
        buttonConfirmTos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBoxConfirmTos = findViewById(R.id.checkBoxConfirmTos);
                boolean isRead = false;
                if(checkBoxConfirmTos.isChecked())
                    isRead = true;
                CustomDialogListener.buttonConfirmTosClicked(isRead); //此处回调接口的方法，具体触发的事件由接口在主函数中定义
            }
        });
    }

}
