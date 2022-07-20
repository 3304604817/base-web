package com.base.basic.app.service.impl;

import com.base.basic.app.service.JaxRsWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JaxRsWebServiceImpl implements JaxRsWebService {

    Logger logger = LoggerFactory.getLogger(JaxRsWebServiceImpl.class);

    @Override
    public String test() {
        return "null";
    }
}
