package com.ayushhurdey.codeganak.controller;

import com.ayushhurdey.codeganak.Service.JavaExecutionService;
import com.ayushhurdey.codeganak.Service.PythonExecutionService;
import com.ayushhurdey.codeganak.model.request.RunRequest;
import com.ayushhurdey.codeganak.model.response.RunResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("codeganak")
@PropertySource("application.yml")
public class ProgramExecutionController {

    @Value("${ops}")
    private String ops;
    @Autowired
    private JavaExecutionService javaExecutionService;
    @Autowired
    private PythonExecutionService pythonExecutionService;

    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public ResponseEntity<RunResponse> testFile(@RequestBody RunRequest runRequest) throws Exception {
        RunResponse runResponse;
        if (runRequest.getLang().equals("java")) {
            runResponse = javaExecutionService.executeProgram(runRequest, ops);
        } else if (runRequest.getLang().equals("python")) {
            runResponse = pythonExecutionService.executeProgram(runRequest, ops);
        } else runResponse = null;
        return ResponseEntity.ok().body(runResponse);
    }
}
