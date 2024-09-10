package br.com.renutrir.servicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleMapsService {

    private static final String API_KEY = "SUA CHAVE DO MAPS";

    public static double calcularDistancia(String origem, String destino) throws Exception {
        origem = origem.replace(" ", "+");
        destino = destino.replace(" ", "+");

        // URL da API do Google Distance Matrix
        String urlString = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origem + "&destinations=" + destino + "&key=" + API_KEY;

        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Ler a resposta da API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Converter resposta para JSON
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray rows = jsonResponse.getJSONArray("rows");
        JSONObject elements = rows.getJSONObject(0);
        JSONArray elementArray = elements.getJSONArray("elements");
        JSONObject element = elementArray.getJSONObject(0);
        JSONObject distanceObject = element.getJSONObject("distance");

        // Distância em metros
        double distanciaMetros = distanceObject.getDouble("value");

        // Converter metros para quilômetros
        return distanciaMetros / 1000;
    }
}
