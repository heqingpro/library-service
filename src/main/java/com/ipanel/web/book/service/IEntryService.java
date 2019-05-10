package com.ipanel.web.book.service;

import com.ipanel.web.book.pageModel.EntryAlbumModel;
import com.ipanel.web.book.pageModel.EntryDetailResp;
import com.ipanel.web.book.pageModel.EntryReq;
import com.ipanel.web.common.model.PageDataModel;

public interface IEntryService {

	 PageDataModel queryEntryList(EntryReq model);
	
	 EntryDetailResp getEntryDetail(EntryReq model);

	 EntryAlbumModel getEntryAlbums(Integer entryId);

	boolean setBookmark(EntryReq model);

}
