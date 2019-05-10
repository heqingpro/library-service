package com.ipanel.web.book.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ipanel.web.book.controller.resp.BaseResp;
import com.ipanel.web.book.elasticsearch.ElasticsearchHelper;
import com.ipanel.web.book.pageModel.EntryDetailResp;
import com.ipanel.web.book.pageModel.EntryReq;
import com.ipanel.web.book.service.IEntryService;
import com.ipanel.web.common.model.PageDataModel;
import com.ipanel.webapp.framework.util.Log;

@Controller
@RequestMapping("/book")
public class EntryController {

	private final String TAG = "EntryController";

	@Autowired
	private IEntryService entryService;
	
	@Autowired
	ElasticsearchHelper elasticsearchHelper;
	
	
	
	/**
	 * 使用搜索引擎实现智能搜索，不带分页（返回全部）
	 *
	 * @author lvchao
	 * @createtime 2018年4月17日 上午10:44:19
	 *
	 * @param searchContent
	 * @return
	 */
	@RequestMapping("/search/{content}")
	@ResponseBody
	public List<Map<String, Object>> searchData(@PathVariable String content) {
		String searchCondition = "title="+content+",author="+content+",original_author="+content+",editor="+content+",description="+content;
		List<Map<String, Object>> listData = elasticsearchHelper.searchListData("library_book_index", "library_book_type",null, searchCondition);
		return listData;
	}
	

	/**
	 * 获取图书列表，带分页
	 * 
	 * @author fangg 2017年5月13日 下午4:59:36
	 * @param session
	 * @param model
	 * @param nodeId
	 * @return
	 */
	@RequestMapping("/listBooks")
	@ResponseBody
	public PageDataModel queryEntryList(EntryReq model) {
		try {
			Log.i(TAG, "*** queryEntryList enter:req=" + model.toString());

			return entryService.queryEntryList(model);
		} catch (Exception e) {
			Log.e(TAG, "*** queryEntryList throw Exception:" + e);
			e.printStackTrace();
			return null;
		}
	}
	

	@RequestMapping("/getBookDetail")
	@ResponseBody
	public EntryDetailResp getEntryDetail(EntryReq model) {
		try {
			Log.i(TAG, "*** getEntryDetail enter,entryId=" + model.getId());
			return entryService.getEntryDetail(model);
		} catch (Exception e) {
			Log.e(TAG, "*** getEntryDetail throw Exception:" + e);
			return null;
		}
	}
	
	
	@RequestMapping("/setBookmark")
	@ResponseBody
	public BaseResp setBookmark(EntryReq model) {
		try {
			Log.i(TAG, "*** setBookmark enter,entryId=" + model.getId());
			if(entryService.setBookmark(model)) {
				return new BaseResp(0, "success");
			} else {
				return new BaseResp(1, "failed");
			}
		} catch (Exception e) {
			Log.e(TAG, "*** setBookmark throw Exception:" + e);
			e.printStackTrace();
			return new BaseResp(1, "failed");
		}
	}

}
