package com.xuexi.v2.crawler.extension;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.xuexi.v2.crawler.ResultItems;
import com.xuexi.v2.crawler.Task;
import com.xuexi.v2.crawler.pipeline.Pipeline;
import com.xuexi.v2.crawler.utils.FilePersistentBase;

/**
 * Store results to files in JSON format.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.2.0
 */
public class JsonFilePipeline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * new JsonFilePageModelPipeline with default path "/data/webmagic/"
     */
    public JsonFilePipeline() {
        setPath("/data/webmagic");
    }

    public JsonFilePipeline(String path) {
        setPath(path);
    }

    @Override
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;
        try {
        	String kind = resultItems.get("kind");
        	String id = resultItems.get("id");
        	PrintWriter printWriter = new PrintWriter(new FileWriter(getFile(path + (StringUtils.isEmpty(kind)?id:kind)/*DigestUtils.md5Hex(resultItems.getRequest().getUrl())*/ + ".json")));
            printWriter.write(JSON.toJSONString(resultItems.getAll()));
            printWriter.close();
        } catch (IOException e) {
            logger.warn("write file error", e);
        }
    }
}
