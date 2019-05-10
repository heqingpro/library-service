package com.ipanel.web.contentProvider.service;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.contentProvider.pageModel.AppReq;
import com.ipanel.web.contentProvider.pageModel.AppResp;
import com.ipanel.web.entity.SysUser;

public interface IAppService {

	PageDataModel queryAppList(AppReq appModel);
}
