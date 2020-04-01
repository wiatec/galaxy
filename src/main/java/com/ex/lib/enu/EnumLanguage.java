package com.ex.lib.enu;

/**
 * @author patrick
 */
public enum EnumLanguage {

    /**
     * 语言、国家枚举
     */
    zh_CN("zh", "CN"),
    zh_TW("zh", "TW"),
    en_US("en", "US"),
    mx_MX("mx", "MX"),
    sk_SK("sk", "SK"),
    cz_CS("cz", "CS"),
    ;

    private String language;
    private String country;

    EnumLanguage(String language, String country) {
        this.language = language;
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
