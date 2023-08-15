package com.base.basic.domain.vo.v0;

import java.util.List;

public class ScriptBodyVO {

    /**
     * 参数
     */
    private List<ScriptParamVO> params;

    /**
     * 要执行的脚本
     */
    private String scriptText;

    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    public List<ScriptParamVO> getParams() {
        return params;
    }

    public void setParams(List<ScriptParamVO> params) {
        this.params = params;
    }
}
