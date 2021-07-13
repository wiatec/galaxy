package com.ex.lib;

import com.ex.lib.common.FileMaster;
import com.ex.lib.common.TimeMaster;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author patrick
 */
public class Main {

    public static void main(String[] args){

        // Rsa
//        Rsa.Key key = Rsa.generateKey();
//        if(key != null) {
//            System.out.println(key.getPrivateKey());
//            System.out.println(key.getPublicKey());
//            String encryptResult = Rsa.encryptWithPrivate(key.getPrivateKey(), "12543r2134231423423423423ggfgfgdsgfdgfdsgdfg34");
//            System.out.println(encryptResult);
//            String decryptResult = Rsa.decryptWithPublic(key.getPublicKey(), encryptResult);
//            System.out.println(decryptResult);
//
//            String encryptResult1 = Rsa.encryptWithPublic(key.getPublicKey(), "12543r2134231423423423423ggfgfgdsgfdgfdsgdfg34");
//            System.out.println(encryptResult1);
//            String decryptResult1 = Rsa.decryptWithPrivate(key.getPrivateKey(), encryptResult1);
//            System.out.println(decryptResult1);
//
//            String sign = Rsa.signWithPrivateKey("12312312", key.getPrivateKey());
//            System.out.println(sign);
//            boolean verifyResult = Rsa.verifySignWithPublicKey("12312312", sign, key.getPublicKey());
//            System.out.println(verifyResult);
//
//            String pri = Rsa.getKeyStrFromPemFile("/Users/patrick/CodeLibraries/rsa/private_1024_pkcs8.pem");
//            System.out.println(pri);
//            String e1 = Rsa.encryptWithPrivate(pri, "123");
//            System.out.println(e1);
//
//            String pub = Rsa.getKeyStrFromPemFile("/Users/patrick/CodeLibraries/rsa/public_1024.pem");
//            System.out.println(pub);
//            String r1 = Rsa.decryptWithPublic(pub, e1);
//            System.out.println(r1);
//
//        }
//
//        //sha256
//        System.out.println(Sha.sha256("sdfsdsfdf2"));
//
//        //auth
//        String auth = Auth.encrypt("312312");
//        System.out.println(auth);
//        System.out.println(Auth.decrypt(auth));
//
//        //jwt
//        String t = Jwt.encrypt();
//        System.out.println(t);
//        System.out.println(Jwt.decrypt(t));
//
//        //regular
//        System.out.println(RegularMaster.matchMacAddress("5a:41:f8:00:21:a3"));
//
//        // unit format or convert
//        System.out.println(UnitMaster.formatStorage(1234324234));

        // time
//        System.out.println(TimeMaster.getString());
//        System.out.println(TimeMaster.getString(1572349281));
//        System.out.println(TimeMaster.getString(DateTimeFormatter.ofPattern(TimeMaster.PATTERN_DATE)));
//        System.out.println(TimeMaster.getString(LocalDateTime.now().plus(1, ChronoUnit.DAYS),
//                DateTimeFormatter.ofPattern(TimeMaster.PATTERN_DATE_TIME)));
//        System.out.println(TimeMaster.isBefore(1572349281L));
//        System.out.println(TimeMaster.isBefore(LocalDateTime.now().plus(1, ChronoUnit.DAYS)));
//        System.out.println(TimeMaster.getUnixSeconds());
//        System.out.println(TimeMaster.getUnixSeconds("2020-05-06 01:03:45"));
//        System.out.println(TimeMaster.getUnixSeconds(LocalDateTime.now()));
//        System.out.println(TimeMaster.getUnixMilliSeconds("2020-05-09 01:03:45"));
//        System.out.println(TimeMaster.getUnixMilliSeconds(LocalDateTime.now()));
//        System.out.println(TimeMaster.getUnixSecondsStartOfDay());
//        System.out.println(TimeMaster.getUnixSecondsEndOfDay());
//        System.out.println(TimeMaster.getDateTime("2020-05-09 01:03:45"));
//        System.out.println(TimeMaster.getDateAfterSomeYears(3));
//        System.out.println(TimeMaster.getDifferentDays(LocalDateTime.now(), LocalDateTime.now()
//                .plus(1, ChronoUnit.YEARS)));
//        System.out.println(TimeMaster.isSameDay(LocalDateTime.now(), LocalDateTime.now()
//                .plus(1, ChronoUnit.HOURS)));

        //file
        System.out.println(FileMaster.getFileName("/Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java"));
        System.out.println(FileMaster.getNoSuffixFileName("/Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java"));
        System.out.println(FileMaster.getSuffixName("/Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java"));
        System.out.println(FileMaster.convertFileSuffix("/Users/patrick/IdeaProjects/galaxy/src/main/java/com/ex/lib/common/FileMaster.java", "png"));
        System.out.println(FileMaster.cleanDirectory("/Users/patrick/Downloads/upload"));
    }
}
