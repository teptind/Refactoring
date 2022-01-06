package ru.akirakozov.sd.refactoring.datalayer.repository.common;

import lombok.SneakyThrows;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ResultSetExtendedMapper<T> extends ResultSetMapper<T> {
    @SneakyThrows
    default List<T> mapMany(ResultSet resultSet) {
        var result = new ArrayList<T>();

        while (resultSet.next()) {
            result.add(map(resultSet));
        }

        return result;
    }

    Optional<Product> mapToOptional(ResultSet resultSet);
}
