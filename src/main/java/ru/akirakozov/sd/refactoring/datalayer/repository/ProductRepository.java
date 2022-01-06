package ru.akirakozov.sd.refactoring.datalayer.repository;

import ru.akirakozov.sd.refactoring.datalayer.dto.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    void add(Product product);

    List<Product> getAll();

    int countAll();

    Optional<Product> getOneWithMaxPrice();

    Optional<Product> getOneWithMinPrice();

    long getSummaryPrice();
}
