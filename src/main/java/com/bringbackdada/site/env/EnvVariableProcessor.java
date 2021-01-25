package com.bringbackdada.site.env;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class EnvVariableProcessor implements EnvironmentPostProcessor{

    private static final DeferredLog logger = new DeferredLog();

    @SneakyThrows
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        application.addInitializers(ctx -> logger.replayTo(EnvVariableProcessor.class));

        Resource captchaApiKey = new ClassPathResource("/envfiles/captcha_api_key.txt");
        Resource captchaSiteKey = new ClassPathResource("/envfiles/captcha_site_key.txt");
        Resource mailservUser = new ClassPathResource("/envfiles/mailserv_user.txt");
        Resource mailservKey = new ClassPathResource("/envfiles/mailserv_key.txt");
        Resource mailservAddress = new ClassPathResource("/envfiles/mailserv_address.txt");
        Properties props = new Properties();

        /*
        FriendlyCaptcha credentials
         */
        if (captchaApiKey.exists()) {
            try {
                String capApiKey = StreamUtils.copyToString(captchaApiKey.getInputStream(), Charset.defaultCharset());
                props.put("captcha.api.key", capApiKey);
                logger.info("EnvVariableProcessor -> Captcha API key was loaded using envfiles directory");
            } catch (IOException e) {
                logger.warn("EnvVariableProcessor -> Error reading loaded captcha API key");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("EnvVariableProcessor -> ERROR! (captchaApiKey) no such resource loaded!");
        }

        if (captchaSiteKey.exists()) {
            try {
                String capSiteKey = StreamUtils.copyToString(captchaSiteKey.getInputStream(), Charset.defaultCharset());
                props.put("captcha.site.key", capSiteKey);
                logger.info("EnvVariableProcessor -> Captcha SITE key was loaded using envfiles directory");
            } catch (IOException e) {
                logger.warn("EnvVariableProcessor -> Error reading loaded captcha SITE key");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("EnvVariableProcessor -> ERROR! (captchaSiteKey) no such resource loaded!");
        }

        /*
        Mail server credentials
         */
        if (mailservUser.exists()) {
            try {
                String mailUser = StreamUtils.copyToString(mailservUser.getInputStream(), Charset.defaultCharset());
                props.put("mail.server.user", mailUser);
                logger.info("EnvVariableProcessor -> Mailserv user was loaded using envfiles directory");
            } catch (IOException e) {
                logger.warn("EnvVariableProcessor -> Error reading loaded mailserv user");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("EnvVariableProcessor -> ERROR! (mailservUser) no such resource loaded!");
        }


        if (mailservKey.exists()) {
            try {
                String mailKey = StreamUtils.copyToString(mailservKey.getInputStream(), Charset.defaultCharset());
                props.put("mail.server.key", mailKey);
                logger.info("EnvVariableProcessor -> Mailserv key was loaded using envfiles directory");
            } catch (IOException e) {
                logger.warn("EnvVariableProcessor -> Error reading loaded mailserv key");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("EnvVariableProcessor -> ERROR! (mailservKey) no such resource loaded!");
        }

        if (mailservAddress.exists()) {
            try {
                String mailAddress = StreamUtils.copyToString(mailservAddress.getInputStream(), Charset.defaultCharset());
                props.put("mail.server.address", mailAddress);
                logger.info("EnvVariableProcessor -> Mailserv address was loaded using envfiles directory");
            } catch (IOException e) {
                logger.warn("EnvVariableProcessor -> Error reading loaded mailserv address");
                throw new RuntimeException(e);
            }
        } else {
            logger.warn("EnvVariableProcessor -> ERROR! (mailservAddress) no such resource loaded!");
        }

        environment.getPropertySources().addLast(new PropertiesPropertySource("appProperties", props));

    }
}
