package br.com.renutrir.servicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleMapsService {

    private static final String API_KEY = "AIzaSyC60cxhNaxQJN2Ra7frE6E8AR2JCwNMLCk";

    // Método para calcular a distância entre dois endereços
    public static double calcularDistanciaEntreDoisEnderecos(String origem, String destino) throws Exception {
        origem = origem.replace(" ", "+");
        destino = destino.replace(" ", "+");

        // URL da API do Google Distance Matrix
        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origem + "&destinations=" + destino + "&key=" + API_KEY;

        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        StringBuilder response = new StringBuilder();

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Verifica se a resposta da API foi bem-sucedida
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("Erro ao conectar com a API do Google Maps. Código de resposta: " + responseCode);
            }

            // Ler a resposta da API
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Converter resposta para JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray rows = jsonResponse.getJSONArray("rows");
            if (rows.length() == 0) {
                throw new Exception("Nenhuma resposta válida da API para as localidades fornecidas.");
            }

            // Verificando o primeiro elemento de resposta
            JSONObject elements = rows.getJSONObject(0);
            JSONArray elementArray = elements.getJSONArray("elements");
            JSONObject element = elementArray.getJSONObject(0);

            // Verifica se há uma distância válida na resposta
            if (element.has("distance")) {
                JSONObject distanceObject = element.getJSONObject("distance");
                double distanciaMetros = distanceObject.getDouble("value");
                return (distanciaMetros / 1000); // Converte para quilômetros
            } else {
                throw new Exception("Não foi possível obter a distância para os endereços fornecidos.");
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
