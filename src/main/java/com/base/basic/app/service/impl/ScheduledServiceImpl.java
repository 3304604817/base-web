package com.base.basic.app.service.impl;

import com.base.basic.app.service.ScheduledService;
import com.base.basic.domain.entity.v1.Scheduled;
import com.base.basic.infra.mapper.ScheduledMapper;
import com.base.common.scheduled.CronTaskRegistrar;
import com.base.common.scheduled.SchedulingRunnable;
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
    public void add(Scheduled scheduled){
        scheduledMapper.insertSelective(scheduled);
        if(scheduled.getStatus().equals(Boolean.TRUE)){
            SchedulingRunnable task = new SchedulingRunnable(scheduled.getBeanName(), scheduled.getParam());
            cronTaskRegistrar.addCronTask(task, scheduled.getCron());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void edit(Scheduled scheduled){
        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        cronTaskRegistrar.removeCronTask(exitTask);

        scheduledMapper.updateByIdSelective(scheduled);

        SchedulingRunnable task = new SchedulingRunnable(scheduled.getBeanName(), scheduled.getParam());
        cronTaskRegistrar.addCronTask(task, scheduled.getCron());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Scheduled scheduled){
        scheduledMapper.deleteByPrimaryKey(scheduled.getId());

        Scheduled exit = scheduledMapper.selectByPrimaryKey(scheduled.getId());
        SchedulingRunnable exitTask = new SchedulingRunnable(exit.getBeanName(), exit.getParam());
        cronTaskRegistrar.removeCronTask(exitTask);
    }
}
