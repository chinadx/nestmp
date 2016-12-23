package com.bellevie.nestmp.service;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bellevie.nestmp.db.dao.DbTimeMapper;
import com.bellevie.nestmp.db.dao.SysLogMapper;
import com.bellevie.nestmp.db.dto.SysLog;

@Service
public class LogService {
	Log logger = LogFactory.getLog(LogService.class);
	
    @Autowired
    private SysLogMapper sysLogMapper;
    
    @Autowired
    private DbTimeMapper dbTimeMapper;

    public void createLog(HttpServletRequest request) {
    	Date tm = dbTimeMapper.selectDbTime().getDbTime();
    	String ip = getRemortIP(request);
    	String ua = null;
    	String url = request.getRequestURL().toString();
    	String path = request.getRequestURI();
		Enumeration headerNames = request.getHeaderNames();
	    while (headerNames.hasMoreElements()) {
	        String key = (String) headerNames.nextElement();
	        String value = request.getHeader(key);
	        logger.info(path + "++++++headers: " + key + "=" + value);
	        if(key.equals("user-agent")) {
	        	ua = value;
	        }
	    }
	    
	    /*
	     * 记录日志表
	     */
	    SysLog sysLog = new SysLog();
	    sysLog.setIp(ip);
	    sysLog.setPath(path);
	    sysLog.setUrl(url);
	    sysLog.setTm(tm);
	    sysLog.setUa(ua);
	    sysLogMapper.insertSelective(sysLog);
    }
    
    public String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}