package com.fxf.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 商户相关的定时任务
 */
@Component("BusinessTask")
public class BusinessTask {

	private static final Logger logger = LoggerFactory.getLogger(BusinessTask.class);
	/**
	 * 同步已售数量
	 */
	public void synNumber(){
		logger.info("已同步！");
	}
}
