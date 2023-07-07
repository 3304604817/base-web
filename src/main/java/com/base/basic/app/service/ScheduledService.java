package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.Scheduled;

public interface ScheduledService {

    /**
     * 新增定时任务
     * @param scheduled
     */
    void add(Scheduled scheduled);

    /**
     * 修改定时任务
     * @param scheduled
     */
    void edit(Scheduled scheduled);

    /**
     * 删除定时任务
     * @param scheduled
     */
    void delete(Scheduled scheduled);
}
