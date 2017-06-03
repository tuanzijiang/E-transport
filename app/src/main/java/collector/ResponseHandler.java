package collector;

/**
 * Created by fanmiaomiao on 2017/5/31.
 */

public interface ResponseHandler {

    /**
     * 交易成功的处理
     * @param response 格式化报文
     */
    CommonResponse success(CommonResponse response);

    /**
     * 报文通信正常，但交易内容失败的处理
     * @param failCode 返回的交易状态码
     * @param failMsg 返回的交易失败说明
     */
    CommonResponse fail(String failCode, String failMsg);
}
