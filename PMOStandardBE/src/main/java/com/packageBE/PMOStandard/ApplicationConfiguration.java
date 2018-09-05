package com.packageBE.PMOStandard;

import java.io.IOException;
import java.util.Properties;

public class ApplicationConfiguration {

    private static final String PROPERTIES_FILE = "/config.properties";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String SMTP_STARTLE = "mail.smtp.starttls.enable";
    private static final String HOST = "host";
    private static final String SSL_SOCKET_FACTORY_CLASS = "mail.smtp.socketFactory.class";
    private static final String MAIL_SUBJECT = "activation.mail.subject";
    private static final String MAIL_CONTENT_FORMAT = "activation.mail";
    private static final String MAIL_SENDER = "activation.mail.sender";
    private static final String MAIL_PASS = "activation.mail.pass";
    private static final String MAIL_TIMEOUT = "activation.mail.timeout";


    private Properties properties;
    private static ApplicationConfiguration instance;

    public static ApplicationConfiguration getInstance() throws IOException{
        if (instance == null){
            instance = new ApplicationConfiguration();
        }
        return instance;
    }

    private ApplicationConfiguration() throws IOException{
        properties = new Properties();
        properties.load(getClass().getResourceAsStream(PROPERTIES_FILE));
    }

    public String getSmtpPort() {
        return properties.getProperty(SMTP_PORT);
    }

    public String getSmtpHost() {
        return properties.getProperty(SMTP_HOST);
    }

    public String getSmtpAuth() {
        return properties.getProperty(SMTP_AUTH);
    }

    public String getSmtpStartle() {
        return properties.getProperty(SMTP_STARTLE);
    }

    public String getHost() {
        return properties.getProperty(HOST);
    }

    public String getSSLSocketFactoryClass() {
        return properties.getProperty(SSL_SOCKET_FACTORY_CLASS);
    }

    public String getMailSubject() {
        return properties.getProperty(MAIL_SUBJECT);
    }

    public String getMailContentFormat() {
        return properties.getProperty(MAIL_CONTENT_FORMAT);
    }
    public String getMailSender() {
        return properties.getProperty(MAIL_SENDER);
    }
    public String getMailPass() {
        return properties.getProperty(MAIL_PASS);
    }
    public Integer getMailTimeout() {
        return Integer.valueOf(properties.getProperty(MAIL_TIMEOUT));
    }

}
