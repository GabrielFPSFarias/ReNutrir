/*package br.com.renutrir.servicos;
import com.google.maps.GeoApiContext;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixElement;
import com.google.maps.model.DistanceMatrixRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class CalculadorDistancia {
    private static final String API_KEY = "SUA CHAVE DO MAPS"; // Substitua por sua chave
    private GeoApiContext context;
    public CalculadorDistancia() {
        // Inicializando o contexto da API do Google Maps com a chave fornecida
        this.context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
    }
    // Método para calcular a distância entre o endereço do doador e uma lista de instituições
    public List<ResultadoDistancia> calcularDistancia(String enderecoDoador, List<String> enderecosInstituicoes) {
        List<ResultadoDistancia> resultados = new ArrayList<>();

        try {
            // Chamando a API DistanceMatrix para calcular as distâncias
            DistanceMatrix result = DistanceMatrixApi.getDistanceMatrix(context, new String[]{enderecoDoador}, enderecosInstituicoes.toArray(new String[0])).await();

            // Processando os resultados
            DistanceMatrixRow[] rows = result.rows;

            for (int i = 0; i < rows.length; i++) {
                DistanceMatrixRow row = rows[i];
                for (int j = 0; j < row.elements.length; j++) {
                    DistanceMatrixElement element = row.elements[j];
                    if (element.distance != null) {
                        String instituicao = enderecosInstituicoes.get(j);
                        long distanciaEmMetros = element.distance.inMeters;

                        // Adicionando o resultado na lista
                        resultados.add(new ResultadoDistancia(instituicao, distanciaEmMetros));
                    }
                }
            }

            // Ordenando os resultados pela menor distância
            Collections.sort(resultados, Comparator.comparingLong(ResultadoDistancia::getDistanciaEmMetros));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultados;
    }

    // Fechar o contexto para evitar vazamentos de memória
    public void fechar() {
        context.shutdown();
    }
}*/
/*
package br.com.renutrir.servicos;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class CalculadorDistancia {

    private static final String API_KEY = "SUA_CHAVE_DE_API_AQUI";

    public static void main(String[] args) {
        String origin = "Avenida Paulista, São Paulo, Brasil";  // Endereço do doador
        String destination = "Rua da Consolação, São Paulo, Brasil";  // Endereço da instituição

        try {
            String distance = getDistanceBetweenAddresses(origin, destination);
            System.out.println("Distância: " + distance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getDistanceBetweenAddresses(String origin, String destination) throws Exception {
        // Substituir espaços por %20 para a URL
        origin = origin.replace(" ", "%20");
        destination = destination.replace(" ", "%20");

        // URL da API Distance Matrix
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destination + "&key=" + API_KEY;

        // Cria o cliente HTTP
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse response = httpClient.execute(request);

        try {
            String jsonResponse = EntityUtils.toString(response.getEntity());

            // Parse do JSON para extrair a distância
            JSONObject jsonObject = new JSONObject(jsonResponse);
            JSONArray rows = jsonObject.getJSONArray("rows");
            JSONObject elements = rows.getJSONObject(0).getJSONArray("elements").getJSONObject(0);

            if (elements.getString("status").equals("OK")) {
                JSONObject distance = elements.getJSONObject("distance");
                return distance.getString("text"); // Retorna a distância em formato legível
            } else {
                return "Não foi possível calcular a distância";
            }

        } finally {
            response.close();
            httpClient.close();
        }
    }
}*/