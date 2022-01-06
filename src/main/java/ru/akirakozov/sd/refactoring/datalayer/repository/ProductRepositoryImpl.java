package ru.akirakozov.sd.refactoring.datalayer.repository;

import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;
import ru.akirakozov.sd.refactoring.datalayer.dto.Product;
import ru.akirakozov.sd.refactoring.datalayer.repository.common.BaseRepository;
import ru.akirakozov.sd.refactoring.datalayer.repository.products.ProductMapper;

import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl extends BaseRepository implements ProductRepository  {
    private final ProductMapper productMapper = new ProductMapper();

    public ProductRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void add(Product product) {
        updatingQuery(String.format("insert into PRODUCT (NAME, PRICE) VALUES (\"%s\", %d)",
                product.getName(), product.getPrice()));
    }

    @Override
    public List<Product> getAll() {
        return selectingQuery("SELECT * FROM PRODUCT", productMapper::mapMany);
    }

    @Override
    public int countAll() {
        return selectingQuery("SELECT COUNT(*) FROM PRODUCT", resultSet -> {
            resultSet.next();
            return resultSet.getInt("COUNT");
        });
    }

    @Override
    public Optional<Product> getOneWithMaxPrice() {
        return selectingQuery("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1", productMapper::mapToOptional);
    }

    @Override
    public Optional<Product> getOneWithMinPrice() {
        return selectingQuery("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1", productMapper::mapToOptional);
    }

    @Override
    public long getSummaryPrice() {
        return selectingQuery("SELECT SUM(PRICE) as SummaryPrice FROM PRODUCT", resultSet -> {
            resultSet.next();
            return resultSet.getLong("SummaryPrice");
        });
    }
}
