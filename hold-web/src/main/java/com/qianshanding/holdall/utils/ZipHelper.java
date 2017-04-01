package com.qianshanding.holdall.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by fish on 2017/3/13.
 * gzip文件压缩和解压缩工具类
 */
public class ZipHelper {

    /**
     * @param inputFileName 需要压缩的文件夹路径
     * @param zipFileName   压缩后的zip文件路径
     * @throws Exception
     */
    public static void zip(String inputFileName, String zipFileName) throws Exception {
        zip(new File(inputFileName), zipFileName);
    }

    /**
     * @param zipFileName
     * @param inputFile
     * @throws Exception
     */
    public static void zip(File inputFile, String zipFileName) throws Exception {
        ZipOutputStream outPutStream = new ZipOutputStream(new FileOutputStream(zipFileName));
        zip(outPutStream, inputFile, "");
        outPutStream.close();
    }

    /**
     * @param outPutStream
     * @param inputFile
     * @param base
     * @throws Exception
     */
    public static void zip(ZipOutputStream outPutStream, File inputFile, String base) throws Exception {
        //判断是否为目录
        if (inputFile.isDirectory()) {
            File[] fl = inputFile.listFiles();
            outPutStream.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(outPutStream, fl[i], base + fl[i].getName());
            }
        } else {
            //压缩目录中的所有文件
            outPutStream.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(inputFile);
            int b;
            while ((b = in.read()) != -1) {
                outPutStream.write(b);
            }
            in.close();
        }
    }
}