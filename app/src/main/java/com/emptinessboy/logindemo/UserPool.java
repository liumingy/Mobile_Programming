package com.emptinessboy.logindemo;

public final class UserPool {
    private static final String[][] UserPass = new String[100][2];
    //临时存放用户名密码（测试用，正常使用应该用数据库）
    private int index = -1;
    UserPool(){
        AddUser("hxf","123456");
    }
    void AddUser(String u,String p){    //增加用户函数
        index++;
        UserPass[index][0]=u;
        UserPass[index][1]=p;
    }
    boolean Login(String u,String p){ //登录函数
        for(int i=0;i<=index;i++){
            if( u.equals(UserPass[i][0]) && p.equals(UserPass[i][1]) ){
                return true;
            }
        }
        return false;
    }
}
