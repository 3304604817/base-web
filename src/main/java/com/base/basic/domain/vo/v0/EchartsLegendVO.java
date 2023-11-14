package com.base.basic.domain.vo.v0;

import java.math.BigDecimal;
import java.util.List;

/**
 * 折线图上每条线就是一个 EchartsLegendVO
 */
public class EchartsLegendVO {
    // 每条折线的名字
    private String legend;

    private List<BigDecimal> values;

    public String getLegend() {
        return legend;
    }

    public void setLegend(String legend) {
        this.legend = legend;
    }

    public List<BigDecimal> getValues() {
        return values;
    }

    public void setValues(List<BigDecimal> values) {
        this.values = values;
    }
}
