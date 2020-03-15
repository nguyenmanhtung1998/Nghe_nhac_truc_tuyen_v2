package com.example.nghenhactrctuyn.Service;

public class APIservice {
    private static String base_url="https://anacreontic-soups.000webhostapp.com/Server/";
    public  static Dataservice getService(){
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
