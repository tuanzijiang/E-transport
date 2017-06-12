package collector;

import android.util.Log;

import org.json.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public class CommonResponse {

    private String resCode = "";

    private String resMsg = "";

    private HashMap<String, String> propertyMap;

    private ArrayList<HashMap<String, String>> mapList;

    public CommonResponse(String responseString) {

        // 日志输出原始应答报文
        Log.d("Response",responseString);

        propertyMap = new HashMap<>();
        mapList = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(responseString);

            /* 说明：
                以下名称"resCode"、"resMsg"、"property"、"list"
                和请求体中提到的字段名称一样，都是和服务器程序开发者约定好的字段名字，在本文接下来的服务端代码会说到
             */
            resCode = root.getString("resCode");
            resMsg = root.optString("resMsg");

            JSONObject property = root.optJSONObject("property");
            if (property != null) {
                parseProperty(property, propertyMap);
            }

            JSONArray list = root.optJSONArray("list");
            if (list != null) {
                parseList(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void parseProperty(JSONObject property, HashMap<String, String> targetMap) {
        Iterator<?> it = property.keys();
        while (it.hasNext()) {
            String key = it.next().toString();
            Object value = property.opt(key);
            targetMap.put(key, value.toString());
        }
    }

    private void parseList(JSONArray list) {
        int i = 0;
        while (i < list.length()) {
            HashMap<String, String> map = new HashMap<>();
            try {
                parseProperty(list.getJSONObject(i++), map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mapList.add(map);
        }
    }

    public String getResCode() {
        return resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public HashMap<String, String> getPropertyMap() {
        return propertyMap;
    }

    public ArrayList<HashMap<String, String>> getDataList() {
        return mapList;
    }
}
