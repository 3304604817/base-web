package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.ScriptBodyVO;
import com.base.basic.domain.vo.v0.ScriptParamVO;

public interface BatchScriptRedisService {

    /**
     * 执行redis脚本
     */
    void executeRedis(ScriptBodyVO scriptBody);
}
