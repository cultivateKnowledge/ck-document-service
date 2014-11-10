package com.cultivateknowledge.acceptance;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.cli.ServerCommand;
import io.dropwizard.lifecycle.ServerLifecycleListener;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.sourceforge.argparse4j.inf.Namespace;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class DropwizardTestSupport<C extends Configuration> {

    private final Class<? extends Application<C>> serviceClass;
    private final String configPath;

    private C configuration;
    private Application<C> application;
    private Environment environment;
    private Server jettyServer;

    public DropwizardTestSupport(Class<? extends Application<C>> serviceClass, String configPath) {
        this.serviceClass = serviceClass;
        this.configPath = configPath;
    }

    public void startIfRequired() {
        if (jettyServer != null) {
            return;
        }

        try {
            application = serviceClass.newInstance();

            final Bootstrap<C> bootstrap = new Bootstrap<C>(application) {
                @Override
                public void run(C configuration, Environment environment) throws Exception {
                    environment.lifecycle().addServerLifecycleListener(new ServerLifecycleListener() {
                        @Override
                        public void serverStarted(Server server) {
                            jettyServer = server;
                        }
                    });
                    DropwizardTestSupport.this.configuration = configuration;
                    DropwizardTestSupport.this.environment = environment;
                    super.run(configuration, environment);
                }
            };

            application.initialize(bootstrap);
            final ServerCommand<C> command = new ServerCommand<C>(application);
            final Namespace namespace = new Namespace(ImmutableMap.<String, Object>of("file", configPath));
            command.run(bootstrap, namespace);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (jettyServer != null) {
            try {
                jettyServer.stop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public C getConfiguration() {
        return configuration;
    }

    public int getLocalPort() {
        return ((ServerConnector) jettyServer.getConnectors()[0]).getLocalPort();
    }

    public int getAdminPort() {
        return ((ServerConnector) jettyServer.getConnectors()[1]).getLocalPort();
    }
    public String getEndpoint() {
        return "http://localhost:" + getLocalPort();
    }

    public ObjectMapper getObjectMapper() {
        return getEnvironment().getObjectMapper();
    }

    public Environment getEnvironment() {
        return environment;
    }
}