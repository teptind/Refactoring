package ru.akirakozov.sd.refactoring.servlet;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.akirakozov.sd.refactoring.common.BaseTest;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;
import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddProductServletTest extends BaseTest {

    private static AddProductServlet addProductServlet;

    public AddProductServletTest() {
        super(new DataSource("jdbc:sqlite:test.db"));
        addProductServlet = new AddProductServlet();
    }


    @BeforeEach
    void prepareTable() {
        createTableIfNotExists();
        cleanTable();
    }

    @Test
    @SneakyThrows
    void shouldPersistSingleCreatedProduct() {
        var product = new Product("car", 200);

        createProduct(product);

        var actual = getAllProducts();

        assertEquals(List.of(product), actual);
    }

    @Test
    @SneakyThrows
    void shouldPersistAllCreatedProducts() {
        var products = List.of(
                new Product("car", 300),
                new Product("plane", 1000)
        );

        products.forEach(this::createProduct);

        assertEquals(products, getAllProducts());
    }

    @Test
    @SneakyThrows
    void shouldPersistAllCreatedProductsWithExisting() {
        var products = List.of(
                new Product("car", 300),
                new Product("plane", 1000)
        );

        var existedProducts = List.of(new Product("door", 50));
        fillTable(existedProducts);

        products.forEach(this::createProduct);

        var expectedProducts = Stream.concat(existedProducts.stream(), products.stream()).collect(Collectors.toList());
        var actualProducts = getAllProducts();

        assertEquals(expectedProducts, actualProducts);
    }

//    @Test
//    @SneakyThrows
//    void shouldThrowOnNameParameterIsAbsent() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            var request = Mockito.mock(HttpServletRequest.class);
//            var response = Mockito.mock(HttpServletResponse.class);
//
//            Mockito.when(request.getParameter("price")).thenReturn("10");
//
//            addProductServlet.doGet(request, response);
//        });
//    }

    @Test
    @SneakyThrows
    void shouldThrowOnPriceParameterIsAbsent() {
        assertThrows(IllegalArgumentException.class, () -> {
            var request = Mockito.mock(HttpServletRequest.class);
            var response = Mockito.mock(HttpServletResponse.class);

            Mockito.when(request.getParameter("name")).thenReturn("car");

            addProductServlet.doGet(request, response);
        });
    }

    @Test
    @SneakyThrows
    void shouldThrowOnPriceParameterIsNotInteger() {
        assertThrows(NumberFormatException.class, () -> {
            var request = Mockito.mock(HttpServletRequest.class);
            var response = Mockito.mock(HttpServletResponse.class);

            Mockito.when(request.getParameter("name")).thenReturn("car");
            Mockito.when(request.getParameter("price")).thenReturn("abc");

            addProductServlet.doGet(request, response);
        });
    }

    @SneakyThrows
    private void createProduct(Product product) {
        var response = Mockito.mock(HttpServletResponse.class);

        var stringWriter = new StringWriter();
        var writer = new PrintWriter(stringWriter);
        Mockito.when(response.getWriter()).thenReturn(writer);

        addProductServlet.doGet(addProductRequest(product), response);

        assertEquals("OK", stringWriter.toString().trim());
    }

    private HttpServletRequest addProductRequest(Product product) {
        var request = Mockito.mock(HttpServletRequest.class);

        Mockito.when(request.getParameter("name")).thenReturn(product.getName());
        Mockito.when(request.getParameter("price")).thenReturn(String.valueOf(product.getPrice()));
        return request;
    }

    @AfterEach
    void clean() {
        cleanTable();
    }


}