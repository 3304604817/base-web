package com.base.basic.app.service;

import java.io.UnsupportedEncodingException;

public interface TranslationService {

    /**
     * 翻译
     */
    String language(String from, String to, String q) throws Exception;
}
