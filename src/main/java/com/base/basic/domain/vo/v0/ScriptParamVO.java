package com.base.basic.domain.vo.v0;

import java.util.List;

public class ScriptParamVO {

    /**
     * 要执行的脚本
     */
    private String scriptText;
    /**
     * 参数key
     */
    private String key;

    /**
     * 参数类型
     * fixed 固定值
     * range 范围值
     */
    private String valueType;

    /**
     * 参数值
     */
    private String value;

    /**
     * 范围从
     */
    private Long rangeFm;

    /**
     * 范围至
     */
    private Long rangeTo;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getRangeFm() {
        return rangeFm;
    }

    public void setRangeFm(Long rangeFm) {
        this.rangeFm = rangeFm;
    }

    public Long getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(Long rangeTo) {
        this.rangeTo = rangeTo;
    }

    public String getScriptText() {
        return scriptText;
    }

    public void setScriptText(String scriptText) {
        this.scriptText = scriptText;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
