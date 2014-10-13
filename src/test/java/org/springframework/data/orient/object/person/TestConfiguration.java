package org.springframework.data.orient.object.person;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.orient.repository.config.EnableOrientRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableOrientRepositories(basePackages = "org.springframework.data.orient.object.person")
public class TestConfiguration {

}
