package com.ipanel.web.category.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipanel.web.category.pageModel.NodeReq;
import com.ipanel.web.category.service.INodeService;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.webapp.framework.util.Log;

@Controller
@RequestMapping("/category")
public class NodeController {
	
	private final String TAG = "NodeController";
	
	@Autowired
	private INodeService nodeService;

	/**
	 * 获取分类树
	 * @author fangg
	 * 2017年5月13日 下午5:00:27
	 * @param model
	 * @param appId
	 * @return
	 */
	@RequestMapping("/listCategorys")
	@ResponseBody
	public PageDataModel queryNodelist(NodeReq model){
		try {
			Log.i(TAG, "***　queryNodelist　enter: req="+model.toString());
			return nodeService.queryNodelist(model);
		} catch (Exception e) {
			Log.e(TAG, "*** queryNodelist throw Exception:"+e);
			e.printStackTrace();
			return null;
		}
	}

	
	
	

}
