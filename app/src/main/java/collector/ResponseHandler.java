package collector;

/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public interface ResponseHandler {

    CommonResponse success(CommonResponse response);

    CommonResponse fail(String failCode, String failMsg);
}
