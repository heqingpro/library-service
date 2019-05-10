package com.ipanel.web.contentProvider.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.contentProvider.pageModel.AppReq;
import com.ipanel.web.contentProvider.pageModel.AppResp;
import com.ipanel.web.contentProvider.service.IAppService;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.utils.Constants;
import com.ipanel.webapp.framework.util.Log;

@Controller
@RequestMapping("/ContentProvider")
public class AppController {

	private final String TAG = "ContentProvider";
	
	@Autowired
	private IAppService appService;
	
	/**
	 * 用户授权的app,超级管理员的特权
	 * @author fangg
	 * 2017年5月31日 下午4:41:35
	 * @return
	 */
	@RequestMapping("/listCps")
	@ResponseBody
	public PageDataModel queryAppList(HttpSession session,AppReq appReq){
		try {
			Log.i(TAG,"*** listCps enter,req="+appReq.toString());
			return appService.queryAppList(appReq);			
		} catch (Exception e) {
			Log.e(TAG, "*** listCps throw exception:" + e);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
}


