package ru.akirakozov.sd.refactoring.datalayer.repository.common;

import ru.akirakozov.sd.refactoring.datalayer.bootstrap.DataSource;
import ru.akirakozov.sd.refactoring.exception.DataLayerException;

public abstract class BaseRepository {
    private final DataSource dataSource;

    protected BaseRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected final void updatingQuery(String sqlQuery) {
        try (var dbConnection = dataSource.getConnection()) {
            dbConnection.prepareStatement(sqlQuery).execute();
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }

    protected final <T> T selectingQuery(String sqlQuery, ResultSetMapper<T> resultMapper) {
        try (var dbConnection = dataSource.getConnection()) {
            var result = dbConnection.prepareStatement(sqlQuery).executeQuery();

            return resultMapper.map(result);
        } catch (Exception e) {
            throw new DataLayerException(e);
        }
    }
}
