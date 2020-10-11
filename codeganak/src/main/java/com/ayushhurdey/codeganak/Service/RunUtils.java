package com.ayushhurdey.codeganak.Service;

import java.io.*;

public class RunUtils {
    public static String getProjectRootPath(){
        return System.getProperty("user.dir"); // to get current working directory
    }

    public static void fileWriter(String filePath, String text) {
        try{
            File file = new File(filePath);
            file.createNewFile();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        try{
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(text);
            myWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static BufferedReader executeCommand(String cmd, String ops) throws Exception{
        ProcessBuilder builder = getProcessBuilder(cmd, ops);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        return r;
    }

    // to check if the root system is windows or linux
    private static ProcessBuilder getProcessBuilder(String cmd, String ops){
        ProcessBuilder builder;
        if (ops.equals("window"))
            builder = new ProcessBuilder(
                    "cmd.exe", "/c", cmd);
        else
            builder = new ProcessBuilder(
                    "bash", "-c", cmd);
        return builder;
    }

    public static String runProgram(String runCommand, String ops) throws Exception{
        BufferedReader r = RunUtils.executeCommand(runCommand, ops);
        String line;
        String output = "";
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
            output +=  line + "\n";
        }
        return output;
    }

    public static void createDir(String path){
        File file = new File(path);
        file.mkdirs();
    }
}
