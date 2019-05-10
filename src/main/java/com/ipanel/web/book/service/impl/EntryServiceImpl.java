package com.ipanel.web.book.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipanel.web.book.pageModel.EntryAlbumModel;
import com.ipanel.web.book.pageModel.EntryAudioModel;
import com.ipanel.web.book.pageModel.EntryDetailResp;
import com.ipanel.web.book.pageModel.EntryFileModel;
import com.ipanel.web.book.pageModel.EntryImageModel;
import com.ipanel.web.book.pageModel.EntryReq;
import com.ipanel.web.book.pageModel.EntryResp;
import com.ipanel.web.book.service.IEntryService;
import com.ipanel.web.common.dao.BaseDao;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.web.entity.Bookmark;
import com.ipanel.web.entity.EntryAudioInfo;
import com.ipanel.web.entity.EntryFileInfo;
import com.ipanel.web.entity.EntryImageInfo;
import com.ipanel.web.entity.EntryInfo;
import com.ipanel.web.entity.EntryToNode;
import com.ipanel.web.entity.NodeInfo;
import com.ipanel.web.utils.FileOperation;
import com.ipanel.webapp.framework.core.dao.DaoQueryOperator;

@Service("entryServcie")
public class EntryServiceImpl implements IEntryService {
	
	@Resource
	private BaseDao baseDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EntryServiceImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean setBookmark(EntryReq model) {
		try {
			List<Bookmark> marks = (List<Bookmark>) baseDao.query("from Bookmark b where b.stbID='" + model.getStbID() + "' and b.entryId=" + model.getId());
			if(marks.size() == 1) {
				Bookmark mark = marks.get(0);
				if(model.getFlag() == 0) {
					mark.setFontSize(model.getFontSize());
					mark.setTotalPages(model.getTotalPages());
					mark.setCurrentPage(model.getCurrentPage());
				} else if(model.getFlag() == 1) {
					mark.setTotalSeconds(model.getTotalSeconds());
					mark.setCurrentSeconds(model.getCurrentSeconds());
				}
				baseDao.update(mark);
			} else {
				Bookmark mark = new Bookmark();
				mark.setEntryId(model.getId());
				mark.setStbID(model.getStbID());
				mark.setFlag(model.getFlag());
				if(model.getFlag() == 0) {
					mark.setFontSize(model.getFontSize());
					mark.setTotalPages(model.getTotalPages());
					mark.setCurrentPage(model.getCurrentPage());
				} else if(model.getFlag() == 1) {
					mark.setTotalSeconds(model.getTotalSeconds());
					mark.setCurrentSeconds(model.getCurrentSeconds());
				}
				baseDao.save(mark);
			}
			return true;
		} catch (Exception e) {
			LOGGER.error("setBookmark->Set bookmark failed!", e);
		}
		return false;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public PageDataModel queryEntryList(EntryReq model) {
		List<Object[]> paramList = new ArrayList<Object[]>();
		int page = model.getPage();
		int rows = model.getRows();
		String title = model.getTitle();
		long total = 0;
		List<EntryResp> entryModels = new ArrayList<EntryResp>();
		//获取某个分类下的列表
		if(model.getCatId()!=null){
			NodeInfo nodeInfo = baseDao.get(NodeInfo.class,model.getCatId());
			List<EntryToNode> entryToNodes = nodeInfo.getEntryToNodeList();
			for(EntryToNode entryToNode:entryToNodes){
				EntryInfo entryInfo = entryToNode.getEntryInfo();
				EntryResp entryResp= new EntryResp();
				if(entryInfo!=null){
					entryResp = parseModel(entryInfo,entryResp);			
					entryModels.add(entryResp);
				}
			}
			if((page-1)*rows<entryModels.size()){
				total = entryModels.size();
				entryModels = entryModels.subList((page-1)*rows, entryModels.size());
			}else {
				total = 0;
				entryModels.clear();				
			}
		}else {
			if(model.getCpId()!=null){
				paramList.add(new Object[]{
					DaoQueryOperator.EQ,"appInfo.id",model.getCpId()	
				});
			}
			if(model.getLangId()!=null){
				paramList.add(new Object[]{
						DaoQueryOperator.EQ,"angleInfo.id",model.getLangId()
					});
			}
			if(model.getYearsId()!=null){
				paramList.add(new Object[]{
					DaoQueryOperator.EQ,"yearsId",model.getYearsId()	
					}
				);
			}
			if(model.getSerialId()!=null){
				paramList.add(new Object[]{
						DaoQueryOperator.EQ,"entryTypeInfo.id",model.getSerialId()
					});
			}
			if(model.getGlobal_guid()!=null){
				paramList.add(new Object[]{
						DaoQueryOperator.EQ,"global_guid",model.getGlobal_guid()
					});
			}
			if(StringUtils.isNotEmpty(title)){
				paramList.add(new Object[]{
					DaoQueryOperator.LIKE,"title",title	
					}
				);
			}
			Object[][] paramObj = null;
			if (paramList.size() > 0) {
				paramObj = new Object[paramList.size()][3];
				for (int i = 0; i < paramList.size(); i++) {
					paramObj[i] = paramList.get(i);
				}
			}
			List<EntryInfo> entryInfos = (List<EntryInfo>)baseDao.query(null, false,
					EntryInfo.class, paramObj, null, new String[]{"id"}, (page-1)*rows, rows);
			total = baseDao.count(null, false, EntryInfo.class, paramObj);		
			entryModels = new ArrayList<EntryResp>();
			for (int i = 0; i < entryInfos.size(); i++) {
					EntryInfo entryInfo = entryInfos.get(i);
					EntryResp entryResp= new EntryResp();
					entryResp = parseModel(entryInfo,entryResp);			
					entryModels.add(entryResp);
			}
		}
		
		entryModels = sortList(entryModels);
		return new PageDataModel((int)total, entryModels,PageDataModel.SUCCESS_CODE,PageDataModel.SUCCESS_MSG);
	}
	
	
	/**
	 * 给图书列表按关键字rankNumber升序排序
	 *
	 * @author lvchao
	 * @createtime 2018年4月19日 下午2:05:03
	 *
	 * @param list
	 * @return
	 */
	public List<EntryResp> sortList(List<EntryResp> list) {
		Collections.sort(list, new Comparator<EntryResp>() {
			
			@Override
			public int compare(EntryResp e1, EntryResp e2) {
				return e1.getRankNumber() - e2.getRankNumber();
			}
		});
		return list;
	}
	

	private EntryResp parseModel(EntryInfo entryInfo,EntryResp entryModel) {
		entryModel.setId(entryInfo.getId());
		entryModel.setTitle(entryInfo.getTitle());
		entryModel.setShortName(entryInfo.getShortName());
		entryModel.setAuthor(entryInfo.getAuthor());
		entryModel.setDesc(entryInfo.getDescription());
		entryModel.setEditor(entryInfo.getEditor());
		entryModel.setOriginalAuthor(entryInfo.getOriginalAuthor());
		entryModel.setIsPrize(entryInfo.getIsPrize());
		entryModel.setGlobal_guid(entryInfo.getGlobal_guid());
		entryModel.setHeight(entryInfo.getHeight());
		entryModel.setWidth(entryInfo.getWidth()); 
		entryModel.setPubOrg(entryInfo.getPubOrg());//出版机构
		entryModel.setPageCount(entryInfo.getPageCount());//页数
		entryModel.setUid(entryInfo.getUid());
		entryModel.setFormatType(entryInfo.getFormatType()); //音频，文本
		String years = "";
		Integer yearsId = entryInfo.getYearsId();
		switch (yearsId) {
		case 1:
			years = "古代";
			break;
		case 2:
			years = "近现代";
			break;
		case 3:
			years = "近当代";
			break;
		default:
			break;
		}
		entryModel.setYears(years);//年代
		entryModel.setEditionType(entryInfo.getEditionType()); //编辑类型
		entryModel.setAddTime(entryInfo.getAddTime());
		entryModel.setModifyTime(entryInfo.getModifyTime());
		entryModel.setRankNumber(entryInfo.getRankNumber());
		
		//封面海报信息
		if(entryInfo.getCoverImageId()!=null){
			EntryImageInfo entryImageInfo = baseDao.get(EntryImageInfo.class,entryInfo.getCoverImageId());
			if(entryImageInfo!=null){
				String coverImageUrl= FileOperation.getImagePath(entryImageInfo.getUniqueName());
				entryModel.setCoverImageUrl(coverImageUrl);	
			}
		}
		//小海报信息
		if(entryInfo.getCoverThumbNailId()!=null){
			EntryImageInfo entryImageInfo = baseDao.get(EntryImageInfo.class, entryInfo.getCoverThumbNailId());
			if(entryImageInfo!=null){
				String coverThumbNail = FileOperation.getImagePath(entryImageInfo.getUniqueName());
				entryModel.setCoverThumbNailUrl(coverThumbNail);
			}
		}
		//语种信息
		if(entryInfo.getAngleInfo()!=null){
			entryModel.setLangName(entryInfo.getAngleInfo().getName());
		}
		//系列（套书信息）
		if(entryInfo.getEntryTypeInfo()!=null){
			entryModel.setSeriesName(entryInfo.getEntryTypeInfo().getTypeName());
		}
		//内容提供商信息
		if(entryInfo.getAppInfo()!=null){
			entryModel.setCpName(entryInfo.getAppInfo().getAppName());
		}
		//分类信息
		List<EntryToNode> entryToNodes  = entryInfo.getEntryToNodeList();
		StringBuffer nameBuffers = new StringBuffer();
		StringBuffer idBuffers = new StringBuffer();
		for(EntryToNode entry:entryToNodes){
			NodeInfo nodeInfo = entry.getNodeInfo();		
			idBuffers.append(","+nodeInfo.getId());
			nameBuffers.append(" "+nodeInfo.getNodeName());				
		}
		String typeIds = idBuffers.toString();
		if(typeIds.endsWith(",")){
			typeIds = typeIds.substring(0,typeIds.indexOf(typeIds.lastIndexOf(",")));
		}
		String typeNames = nameBuffers.toString();
		if(typeNames.endsWith(",")){
			typeNames = typeNames.substring(0,typeNames.indexOf(typeNames.lastIndexOf(",")));
		}
		entryModel.setCategoryNames(typeNames);
		//附加信息
		if(entryInfo.getEntryAttachInfo()!=null){
			entryModel.setAuditStatus(entryInfo.getEntryAttachInfo().getAuditStatus());
			entryModel.setBrowseCount(entryInfo.getEntryAttachInfo().getBrowseCount());
			entryModel.setDownloadCount(entryInfo.getEntryAttachInfo().getDownloadCount());
		}
		return entryModel;
	}
	
	
	private void parseDetailContent(EntryInfo entryInfo, EntryDetailResp entryDetailResp) {
		//------------------------ 属于图书的内容信息------------------
		//图书音频信息
		EntryAudioInfo entryAudioInfo = entryInfo.getEntryAudio();
		List<EntryAudioModel> entryAudioModels = new ArrayList<EntryAudioModel>();
		if(entryAudioInfo!=null){
			EntryAudioModel entryAudioModel = new EntryAudioModel();
			EntryAudioModel.parseEntityToModel(entryAudioInfo, entryAudioModel);
			entryAudioModels.add(entryAudioModel);
		}
		entryDetailResp.setAudioModels(entryAudioModels);
		//图书文件信息
		EntryFileInfo entryFileInfo = entryInfo.getEntryFile();
		if(entryFileInfo!=null){
			EntryFileModel entryFileModel = new EntryFileModel();
			EntryFileModel.parseEntityToModel(entryFileInfo, entryFileModel);
			entryDetailResp.setContentModel(entryFileModel);
		}
		//图书画册信息
		List<EntryImageInfo> entryAlbumInfos = entryInfo.getEntryImageList();
		List<EntryImageModel> entryImageModels = new ArrayList<EntryImageModel>();
		for(EntryImageInfo entryImageInfo:entryAlbumInfos){
			if(entryImageInfo.getPosition()==1){	//去掉封面图片
				EntryImageModel entryImageModel = new EntryImageModel();
				EntryImageModel.parseEntityToModel(entryImageInfo, entryImageModel);
				entryImageModels.add(entryImageModel);	
			}
		}
		entryDetailResp.setImageModels(entryImageModels);
		//获取content
		if(StringUtils.isNotEmpty(entryInfo.getContent())){
			entryDetailResp.setContent(entryInfo.getContent().replace("\n", "<br>"));	
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public EntryDetailResp getEntryDetail(EntryReq model) {
		EntryDetailResp entryDetailResp = new EntryDetailResp();
		List<Bookmark> marks = (List<Bookmark>) baseDao.query("from Bookmark b where b.stbID='" + model.getStbID() + "' and b.entryId=" + model.getId());
		if(marks.size() == 1) {//带上书签信息
			entryDetailResp.setBookmark(marks.get(0));
		}
		
		EntryInfo entryInfo = baseDao.get(EntryInfo.class, model.getId());
		if(entryInfo!=null){
			EntryResp entryResp= new EntryResp();
			entryResp = parseModel(entryInfo,entryResp);
			try {
				BeanUtils.copyProperties(entryDetailResp, entryResp);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			parseDetailContent(entryInfo,entryDetailResp); 
		}
		return entryDetailResp;
	}

	
	@Override
	public EntryAlbumModel getEntryAlbums(Integer entryId) {
		EntryInfo entryInfo = baseDao.get(EntryInfo.class,entryId);
		List<EntryImageInfo> entryImageInfos = entryInfo.getEntryImageList();
		List<EntryImageModel> entryImageModels = new ArrayList<EntryImageModel>();
		EntryAlbumModel entryAlbumModel = new EntryAlbumModel();
		for(EntryImageInfo entryImageInfo:entryImageInfos){
			EntryImageModel entryImageModel = new EntryImageModel();
			entryImageModel.setId(entryImageInfo.getId());
			if(entryImageInfo.getUniqueName()!=null){
				entryImageModel.setUniqueName(entryImageInfo.getUniqueName());
				entryImageModel.setImagePath(FileOperation.getImagePath(entryImageInfo.getUniqueName()));	
			}
			entryImageModels.add(entryImageModel);
		}
		entryAlbumModel.setEntryImages(entryImageModels);
		return entryAlbumModel;
	}
	
}
