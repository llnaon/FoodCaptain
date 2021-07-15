package com.cityu.foodcaptain.utils;

import com.cityu.foodcaptain.constants.Constants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {


    public static List<String> readFileByLines(String fileName) {
        List<String> res = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return res;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                res.add(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return res;
    }

    public static String readAll(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void clearFile(String file) {
        File f = new File(file);
        try {
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
        }
    }

    public static void writeByLine(String fileName, List<String> data) {
        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);
            for (String arr : data) {
                bw.write(arr + "\n");
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
        }
    }


}
