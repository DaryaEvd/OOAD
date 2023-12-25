package ooad.timewise.cheerup;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class APIDataTaker {
    private static final String CATS_API_KEY = "live_6yLnFUjOhdHHOrKTtdTrvZrlBJymtaL6qYh3xkYE2xDRmLrEgNKY323vteABVEKR";
    private static final String CATS_QUERY = "https://api.thecatapi.com/v1/images/search?api_key=" + CATS_API_KEY;
    private static final String QUOTE_QUERY = "https://favqs.com/api/qotd";
    private static final APIDataTaker INSTANCE = new APIDataTaker();

    private APIDataTaker() {
    }

    static APIDataTaker getInstance() {
        return INSTANCE;
    }

    String getUrlCatPic() throws IOException {

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(CATS_QUERY)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                okhttp3.ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return null;
                }
                return parseCatImageUrl(responseBody.string());
            }

        }
        return null;
    }

    String[] getUrlQuoteText() throws IOException {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(QUOTE_QUERY)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                okhttp3.ResponseBody responseBody = response.body();
                if (responseBody == null) {
                    return null;
                }
                return parseQuoteTextUrl(responseBody.string());
            }

        }
        return null;
    }

    private String[] parseQuoteTextUrl(String response) {
        String[] res = new String[2];
        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONObject quote = jsonResponse.getJSONObject("quote");

            String author = quote.getString("author");
            String body = quote.getString("body");

            res[0] = author;
            res[1] = body;

            return res;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseCatImageUrl(String jsonResponse) {
        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                return jsonObject.getString("url");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
