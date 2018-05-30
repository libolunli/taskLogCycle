package com.logcycle.task.tasks;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class QuartzLog {

    @Scheduled(cron = "0/2 * * * * ?") //每2秒执行一次
    public void doSomething() throws Exception {
        System.out.println("每2秒执行一个的定时任务："+new Date());
    }
}
