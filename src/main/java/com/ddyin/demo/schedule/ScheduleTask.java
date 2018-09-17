package com.ddyin.demo.schedule;

import com.ddyin.demo.cache.CacheService;
import com.ddyin.demo.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author ddyin
 * @Description
 * @date 2018/9/17 16:49
 */
@Service
public class ScheduleTask {

    public final static Logger logger = LoggerFactory.getLogger(UserInfoService.class);

    //执行频率
    public static final int CACHE_RATE=1000;

    //开始执行时间(3min后执行任务)
    public static final int CACHE_START_TIME=180;

    @Autowired
    private CacheService cacheService;

    //定时任务执行(多线程，任务并发执行，只有当任务的执行时间到来时，ScheduledExecutorService才会真正启动一个线程，其余时间ScheduledExecutorService都是在轮询任务的状态。)
    @PostConstruct
    public void excuteScheduleTask(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
        try {

            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    cacheService.flushMemoryCache();
                    logger.info("ScheduleTask.scheduleFlushCache success");
                }
            }, CACHE_START_TIME, CACHE_RATE, TimeUnit.SECONDS);//3min后执行，执行频率是1000秒


//            //定时更新产品状态
//            Integer updateProductStatusRate =Integer.valueOf(map.get(QConfigKey.UPDATE_PRODUCT_STATUS_RATE)) * CALCULATE_HOUR ;//换算成小时
//            Integer updateProductStatusTime =Integer.valueOf(map.get(QConfigKey.UPDATE_PRODUCT_STATUS_TIME)) ;//小时
//
//            scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
//                @Override
//                public void run() {
//                    productService.updateProductStatus();
//                    logger.info("ScheduleTask.scheduleUpdateProductStatus success");
//                }
//            }, updateProductStatusTime, updateProductStatusRate, TimeUnit.HOURS);

        } catch (Exception e) {
            logger.error("ScheduleTask.excuteScheduleTask exception"+e.getMessage());
        }
    }

}
