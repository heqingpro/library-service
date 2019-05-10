package com.ipanel.web.category.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ipanel.web.category.pageModel.NodeReq;
import com.ipanel.web.category.pageModel.NodeResp;
import com.ipanel.web.category.pageModel.NodeTreeModel;
import com.ipanel.web.category.service.INodeService;
import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.define.NodeDefined;
import com.ipanel.web.entity.AppInfo;
import com.ipanel.web.entity.EntryImageInfo;
import com.ipanel.web.entity.EntryInfo;
import com.ipanel.web.entity.EntryToNode;
import com.ipanel.web.entity.NodeImageInfo;
import com.ipanel.web.entity.NodeInfo;
import com.ipanel.web.entity.SysUser;
import com.ipanel.web.utils.Constants;
import com.ipanel.web.utils.FileOperation;
import com.ipanel.web.utils.TimeUtil;
import com.ipanel.webapp.framework.core.dao.DaoQueryOperator;
import com.ipanel.webapp.framework.util.Log;

@Service("nodeService")
public class NodeServiceImpl implements INodeService {

	private final String TAG = "NodeServiceImpl";
	
	@Resource
	private BaseDao baseDao;
	
	@Override
	public PageDataModel queryNodelist(NodeReq model) {
		//获取全部分类列表
		List<Object[]> paramList = new ArrayList<Object[]>();
		int page = model.getPage();
		int rows = model.getRows();
		//去除根节点本身
		paramList.add(new Object[]{
				DaoQueryOperator.NIS,"parentNodeId",0
		});
		if(model.getCpId()!=null){
			paramList.add(new Object[]{
				DaoQueryOperator.EQ,"appInfo.id",model.getCpId()	
			});
		}
		//先获取跟节点，才能获取一级节点
		if(model.getpId()!=null) {
			if(model.getpId()==0) {//代表获取一级节点
				if(model.getCpId()!=null) {
					AppInfo appInfo = baseDao.get(AppInfo.class, model.getCpId());
					System.out.println("根节点=="+appInfo.getRootNodeId());
					paramList.add(new Object[]{
							DaoQueryOperator.EQ,"parentNodeId",appInfo.getRootNodeId()
						});
				}
			}else {
				paramList.add(new Object[]{
						DaoQueryOperator.EQ,"parentNodeId",model.getpId()	
					});	
			}
		}
		if(model.getName()!=null){
			paramList.add(new Object[]{
				DaoQueryOperator.LIKE,"nodeName",model.getName()	
			});
		}
		Object[][] paramObj = null;
		if (paramList.size() > 0) {
			paramObj = new Object[paramList.size()][3];
			for (int i = 0; i < paramList.size(); i++) {
				paramObj[i] = paramList.get(i);
			}
		}
		System.out.println(paramObj);
		List<NodeInfo> nodeInfos = (List<NodeInfo>)baseDao.query(null, false,
				NodeInfo.class, paramObj, new String[]{"id"}, null, (page-1)*rows, rows);
		long total = baseDao.count(null, false, NodeInfo.class, paramObj);		
		
		List<NodeResp> nodeRespLs = new ArrayList<NodeResp>(); 
		for(NodeInfo node:nodeInfos){
			NodeResp nodeResp = new NodeResp();
			nodeResp.setId(node.getId());
			nodeResp.setpId(node.getParentNodeId());
			nodeResp.setCpId(node.getAppInfo().getId());
			nodeResp.setIsOnline(node.getIsOnline());
			nodeResp.setName(node.getNodeName());
			if(node.getNodeImage_id()!=null){
				NodeImageInfo nodeImageInfo = baseDao.get(NodeImageInfo.class, node.getNodeImage_id());
				nodeResp.setImageUrl(FileOperation.getImagePath(nodeImageInfo.getUniqueName()));
			}
			if(node.getThumbnail_id()!=null){
				NodeImageInfo nodeImageInfo =  baseDao.get(NodeImageInfo.class, node.getThumbnail_id());
				nodeResp.setThumbnailUrl(FileOperation.getImagePath(nodeImageInfo.getUniqueName()));
			}
			nodeRespLs.add(nodeResp);
		}
		return new PageDataModel((int)total, nodeRespLs,PageDataModel.SUCCESS_CODE,PageDataModel.SUCCESS_MSG);
	}


}
