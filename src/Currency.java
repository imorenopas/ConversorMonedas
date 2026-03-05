public class Currency {
    private String base;
    private String target;
    private double rate;
    private double result;

    // este atributo no viene en la API
    private double amount;

    // Constructor
    public Currency(String mbase, String mtarget, double mamount) {
        base = mbase;
        target = mtarget;
        amount = mamount;
    }

    // Constructor con un objeto de la CurrencyAPI como argumento
    public Currency(CurrencyAPI miCurrencyAPI) {
        this.base = miCurrencyAPI.base_code();
        this.target = miCurrencyAPI.target_code();
        this.rate = Double.parseDouble(miCurrencyAPI.conversion_rate());
        this.result = Double.parseDouble(miCurrencyAPI.conversion_result());
    }

    public void setAmount(double mamount) {
        amount = mamount;
    }

    public String toString(){
        return  "La cantidad de " + amount + "[" +
                base + "] corresponde a " +
                result + "[" + target + "]" +
                " al tipo de cambio: " + rate;
    }


}
