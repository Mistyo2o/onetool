package com.onetool.spider.utils;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zh
 * @date: 2023/3/23 9:22
 * @description:
 */

public class BashUtils {

    public static final Integer SUCCESS_CODE = 0;

    public static void execCommand(String command, String dir) throws IOException, InterruptedException {
        String[] commands = command.split(" ");
        List<String> commandList = new ArrayList<>(commands.length);
        for (String s : commands) {
            if (StringUtils.isBlank(s)) {
                continue;
            }
            commandList.add(s);
        }
        commands = new String[commandList.size()];
        commands = commandList.toArray(commands);
        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        if (StringUtils.isNotBlank(dir)) {
            processBuilder.directory(new File(dir));
        }
        System.out.println("开始执行命令...: " + command);
        Process exec = processBuilder.start();
        //        BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
//        String line;
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//        }
        new Thread(new OutputHandlerRunnable(exec.getInputStream(), false)).start();
        new Thread(new OutputHandlerRunnable(exec.getErrorStream(), true)).start();
        int i = exec.waitFor();
        System.out.println("执行结果：" + i);


    }

    private static class OutputHandlerRunnable implements Runnable {

        private final InputStream in;
        private final boolean error;

        public OutputHandlerRunnable(InputStream in, boolean error) {
            this.in = in;
            this.error = error;
        }

        @Override
        public void run() {
            try (BufferedReader bufr = new BufferedReader(new InputStreamReader(this.in))) {
                String line = null;
                while ((line = bufr.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
