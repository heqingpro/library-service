package com.ipanel.web.series.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.common.model.BaseDataModel.ResponseDataModel;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.series.pageModel.EntryTypeReq;
import com.ipanel.web.series.service.IEntryTypeService;
import com.ipanel.web.utils.Constants;
import com.ipanel.webapp.framework.util.Log;

/**
 * EntryTypeController
 * @author fangg
 * 2017年5月9日 上午9:18:06
 */

@Controller
@RequestMapping("/series")
public class EntryTypeController {

	private final String TAG = "EntryTypeController";
	
	@Autowired
	private IEntryTypeService entryTypeService;
	
	@RequestMapping("/listSeries")
	@ResponseBody
	public PageDataModel queryEntryTypeList(HttpSession session,EntryTypeReq model){
		try {
			Log.i(TAG, "*** queryEntryTypeList enter： req = "+model.toString());
			return entryTypeService.queryEntryTypeList((SysUser)session.getAttribute(Constants.SESSION_USER),model);
		} catch (Exception e) {
			Log.e(TAG, "*** queryEntryTypeList throw Exception:"+e);
			e.printStackTrace();
			return null;
		}		
	}
	
	
	
}
