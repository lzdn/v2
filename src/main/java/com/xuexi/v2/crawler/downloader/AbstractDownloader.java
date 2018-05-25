package com.xuexi.v2.crawler.downloader;

import com.xuexi.v2.crawler.Page;
import com.xuexi.v2.crawler.Request;
import com.xuexi.v2.crawler.Site;
import com.xuexi.v2.crawler.selector.Html;;

/**
 * Base class of downloader with some common methods.
 *
 * @author code4crafter@gmail.com
 * @since 0.5.0
 */
public abstract class AbstractDownloader implements Downloader {

    /**
     * A simple method to download a url.
     *
     * @param url url
     * @return html
     */
    public Html download(String url) {
        return download(url, null);
    }

    /**
     * A simple method to download a url.
     *
     * @param url url
     * @param charset charset
     * @return html
     */
    public Html download(String url, String charset) {
        Page page = download(new Request(url), Site.me().setCharset(charset).toTask());
        return (Html) page.getHtml();
    }

    protected void onSuccess(Request request) {
    }

    protected void onError(Request request) {
    }

}
