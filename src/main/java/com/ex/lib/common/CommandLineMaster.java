package com.ex.lib.common;

import io.reactivex.internal.schedulers.RxThreadFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * @author patrick
 */
public class CommandLineMaster {

    private static final ExecutorService executorService = new ThreadPoolExecutor(4, 10,
            30000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
            new RxThreadFactory("CommandLineMaster"));

    public static int run(String command){
        try {
            String [] commands ={"/bin/bash", "-c", command};
            Process process = Runtime.getRuntime().exec(commands);
            InputStream inputStream = process.getInputStream();
            InputStream errorStream = process.getErrorStream();
            System.out.println("CommandLineMaster process command ==> : " + command);
            if(inputStream != null) {
                executorService.execute(() -> {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    try {
                        String line;
                        while (((line = bufferedReader.readLine()) != null)){
                            System.out.println("CommandLineMaster process stream ==> : " + line);
                        }
                    }catch (Exception e){
                        System.out.println("CommandLineMaster execute fail ==> : " + e);
                    }finally {
                        try {
                            inputStream.close();
                        }catch (Exception e){
                            System.out.println("CommandLineMaster execute fail ==> : " + e);
                        }
                    }
                });
            }
            if(errorStream != null) {
                executorService.execute(() -> {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                    try {
                        String line;
                        while (((line = bufferedReader.readLine()) != null)){
                            System.out.println("CommandLineMaster error stream ==> : " + line);
                        }
                    }catch (Exception e){
                        System.out.println("CommandLineMaster execute fail ==> : " + e);
                    }finally {
                        try {
                            errorStream.close();
                        }catch (Exception e){
                            System.out.println("CommandLineMaster execute fail ==> : " + e);
                        }
                    }
                });
            }
            int code = process.waitFor();
            System.out.println("CommandLineMaster execute result ==> : " + code);
            process.destroy();
            return code;
        } catch (Exception e) {
            System.out.println("CommandLineMaster execute fail ==> : " + e);
            return 500;
        }
    }
}
