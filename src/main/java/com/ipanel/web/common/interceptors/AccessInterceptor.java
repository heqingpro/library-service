package com.ipanel.web.common.interceptors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.entity.AccessCount;
import com.ipanel.web.entity.AccessRecord;
import com.ipanel.web.utils.CommonUtils;

/**
 * 统计终端访问记录拦截器
 * 
 * @author: lvchao
 * @mail: chao9038@hnu.edu.cn
 * @time: 2018年4月27日下午2:11:00
 */
public class AccessInterceptor implements HandlerInterceptor {
	
	private static Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);
	
	@Resource
	private BaseDao baseDao;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		try {
			String url = request.getRequestURI();
			if(!url.contains("setBookmark")){
				AccessRecord record = new AccessRecord();
				record.setId(CommonUtils.getUUID());
				record.setUrl(url);
				record.setAccessTime(new Date());
				baseDao.save(record);
				
				String ds = CommonUtils.getDateString("yyyy-MM-dd", record.getAccessTime());
				AccessCount count = baseDao.get(AccessCount.class, ds);
				if(!CommonUtils.isNull(count)) {
					count.setCount(count.getCount() + 1);
					baseDao.update(count);
				} else {
					count = new AccessCount(ds, 1);
					baseDao.save(count);
				}
				
				/*for(int i=0; i<30; i++) {
					Calendar ca = Calendar.getInstance();
					ca.add(Calendar.DATE, i-30);
					Date date = ca.getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					AccessCount count = new AccessCount(sdf.format(date), i*10);
					baseDao.save(count);
				}*/
				
			}
		} catch (Exception e) {
			logger.error("Insert a access record error!", e);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
