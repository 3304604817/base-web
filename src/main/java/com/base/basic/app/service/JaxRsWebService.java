package com.base.basic.app.service;

import javax.jws.WebService;

/**
 * 对外发布的webservice
 */
@WebService
public interface JaxRsWebService {

    String test();
}
