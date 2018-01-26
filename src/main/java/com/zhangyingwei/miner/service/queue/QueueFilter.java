package com.zhangyingwei.miner.service.queue;

import com.zhangyingwei.cockroach.executer.task.Task;
import com.zhangyingwei.cockroach.queue.IQueueTaskFilter;
import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;

/**
 * Created by zhangyw on 2018/1/26.
 */
public class QueueFilter implements IQueueTaskFilter {
    @Override
    public boolean accept(Task task) {
        return StringUtils.isNotBlank(task.getUrl());
    }
}
