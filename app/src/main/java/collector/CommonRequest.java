package collector;

import android.util.Log;

import org.json.*;
import java.util.HashMap;


/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public class CommonRequest {

    private String requestCode;

    private HashMap<String, String> requestParam;

    public CommonRequest() {
        requestCode = "";
        requestParam = new HashMap<>();
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }

    public void addRequestParam(String paramKey, String paramValue) {
        requestParam.put(paramKey, paramValue);
    }

    public String getJsonStr() {
        JSONObject object = new JSONObject();
        JSONObject param = new JSONObject(requestParam);
        try {
            object.put("requestCode", requestCode);
            object.put("requestParam", param);
        } catch (JSONException e) {
            Log.d(e.toString(),"请求报文组装异常：" + e.getMessage());
        }
        // 打印原始请求报文
        Log.d("Request",object.toString());
        return object.toString();
    }
}
