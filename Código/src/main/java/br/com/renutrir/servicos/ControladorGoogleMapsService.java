package br.com.renutrir.servicos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class ControladorGoogleMapsService {

    private static final String API_KEY = "{API KEY Google Maps";

    public String calcularDistanciaEntreDoisEnderecos(String origem, String destino) throws Exception {
        origem = origem.replace(" ", "+");
        destino = destino.replace(" ", "+");

        String urlString = "{url/distance-matrix-google-maps}" + origem + "&destinations=" + destino + "&key=" + API_KEY;

        URL url = new URL(urlString);
        HttpURLConnection conn = null;
        StringBuilder response = new StringBuilder();

        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                throw new Exception("Erro ao conectar com a API do Google Maps. Código de resposta: " + responseCode);
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            JSONObject jsonResponse = new JSONObject(response.toString()); //converte pra json
            JSONArray rows = jsonResponse.getJSONArray("rows");
            if (rows.length() == 0) {
                throw new Exception("Nenhuma resposta válida da API para as localidades fornecidas.");
            }

            JSONObject elements = rows.getJSONObject(0);
            JSONArray elementArray = elements.getJSONArray("elements");
            JSONObject element = elementArray.getJSONObject(0);

            if (element.has("distance")) {
                JSONObject distanceObject = element.getJSONObject("distance");
                double distanciaMetros = distanceObject.getDouble("value");

                return medidaDistancia(distanciaMetros);
            } else {
                throw new Exception("Não foi possível obter a distância para os endereços fornecidos.");
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
    public static String medidaDistancia(double distanciaMetros) {

        if (distanciaMetros >= 1000) {

            return String.format("%.1f Km", distanciaMetros / 1000);
        }

        return String.format("%.0f metros", distanciaMetros);
    }
}
