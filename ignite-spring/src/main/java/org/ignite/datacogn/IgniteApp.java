package org.ignite.datacogn;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteSpringBean;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.annotation.Resource;

/**
 * User:krisjin
 * Date:2019/3/11
 */
@SpringBootApplication
@ImportResource(locations = {"classpath:example-default.xml"})
public class IgniteApp {

    @Resource
    private IgniteConfiguration igniteConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(IgniteApp.class, args);
    }


    @Bean
    public Ignite getIgnite() {

        try {
            IgniteSpringBean ignite = new IgniteSpringBean();
            ignite.setConfiguration(igniteConfiguration);

            return ignite;
        } catch (Exception e) {
            return null;
        }
    }
}
