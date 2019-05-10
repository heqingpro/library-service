package com.ipanel.web.lang.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.lang.pageModel.AngleReq;

/**
 * @author fangg
 * 2017年5月31日 下午5:32:47
 */
public interface IAngleService {

	public PageDataModel queryAngleList(AngleReq model);

}
