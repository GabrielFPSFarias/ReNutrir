package br.com.renutrir.servicos;

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
}