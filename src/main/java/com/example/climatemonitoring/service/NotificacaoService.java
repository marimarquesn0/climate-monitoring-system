package com.example.climatemonitoring.service;

import com.example.climatemonitoring.data.NotificacaoRepository;
import com.example.climatemonitoring.models.Notificacao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public void adicionarNotificacao(String mensagem) {
        List<Notificacao> notificacoes = notificacaoRepository.listarTodos();
        Notificacao nova = new Notificacao(mensagem, LocalDateTime.now());
        notificacoes.add(nova);
        notificacaoRepository.salvarTodos(notificacoes);
    }

    public List<Notificacao> listarNotificacoes() {
        return notificacaoRepository.listarTodos();
    }
}
