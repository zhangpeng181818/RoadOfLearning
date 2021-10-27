package com.nuc.zp.ssh;

/**

 * 测试

 * @author wangchunlan

 * @Description

 * @date 2018/10/12 16:29

 **/

public class TestDemo {

    public static void main(String[] args) {

// 测试一、创建目录

        createDir("wangchunlan");

// 测试二、 上传单个文件

//        uploadTo("/root/kvm2.txt","/root/","C:\\Users\\Administrator\\Desktop\\kvm2.xml");

    }

    /**
js
     * 创建文件夹

     * tip：当文件夹存在时，不报错。

     * @param targetDirFileLocation 创建（目标）文件夹的绝对路径 如：/root/ma

     */

    public static void createDir(String targetDirFileLocation) {

        SSHConnection ssh = new SSHConnection();

        try {

            boolean connect = ssh.connect();
            System.out.println(connect);



//            if (ssh.getSshInfo().isReady()) {
//
//                ssh.write("mkdir -p " + targetDirFileLocation);
//
//                String out = ssh.write("ifconfig");
//
//                System.out.print(out);
//
//                ssh.close();
//
//            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    /**

     * 上传单个文件

     *

     * @param targetFile 目标文件的绝对路径 如 ：/root/kvm2.txt"

     * @param targetDirFileLocation 目标文件所在绝对路径 如：/root/

     * @param sourceFile 源文件的绝对路径完整名称 如：C:\Users\kvm2.txt

     * @return

     */

    public static boolean uploadTo(String targetFile, String targetDirFileLocation, String sourceFile) {

        SSHConnection ssh = new SSHConnection();

        try {

            ssh.connect();

            if (ssh.getSshInfo().isReady()) {

                boolean isExist = ssh.prepareUpload(sourceFile);

                if (!isExist) {

                    System.out.print("本地不存在此文件");

                    return false;

                }

                if (ssh.upload(sourceFile, targetFile, targetDirFileLocation)) {

                    System.out.print("文件上传成功");

                    ssh.close();

                    return true;

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return false;

    }

    /**

     * 创建文件夹

     * tip：当文件夹存在时，不报错。

     *

     * @param targetDirFileLocation 创建（目标）文件夹的绝对路径 如：/root/ma

     */

    public static void createDir(SSHConnection ssh, String targetDirFileLocation) {

        ssh.write("mkdir -p " + targetDirFileLocation);

    }

}
