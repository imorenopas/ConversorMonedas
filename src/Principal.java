import com.google.gson.Gson;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {

        // Definimos la tabla de las divisas a convertir de acuerdo a las opciones del menu
        String[][] OpcDivisas = {
                {"USD", "MXN", "CAD", "MXN", "USD", "ARS", "USD", "BRL"},
                {"MXN", "USD", "MXN", "CAD", "ARS", "USD", "BRL", "USD"}
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

            if (opcion >= 1 && opcion <= 8) {

                // Leemos la cantidad a convertir
                System.out.println("Ingrese el valor que desea convertir");
                var strAmount = lectura.nextLine();

                // Formo la dirección URL para enviar el get a la API ExchangeRate-API

                // obtenemos las monedas a cambiar de acuerdo a la opcion proporcionada
                String base = OpcDivisas[0][opcion - 1];
                String target = OpcDivisas[1][opcion - 1];
                double amount = Double.valueOf(strAmount);

                try {
                    // Hacer consulta en la API
                    ConsultaRate miConsultaRate = new ConsultaRate();
                    CurrencyAPI miCurrencyAPI = miConsultaRate.buscarCurrency(base, target, amount);


                    // Genero mi objeto Currency
                    Currency miCurrency = new Currency(miCurrencyAPI);
                    miCurrency.setAmount(amount);
                    System.out.println(miCurrency);
                } finally {
                    System.out.println("El programa ha terminado por un error");
                }


            } else {
                if (opcion == 9) {
                    System.out.println("Programa finalizado exitosamente ...");
                    break;
                } else {
                    System.out.println("Opcion inválida, intenta nuevamente");
                    System.out.println("");
                }
            }
        }
        while (opcion != 9);
    }
}





