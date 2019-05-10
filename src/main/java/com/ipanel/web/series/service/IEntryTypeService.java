package com.ipanel.web.series.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.series.pageModel.EntryTypeReq;

public interface IEntryTypeService {

	public PageDataModel queryEntryTypeList(SysUser user, EntryTypeReq model);

}
