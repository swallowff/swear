package cn.swallowff.common.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;

public class GsonHelper {
    private static GsonBuilder gsonBuilder = new GsonBuilder();

    public static String toJson(Object object){
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }

    public static <T> T parseJson(String jsonStr,Class<T> type){
        Gson gson = gsonBuilder.create();
        return gson.fromJson(jsonStr,type);
    }

    public static <T> List<T> parseNoHeaderArray(String jsonStr,Class<T> type){
        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(jsonStr).getAsJsonArray();
        return jsonArrayToList(jsonArray,type);
    }

    public static <T> List<T> parseHeaderArray(String jsonStr,String headerName,Class<T> type) {
        //先转JsonObject
        JsonObject jsonObject = new JsonParser().parse(jsonStr).getAsJsonObject();
        //再转JsonArray 加上数据头
        JsonArray jsonArray = jsonObject.getAsJsonArray(headerName);
        return jsonArrayToList(jsonArray,type);
    }

    private static <T> List<T> jsonArrayToList(JsonArray jsonArray,Class<T> type){
        ArrayList<T> list = new ArrayList<>();
        Gson gson = gsonBuilder.create();
        //循环遍历
        for (JsonElement user : jsonArray) {
            //通过反射 得到UserBean.class
            T t = gson.fromJson(user, TypeToken.of(type).getType());
            list.add(t);
        }
        return list;
    }

//    public static void main(String[] args){
//        String jsonStr = "[\n" +
//                "    {\n" +
//                "        \"name\": \"name1\",\n" +
//                "        \"id\": 1000,\n" +
//                "        \"sex\": 1\n" +
//                "    },\n" +
//                "    {\n" +
//                "        \"name\": \"name2\",\n" +
//                "        \"id\": 1001,\n" +
//                "        \"sex\": 1\n" +
//                "    }\n" +
//                "]";
//        List<User> list = parseNoHeaderArray(jsonStr,User.class);
//        System.out.println(toJson(list));
//
//        String jsonStr2 = "{\n" +
//                "    \"name\": [\n" +
//                "        {\n" +
//                "            \"name\": \"name1\",\n" +
//                "            \"id\": 123,\n" +
//                "            \"sex\": 1\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"name\": \"name2\",\n" +
//                "            \"id\": 123,\n" +
//                "            \"sex\": 1\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        List<User> list2 = parseHeaderArray(jsonStr2,"name",User.class);
//        System.out.println(toJson(list2));
//
//    }
}
