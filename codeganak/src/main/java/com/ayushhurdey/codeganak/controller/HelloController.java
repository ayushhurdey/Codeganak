package com.ayushhurdey.codeganak.controller;

import com.ayushhurdey.codeganak.model.response.HelloResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("codeganak")
@PropertySource("application.yml")
public class HelloController {
    @Value("${ops}")
    private String ops;

    @CrossOrigin("*")
    @RequestMapping("/hello")
    public ResponseEntity<HelloResponse> hello(){
        String path = System.getProperty("user.dir");
        System.out.println("Current path: "+ path );
        System.out.println("Your Server is up and running..");
        HelloResponse helloResponse = new HelloResponse();
        helloResponse.setMsg("Your Server is up and running in " + ops);
        return ResponseEntity.ok().body(helloResponse);
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome on our System";
    }

    @RequestMapping("/welcome/ayush")
    public String ayush(){
        return "This server is authorised by Ayush Choudhary.";
    }

}
