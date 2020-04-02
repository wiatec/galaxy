package com.ex.lib.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author patrick
 */
public class RSA {

    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 生成String格式密钥对， 结果以内部类Key对象形式返回
     */
    public static Key generateKey() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            if(keyPair == null){
                return null;
            }
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
            String publicKey = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
            String privateKey = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
            Key key = new Key();
            key.setPrivateKey(privateKey);
            key.setPublicKey(publicKey);
            return key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 私钥加密
     */
    public static String encryptWithPrivate(String privateKey, String content) {
        try {
            RSAPrivateKey rsaPrivateKey = convertPrivateKey(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory;
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey1);
            byte[] result = cipher.doFinal(content.getBytes(Charset.forName("utf-8")));
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     */
    public static String decryptWithPrivate(String privateKey, String content) {
        try {
            RSAPrivateKey rsaPrivateKey = convertPrivateKey(privateKey);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory;
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey key = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密
     */
    public static String encryptWithPublic(String publicKey, String content) {
        try {
            RSAPublicKey rsaPublicKey = convertPublicKey(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(content.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥解密
     */
    public static String decryptWithPublic(String publicKey, String encodeString) {
        try {
            RSAPublicKey rsaPublicKey = convertPublicKey(publicKey);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey key = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encodeString));
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 用私钥给内容签名, 返回签名字符串
     */
    public static String signWithPrivateKey(String content, String privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            RSAPrivateKey rsaPrivateKey = convertPrivateKey(privateKey);
            signature.initSign(rsaPrivateKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            byte[] signed = signature.sign();
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encodeBuffer(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用公钥验证签名
     */
    public static boolean verifySignWithPublicKey(String content, String sign, String publicKey) {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            RSAPublicKey rsaPublicKey = convertPublicKey(publicKey);
            signature.initVerify(rsaPublicKey);
            signature.update(content.getBytes(StandardCharsets.UTF_8));
            return signature.verify(base64Decoder.decodeBuffer(sign));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 将私钥字符串转换成 {@link RSAPrivateKey}
     */
    private static RSAPrivateKey convertPrivateKey(String privateKeyStr) throws Exception {
        try {
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] buffer = base64Decoder.decodeBuffer(privateKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException e) {
            throw new Exception("InvalidKeySpecException");
        } catch (NullPointerException e) {
            throw new Exception("NullPointerException");
        }
    }

    /**
     * 将公钥字符串转换成  {@link RSAPublicKey}
     */
    private static RSAPublicKey convertPublicKey(String publicKeyStr) throws Exception {
        try {
            BASE64Decoder base64 = new BASE64Decoder();
            byte[] buffer = base64.decodeBuffer(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException e) {
            throw new Exception("InvalidKeySpecException");
        } catch (NullPointerException e) {
            throw new Exception("NullPointerException");
        }
    }

    /**
     * 读取pem文件中key字符串
     */
    public static String getKeyStrFromPemFile(String pemFilePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pemFilePath));
            String readLine;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) != '-') {
                    sb.append(readLine);
                }
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 生成的密钥对象，以字符串形式保存密钥
     */
    public static class Key{
        private String privateKey;
        private String publicKey;

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        @Override
        public String toString() {
            return "Key{" +
                    "privateKey='" + privateKey + '\'' +
                    ", publicKey='" + publicKey + '\'' +
                    '}';
        }
    }

}
