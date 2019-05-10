package com.ipanel.web.series.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.define.EntryTypeDefined;
import com.ipanel.web.entity.EntryTypeInfo;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.series.pageModel.EntryTypeReq;
import com.ipanel.web.series.pageModel.EntryTypeResp;
import com.ipanel.web.series.service.IEntryTypeService;
import com.ipanel.web.utils.Constants;
import com.ipanel.web.utils.FileOperation;
import com.ipanel.web.utils.TimeUtil;
import com.ipanel.webapp.framework.core.dao.DaoQueryOperator;
import com.ipanel.webapp.framework.util.Log;

@Service("entryTypeServcie")
public class EntryTypeServiceImpl implements IEntryTypeService {

	private final String TAG= "EntryTypeServiceImpl";
	
	@Resource
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageDataModel queryEntryTypeList(SysUser user,EntryTypeReq model) {
		int page = model.getPage();
		int rows = model.getRows();
		String  typeName = model.getName();
		List<Object[]> paramList = new ArrayList<Object[]>();
		if(StringUtils.isNotEmpty(typeName)){
				paramList.add(new Object[]{
					DaoQueryOperator.LIKE,"typeName",typeName
				});
		}
		Object[][] paramObj = null;
		if(paramList.size()>0){
			paramObj  = new Object[paramList.size()][3];
			for(int i=0;i<paramList.size();i++){
				paramObj[i]  = 	paramList.get(i);
			}			
		}
		List<EntryTypeInfo> entryTypeInfos = (List<EntryTypeInfo>)baseDao.query(null,false
				, EntryTypeInfo.class, paramObj, null, new String[]{"id"}, (page-1)*rows, rows);
		
		long total = baseDao.count(null, false, EntryTypeInfo.class, paramObj);
		
		List<EntryTypeResp> entryTypeModels = new ArrayList<EntryTypeResp>();
		for(EntryTypeInfo entryTypeInfo:entryTypeInfos){
			EntryTypeResp entryTypeModel = new EntryTypeResp();
			entryTypeModel.setId(entryTypeInfo.getId());
			entryTypeModel.setName(entryTypeInfo.getTypeName());
			if(entryTypeInfo.getUniqueImageName()!=null){
				entryTypeModel.setUniqueImageName(entryTypeInfo.getUniqueImageName());
				entryTypeModel.setImageUrl(FileOperation.getImagePath(entryTypeInfo.getUniqueImageName()));
			}
			entryTypeModel.setAddTime(entryTypeInfo.getAddTime());
			entryTypeModels.add(entryTypeModel);
		}		
		return new PageDataModel((int)total,entryTypeModels,PageDataModel.SUCCESS_CODE,PageDataModel.SUCCESS_MSG);
	}
	
}
