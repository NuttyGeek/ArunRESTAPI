package com.nuttygeek.restapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Hero> heroes;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heroes = new ArrayList<>();
        resultTextView = findViewById(R.id.result);
    }

    /**
     * executed when button is click in UI
     * @param v view clicked on
     */
    public void clicked(View v){

       //  volley  solution
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://simplifiedcoding.net/demos/view-flipper/heroes.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.v("nuttygeek_res", response);

                try {
                    JSONObject resObj = new JSONObject(response);
                    JSONArray heroesJsonArray = resObj.getJSONArray("heroes");
                    Log.v("nuttygeek_heroes", heroesJsonArray.toString());
                    for(int i=0; i<heroesJsonArray.length(); i++){
                        JSONObject heroJsonObj = heroesJsonArray.getJSONObject(i);
                        String heroName = heroJsonObj.getString("name");
                        String url = heroJsonObj.getString("imageurl");
                        Hero hero = new Hero(heroName, url);
                        heroes.add(hero);
                    }
                    // populating the UI

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateTextView();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("nuttygeek_res", error.getMessage());
            }
        });
        queue.add(stringRequest);

    }

    /**
     * populate the textview with result string
     */
    public void populateTextView(){
        String res = "";
        for(Hero hero: heroes){
            res += hero.getFullString()+"\n\n";
        }
        resultTextView.setText(res);
    }
}
