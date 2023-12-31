package com.base.basic.app.service.impl;

import com.base.basic.app.service.ScheduledService;
import com.base.basic.domain.entity.v1.DbCache;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.basic.infra.mapper.ScheduledMapper;
import com.base.common.scheduled.CronTaskRegistrar;
import com.base.common.scheduled.SchedulingRunnable;
import com.base.common.util.page.PageParmaters;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduledServiceImpl implements ScheduledService {

    @Autowired
    @SuppressWarnings("all")
    private ScheduledMapper scheduledMapper;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public PageInfo<DbCache> pageList(PageParmaters pageParmaters, Scheduled searchBody){
        return PageHelper.startPage(pageParmaters.getPage(), pageParmaters.getLimit()).doSelectPageInfo(() -> scheduledMapper.list(searchBody));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Scheduled scheduled){
        scheduledMapper.insertSelective(scheduled);

        // 新建定时任务默认启动
        SchedulingRunnable task = new SchedulingRunnable(scheduled.getBeanName(), scheduled.getParam());
        cronTaskRegistrar.addCronTask(task, scheduled.getCron());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(Scheduled scheduled){
        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        /**
         * 关闭成功则更新
         */
        if(cronTaskRegistrar.removeCronTask(exitTask)){
            scheduledMapper.updateByIdSelective(scheduled);
            if(exit.getStatus()){
                /**
                 * 如果编辑的定时任务已经是启动状态则继续添加启动
                 */
                SchedulingRunnable task = new SchedulingRunnable(scheduled.getBeanName(), scheduled.getParam());
                cronTaskRegistrar.addCronTask(task, scheduled.getCron());
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Scheduled scheduled){
        /**
         * 先关闭定时任务再删除数据
         */
        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        if(cronTaskRegistrar.removeCronTask(exitTask)){
            // 关闭成功则删除定时任务记录
            scheduledMapper.deleteByPrimaryKey(scheduled.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Scheduled scheduled){
        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        if(cronTaskRegistrar.removeCronTask(exitTask)){
            scheduled.setStatus(Boolean.FALSE);
            scheduledMapper.updateOptional(scheduled, Scheduled.FIELD_STATUS);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Scheduled scheduled){
        scheduled.setStatus(Boolean.TRUE);
        scheduledMapper.updateOptional(scheduled, Scheduled.FIELD_STATUS);

        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        cronTaskRegistrar.addCronTask(exitTask, exit.getCron());
    }
}
