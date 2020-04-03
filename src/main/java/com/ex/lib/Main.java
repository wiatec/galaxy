package com.ex.lib;

import com.ex.lib.common.RegularMaster;
import com.ex.lib.security.*;

/**
 * @author patrick
 */
public class Main {

    public static void main(String[] args){

        // Rsa
        Rsa.Key key = Rsa.generateKey();
        if(key != null) {
            System.out.println(key.getPrivateKey());
            System.out.println(key.getPublicKey());
            String encryptResult = Rsa.encryptWithPrivate(key.getPrivateKey(), "12543r2134231423423423423ggfgfgdsgfdgfdsgdfg34");
            System.out.println(encryptResult);
            String decryptResult = Rsa.decryptWithPublic(key.getPublicKey(), encryptResult);
            System.out.println(decryptResult);

            String encryptResult1 = Rsa.encryptWithPublic(key.getPublicKey(), "12543r2134231423423423423ggfgfgdsgfdgfdsgdfg34");
            System.out.println(encryptResult1);
            String decryptResult1 = Rsa.decryptWithPrivate(key.getPrivateKey(), encryptResult1);
            System.out.println(decryptResult1);

            String sign = Rsa.signWithPrivateKey("12312312", key.getPrivateKey());
            System.out.println(sign);
            boolean verifyResult = Rsa.verifySignWithPublicKey("12312312", sign, key.getPublicKey());
            System.out.println(verifyResult);

            String pri = Rsa.getKeyStrFromPemFile("/Users/patrick/CodeLibraries/rsa/private_1024_pkcs8.pem");
            System.out.println(pri);
            String e1 = Rsa.encryptWithPrivate(pri, "123");
            System.out.println(e1);

            String pub = Rsa.getKeyStrFromPemFile("/Users/patrick/CodeLibraries/rsa/public_1024.pem");
            System.out.println(pub);
            String r1 = Rsa.decryptWithPublic(pub, e1);
            System.out.println(r1);

        }

        //sha256
        System.out.println(Sha.sha256("sdfsdsfdf2"));

        //auth
        String auth = Auth.encrypt("312312");
        System.out.println(auth);
        System.out.println(Auth.decrypt(auth));

        //jwt
        String token = Jwt.encrypt();
        System.out.println(token);
        Jwt.Data data = Jwt.decrypt(token);
        System.out.println(data);

        //regular
        System.out.println(RegularMaster.matchMacAddress("5a:41:f8:00:21:a3"));


    }
}
