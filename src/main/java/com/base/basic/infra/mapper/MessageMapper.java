package com.base.basic.infra.mapper;

import com.base.basic.domain.entity.v0.Message;
import com.base.common.util.mybatis.mapper.SupperMapper;

import java.util.List;

/**
 * 消息发送Mapper
 *
 * @author 3304604817@qq.com 2022-10-06 22:47:18
 */
public interface MessageMapper extends SupperMapper<Message> {

    List<Message> list(Message message);
}
