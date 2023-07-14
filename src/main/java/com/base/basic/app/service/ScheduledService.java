package com.base.basic.app.service;

import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageInfo;

public interface ScheduledService {

    PageInfo<DbCache> pageList(PageParmaters pageParmaters, Scheduled searchBody);

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

    /**
     * 暂停定时任务
     * @param scheduled
     */
    void pause(Scheduled scheduled);

    /**
     * 启用定时任务
     * @param scheduled
     */
    void enable(Scheduled scheduled);
}
