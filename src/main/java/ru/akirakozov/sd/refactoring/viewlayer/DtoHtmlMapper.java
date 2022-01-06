package ru.akirakozov.sd.refactoring.viewlayer;

import java.util.List;

public interface DtoHtmlMapper<T> {
    String toSingleDto(String message, T dto);

    String toDtoList(List<T> dtos);

    String toHeaderMessage(String message);

    String toMessage(String message);
}
