package com.yy.schedule.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yy
 * @date: 2021/3/5
 */
@Component
@Slf4j
public class MeetingRoomHandler {

    /**
     * 会议室任务，需要定时从从ids获取会议室文件入库
     * 子任务id为会议室入钉钉部门
     */
    @XxlJob("meetingRoomHandler")
    public ReturnT<String> meetingRoomHandler(String param) throws Exception {
        return ReturnT.SUCCESS;
    }
}
