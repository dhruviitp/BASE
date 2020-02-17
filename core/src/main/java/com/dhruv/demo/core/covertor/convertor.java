package com.dhruv.demo.core.covertor;

public interface convertor<I,O> {

    I convertToEntity(O model);

    O convertToModel(I entity);

}
