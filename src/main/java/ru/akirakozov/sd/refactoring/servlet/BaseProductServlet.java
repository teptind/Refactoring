package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;
import ru.akirakozov.sd.refactoring.datalayer.repository.ProductRepository;
import ru.akirakozov.sd.refactoring.datalayer.repository.ProductRepositoryImpl;

import javax.servlet.http.HttpServlet;


public abstract class BaseProductServlet extends HttpServlet {
    protected final ProductRepository productRepository = new ProductRepositoryImpl(new DataSource("jdbc:sqlite:test.db"));
}
