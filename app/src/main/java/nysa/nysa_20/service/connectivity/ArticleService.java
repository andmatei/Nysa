package nysa.nysa_20.service.connectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ArticleService {
    private ArticleService(){}

    public static JSONArray getArticles(){
        JSONArray rez = new JSONArray();

        //TODO: retrieve Articles

        return rez;
    }

    public static JSONArray getArticles(String s){
        JSONArray jsonArray = new JSONArray();

        try {
            JSONObject jsonObject = new JSONObject(s);

            jsonArray = jsonObject.getJSONArray("articles");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonArray;
    }
}
