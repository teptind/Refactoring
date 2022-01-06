package ru.akirakozov.sd.refactoring.viewlayer;

import ru.akirakozov.sd.refactoring.datalayer.dto.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductHtmlMapper implements DtoHtmlMapper<Product> {
    private static final String HTML_TEMPLATE = "<html><body>%s</body></html>";

    @Override
    public String toSingleDto(String message, Product dto) {
        return String.format(HTML_TEMPLATE, String.format("<h1>%s</h1>%s", message, mapProductToHtml(dto)));
    }

    @Override
    public String toHeaderMessage(String message) {
        return String.format(HTML_TEMPLATE, String.format("<h1>%s</h1>", message));
    }

    @Override
    public String toMessage(String message) {
        return String.format(HTML_TEMPLATE, message);
    }

    @Override
    public String toDtoList(List<Product> dtos) {
        return String.format(HTML_TEMPLATE, dtos.stream().map(this::mapProductToHtml).collect(Collectors.joining()));
    }

    private String mapProductToHtml(Product product) {
        return product.getName() + "\t" + product.getPrice() + "</br>";
    }
}
