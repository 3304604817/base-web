package com.base.common.current;

import com.base.basic.domain.vo.v0.CurrentUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUserHelper {

    private static final Logger logger = LoggerFactory.getLogger(CurrentUserHelper.class);

    /**
     * 获取当前用户
     * @return
     */
    public static CurrentUserVO userDetail(){
        try {
            return (CurrentUserVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            logger.error("e {}", e);
        }
        return null;
    }
}
