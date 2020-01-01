package com.supermap.testMultiModule.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Created By wenyutun
 * Date: 2018-05-25
 * Time: 11:20
 */
public class FileUtils {
    /**
     * 上传文件
     *
     * @param file     文件对应的byte数组流   使用file.getBytes()方法可以获取
     * @param filePath 上传文件路径，不包含文件名
     * @param fileName 上传文件名
     * @throws Exception
     */
    public static Map uploadFile(byte[] file, String filePath, String fileName, String contentType) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        Map<String, String> md = new HashMap<>();
        md.put("MD_NAME", fileName);
        md.put("FILE_PATH", filePath);
        md.put("FILE_SIZE", String.valueOf(file.length / 1024));
        // md.put ("FILE_EXT",contentType);
        FileOutputStream out = new FileOutputStream(filePath + "/" + fileName);
        out.write(file);
        out.flush();
        out.close();
        return md;
    }

    /**
     * 赋值文件方法
     *
     * @param source 源路径
     * @param target 目标路径
     * @return true
     */
    public static boolean copyFile(File source, File target) {
        java.nio.channels.FileChannel in = null;
        java.nio.channels.FileChannel out = null;
        FileInputStream inStream = null;
        FileOutputStream outStream = null;
        try {

            if (!new File(target.getParent()).exists()) {
                new File(target.getParent()).mkdirs();
            }
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(target);
            in = inStream.getChannel();
            out = outStream.getChannel();
            in.transferTo(0, in.size(), out);
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            try {
                inStream.close();
                in.close();
                outStream.close();
                out.close();
            } catch (Exception ex) {

            }
        }
    }

    /**
     * 复制文件
     * @param url1 源路径
     * @param url2 目标路径
     * @throws Exception
     */
    public static void copy(String url1, String url2) throws Exception {
        //将反斜杠替换成正斜杠
        String url3 = url1.replace("\\", "/");
        URL url = new URL(url3);
        File tfile = new File(url2);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream out = new FileOutputStream(tfile);
        System.out.println(tfile + "：" + tfile.getAbsolutePath());
        byte[] buff = new byte[512];
        int n = 0;
        System.out.println("复制文件：" + "\n" + "源路径：" + url1 + "\n" + "目标路径："
                + url2);
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();
        System.out.println("复制完成");
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            return false;
        }
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

}
