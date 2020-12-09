package com.bringbackdada.site.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

public class DockerEnvVariableProcessor implements EnvironmentPostProcessor{

    private static final DeferredLog logger = new DeferredLog();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        application.addInitializers(ctx -> logger.replayTo(DockerEnvVariableProcessor.class));

        Resource captchaApiKey = new FileSystemResource("/run/secrets/captcha-api-key");
        Resource captchaSiteKey = new FileSystemResource("/run/secrets/captcha-site-key");
        Properties props = new Properties();

        if (captchaApiKey.exists()) {
            try {
                String capApiKey = StreamUtils.copyToString(captchaApiKey.getInputStream(), Charset.defaultCharset());
                props.put("captcha.api.key", capApiKey);
                logger.info("DockerEnvVariableProcessor -> Captcha API key was loaded using secrets");
            } catch (IOException e) {
                logger.warn("DockerEnvVariableProcessor -> Error reading captcha API key using secrets");
                throw new RuntimeException(e);
            }
        }

        if (captchaSiteKey.exists()) {
            try {
                String capSiteKey = StreamUtils.copyToString(captchaSiteKey.getInputStream(), Charset.defaultCharset());
                props.put("captcha.site.key", capSiteKey);
                logger.info("DockerEnvVariableProcessor -> Captcha SITE key was loaded using secrets");
            } catch (IOException e) {
                logger.warn("DockerEnvVariableProcessor -> Error reading captcha SITE key using secrets");
                throw new RuntimeException(e);
            }
        }

        environment.getPropertySources().addLast(new PropertiesPropertySource("appProperties", props));

    }
}
