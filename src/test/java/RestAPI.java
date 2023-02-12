import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class RestAPI {
    public static void main(String[] args) throws IOException, JSONException {
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://restcountries.com/v2/name/israel?fullText=true")
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        response.close();
        // parse JSON
//        JSONObject mainJsonObject = new JSONObject(jsonData);
        JSONArray mainJsonArray = new JSONArray(jsonData);
        JSONObject data = (JSONObject) (mainJsonArray.getJSONObject(0));
        JSONArray currency = data.getJSONArray("currencies");
        JSONObject insideCurrencies = (JSONObject) (currency.getJSONObject(0));
        String symbol = insideCurrencies.getString("symbol");




//        String symbolCoin = symbol.getString("symbol");


        // get Json object

        // get value
//        double val = resultsJson.getDouble("rate");
        System.out.println(data.get("region"));
        System.out.println(data.get("borders"));
        System.out.println(symbol);

    }
}
