import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalJunto {
    public static void main(String[] args) throws InterruptedException {

        // Definimos la tabla de las divisas a convertir de acuerdo a las opciones del menu
        String[][] OpcDivisas = {
                {"USD", "MXN", "CAD", "MXN", "USD", "ARS", "USD", "BRL"},
                {"MXN", "USD", "MXN", "CAD3", "ARS", "USD", "BRL", "USD"}
        };

        int opcion = 9;

        do {
            // Menú
            System.out.println("*************************************");
            System.out.println("Conversor de Divisas");
            System.out.println("1) Dolar            =>>  Peso mexicano");
            System.out.println("2) Peso mexicano    =>>  Dolar");
            System.out.println("3) Dolar canadiense =>>  Peso mexicano");
            System.out.println("4) Peso mexicano    =>>  Dolar canadiense");
            System.out.println("5) Dolar            =>>  Peso argentino");
            System.out.println("6) Peso argentino   =>>  Dolar");
            System.out.println("7) Dolar            =>>  Real brasileño");
            System.out.println("8) Real brasileño   =>>  Dolar");
            System.out.println("9) Salir");

            Scanner lectura = new Scanner(System.in);
            //Leemos la opcion del menu
            System.out.println("Elija una opción valida");
            var strOpcion = lectura.nextLine();
            // convertir strOpcion a integer
            opcion = Integer.valueOf(strOpcion);

            if (opcion >=1 && opcion <= 8){

                // Leemos la cantidad a convertir
                System.out.println("Ingrese el valor que desea convertir");
                var strAmount = lectura.nextLine();


                // Formo la dirección URL para enviar el get a la API ExchangeRate-API

                // obtenemos las monedas a cambiar de acuerdo a la opcion proporcionada
                String base = OpcDivisas[0][opcion - 1];
                String target = OpcDivisas[1][opcion - 1];
                double amount = Double.valueOf(strAmount);


                // se envia la APIKey, la moneda base, la moneda a convertir y la cantidad a convertir
                URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1e2d91dcae1dbdc3b810a263/pair/" +
                        base + "/" + target + "/" + amount);

                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(direccion)
                        .build();

                HttpResponse<String> response = null;
                try {
                    response = client
                            .send(request, HttpResponse.BodyHandlers.ofString());

                    // se obtienen los datos de la get en la cadena json
                    String json = response.body();
                    System.out.println(json);

                    Gson gson = new Gson();
                    CurrencyAPI miCurrencyAPI = gson.fromJson(json, CurrencyAPI.class);
                    System.out.println(miCurrencyAPI);

                    Currency miCurrency = new Currency(miCurrencyAPI);
                    System.out.println(miCurrency);


                } catch (IOException | RuntimeException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                if (opcion == 9) {
                    System.out.println("Programa finalizado exitosamente ...");
                    break;
                }
                else {
                    System.out.println("Opcion inválida, intenta nuevamente");
                }
            }
        }
        while (opcion != 9);
    }
}
