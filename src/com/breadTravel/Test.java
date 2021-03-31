package com.breadTravel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
    static File fp = new File("G:\\myAndroidProject\\FilmReview\\app\\src\\main\\java\\com\\example\\filmreview");

    static int len = 0;
    static int size = 0;
    public static void main(String[] args) {
        fun(fp);
        System.out.println("Android 所有长度: "+size+" 所有行: "+len);
    }

    static void fun(File fp) {
        if (fp.getName().equals("Test.java")) {
            return;
        }
        if (fp.isFile()) {
            read(fp);
        } else {
            for (File file : fp.listFiles()) {
                fun(file);
            }
        }
    }

    static void read(File fp) {
        try {
            FileInputStream in = new FileInputStream(fp);
            int len = 0, value;
            while ((value = in.read()) != -1) {
                if (value == '\n') {
                    len++;
                }
            }
            System.out.println(String.format("%s: %d %d", fp.getName(), (int) fp.length(), len));
            Test.size += fp.length();
            Test.len += len;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
