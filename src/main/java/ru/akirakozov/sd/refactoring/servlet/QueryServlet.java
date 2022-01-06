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
public class QueryServlet extends BaseProductServlet {
    public QueryServlet(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        switch (command) {
            case "max":
                doMax(response);
                break;
            case "min":
                doMin(response);
                break;
            case "sum":
                doSum(response);
                break;
            case "count":
                doCount(response);
                break;
            default:
                response.getWriter().println("Unknown command: " + command);
        }
    }

    private void doMax(HttpServletResponse response) {
        var message = "Product with max price: ";

        addContent(response, productRepository.getOneWithMaxPrice()
                .map(p -> productHtmlMapper.toSingleDto(message, p))
                .orElse(productHtmlMapper.toHeaderMessage(message)));
    }

    private void doMin(HttpServletResponse response) {
        var message = "Product with min price: ";

        addContent(response, productRepository.getOneWithMinPrice()
                .map(p -> productHtmlMapper.toSingleDto(message, p))
                .orElse(productHtmlMapper.toHeaderMessage(message)));

    }

    private void doSum(HttpServletResponse response) {
        addContent(response, productHtmlMapper.toMessage("Summary price: " + productRepository.getSummaryPrice()));
    }

    private void doCount(HttpServletResponse response) {
        addContent(response, productHtmlMapper.toMessage("Number of products: " + productRepository.countAll()));
    }
}
