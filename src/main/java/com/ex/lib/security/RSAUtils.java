package com.ex.lib.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author patrick
 */
public class RSAUtils {


    private static KeyPair generateKeyPair() {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair;
    }

    private static Map<String, RSAKey> generateKeyMap() {
        KeyPair keyPair = generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKey = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
        System.out.println(publicKey);
        System.out.println(privateKey);
        Map<String, RSAKey> keyMap = new HashMap<>(2);
        keyMap.put("private", rsaPrivateKey);
        keyMap.put("public", rsaPublicKey);
        return keyMap;
    }

    /**
     * 读取pem文件中key字符串
     * @param pemPath
     * @return
     * @throws Exception
     */
    public static String loadKeyFromPem(String pemPath) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(pemPath));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                }
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            throw new Exception("public pem read error");
        }
    }

    /**
     * 将PKCS8格式的rsa private key字符串转换成 {@link RSAPrivateKey}
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKeyFromPKCS8Str(String privateKeyStr)
            throws Exception {
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
     * 将PKCS8格式的rsa private key字符串转换成 {@link RSAPrivateKey}
     * @param privatePKCS8KeyPath
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey loadPrivateKeyFromPKCS8Path(String privatePKCS8KeyPath)
            throws Exception {
        try {
            return loadPrivateKeyFromPKCS8Str(loadKeyFromPem(privatePKCS8KeyPath));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException e) {
            throw new Exception("InvalidKeySpecException");
        } catch (NullPointerException e) {
            throw new Exception("NullPointerException");
        }
    }

    /**
     * 将rsa public key字符串转换成 {@link RSAPublicKey}
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static RSAPublicKey loadPublicKeyFromStr(String publicKeyStr)
            throws Exception {
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

    //私钥加密
    public static String privateEncrypt(RSAPrivateKey rsaPrivateKey, String value) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey1);
            byte[] result = cipher.doFinal(value.getBytes(Charset.forName("utf-8")));
            return Base64.getEncoder().encodeToString(result);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    //公钥解密
    public static String publicDecrypt(RSAPublicKey rsaPublicKey, String encodeString) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(encodeString));
            return new String(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    //公钥加密
    public static String publicEncrypt(RSAPublicKey rsaPublicKey, String value) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    //私钥解密
    public static String privateDecrypt(RSAPrivateKey rsaPrivateKey, String encodeString) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encodeString));
            return new String(result);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            // 取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            // 取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * 用私钥给内容签名
     * @param content
     * @param privateKey
     * @return
     */
    public static String signWithPrivateKey(String content, RSAPrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey);
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
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    public static boolean checkSignByPublicKey(String content, String sign, RSAPublicKey publicKey) {
         try {
             BASE64Decoder base64Decoder = new BASE64Decoder();
             Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
             signature.initVerify(publicKey);
             signature.update(content.getBytes(StandardCharsets.UTF_8));
             return signature.verify(base64Decoder.decodeBuffer(sign));
         } catch (Exception e) {
             e.printStackTrace();
         }
        return false;
    }



    public static void main(String[] args) throws Exception {
        Map<String, RSAKey> stringRSAKeyMap = generateKeyMap();
        RSAPrivateKey privateKey = (RSAPrivateKey) stringRSAKeyMap.get("private");
        RSAPublicKey publicKey = (RSAPublicKey) stringRSAKeyMap.get("public");


        String encrypt = privateEncrypt( privateKey, "sdfsfsffsd");
        System.out.println(encrypt);
        String decrypt = publicDecrypt(publicKey, encrypt);
        System.out.println(decrypt);

        String encrypt1 = publicEncrypt(publicKey, "sdfsfsffsd");
        System.out.println(encrypt1);
        String decrypt1 = privateDecrypt(privateKey, encrypt1);
        System.out.println(decrypt1);

        String sign = signWithPrivateKey("21312312313", privateKey);
        System.out.println(sign);
        boolean result = checkSignByPublicKey("21312312313", sign, publicKey);
        System.out.println(result);



        String pri = loadKeyFromPem("/Users/patrick/CodeLibraries/rsa/private_1024_pkcs8.pem");
        String pub = loadKeyFromPem("/Users/patrick/CodeLibraries/rsa/public_1024.pem");
        System.out.println(pri);
        System.out.println(pub);
        RSAPrivateKey rsaPrivateKey = loadPrivateKeyFromPKCS8Str(pri);
        RSAPublicKey rsaPublicKey = loadPublicKeyFromStr(pub);

        String encrypt11 = privateEncrypt(rsaPrivateKey, "sdfsfsffsd");
        System.out.println(encrypt11);
        String decrypt11 = publicDecrypt(rsaPublicKey, encrypt11);
        System.out.println(decrypt11);

        String encrypt12 = publicEncrypt(rsaPublicKey, "411111111111111111");
        System.out.println(encrypt12);
        String decrypt12 = privateDecrypt(rsaPrivateKey, encrypt12);
        System.out.println(decrypt12);
    }


}
