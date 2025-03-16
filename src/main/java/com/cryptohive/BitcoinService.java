package com.cryptohive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BitcoinService {

    private static final Logger logger = LoggerFactory.getLogger(BitcoinService.class);

    private double currentPrice = 0.0;

    @Autowired
    private BitcoinAPI bitcoinAPI;

    private String currency = "usd"; // Default currency

    @Scheduled(fixedRate = 60000) // This will fetch new Bitcoin data every minute
    public void updateBitcoinPrice() {
        try {
            String jsonData = bitcoinAPI.fetchBitcoinData(currency);  
            
            if (jsonData != null && !jsonData.isEmpty()) {
                this.currentPrice = BitcoinParser.parseBitcoinPrice(jsonData); // Parsing method, ensure you have this
                logger.info("Updated Bitcoin Price: ${}", this.currentPrice);
            } else {
                logger.warn("No Bitcoin data available for currency: {}", currency);
            }
        } catch (Exception e) {
            logger.error("Error fetching or parsing Bitcoin data", e);
        }
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
