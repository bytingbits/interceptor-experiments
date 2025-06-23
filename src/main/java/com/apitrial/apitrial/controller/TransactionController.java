package com.apitrial.apitrial.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apitrial.apitrial.model.TransactionRequest;


@RestController //class is rest controller 
@RequestMapping("/pay") //this is "pay" - base endpoint for the transaction controller
public class TransactionController 
{
    @PostMapping //handling POST requests to the "/pay" endpoint
    public ResponseEntity<?> doTransaction(@RequestBody TransactionRequest req)
    {
        if (req.getUsername().equals("banned"))
        {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("XX - Banned User");
        }
        else if (req.getAmount() > 1000)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("XX - Amount exceeds limit");
        }
        else
        {
            return ResponseEntity.ok().body("transaction feasible");
        }
    }
}
