package com.ex.lib.security;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author patrick
 */
public class Hex2String {


        private static final char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
                'F' };

        private static final String hexString = "0123456789ABCDEF";

        public static String toHexString(String filePath) throws FileNotFoundException, IOException {
            if (isEmptyStr(filePath)) {
                return "";
            }
            return toHexString(new File(filePath));
        }

        public static String toHexString(File file) throws FileNotFoundException, IOException {
            if (null == file || !file.exists() || file.isDirectory()) {
                return "";
            }
            return toHexString(new FileInputStream(file));
        }

        public static String toHexString(InputStream inputStream) throws IOException {
            byte[] bytes = inputStream2ByteArray(inputStream);
            return toHexString(bytes);
        }

        private static byte[] inputStream2ByteArray(InputStream in) throws IOException {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
            return out.toByteArray();
        }

        /**
         * Converts a byte array to hex string
         */
        private static String toHexString(byte[] bytes) {
            StringBuffer buf = new StringBuffer();

            int len = bytes.length;

            for (int i = 0; i < len; i++) {
                byte2hex(bytes[i], buf);
            }
            return buf.toString();
        }

        /**
         * Converts a byte to hex digit and writes to the supplied buffer
         */
        private static void byte2hex(byte b, StringBuffer buf) {
            int high = ((b & 0xf0) >> 4);
            int low = (b & 0x0f);
            buf.append(hexChars[high]);
            buf.append(hexChars[low]);
        }

        /**
         * Converts a hex string to byte array
         */
        private static byte[] convert2bytes(String stringOfHex) {
            if (isEmptyStr(stringOfHex)) {
                return null;
            }
            stringOfHex = stringOfHex.trim().toUpperCase();
            if (stringOfHex.length() % 2 == 1){
                return null;
            }
            int length = stringOfHex.length() / 2;
            byte[] bytes = new byte[length];
            char[] charArray = stringOfHex.toCharArray();
            for (int i = 0; i < length; i++) {
                int pos = i * 2;
                bytes[i] = (byte) (charToByte(charArray[pos]) << 4 | charToByte(charArray[pos + 1]));
            }
            return bytes;
        }

        /**
         * Converts a character to byte
         */
        private static byte charToByte(char c) {
            return (byte) hexString.indexOf(c);
        }

        /**
         * Converts a byte array to file
         */
        @SuppressWarnings("resource")
        public static void writeBytes2File(String filePath, byte[] bytes) throws IOException {
            File outputFile = new File(filePath);
            File parentFile = outputFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            ByteBuffer bb = ByteBuffer.wrap(bytes);

            FileChannel fc = new FileOutputStream(outputFile).getChannel();
            fc.write(bb);
            fc.close();
        }

        /**
         * Determine whether a string is empty
         */
        private static boolean isEmptyStr(String str) {
            return str == null || str.length() == 0;
        }

        public static void main(String[] args) throws FileNotFoundException, IOException {
            String filePath = "/Users/patrick/IdeaProjects/wiatec_common/src/main/java/com/wiatec/common/security/enc1.key";
            long start = System.currentTimeMillis();
            String hexStr = toHexString(filePath);
            System.out.println(hexStr);
            long end = System.currentTimeMillis();
            System.out.println(end - start);
        }

}
