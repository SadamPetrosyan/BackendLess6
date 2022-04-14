import BackendLess6.db.dao.ProductsMapper;
import BackendLess6.db.model.Products;
import BackendLess6.db.model.ProductsExample;
import groovy.util.logging.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.util.List;



@Slf4j
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("myBatisConfig.xml"));

        SqlSession session = sessionFactory.openSession();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);

        Products product = productsMapper.selectByPrimaryKey(22L);
        log.info("product: {}", product);

        ProductsExample filter = new ProductsExample();

        filter.createCriteria()
                .andTitleGreaterThan("b")
                .andTitleLessThan("z");

        List<Products> products = productsMapper.selectByExample(filter);
        products.forEach(p -> log.info("product: {}", p));

        Products newProduct = new Products();
        newProduct.setPrice(120);
        newProduct.setTitle("BigBurger");
        newProduct.setCategoryId(1L);

        productsMapper.insertSelective(newProduct);

        filter.clear();
        filter.createCriteria()
                .andTitleEqualTo("Burger");

        List<Products> newProducts = productsMapper.selectByExample(filter);

        newProducts.forEach(p -> log.info("product new: {}", p));


        session.commit();
    }

}
