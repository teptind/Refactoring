package ru.akirakozov.sd.refactoring.viewlayer;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductHtmlMapperTest {

    private final DtoHtmlMapper<Product> productHtmlMapper = new ProductHtmlMapper();

    @Test
    void shouldMapProduct() {
        var expected = "<html><body><h1>Data: </h1>car\t100</br></body></html>";

        var product = new Product("car", 100);

        assertEquals(expected, productHtmlMapper.toSingleDto("Data: ", product));
    }


    @Test
    void shouldMapAllProducts() {
        var expected = "<html><body>car	100</br>table\t10</br></body></html>";

        var products = List.of(
                new Product("car", 100),
                new Product("table", 10)
        );

        assertEquals(expected, productHtmlMapper.toDtoList(products));
    }

    @Test
    void shouldMapMessage() {
        var expected = "<html><body>Message</body></html>";

        assertEquals(expected, productHtmlMapper.toMessage("Message"));
    }

    @Test
    void shouldMapHeaderMessage() {
        var expected = "<html><body><h1>Message</h1></body></html>";

        assertEquals(expected, productHtmlMapper.toHeaderMessage("Message"));
    }
}