package com.nuc.zp.ssh;

import com.jcraft.jsch.Channel;

import com.jcraft.jsch.JSch;

import com.jcraft.jsch.Session;
//import org.springframework.boot.web.servlet.server.Session;

import java.io.BufferedReader;

import java.io.InputStream;

import java.io.InputStreamReader;

/**
 * 跳板机 与目标机器的 常用属性 封装
 *
 * @author wangchunlan
 * @Description
 * @date 2018/10/12 14:52
 **/

public class SSHInfo {

    private Session session;

    private JSch ssh;

// 目标机器

    private String targer_username;

    private String targer_password;

    private String targer_host;

// 堡垒机

    private String jump_username;

    private String jump_password;

    private String jump_host;

    private int port = 22;

    private InputStream commandOutput;

    private BufferedReader reader;

    private Channel channel;

    private boolean ready;


    public SSHInfo() {

    }

    public SSHInfo(String targer_username, String targer_password, String targer_host, String jump_username, String jump_password, String jump_host, int port) {

        this.ssh = new JSch();

        this.targer_username = targer_username;

        this.targer_password = targer_password;

        this.targer_host = targer_host;

        this.jump_username = jump_username;

        this.jump_password = jump_password;

        this.jump_host = jump_host;

        this.port = port;

    }

    public SSHInfo(String targer_username, String targer_password, String targer_host, String jump_username, String jump_password, String jump_host, int port, InputStream commandOutput) {

        this.ssh = new JSch();

        this.targer_username = targer_username;

        this.targer_password = targer_password;

        this.targer_host = targer_host;

        this.jump_username = jump_username;

        this.jump_password = jump_password;

        this.jump_host = jump_host;

        this.port = port;

        this.commandOutput = commandOutput;

    }

    public Session getSession() {

        return session;

    }

    public void setSession(Session session) {

        this.session = session;

    }

    public JSch getSsh() {

        return ssh;

    }

    public void setSsh(JSch ssh) {

        this.ssh = ssh;

    }

    public String getTarger_username() {

        return targer_username;

    }

    public void setTarger_username(String targer_username) {

        this.targer_username = targer_username;

    }

    public String getTarger_password() {

        return targer_password;

    }

    public void setTarger_password(String targer_password) {

        this.targer_password = targer_password;

    }

    public String getTarger_host() {

        return targer_host;

    }

    public void setTarger_host(String targer_host) {

        this.targer_host = targer_host;

    }

    public String getJump_username() {

        return jump_username;

    }

    public void setJump_username(String jump_username) {

        this.jump_username = jump_username;

    }

    public String getJump_password() {

        return jump_password;

    }

    public void setJump_password(String jump_password) {

        this.jump_password = jump_password;

    }

    public String getJump_host() {

        return jump_host;

    }

    public void setJump_host(String jump_host) {

        this.jump_host = jump_host;

    }

    public int getPort() {

        return port;

    }

    public void setPort(int port) {

        this.port = port;

    }

    public InputStream getCommandOutput() {

        return commandOutput;

    }

    public void setCommandOutput(InputStream commandOutput) {

        this.commandOutput = commandOutput;

        reader = new BufferedReader(new InputStreamReader(commandOutput));

    }

    public BufferedReader getReader() {

        return reader;

    }

    public void setReader(BufferedReader reader) {

        this.reader = reader;

    }

    public Channel getChannel() {

        return channel;

    }

    public void setChannel(Channel channel) {

        this.channel = channel;

    }

    public boolean isReady() {

        return ready;

    }

    public void setReady(boolean ready) {

        this.ready = ready;

    }

}