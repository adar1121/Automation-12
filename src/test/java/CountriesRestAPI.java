import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class CountriesRestAPI {
    public static void main(String[] args) throws IOException, JSONException {
        Scanner scanner = new Scanner(System.in);
        String playing;
        String userCountryInput;
        boolean onProgress = true;


        System.out.print("Enter Country: ");
        userCountryInput = scanner.next();

        while (onProgress) {

            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder()
                    .url("https://restcountries.com/v2/name/" + userCountryInput + "?fullText=true")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.code() == 200) {
                String jsonData = response.body().string();
                response.close();
                // parse JSON
//        JSONObject mainJsonObject = new JSONObject(jsonData);
                JSONArray mainJsonArray = new JSONArray(jsonData);
                JSONObject data = (JSONObject) (mainJsonArray.getJSONObject(0));
                JSONArray currency = data.getJSONArray("currencies");
                JSONObject insideCurrencies = (JSONObject) (currency.getJSONObject(0));
                String symbol = insideCurrencies.getString("symbol");

                // get Json object

                // get value
//        double val = resultsJson.getDouble("rate");
                System.out.println(data.get("region"));
                System.out.println(data.get("borders"));
                System.out.println(symbol);

                System.out.print("Enter a Country: ");
                userCountryInput = scanner.next();


            } else if (userCountryInput.equals("exit")) {
                onProgress = false;

            } else {
                System.out.print("country doesn't exist, please re enter: ");
                userCountryInput = scanner.next();
            }
        }

        System.out.println("Good bye ☺");

    }
}

