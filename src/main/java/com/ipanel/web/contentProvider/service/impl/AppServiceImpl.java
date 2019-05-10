package com.ipanel.web.contentProvider.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.contentProvider.pageModel.AppReq;
import com.ipanel.web.contentProvider.pageModel.AppResp;
import com.ipanel.web.contentProvider.service.IAppService;
import com.ipanel.web.define.AppDefined;
import com.ipanel.web.entity.AppInfo;
import com.ipanel.web.entity.NodeInfo;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.entity.SysUserToApp;
import com.ipanel.web.utils.TimeUtil;
import com.ipanel.webapp.framework.core.dao.DaoQueryOperator;
import com.ipanel.webapp.framework.util.Log;
import com.sun.corba.se.impl.orbutil.graph.Node;

@Service("appService")
public class AppServiceImpl implements IAppService {

	private final String TAG = "AppServiceImpl";
	
	@Resource
	private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public PageDataModel queryAppList(AppReq appModel) {
		List<Object[]> paramList = new ArrayList<Object[]>();
		String appName = appModel.getName();
		int page  = appModel.getPage();
		int rows  = appModel.getRows();
		if(StringUtils.isNotEmpty(appName)){
			paramList.add(new Object[]{
					DaoQueryOperator.LIKE,"appName", appName			
			});
		}
		Object[][] paramObj = null;
		if (paramList.size() > 0) {
			paramObj = new Object[paramList.size()][3];
			for (int i = 0; i < paramList.size(); i++) {
				paramObj[i] = paramList.get(i);
			}
		}
		List<AppResp> modelList = new ArrayList<AppResp>();
		List<AppInfo> appList = (List<AppInfo>)baseDao.query(null,false,
					AppInfo.class,paramObj,null,new String[] { "id" },(page-1)*rows,rows);
		long total = baseDao.count(null, false, AppInfo.class, paramObj);
		for(AppInfo app:appList){
			AppResp model = new AppResp();
			model.setId(app.getId());
			model.setName(app.getAppName());
			model.setEnName(app.getEnName());
			modelList.add(model);
		}
		return new PageDataModel((int)total,modelList,PageDataModel.SUCCESS_CODE,PageDataModel.SUCCESS_MSG);		
	}
	

}
