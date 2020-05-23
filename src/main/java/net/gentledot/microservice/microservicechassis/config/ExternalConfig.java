package net.gentledot.microservice.microservicechassis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource(factory = YamlPropertySourceFactory.class,
                ignoreResourceNotFound = true,
                value = {"classpath:microservice.yml",
                        "file:/conf/microservice.yml",
                        "file:c:/workspace/study/spring_microservice/microservice_class_practice/conf/microservice.yml"})
})
public class ExternalConfig {
}