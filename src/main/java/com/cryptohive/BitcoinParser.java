package com.cryptohive;

import org.json.JSONObject;

public class BitcoinParser {

    // Parse the current Bitcoin price from the response JSON
    public static double parseBitcoinPrice(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        // Assuming the JSON format: {"bitcoin": {"usd": 12345.67}}
        JSONObject bitcoinData = jsonObject.getJSONObject("bitcoin");
        return bitcoinData.getDouble("usd");
    }
}
