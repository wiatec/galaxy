package com.ex.lib.security;

import com.ex.lib.Constant;
import com.ex.lib.common.TimeUtils;
import com.ex.lib.response.EnumResponseStatus;
import org.apache.commons.lang3.StringUtils;

/**
 * @author patrick
 */
public class AuthMaster {

    private static final String HEADER_PREFIX = "Bearer ";
    private static final String AES_KEY = "ry724s5ke9ji0ci123n94nn23";

    /**
     * 传入任意的字符串识别码，将识别码加时间戳进行aes加密和Base64编码为auth
     * @param identifier
     * @return
     */
    public static String encrypt(String identifier){
        String s = identifier + "," + TimeUtils.getUnixSeconds();
        String aes = AESUtils.encrypt(s, AES_KEY);
        String base64 = Base64Utils.encode(aes);
        return HEADER_PREFIX + base64;
    }

    /**
     * 解密auth，返回解密结果
     * 解密成功，结果对象的msg为加密时传入的识别码
     * 解密失败，结果对象的msg为加密失败提示
     * @param authorization
     * @return
     */
    public static AuthDecryptResult decrypt(String authorization){
        AuthDecryptResult authDecryptResult = new AuthDecryptResult();
        if(StringUtils.isEmpty(authorization)){
            authDecryptResult.setAccepted(false);
            authDecryptResult.setMsg(Constant.msg.ACCESS_TOKEN_NO_PROVIDE);
            return authDecryptResult;
        }
        if(!authorization.startsWith(HEADER_PREFIX)){
            authDecryptResult.setAccepted(false);
            authDecryptResult.setMsg(Constant.msg.ACCESS_TOKEN_ERROR);
            return authDecryptResult;
        }
        authorization = authorization.substring(HEADER_PREFIX.length());
        try {
            authorization = Base64Utils.decode(authorization);
            authorization = AESUtils.decrypt(authorization, AES_KEY);
        }catch (Exception e){
            authDecryptResult.setAccepted(false);
            authDecryptResult.setMsg(EnumResponseStatus.ERROR_FORBIDDEN.getMessage());
            return authDecryptResult;
        }
        String [] ss = authorization.split(",");
        if(ss.length != 2){
            authDecryptResult.setAccepted(false);
            authDecryptResult.setMsg(Constant.msg.ACCESS_TOKEN_ERROR);
            return authDecryptResult;
        }
        try {
            int t = Integer.parseInt(ss[1]);
            if(TimeUtils.getUnixSeconds() - t > 20){
                authDecryptResult.setAccepted(false);
                authDecryptResult.setMsg(Constant.msg.ACCESS_TOKEN_EXPIRES);
                return authDecryptResult;
            }
        }catch (Exception e){
            authDecryptResult.setAccepted(false);
            authDecryptResult.setMsg(Constant.msg.ACCESS_TOKEN_ERROR);
            return authDecryptResult;
        }
        authDecryptResult.setAccepted(true);
        authDecryptResult.setMsg(ss[0]);
        return authDecryptResult;
    }

    public static class AuthDecryptResult{

        private boolean accepted;
        private String msg;

        public boolean isAccepted() {
            return accepted;
        }

        public void setAccepted(boolean accepted) {
            this.accepted = accepted;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "AuthDecryptResult{" +
                    "accepted=" + accepted +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
