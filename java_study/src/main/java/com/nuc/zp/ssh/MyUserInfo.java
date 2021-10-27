package com.nuc.zp.ssh;

import com.jcraft.jsch.UIKeyboardInteractive;

import com.jcraft.jsch.UserInfo;

/**

 * @author wangchunlan

 * @Description

 * @date 2018/10/12 14:46

 **/

public abstract class MyUserInfo implements UserInfo,UIKeyboardInteractive {

    @Override

    public String[] promptKeyboardInteractive(String destination, String name, String instruction, String[] prompt, boolean[] echo) {

        return new String[0];

    }

    @Override

    public String getPassphrase() {

        return null;

    }

    @Override

    public String getPassword() {

        return null;

    }

    @Override

    public boolean promptPassword(String message) {

        return false;

    }

    @Override

    public boolean promptPassphrase(String message) {

        return false;

    }

    @Override

    public boolean promptYesNo(String message) {

// 注意此处改为true

        return true;

    }

    @Override

    public void showMessage(String message) {

    }

}