package com.base.basic.app.service;

import com.alibaba.fastjson.JSONObject;
import com.base.basic.domain.vo.v0.FormatToolVO;

public interface FormatToolService {

    /**
     *  Json格式化
     */
    FormatToolVO jsonFormat(FormatToolVO formatToolVO);

    /**
     *  Base64加密
     */
    FormatToolVO base64Encryp(FormatToolVO formatToolVO);

    /**
     *  Base64解密
     */
    FormatToolVO base64Decrypt(FormatToolVO formatToolVO);

    /**
     *  MD5加密
     */
    FormatToolVO md5Encryp(FormatToolVO formatToolVO);

    /**
     *  UUID生成
     */
    FormatToolVO uuidGenerate(FormatToolVO formatToolVO);
}
