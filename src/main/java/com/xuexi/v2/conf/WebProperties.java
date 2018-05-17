package com.xuexi.v2.conf;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;

@Configuration  
@ConfigurationProperties(prefix = "web")  
@PropertySource("classpath:web.properties")
public class WebProperties {

	/*@Autowired  
    private Environment env; */
	
	@Value("${web.securityKey}") 
	private String securityKey;

	public String getSecurityKey() {
		return securityKey;
	}
 
	@Value("${web.cookieKey}") 
	private String cookieKey;

	public String getCookieKey() {
		return cookieKey;
	}
	
	@Value("${web.cookieDomain}") 
	private String cookieDomain;

	public String getCookieDomain() {
		return cookieDomain;
	}
	
	@Value("${web.secretKey}") 
	private String secretKey;

	public String getSecretKey() {
		return secretKey;
	}
	
	@Value("${web.cookieName}") 
	private String cookieName;

	public String getCookieName() {
		return cookieName;
	}
	
	@Value("${web.sessionName}") 
	private String sessionName;

	public String getSessionName() {
		return sessionName;
	}
	
	@Value("${web.basePath}") 
	private String basePath;

	public String getBasePath() {
		return basePath;
	}

	@Value("${web.production}") 
	private boolean production = false;

	public boolean isProduction() {
		return production;
	}

	@Value("${web.uploadLocalPath}") 
	private String uploadLocalPath;

	public String getUploadLocalPath() {
		return uploadLocalPath;
	}
 
	@Value("${web.projectName}")
	private String projectName;

	public String getProjectName() {
		return projectName;
	}
	
	@Value("${web.downloadPath}")
	private String downloadPath;

	public String getDownloadPath() {
		return downloadPath;
	}
	
	 
	
	
}
