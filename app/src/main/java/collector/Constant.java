package collector;

/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public class Constant {

    public static final int HANDLER_HTTP_SEND_FAIL = 1001;
    public static final int HANDLER_HTTP_RECEIVE_FAIL = 1002;
    public static final String SETTING_URL = "http://118.89.191.184:8080/ETServer/ETHome/SettingServlet";
    public static final String LOGIN_URL = "http://118.89.191.184:8080/ETServer/ETHome/LoginServlet";

    public static String avatarPath = null;

    /*
     * Activity跳转用RequestCode
     */
    public static int REQUEST_CODE_ = 0;

    /*
     * Activity跳转用ResponseCode
     */
    public static int RESPONSE_CODE_SUCCESS = 100;
    public static int RESPONSE_CODE_FAIL = 101;
    public static int RESPONSE_CODE_NO_RESULT = 102;
}