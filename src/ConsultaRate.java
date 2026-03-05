import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaRate {

    // Metodo que regresa un objeto CurrencyAPI
    public CurrencyAPI buscarCurrency(String base, String target, double amount) {
        // Formo la dirección URL para enviar el get a la API ExchangeRate-API
        // se envia la APIKey, la moneda base, la moneda a convertir y la cantidad a convertir


        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1e2d91dcae1dbdc3b810a263/pair/" +
                base + "/" + target + "/" + amount);

        //System.out.println("Clase ConsultaRate");
        //System.out.println(direccion);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        HttpResponse<String> response = null;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Convierte la respuesta obtenida del Get a la API en un record Currency
            return new Gson().fromJson(response.body(), CurrencyAPI.class);

        } catch (Exception e) {
            throw new RuntimeException("Error 1");
        }

    }

}
