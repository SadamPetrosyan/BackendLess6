package api;

import retrofit2.Call;
import retrofit2.http.*;
import swagger_model.AutoCompleteProductResponse;
import swagger_model.SwaggerProductsResponse;

public interface SwaggerApi {

    //todo Returns products
    @GET("/api/v1/products")
    Call<SwaggerProductsResponse> findAllProducts(
            @Query("id") int id,
            @Query("title") String title,
            @Query("prise") int prise,
            @Query("categoryTitle") String categoryTitle

    );



    @GET("food/products/suggest")
    Call<AutoCompleteProductResponse> autocomplete(
            @Query("query") String query,
            @Query("number") Long number
    );
}