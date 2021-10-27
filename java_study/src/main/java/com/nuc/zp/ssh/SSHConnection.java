package com.nuc.zp.ssh;


import com.jcraft.jsch.*;


import java.io.File;

import java.util.List;

/**

 * SSH链接工具类

 * @author wangchunlan

 * @Description
 * @date 2018/10/12 15:43

 **/

public class SSHConnection {

// private SSHInfo sshInfo=new SSHInfo();

    private SSHInfo sshInfo=new SSHInfo("root","mdk.2017","39.107.235.225","root","129.8.240.36","",5524);
//    private SSHInfo sshInfo=new SSHInfo("mdk","mdk.2017","39.107.235.225","root","Lsrmyy@2019","129.8.240.36",5589);
//    private SSHInfo sshInfo=new SSHInfo("root","mdk.2017","http://aisc.me/","","","",9722);
//
    public boolean connect(){

        try {

            String config=config(sshInfo.getPort(),sshInfo.getTarger_username(),sshInfo.getTarger_host(),sshInfo.getJump_username(),sshInfo.getJump_host());

            System.out.println(config);

            ConfigRepository configRepository=OpenSSHConfig.parse(config);

            sshInfo.getSsh().setConfigRepository(configRepository);

            Session session=sshInfo.getSsh().getSession("foo");

            session.setPassword(sshInfo.getTarger_password());

            session.setUserInfo(new MyUserInfo() {});

            session.connect(300000);


            sshInfo.setSession(session);

            sshInfo.setReady(true);

//            session.setPortForwardingR(9999,"127.0.0.1",15672);
//            session.setPortForwardingL(8098, "192.168.1.98", 8098);
            session.setPortForwardingL(8080, "127.0.0.1", 8080);
//            session.setHost("192.168.0.217");
//            session.set

//            AllowAgentForwarding yes
//            AllowTcpForwarding yes
//            GatewayPorts yes
//            X11Forwarding yes
            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }


    public String write(String command) {

        try {

            sshInfo.setChannel(sshInfo.getSession().openChannel("exec"));

            Channel channel= sshInfo.getChannel();

            ((ChannelExec) channel).setCommand(command);

            sshInfo.setCommandOutput(channel.getInputStream());

            channel.connect(3000);

            StringBuilder sBuilder = new StringBuilder();

            String lido = sshInfo.getReader().readLine();

            while (lido != null) {

                sBuilder.append(lido);

                sBuilder.append("\n");

                lido = sshInfo.getReader().readLine();

            }

            System.out.println("The remote command is: " + command);

            return sBuilder.toString();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;

    }

// 断开 通道和会话

    public void close() {

        if (sshInfo.getChannel() != null)

            sshInfo.getChannel().disconnect();

        if (sshInfo.getSession() != null)

            sshInfo.getSession() .disconnect();

        sshInfo.setReady(false);

    }

    public String config(int port,String targer_username,String targer_host,String jump_username,String jump_host){

// todo :foo 要改为final 常量

        String bastion=jump_username+"@"+jump_host+":"+port;

        String config="";

        config=

                "Port "+port+"\n"+

                        "\n"+

                        "Host foo"+"\n"+

                        " User "+targer_username+"\n"+

                        " Hostname "+targer_host+"\n"+

                        " ProxyJump "+bastion+"\n"+

                        "Host *\n"+

                        " ConnectTime 30000\n"+

                        " PreferredAuthentications keyboard-interactive,password,publickey\n"+

                        " #ForwardAgent yes\n"+

                        " AllowAgentForwarding yes\n"+
                        " AllowTcpForwarding yes\n"+
                        " GatewayPorts yes\n"+
                        " X11Forwarding yes\n"+

                        " #StrictHostKeyChecking no\n"+

                        " #IdentityFile ~/.ssh/id_rsa\n"+ //登陆跳板机的私钥所在位置，如默认位置可不用显示指定

                        " #UserKnownHostsFile ~/.ssh/known_hosts";

        return config;

    }

    /**
     * AllowAgentForwarding yes
     * AllowTcpForwarding yes
     * GatewayPorts yes
     * X11Forwarding yes
     */

/**

 * 上传文件

 * @param sourceFile 本地路径

 * @param dirDestino 上传文件绝对路径 如：/root/kvm2.xml

 * @return

 */

    /**

     * 上传文件

     * @param sourceFile 本地文件绝对路径 如：c:/kvm.xml

     * @param targetDirFileLocation 上传文件所在目录 如：/root/

     * @return

     */

    public boolean upload(String sourceFile,String targetDirFileLocation) {

        try {

            File origem_ = new File(sourceFile);

            targetDirFileLocation = targetDirFileLocation.replace(" ", "_");

            String targetFile = targetDirFileLocation.concat("/").concat(origem_.getName());

            return upload(sourceFile, targetFile, targetDirFileLocation);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return false;

    }

    /**

     * 上传文件

     * @param sourceFile 本地文件绝对路径 如：c:/kvm.xml

     * @param targetFile 目标文件绝对路径 如：/root/kvm2.xml

     * @param targetDirFileLocation 上传文件所在目录 如：/root/

     * @return

     */

    public boolean upload(String sourceFile,String targetFile,String targetDirFileLocation) {

        try {

            ChannelSftp sftp = (ChannelSftp) sshInfo.getSession().openChannel("sftp");

            sftp.connect();

            targetDirFileLocation = targetDirFileLocation.replace(" ", "_");

            sftp.cd(targetDirFileLocation);

            sftp.put(sourceFile, targetFile);

            sftp.disconnect();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    /**

     * 下载文件

     * @param sourceFile 下载文件绝对路径名称 如：/root/kvm2.xml

     * @param targetFile 下载文件目标位置绝对路径名称 如：C:\Users\kvm2.xml

     * @return

     */

    public boolean download(String sourceFile, String targetFile){

        try {

            ChannelSftp sftp = (ChannelSftp) sshInfo.getSession().openChannel("sftp");

            sftp.connect();

            sftp.get(sourceFile, targetFile);

            sftp.disconnect();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    /**

     * 判断单个源文件[上传文件] 是否存在

     * @param sourceFile 源文件

     * @return

     */

    public boolean prepareUpload(String sourceFile) {

        File file = new File(sourceFile);

        if (file.exists() && file.isFile()) {

            return true;

        }

        return false;

    }

    /**

     * 判断多个源文件[上传文件] 是否存在

     * @param sourceFiles 源文件

     * @return

     */

    public boolean prepareUpload(List<String> sourceFiles) {

        for(String item:sourceFiles){

            File file = new File(item);

            boolean isTrue=file.exists() && file.isFile();

            if (!isTrue) {

                return false;

            }

            continue;

        }

        return true;

    }

    public SSHInfo getSshInfo() {

        return sshInfo;

    }

    public void setSshInfo(SSHInfo sshInfo) {

        this.sshInfo = sshInfo;

    }

}
