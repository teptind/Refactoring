package ru.akirakozov.sd.refactoring.servlet;

import lombok.SneakyThrows;
import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;
import ru.akirakozov.sd.refactoring.datalayer.repository.ProductRepository;
import ru.akirakozov.sd.refactoring.datalayer.repository.ProductRepositoryImpl;
import ru.akirakozov.sd.refactoring.viewlayer.DtoHtmlMapper;
import ru.akirakozov.sd.refactoring.viewlayer.ProductHtmlMapper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;


public abstract class BaseProductServlet extends HttpServlet {
    protected final ProductRepository productRepository;
    protected final DtoHtmlMapper<Product> productHtmlMapper = new ProductHtmlMapper();

    public BaseProductServlet(DataSource dataSource) {
        productRepository = new ProductRepositoryImpl(dataSource);
    }

    @SneakyThrows
    protected static void addContent(HttpServletResponse response, String content) {
        response.getWriter().println(content);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
