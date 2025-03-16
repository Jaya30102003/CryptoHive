package com.cryptohive;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class BitcoinAPI {

    private final OkHttpClient client = new OkHttpClient();

    // Fetch current Bitcoin data for a given currency
    public String fetchBitcoinData(String currency) throws IOException {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=" + currency;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    // Fetch historical Bitcoin data for a given currency, fromDate, and toDate
    public String fetchBitcoinHistory(String currency, long fromDate, long toDate) throws IOException {
        String url = "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart/range?vs_currency=" + currency
            + "&from=" + fromDate + "&to=" + toDate;

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    // Helper method to convert date string to UNIX timestamp (seconds since epoch)
    @SuppressWarnings("unused")
	private long convertToUnixTimestamp(String date) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parsedDate = sdf.parse(date);
            return parsedDate.getTime() / 1000; // Convert to seconds
        } catch (java.text.ParseException e) {
            e.printStackTrace();
            return 0; // Return 0 if date parsing fails
        }
    }
}
