package com.ipanel.web.lang.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.entity.AngleInfo;
import com.ipanel.web.lang.pageModel.AngleReq;
import com.ipanel.web.lang.pageModel.AngleResp;
import com.ipanel.web.lang.service.IAngleService;
import com.ipanel.web.utils.FileOperation;
import com.ipanel.webapp.framework.core.dao.DaoQueryOperator;

/**
 * @author fangg
 * 2017年5月31日 下午5:33:33
 */
@Service("angleService")
public class AngleServiceImpl implements IAngleService {
	
	private final String TAG = "AngleServiceImpl";
	
	@Resource
	private BaseDao baseDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public PageDataModel queryAngleList(AngleReq model) {
		List<Object[]> paramList = new ArrayList<Object[]>();
		String name = model.getName();
		int page = model.getPage();
		int rows = model.getRows();
		if(StringUtils.isNotEmpty(name)){
			paramList.add(new Object[]{
					DaoQueryOperator.LIKE,"name",name
			});
		}
		Object[][] paramObj = null;
		if (paramList.size() > 0) {
			paramObj = new Object[paramList.size()][3];
			for (int i = 0; i < paramList.size(); i++) {
				paramObj[i] = paramList.get(i);
			}
		}	
		List<AngleInfo> angleInfos = new ArrayList<AngleInfo>();
		if(page==0||rows==0){
			angleInfos = (List<AngleInfo>)baseDao.query(null,false,AngleInfo.class,paramObj,null,new String[]{"id"},null,null);
		}else {
			angleInfos = (List<AngleInfo>)baseDao.query(null,false,AngleInfo.class,paramObj,null,new String[]{"id"},(page-1)*rows,rows);
		}
		long total = baseDao.count(null, false, AngleInfo.class, paramObj);
		List<AngleResp> angleResps = new ArrayList<AngleResp>();
		for(AngleInfo  angleInfo:angleInfos){
			AngleResp angleResp = new AngleResp();
			angleResp.setId(angleInfo.getId());
			angleResp.setName(angleInfo.getName());
			if(angleInfo.getUniqueImageName()!=null){
				angleResp.setUniqueImageName(angleInfo.getUniqueImageName());
				angleResp.setImageUrl(FileOperation.getImagePath(angleInfo.getUniqueImageName()));
			}
			angleResps.add(angleResp);
		}		
		return new PageDataModel((int)total,angleResps,PageDataModel.SUCCESS_CODE,PageDataModel.SUCCESS_MSG);
	}


}
