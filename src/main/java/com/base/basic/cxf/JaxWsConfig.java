package com.base.basic.cxf;

import com.base.basic.app.service.JaxWsWebService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class JaxWsConfig {

    @Autowired
    private Bus bus;

    @Autowired
    private JaxWsWebService jaxWsWebService;

    @Bean
    public Endpoint createEndpoint(){
        Endpoint endpoint = new EndpointImpl(bus, jaxWsWebService);
        endpoint.publish("/ws/web-service");
        return endpoint;
    }
}
