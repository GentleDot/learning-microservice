package net.gentledot.microservice.microservicechassis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class YamlPropertySourceFactory implements PropertySourceFactory {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        log.info("resource : {}", resource);
        Properties propertiesFromYaml = loadYamlIntoProperties(resource);
        String sourceName = name != null ? name : resource.getResource().getFilename();

        return new PropertiesPropertySource(sourceName, propertiesFromYaml);
    }

    private Properties loadYamlIntoProperties(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean factoryBean = new YamlPropertiesFactoryBean();
            factoryBean.setResources(resource.getResource());
            factoryBean.afterPropertiesSet();

            return factoryBean.getObject();
        } catch (IllegalStateException e) {
            Throwable cause = e.getCause();

            if (cause instanceof FileNotFoundException) {
                throw (FileNotFoundException) cause;
            }

            throw e;
        }
    }
}
