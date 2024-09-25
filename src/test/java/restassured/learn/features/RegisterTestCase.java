package restassured.learn.features;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.dataValidation;
import org.example.endPoint;
import org.json.JSONObject;
import restassured.learn.api.RequestCommons;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegisterTestCase {
    private final RequestCommons requestCommons = new RequestCommons();
    private final dataValidation dataValidation = new dataValidation();

    public void registerSuccess() {
        RequestSpecification httpReq = given().log().all()
                .headers(requestCommons.setHeader())
                .body(requestCommons.jsonRequestBody().toString());

        Response response = httpReq.when().post(endPoint.EP_REGISTER);
        response.prettyPrint();
    }

    public void register_WithMissingHeader() throws JsonProcessingException {
        requestCommons.missFieldRequestHeaders();
        for (String missingField : requestCommons.getKeyRequestHeader()) {
            ObjectMapper objectMapper = new ObjectMapper(); //Khởi tạo ObjectMapper để xử ly JSON
            JSONObject jsonHeaders = new JSONObject(requestCommons.setHeader()); //Tạo đối tượng JSONObject
            // Chuyển đổi JSONObject thành ObjectNode, cho phép thao tác dễ dàng hơn với các thuộc tính của JSON.
            ObjectNode objectNode = objectMapper.readValue(jsonHeaders.toString(), ObjectNode.class);
            objectNode.remove(missingField); // Xóa trường thiếu
            // Chuyển đổi ObjectNote thành Map
            Map<String, Object> headersMap = objectMapper.convertValue(objectNode, Map.class);

            RequestSpecification httpReq = given().log().all()
                    .headers(headersMap)
                    .body(requestCommons.jsonRequestBody().toString());

            // Thực thi hamf
            Response response = httpReq.when().post(endPoint.EP_REGISTER);
            response.prettyPrint();
            response.then().assertThat().contentType("application/json");

        }
    }

    public void register_WithMissingFieldBody() throws JsonProcessingException {
        requestCommons.missFieldRequestBody();
        for (String missingField : requestCommons.getKeyRequestBody()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String strs = String.valueOf(requestCommons.jsonRequestBody());
            ObjectNode objectNode = objectMapper.readValue(strs, ObjectNode.class);
            objectNode.remove(missingField);
            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(objectNode));
            RequestSpecification httpReq = given().log().all()
                    .headers(requestCommons.setHeader())
                    .body(jsonObject.toString());
            Response response = httpReq.when().post(endPoint.EP_REGISTER);
            response.prettyPrint();
        }
    }

    public void register_ValidationFieldUserName() throws JsonProcessingException {
        dataValidation.dataValidationUsername();
        for (Object missingField: dataValidation.getValUsername()) {
            ObjectMapper objectMapper = new ObjectMapper();
            String strs = String.valueOf(requestCommons.jsonRequestBody());
            ObjectNode objectNode = objectMapper.readValue(strs, ObjectNode.class);
            if(missingField instanceof Integer) {
                objectNode.put("username", (Integer) missingField);
            } else if(missingField instanceof Boolean) {
                objectNode.put("username", (Boolean) missingField);
            } else {
                objectNode.put("username", (String) missingField);
            }
            JSONObject jsonObject = new JSONObject(objectMapper.writeValueAsString(objectNode));
            RequestSpecification httpReq = given().log().all()
                    .headers(requestCommons.setHeader())
                    .body(jsonObject.toString());
            Response response = httpReq.when().post(endPoint.EP_REGISTER);
            response.prettyPrint();
        }
    }
}
