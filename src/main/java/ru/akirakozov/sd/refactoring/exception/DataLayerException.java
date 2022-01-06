package ru.akirakozov.sd.refactoring.exception;

public class DataLayerException extends RuntimeException {
    public DataLayerException(String message) {
        super(message);
    }

    public DataLayerException(Exception inner) {
        super(inner);
    }
}
