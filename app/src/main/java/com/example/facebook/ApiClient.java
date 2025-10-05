package com.example.facebook;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "http://10.0.2.2:3000/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build();

    private static Retrofit retrofit = null;
    private static ApiService apiService = null;

    // Trả về instance ApiService
    public static ApiService getApiService() {
        if (apiService == null) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            apiService = retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
