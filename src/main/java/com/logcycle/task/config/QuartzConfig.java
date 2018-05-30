package com.logcycle.task.config;

import com.logcycle.task.AutowiringSpringBeanJobFactory;
import com.logcycle.task.Job.CalculateJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean(name="calculate")
    public JobDetailFactoryBean jobDetailFactoryBean(){
        //生成JobDetail
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        factory.setJobClass(CalculateJob.class);  //设置对应的Job
        factory.setGroup("calculateGroup");
        factory.setName("calculateJob");
        return factory;
    }


    @Bean(name="calculateTrigger")
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
        //设置JobDetail
        stFactory.setJobDetail(jobDetailFactoryBean().getObject());
        stFactory.setStartDelay(1000);
        stFactory.setName("calculateTrigger");
        stFactory.setGroup("calculateGroup");
        stFactory.setCronExpression("0/2 * * * * ?");  //定时任务时间配置
        return stFactory;
    }

    @Autowired
    @Qualifier("calculateTrigger")
    private CronTriggerFactoryBean calculateTrigger;
    //Quartz中的job自动注入spring容器托管的对象
    @Bean
    public AutowiringSpringBeanJobFactory autoWiringSpringBeanJobFactory() {
        return new AutowiringSpringBeanJobFactory();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(autoWiringSpringBeanJobFactory());  //配置Spring注入的Job类
        scheduler.setTriggers(
                calculateTrigger.getObject()
        ); //这里可以设置多个CronTriggerFactoryBean
        return scheduler;
    }
}
