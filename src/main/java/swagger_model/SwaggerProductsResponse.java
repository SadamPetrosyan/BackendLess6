package swagger_model;

import lombok.Getter;

import java.util.List;

@Getter

public class SwaggerProductsResponse {
    private String type;

    private List<ProductView> products;

    private int id;

    private String title;

    private int prise;

    private String categoryTitle;



}