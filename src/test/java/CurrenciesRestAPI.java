import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Scanner;

public class CurrenciesRestAPI {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int amountUserInput = 0;
        System.out.println("Welcome to currency converter");
        System.out.print("Please enter an amount of Dollars to convert: ");
        amountUserInput = scanner.nextInt();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.currencyapi.com/v3/latest?apikey=8zPM0kN9qeIkYQ53CGBv5pNiMhLsy9iGj6GhmdKC&currencies=ILS")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        response.close();
        JSONObject mainJsonObject = new JSONObject(jsonData);
        JSONObject dataresult = mainJsonObject.getJSONObject("data");
        JSONObject result = dataresult.getJSONObject("ILS");
        double value = result.getDouble("value");
        System.out.println("Result: " + value*amountUserInput);
        System.out.println("Thanks for using our currency converter");
    }
}
