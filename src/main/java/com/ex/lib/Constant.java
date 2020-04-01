package com.ex.lib;

/**
 * @author patrick
 */
public final class Constant {


    public static final class path{
        public static final String FTP_IMAGES = "images/";
        public static final String FTP_VIDEOS = "videos/";
        public static final String FTP_ICON = "icon/";
        public static final String RESOURCE_EXPORT = "/home/resource/export/";
    }

    public static final class url {
        private static final String URL_RESOURCE = "http://resource.vipnow.work:8033/";
        public static final String IMAGE_PREFIX = URL_RESOURCE + path.FTP_IMAGES;
        public static final String VIDEO_PREFIX = URL_RESOURCE + path.FTP_VIDEOS;
        public static final String ICON_PREFIX = URL_RESOURCE + path.FTP_ICON;
    }

    public static final class count{
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int TWO = 2;
        public static final int THREE = 3;
        public static final int FOUR = 4;
        public static final int FIVE = 5;
        public static final int SIX = 6;
        public static final int SEVEN = 7;
        public static final int EIGHT = 8;
        public static final int NINE = 9;
        public static final int TEN = 10;
    }

    public static final class session_key{
        public static final String ADMIN_USER_INFO = "adminUserInfo";
        public static final String USER_INFO = "userInfo";

    }

    public static final class cache_key{
        public static final String DEVICES = "devices";
        public static final String DEVICE_AGENTS = "device_agents";
        public static final String DEVICE = "device";
    }

    public static final class page{
        public static final String DEFAULT_NUM = "1";
        public static final String DEFAULT_SIZE = "200";
    }

    public static final class rabbit{
        public static final String EXCHANGE_EMAIL_VIPNOW = "mail.vipnow";
        public static final String EXCHANGE_THUNDER = "thunder";

        public static final String QUEUE_EMAIL_DEFAULT = "mail.default";
        public static final String QUEUE_EMAIL_REGISTER = "mail.register";
        public static final String QUEUE_EMAIL_RESET_PWD = "mail.reset.pwd";
        public static final String QUEUE_EMAIL_VOUCHER = "mail.voucher";
        public static final String QUEUE_EMAIL_VOUCHER_REPORT = "mail.voucher.report";
        public static final String QUEUE_EMAIL_ERROR_REPORT = "mail.error.report";
        public static final String QUEUE_EMAIL_NEW_ORDER = "mail.order.new";
        public static final String QUEUE_EMAIL_CHANNEL_ERROR_REPORT = "mail.channel.error.report";
        public static final String QUEUE_THUNDER_NEW_CHANNEL = "thunder.channel.create";
        public static final String QUEUE_THUNDER_MEDIA_CONVERT = "thunder.media.convert";

        public static final String ROUTING_EMAIL_DEFAULT = "mail.default";
        public static final String ROUTING_EMAIL_REGISTER = "mail.register";
        public static final String ROUTING_EMAIL_RESET_PWD = "mail.reset.pwd";
        public static final String ROUTING_EMAIL_VOUCHER = "mail.voucher";
        public static final String ROUTING_EMAIL_VOUCHER_REPORT = "mail.voucher.report";
        public static final String ROUTING_EMAIL_ERROR_REPORT = "mail.error.report";
        public static final String ROUTING_EMAIL_NEW_ORDER = "mail.order.new";
        public static final String ROUTING_EMAIL_CHANNEL_ERROR_REPORT = "mail.channel.error.report";
        public static final String ROUTING_THUNDER_NEW_CHANNEL = "thunder.channel.create";
        public static final String ROUTING_THUNDER_MEDIA_CONVERT = "thunder.media.convert";

    }

    public static final class msg{
        public static final String SUCCESSFULLY = "Successfully";
        public static final String BAD_REQUEST = "Bad request";
        public static final String UNAUTHORIZED = "Unauthorized";
        public static final String LOGIN_EXPIRES = "Login time session expired, please login to your account again.";
        public static final String FORBIDDEN = "Forbidden, authority denied";
        public static final String NO_FOUND = "Resource not exists";
        public static final String METHOD_NOT_ALLOWED = "Request method not allowed";
        public static final String REQUEST_TIMEOUT = "Request Timeout";
        public static final String REQUEST_PARAMS_INCORRECT = "Request params format incorrect";
        public static final String INTERNAL_SERVER_ERROR = "Internal server error";
        public static final String INTERNAL_SERVER_ERROR_SQL = "Internal server error #1";
        public static final String INTERNAL_SERVER_ERROR_SQL_I = "Internal server error #I";
        public static final String INTERNAL_SERVER_ERROR_SQL_D = "Internal server error #D";
        public static final String INTERNAL_SERVER_ERROR_SQL_Q = "Internal server error #Q";
        public static final String INTERNAL_SERVER_ERROR_SQL_U = "Internal server error #U";
        public static final String INTERNAL_SERVER_ERROR_MS = "Internal server error #ms";


        public static final String ACCESS_TOKEN_ERROR = "access token error";
        public static final String ACCESS_TOKEN_NO_PROVIDE = "auth token no provide";
        public static final String ACCESS_TOKEN_EXPIRES = "access token expires";

        public static final String USERNAME_DUPLICATE = "username duplicate";
        public static final String USERNAME_EXISTS = "username exists";
        public static final String USERNAME_NOT_EXISTS = "username not exists";
        public static final String PASSWORD_NOT_MATCH = "username and password not match";
        public static final String EMAIL_EXISTS = "email exists";
        public static final String EMAIL_NOT_EXISTS = "email not exists";
        public static final String EMAIL_FORMAT_ERROR = "email format error";
        public static final String SSN_EXISTS = "SSN exists";

        public static final String DEVICE_SERVER_ERROR = "device server no response, please try again later";
        public static final String DEVICE_IN_USING = "device in using";
        public static final String DEVICE_TYPE_ERROR = "device type choose error";
        public static final String DEVICE_IDENTIFIER_ERROR = "device identifier error";

        public static final String PAY_SERVER_ERROR = "pay server no response, please try again later";

        public static final String USER_SERVER_ERROR = "user server no response, please try again later";
        public static final String VOUCHER_SERVER_ERROR = "voucher server no response, please try again later";

    }

    public static final class time{
        public static final long UNIX_2001 = 978278400000L;
    }

    public static final class env{
        public static final String DEV = "dev";
        public static final String PRO = "pro";
    }

    public static final class pcp{
        public static final String DEPOSIT = "100.00";
        public static final String ACTIVATE = "35.00";
        public static final String MONTHLY = "29.99";
        public static final String MONTHLY_OLD = "19.99";
        public static final String YEARLY_OLD = "239.88";
    }

    public static final class platform {
        public static final String infotainment_all = "0";
        public static final String infotainment_ele = "1";
        public static final String infotainment_wt = "2";
        public static final String infotainment_wp = "3";
        public static final String infotainment_wc = "4";
        public static final String infotainment_wb = "5";
        public static final String infotainment_wv = "6";
        public static final String infotainment_wm = "7";
    }

    public static final class mac {
        public static final String prefix = "5C:41";
    }


}
