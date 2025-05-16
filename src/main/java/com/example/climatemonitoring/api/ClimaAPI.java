package com.example.climatemonitoring.api;

import com.example.climatemonitoring.models.Clima;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ClimaAPI {

    private static final String API_KEY = "c83a9b491f99c00c44dfd4af7d22b870";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public Clima consultarClima(String cidade) {
        try {
            String urlStr = String.format("%s?q=%s&appid=%s&units=metric&lang=pt", BASE_URL, cidade, API_KEY);
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Lê a resposta da API
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
            reader.close();

            // Converte a resposta JSON
            JSONObject json = new JSONObject(resposta.toString());

            String localizacao = json.getString("name");
            double temperatura = json.getJSONObject("main").getDouble("temp");
            double umidade = json.getJSONObject("main").getDouble("humidity");
            String condicoes = json.getJSONArray("weather").getJSONObject(0).getString("description");

            return new Clima(localizacao, temperatura, umidade, condicoes);

        } catch (Exception e) {
            System.err.println("Erro ao buscar clima: " + e.getMessage());
            return null;
        }
    }
}
