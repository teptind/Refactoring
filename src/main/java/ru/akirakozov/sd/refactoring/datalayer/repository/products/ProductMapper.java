package ru.akirakozov.sd.refactoring.datalayer.repository.products;

import lombok.SneakyThrows;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;
import ru.akirakozov.sd.refactoring.datalayer.repository.common.ResultSetExtendedMapper;

import java.sql.ResultSet;
import java.util.Optional;

public class ProductMapper implements ResultSetExtendedMapper<Product> {

    @Override
    @SneakyThrows
    public Optional<Product> mapToOptional(ResultSet resultSet) {
        return resultSet.next() ? Optional.of(map(resultSet)) :  Optional.empty();
    }

    @Override
    @SneakyThrows
    public Product map(ResultSet resultSet) {
        var name = resultSet.getString("NAME");
        var price = resultSet.getInt("PRICE");

        return new Product(name, price);
    }
}
