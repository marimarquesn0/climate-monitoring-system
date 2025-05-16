package com.example.climatemonitoring.data;

import com.example.climatemonitoring.models.Notificacao;
import com.example.climatemonitoring.utils.JsonUtil;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;

@Repository
public class NotificacaoRepository {

    private static final File ARQUIVO = new File("dados/notificacoes.json");
    private static final Type TIPO_LISTA = new TypeToken<List<Notificacao>>() {}.getType();

    public List<Notificacao> listarTodos() {
        if (!ARQUIVO.exists()) return List.of();
        return JsonUtil.lerJson(ARQUIVO, TIPO_LISTA);
    }

    public void salvarTodos(List<Notificacao> notificacoes) {
        JsonUtil.salvarListaComoJson(ARQUIVO, notificacoes);
    }
}
