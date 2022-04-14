package clients;

import api.ResponseUtils;
import api.SwaggerApi;
import swagger_model.AutoCompleteProductResponse;
import swagger_model.SwaggerProductsRequest;
import swagger_model.SwaggerProductsResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SwaggerClient {

    private static final String API_URL = "https://minimarket1.herokuapp.com/market/swagger-ui.html#/";

    private SwaggerApi api;

    public SwaggerClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(1000, TimeUnit.MILLISECONDS)
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .writeTimeout(1000, TimeUnit.MILLISECONDS)
                .callTimeout(Duration.ofSeconds(1))
                .addInterceptor(loggingInterceptor)
                .build();



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        this.api = retrofit.create(SwaggerApi.class);
    }

    public SwaggerProductsResponse findAllProducts(
            SwaggerProductsRequest request
    ) {
        Call<SwaggerProductsResponse> responseCall = api.findAllProducts(
                request.getId(),
                request.getTitle(),
                request.getPrise(),
                request.getCategoryTitle()

        );
        return ResponseUtils.executeCall(responseCall);
    }

    public AutoCompleteProductResponse autocomplete(
            String query,
            Long number
    ) {
        Call<AutoCompleteProductResponse> responseCall = api.autocomplete( query, number);
        return ResponseUtils.executeCall(responseCall);
    }


}