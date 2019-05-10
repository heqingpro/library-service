package com.ipanel.web.lang.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.common.model.BaseDataModel.ResponseDataModel;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.lang.pageModel.AngleReq;
import com.ipanel.web.lang.service.IAngleService;
import com.ipanel.web.utils.Constants;
import com.ipanel.webapp.framework.util.Log;

/**
 * @author fangg
 * 2017年5月31日 下午5:32:21
 */
@Controller
@RequestMapping("/language")
public class AngleController {
	
	private final String TAG = "language";
	
	@Autowired
	private IAngleService angleService;
	
	@RequestMapping("/listLanguages")
	@ResponseBody
	public PageDataModel queryAngleList(HttpSession session,AngleReq model){
		try {
			Log.i(TAG,"*** listLanguages enter :req"+model.toString());
			return angleService.queryAngleList(model);
		} catch (Exception e) {
			Log.e(TAG,"*** listLanguages throw Exception :"+e);
			e.printStackTrace();
			return null;
		}
	}

}
