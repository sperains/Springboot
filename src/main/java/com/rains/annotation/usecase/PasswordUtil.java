package com.rains.annotation.usecase;


import com.sun.tools.javac.util.List;

/**
 * Created by Rains on 2016/8/24.
 */
public class PasswordUtil {

    @UseCase(id = 1 , description = "密码必须包含一个数字")
    public boolean validatePassword(String password){
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 2)
    public String encryptPassword(String password){
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id= 3 ,description = "新密码不能跟旧密码一样！")
    public boolean checkForNewPassword(List<String> prePasswords , String password){
        return !prePasswords.contains(password);
    }
}
