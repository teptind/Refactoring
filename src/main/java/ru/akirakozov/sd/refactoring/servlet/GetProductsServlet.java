package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends BaseProductServlet {

    public GetProductsServlet(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var products = productRepository.getAll();

        addContent(response, productHtmlMapper.toDtoList(products));
    }
}
