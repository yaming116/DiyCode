package me.shenfan.retrodiycode;

/**
 * Created by Sun on 2016/7/19.
 */
public class DiyCodeConstants {
    public static final String BASE_URL = "http://www.diycode.cc";
    public static final String BASE_HTTPS_URL = "https://www.diycode.cc";
    public static final String BASE_API = "https://diycode.cc/api/v3";

    public static final String ACCESS_TOKEN_URL = "https://diycode.cc/oauth/token";
    public static final String AUTHORIZATION_URL = "http://diycode.cc/oauth/authorize";
    public static final String AUTHORIZATION_HTTPS_URL = "https://diycode.cc/oauth/authorize";


    private static String clientId = null;
    private static String secret = null;
    private static String callBackUrl = null;


    public static void init(String clientId, String secret, String callBackUrl){
        DiyCodeConstants.clientId = clientId;
        DiyCodeConstants.secret = secret;
        DiyCodeConstants.callBackUrl = callBackUrl;
    }

    public static String getAuthorizationUrl(){
        return AUTHORIZATION_HTTPS_URL + "?"
                + "response_type=code" + "&"
                + "client_id=" + clientId + "&"
                + "state=" + "E3ZYKC1T6H2yP4z" + "&"
                + "redirect_uri=" + callBackUrl;
    }

}
