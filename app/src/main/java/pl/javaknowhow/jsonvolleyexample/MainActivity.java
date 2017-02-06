package pl.javaknowhow.jsonvolleyexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView currency;
    private String currencyUrl = "https://openexchangerates.org/api/latest.json?app_id=WSTAW_TUTAJ_SWÓJ_KLUCZ_API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currency = (TextView) findViewById(R.id.currencyTextPLN);

        getJsonObject(currencyUrl);

    }

    private void getJsonObject(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONObject ratesObject = response.getJSONObject("rates");
                    // Zmień PLN na inną walutę
                    String currencyText = ratesObject.getString("PLN");

                    currency.setText("USDPLN " + currencyText);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
