package com.logcycle.task.Job;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@DisallowConcurrentExecution
public class CalculateJob implements Job {

    Logger logger = LogManager.getLogger("RollingFileInfo");

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        // 具体执行操作
        logger.info("-------------------------");
    }
}
