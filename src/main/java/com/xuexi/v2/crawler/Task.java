package com.xuexi.v2.crawler;;

/**
 * Interface for identifying different tasks.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 * @see com.xuexi.v2.crawler.scheduler.Schedulerr
 * @see com.xuexi.v2.crawler.pipeline.Pipelinee
 */
public interface Task {

    /**
     * unique id for a task.
     *
     * @return uuid
     */
    public String getUUID();

    /**
     * site of a task
     *
     * @return site
     */
    public Site getSite();

}
