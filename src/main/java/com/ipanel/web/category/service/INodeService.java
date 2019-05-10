package com.ipanel.web.category.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.category.pageModel.NodeReq;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.entity.SysUser;

public interface INodeService {

	public PageDataModel queryNodelist(NodeReq model);


	

}
