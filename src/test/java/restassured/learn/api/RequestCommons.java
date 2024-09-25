package restassured.learn.api;

import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class RequestCommons {
    private ArrayList<String> keyRequestHeader, keyRequestBody;

    public Map<String, Object> setHeader() {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        headerMap.put("accept", "application/json");
        return headerMap;
    }

    public void missFieldRequestHeaders() {
        keyRequestHeader = new ArrayList<>();
        keyRequestHeader.add("Content-Type");
        keyRequestHeader.add("accept");

    }

    public JSONObject jsonRequestBody() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "test");
        jsonObject.put("firstName", "John");
        jsonObject.put("lastName", "James");
        jsonObject.put("email", "test@email.com");
        jsonObject.put("password", "Demo@123");
        jsonObject.put("phone", "0912345678");
        jsonObject.put("userStatus", 1);
        return jsonObject;
    }

    public void missFieldRequestBody() {
        keyRequestBody = new ArrayList<>();
        keyRequestBody.add("username");
        keyRequestBody.add("firstName");
        keyRequestBody.add("lastName");
        keyRequestBody.add("email");
        keyRequestBody.add("password");
        keyRequestBody.add("phone");
        keyRequestBody.add("userStatus");
    }
}
