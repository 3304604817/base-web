package com.base.basic.app.service;

import com.base.basic.domain.vo.v0.ScriptBodyVO;
import com.base.basic.domain.vo.v0.ScriptParamVO;

public interface BatchScriptRedisService {

    void executeRedis(ScriptBodyVO scriptBody);
}
