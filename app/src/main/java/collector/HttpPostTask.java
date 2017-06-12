package collector;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public class HttpPostTask extends AsyncTask<String,String,String> {
    /** BaseActivity 中基础问题的处理 handler */
    private Handler mHandler;

    private CommonResponse finalresponse;
    /** 返回信息处理回调接口 */
    private ResponseHandler rHandler;

    /** 请求类对象 */
    private CommonRequest request;

    public HttpPostTask(CommonRequest request,
                        Handler mHandler,
                        ResponseHandler rHandler) {
        this.request = request;
        this.mHandler = mHandler;
        this.rHandler = rHandler;
    }

    @Override
    protected String doInBackground(String... params) {
        StringBuilder resultBuf = new StringBuilder();
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(50000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(request.getJsonStr());
            out.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 通过连接的输入流获取下发报文，然后就是Java的流处理
                InputStream in = connection.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                String line;
                while((line = read.readLine()) != null) {
                    resultBuf.append(line);
                }
                return resultBuf.toString();
            } else {
                // 异常情况，如404/500...
                mHandler.obtainMessage(Constant.HANDLER_HTTP_RECEIVE_FAIL,
                        "[" + responseCode + "]" + connection.getResponseMessage()).sendToTarget();
            }
        } catch (IOException e) {
            // 网络请求过程中发生IO异常
            Log.d("IO",e.toString());
            mHandler.obtainMessage(Constant.HANDLER_HTTP_SEND_FAIL,
                    e.getClass().getName() + " : " + e.getMessage()).sendToTarget();
        }
        return resultBuf.toString();
    }

    @Override
    protected void onPostExecute(String result) {
        if (rHandler != null) {
            if (!"".equals(result)) {
                CommonResponse response = new CommonResponse(result);
                if ("0".equals(response.getResCode())) { // 正确
                    finalresponse = rHandler.success(response);
                } else {
                    finalresponse = rHandler.fail(response.getResCode(), response.getResMsg());
                }
            }
        }
    }

    public CommonResponse getFinalresponse(){
        return finalresponse;
    }
}
