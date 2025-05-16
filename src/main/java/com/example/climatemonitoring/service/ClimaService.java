package com.example.climatemonitoring.service;

import com.example.climatemonitoring.models.Clima;
import com.example.climatemonitoring.models.observers.ClimaSubject;
import com.example.climatemonitoring.models.observers.Observer;
import com.example.climatemonitoring.models.observers.Subject;
import com.example.climatemonitoring.api.ClimaAPI;
import org.springframework.stereotype.Service;

@Service
public class ClimaService {

    private final ClimaAPI climaAPI;
    private final Subject climaSubject;
    private Clima climaAtual;

    public ClimaService(ClimaAPI climaAPI) {
        this.climaAPI = climaAPI;
        this.climaSubject = new ClimaSubject();
    }

    public Clima getClimaAtual() {
        return climaAtual;
    }

    public void atualizarClima(String localizacao) {
        Clima novoClima = climaAPI.consultarClima(localizacao);
        if (novoClima != null) {
            this.climaAtual = novoClima;
            climaSubject.notifyObservers("Clima atualizado: " + novoClima.toString());
        }
    }

    public void adicionarObservador(Observer observer) {
        climaSubject.addObserver(observer);
    }

    public void removerObservador(Observer observer) {
        climaSubject.removeObserver(observer);
    }
}

