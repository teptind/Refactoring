package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class AddProductServlet extends BaseProductServlet {

    public AddProductServlet(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        validateName(name);

        long price = Long.parseLong(request.getParameter("price"));

        productRepository.add(new Product(name, price));

        addContent(response, "OK");
    }

    private void validateName(String name) {
        if (name == null) throw new IllegalArgumentException();
    }
}
