package com.cultivateknowledge.service;

import io.dropwizard.views.View;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    @GET
    public IndexView handle(@Context UriInfo uriInfo) {
        IndexView view = new IndexView("/index.mustache");
        
        view.setHelloWorldMsg("Hello World!");
        
        List<String> worlds = new ArrayList<String>();
        worlds.add("Mercury");
        worlds.add("Venus");
        worlds.add("Earth");
        worlds.add("Mars");
        worlds.add("Not Pluto");
        view.setWorlds(worlds);
        view.setHostname(uriInfo.getRequestUri().getHost());

        return view;
    }

    public static class IndexView extends View {

        private String helloWorldMsg;
        private List<String> worlds;
        private String hostname;
        
        protected IndexView(String templateName) {
            super(templateName);
        }

        public void setHelloWorldMsg(String helloWorldMsg) {
            this.helloWorldMsg = helloWorldMsg;
        }
        
        public String getHelloWorldMsg() {
            return this.helloWorldMsg;
        }
        
        public void setWorlds(List<String> worlds) {
            this.worlds = worlds;
        }
        
        public List<String> getWorlds() {
            return this.worlds;
        }

        public String getHostname() {
            return this.hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }
    }
}
