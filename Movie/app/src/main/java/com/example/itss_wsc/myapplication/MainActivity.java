package com.example.itss_wsc.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new GetData().execute("https://api.themoviedb.org/3/search/movie?api_key=f4aaa9c021d53479334ee7edae88f8ef&language=enUS&query={MOVIE%20NAME}");
    }

    class GetData extends AsyncTask<String, Integer, String>{

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            try{
                URL url = new URL(strings[0].toString());
                HttpURLConnection con = (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line = "";
                while((line = reader.readLine()) !=null){
                    result += line;
                }

            }catch (Exception ex){

            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject obj = new JSONObject(s);

                String page = obj.getString("page");


                JSONArray arrayResult = obj.getJSONArray("results");

                for(Integer i = 0 ; i < arrayResult.length();i++){
                    JSONObject objPerResult = new JSONObject(arrayResult.get(i).toString());

                    String title = objPerResult.getString("title");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
