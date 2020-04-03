package com.ex.lib.security;

import com.ex.lib.Constant;
import com.ex.lib.common.TimeMaster;
import com.ex.lib.response.EnumResponseStatus;
import com.ex.lib.response.Response;
import org.apache.commons.lang3.StringUtils;

/**
 * @author patrick
 */
public class Auth {

    private static final String HEADER_PREFIX = "Bearer ";
    private static final String AES_KEY = "ry724s5ke9ji0ci123n94nn23";

    /**
     * 传入任意的字符串识别码，将识别码加时间戳进行aes加密和Base64编码为auth
     */
    public static String encrypt(String identifier) {
        String s = identifier + "," + TimeMaster.getUnixSeconds();
        String aes = Aes.encrypt(s, AES_KEY);
        String base64 = B64.encode(aes);
        return HEADER_PREFIX + base64;
    }

    /**
     * 解密auth，解密结果返回response对象
     * 解密成功，结果对象的msg为加密时传入的识别码
     * 解密失败，结果对象的msg为加密失败提示
     */
    public static Response decrypt(String authorization) {
        if (StringUtils.isEmpty(authorization)) {
            return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN_EMPTY);
        }
        if (!authorization.startsWith(HEADER_PREFIX)) {
            return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN);
        }
        authorization = authorization.substring(HEADER_PREFIX.length());
        try {
            authorization = B64.decode(authorization);
            authorization = Aes.decrypt(authorization, AES_KEY);
        } catch (Exception e) {
            return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN);
        }
        String[] ss = authorization.split(",");
        if (ss.length != Constant.Num.TWO) {
            return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN);
        }
        try {
            int t = Integer.parseInt(ss[1]);
            if (TimeMaster.getUnixSeconds() - t > Constant.Num.TWENTY) {
                return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN_EXPIRES);
            }
        } catch (Exception e) {
            return Response.error(EnumResponseStatus.ERROR_ACCESS_TOKEN);
        }
        return Response.success(ss[0]);
    }

}