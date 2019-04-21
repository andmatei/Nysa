package nysa.nysa_20.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class APIResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("messege")
    @Expose
    private String messege;

    @SerializedName("data")
    @Expose
    private Map<String, String> data;

    private JSONObject dataJSON;

    public APIResponse(String status, String messege, Map<String, String> data) {
        this.status = status;
        this.messege = messege;
        this.data = data;
        try {
            this.dataJSON = new JSONObject(this.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
        try {
            this.dataJSON = new JSONObject(this.data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONObject getDataJSON() {
        return this.dataJSON;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "status='" + status + '\'' +
                ", messege='" + messege + '\'' +
                ", data=" + data +
                '}';
    }
}
