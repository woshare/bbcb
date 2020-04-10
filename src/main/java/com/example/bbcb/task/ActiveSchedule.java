package com.example.bbcb.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 在springboot的主启动程序添加注解@EnableScheduling
 * fixedDelay:每次执行任务之后间隔多久再次执行该任务 fixedDelay = 1000
 * 设置时分秒等具体的定时 cron="0/5 * *  * * ? "
 *       1  秒（0~59）
 *       2  分钟（0~59）
 *       3  小时（0~23）
 *       4  天（0~31）
 *       5  月（0~11）
 *       6  星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
 *       7  年份（1970－2099）
 * initialDelay :初次执行任务之前需要等待的时间
 *fixedRate:执行频率，每隔多少时间就启动任务，不管该任务是否启动完成
 */
@Slf4j
@Component
public class ActiveSchedule {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //@Scheduled(fixedDelay = 1000)
    public void testSchedule(){

        long currentTime=System.currentTimeMillis();
        log.info("theCurrentTime:{},{}",currentTime,dateFormat.format(new Date()));
    }
}
