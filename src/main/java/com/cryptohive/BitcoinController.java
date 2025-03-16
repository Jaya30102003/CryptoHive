package com.cryptohive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:8082") // Enable cross-origin for the frontend to access
public class BitcoinController {

    @Autowired
    private BitcoinAPI bitcoinAPI;

    @GetMapping("/bitcoin-price")
    public String getBitcoinPrice(@RequestParam String currency) throws IOException {
        return bitcoinAPI.fetchBitcoinData(currency);
    }

    @GetMapping("/bitcoin-history")
    public String getBitcoinHistory(@RequestParam String currency, @RequestParam long fromDate, @RequestParam long toDate) throws IOException {
        // Passing the fromDate and toDate as long values instead of String
        return bitcoinAPI.fetchBitcoinHistory(currency, fromDate, toDate);
    }
}
