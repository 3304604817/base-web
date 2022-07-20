package com.base.basic.app.service.impl;

import com.base.basic.app.service.JaxWsWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JaxWsWebServiceImpl implements JaxWsWebService {

    Logger logger = LoggerFactory.getLogger(JaxWsWebServiceImpl.class);

    @Override
    public String jaxWs() {
        return "null";
    }
}
