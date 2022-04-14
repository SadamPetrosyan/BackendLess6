package swagger_model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SwaggerProductsRequest {

    private int id;
    private String title;
    private int prise;
    private String categoryTitle;

}