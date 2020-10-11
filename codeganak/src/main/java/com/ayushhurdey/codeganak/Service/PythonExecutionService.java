package com.ayushhurdey.codeganak.Service;


import com.ayushhurdey.codeganak.model.request.RunRequest;
import com.ayushhurdey.codeganak.model.response.RunResponse;
import org.springframework.stereotype.Service;

@Service
public class PythonExecutionService {

    public RunResponse executeProgram(RunRequest runRequest, String ops) throws Exception{
        RunResponse runResponse = new RunResponse();
        String projRootPath = RunUtils.getProjectRootPath();
        String dataPath = projRootPath + "/data/python";
        String inputFilePath = dataPath + "/" + "input.txt";
        String programFilePath = dataPath + "/" + "Main.py";
        RunUtils.createDir(dataPath);
        RunUtils.fileWriter(programFilePath, runRequest.getCode());
        RunUtils.fileWriter(inputFilePath, runRequest.getInput());
        String runCommand = "cd " + dataPath + " && python Main.py < input.txt";
        String runOutput = RunUtils.runProgram(runCommand, ops);
        runResponse.setOutput(runOutput);
        return runResponse;
    }
}
