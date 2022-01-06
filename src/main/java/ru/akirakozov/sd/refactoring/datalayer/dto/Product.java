package ru.akirakozov.sd.refactoring.datalayer.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class Product {
    private final @NonNull String name;
    private final @NonNull long price;
}
