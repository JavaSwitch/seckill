package com.hwk.seckill.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Exposer implements Serializable {

    /**
     * 是否开启秒杀
     */
    private boolean isStartSeckill;

    /**
     * md5 用于动态url
     */
    private String md5;

    /**
     * 秒杀开始时间
     */
    private LocalDateTime startTime;

    /**
     *
     * 秒杀结束时间
     */
    private LocalDateTime endTime;


    /**
     * 构造方法
     * @param isStartSeckill
     */
    public Exposer(boolean isStartSeckill){
        this.isStartSeckill = isStartSeckill;
    }


    /**
     * 构造方法
     * @param isStartSeckill
     * @param md5
     * @param startTime
     * @param endTime
     */
    public Exposer(boolean isStartSeckill, String md5,LocalDateTime startTime, LocalDateTime endTime){
        this.isStartSeckill = isStartSeckill;
        if(isStartSeckill) {
            this.md5 = md5;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    public boolean isStartSeckill() {
        return isStartSeckill;
    }

    public void setStartSeckill(boolean startSeckill) {
        isStartSeckill = startSeckill;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
