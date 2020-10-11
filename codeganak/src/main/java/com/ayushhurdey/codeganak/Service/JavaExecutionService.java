package com.ayushhurdey.codeganak.Service;

import com.ayushhurdey.codeganak.model.request.RunRequest;
import com.ayushhurdey.codeganak.model.response.RunResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;

@Service           // making singleton objects
public class JavaExecutionService {
    public RunResponse executeProgram(RunRequest runRequest, String ops) throws Exception{
        RunResponse runResponse;
        String projRootPath = RunUtils.getProjectRootPath();
        String dataPath = projRootPath + "/data/java";
        String inputFilePath = dataPath + "/" + "input.txt";
        String programFilePath = dataPath + "/" + "Main.java";
        RunUtils.createDir(dataPath);
        RunUtils.fileWriter(programFilePath, runRequest.getCode());
        RunUtils.fileWriter(inputFilePath, runRequest.getInput());

        String compileCmd = "cd " + dataPath + " && javac Main.java";
        String compileOutput = compileProgram(compileCmd,ops);
        runResponse = new RunResponse();
        if (compileOutput.contains("error")) {
            runResponse.setOutput("Compilation Error.\n" + compileOutput);
            return  runResponse;
        }
        String runCommand = "cd " + dataPath + " && java Main < input.txt";
        String runOutput = RunUtils.runProgram(runCommand, ops);
        runResponse.setOutput(runOutput);
        return runResponse;
    }

    private String compileProgram(String compileCmd, String ops) throws Exception{
        BufferedReader r = RunUtils.executeCommand(compileCmd,ops);
        String output = "";
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            output += line;
            if (line.contains("error")) {break;}
        }
        return output;
    }
}