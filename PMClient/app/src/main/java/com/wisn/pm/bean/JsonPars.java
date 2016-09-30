package com.wisn.pm.bean;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonPars {
    /**
     * @param <T>
     * @param message {"code":    ,"content":{},"reason":{} }
     * @return
     */
    public static <T> T fromJson(String message, Class<T> clazz) {
        JSONObject fromObject = null;
        try {
            fromObject = new JSONObject(message);
            Gson gson = new Gson();
            T bean = (T) gson.fromJson(fromObject.getString(Type.content), clazz);
            return bean;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * client  receive  str
     *
     * @param operation
     * @param code
     * @return
     */
    public static String toJson(Object operation, Object reason, int code) {
        try {
            JSONObject JsonObject = new JSONObject();
            JsonObject.put(Type.code, code);
            if (reason != null) {
                JsonObject.put(Type.reason, reason);
            } else {
                JsonObject.put(Type.reason, "{}");
            }
            if (operation == null) {
                JsonObject.put(Type.content, "{}");
            } else {
                JsonObject.put(Type.content, operation);
            }
            return JsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{" + Type.code + ":0," + Type.content + ":{}, " + Type.reason + ":{}}";
        }
    }

    //test
    public static void main(String[] args) {
        String json = toJson(new OperationMessage(22, 55, System.currentTimeMillis(), System.currentTimeMillis(), "ahhah"), null, 3);
        System.out.println(json);
        System.out.println(fromJson(json, OperationMessage.class).toString());
        String json1 = toJson(new DeviceInformation("ererwq", "ererwq", "ererwq", "ererwq", "ererwq", "ererwq"
                , "ererwq", "ererwq", "ererwq", "ererwq", "ererwq", 77
                , "ererwq", "ererwq"), null, 3);
        System.out.println(json1);
        System.out.println(fromJson(json1, DeviceInformation.class).toString());
    }
}
