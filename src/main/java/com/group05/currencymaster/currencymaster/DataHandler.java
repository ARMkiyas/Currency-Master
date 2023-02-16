package com.group05.currencymaster.currencymaster;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.File;
import java.util.Objects;

public class DataHandler {
    private static final String API_URL = "https://cdn.moneyconvert.net/api/latest.json";
    private static final String CURRENCY_LIST = "https://raw.githubusercontent.com/ARMkiyas/Currency-Master/master/src/main/resources/Data/currencylist.json";
    private final JsonNode datalist;
    private final JsonNode currencysybols;

    DataHandler() throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(API_URL)
                .method("GET", null)
                .build();
        Request request2 = new Request.Builder()
                .url(CURRENCY_LIST)
                .method("GET", null)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String json = Objects.requireNonNull(response.body()).string();
            ObjectMapper mapper = new ObjectMapper();
            datalist = mapper.readTree(json);
            Response response2 = client.newCall(request2).execute();
            String json2 = Objects.requireNonNull(response2.body()).string();
            currencysybols = mapper.readTree(json2);


        }
        catch (Exception e){
            throw new Exception("Task failed with exception: " + e.getMessage(), e);
        }
    }


    JsonNode getCurrencyList(){

        return datalist;
    }

    String getCurrencySymbol(String currency){
        try {
            return currencysybols.get(currency).get("symbol").asText();
        }catch (Exception e){
            return currency;
        }

    }

    Double convertCurrency(String base, String target, Double amount){
        if (base.equals("USD")){

            return amount*datalist.get("rates").get(target).asDouble();

        }
        Double baseamount = datalist.get("rates").get(base).asDouble();
        Double targetamount = datalist.get("rates").get(target).asDouble();
        return (targetamount/baseamount)*amount;
    }


}
