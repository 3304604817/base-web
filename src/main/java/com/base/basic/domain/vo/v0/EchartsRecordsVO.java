package com.base.basic.domain.vo.v0;

import java.math.BigDecimal;
import java.util.List;

public class EchartsRecordsVO {

    // X 轴的名字
    private List<String> xAxis;

    private List<EchartsLegendVO> legendVOList;

    public List<String> getxAxis() {
        return xAxis;
    }

    public void setxAxis(List<String> xAxis) {
        this.xAxis = xAxis;
    }

    public List<EchartsLegendVO> getLegendVOList() {
        return legendVOList;
    }

    public void setLegendVOList(List<EchartsLegendVO> legendVOList) {
        this.legendVOList = legendVOList;
    }
}
