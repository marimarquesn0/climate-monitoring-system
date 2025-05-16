package com.example.climatemonitoring.data;

import com.example.climatemonitoring.utils.JsonUtil;

import java.util.List;

public class JsonPersistence<T> implements Persistence<T> {

    private final String filePath;
    private final Class<T> clazz;

    public JsonPersistence(String filePath, Class<T> clazz) {
        this.filePath = filePath;
        this.clazz = clazz;
    }

    @Override
    public List<T> listarTodos() {
        return JsonUtil.lerJsonParaLista(filePath, clazz);
    }

    @Override
    public void salvarTodos(List<T> items) {
        JsonUtil.salvarListaComoJson(filePath, items);
    }
}

