package com.chainhaus.mtg.util;

import java.io.File;

/**
 * Created by Asad Sarwar on 20/06/2020.
 */
public interface Constants {
    String FLIP = "flip";
    int CUSTOM_UN_AUTHENTICATED_CODE = 409;
    String CUSTOM_UN_AUTHENTICATED_MESSAGE = "Unauthenticated";
    String DEFAULT_FORMAT = "jfif";

    String PROPERTY_EMAIL_USER_NAME = "com.chainhaus.reilize.email.username";
    String PROPERTY_EMAIL_PASSWORD = "com.chainhaus.reilize.email.password";
    String PROPERTY_EMAIL_ENABLED = "com.chainhaus.reilize.email.enabled";

    String WEB_BASE_PATH = "com.chainhaus.reilize.basepath";

    String MAIL_SMTP_AUTH= "mail.smtp.auth";
    String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    String MAIL_SMTP_HOST = "mail.smtp.host";
    String MAIL_SMTP_PORT = "mail.smtp.port";

    Long ONE_BILLION = new Long("1000000000");
    Long TEN_BILLION = new Long("10000000001");

    String INSTA_APP_ID = "insta.app.id";
    String INSTA_APP_SECRET = "insta.app.secret";
    String IPFS_API_URL = "ipfs.api.url";
    String MTG_FILE_DIR_PATH = "insta.file.directory.path";
    String MTG_INSTA_SUCCESS_REDIRECT_URL = "insta.success.redirect.url";

    public static final class FILE_PATH
    {
//        public static final String APP_FOLDER_NAME = "MTG_FILES";

//        static {
//            if(System.getenv("CATALINA_HOME") != null){
//                MTG_FILES = System.getenv("CATALINA_HOME") + File.separator + APP_FOLDER_NAME + File.separator;
//            }else{
//                MTG_FILES = System.getProperty("catalina.home") + File.separator + APP_FOLDER_NAME + File.separator;
//            }
//        }

        public static final String MTG_FILES = System.getProperty(MTG_FILE_DIR_PATH);// = "C:/apache-tomcat-8.5.51/webapps/textile-app/assets/reports/pdf" + File.separator;
    }

}
