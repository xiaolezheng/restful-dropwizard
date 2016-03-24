package com.lxz.restful.app;

import lombok.extern.slf4j.Slf4j;

import com.lxz.restful.config.HelloWorldConfiguration;
import com.lxz.restful.resource.HelloWorldResource;
import com.lxz.restful.support.TemplateHealthCheck;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

@Slf4j
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
        log.info("init......");
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(configuration.getTemplate(),configuration.getDefaultName());
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
        //final ThreadDeadlockHealthCheck deadlockHealthCheck = new ThreadDeadlockHealthCheck();

        environment.jersey().register(resource);
        environment.healthChecks().register("healthCheck", healthCheck);
        //environment.healthChecks().register("deadlockCheck", deadlockHealthCheck);

        log.info("run.......");
    }
}