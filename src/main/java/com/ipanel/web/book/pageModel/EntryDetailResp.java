package com.ipanel.web.book.pageModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.ipanel.web.entity.Bookmark;

/**
 * @author fangg
 * 2017年8月8日 下午5:04:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryDetailResp {
	

	private Integer id;
	
	private String title;//图书名称

	private String shortName;//长度200限制
	
	private String author;//作者
	
	private String desc;//简介
	
	private String pubOrg;// 出版机构
	
	private String editor; //编者
	
	private String originalAuthor; //原著作者
	
	private String years; //  年代id: 1古代 2近现代 3现当代
	
	private String uid;//图书第三方id 
	
	private String global_guid;//图书唯一编码 
	
	private Integer pageCount; //页数
	
	private Integer width;//宽度
	
	private Integer height;//高度
	
	private Integer formatType; //格式:1无声 2有声,3 txt（三秦）
	
	private String coverImageUrl;//图书封面url
	
	private String coverThumbNailUrl;//图书封面小图url
	
	private Integer editionType;//版本类型
	
	private Integer isPrize;//是否为获奖作品
	
	private String langName;//图书绑定语种
	
	private String cpName; //所属的内容提供商名称
	
	private String seriesName;// 所属的图书套书信息
	
	private String categoryNames; //所属的分类名称集合

	private Integer browseCount;//浏览次数
	
	private String downloadCount;//下载次数
	
	private Integer auditStatus;//状态   （分类状态：0未审核 1已审核）
	
	private String addTime;//添加时间
	
	private String modifyTime; // 更新时间
	
	private String content;//文本类图书的内容
	
	private EntryFileModel contentModel; //文件内容
	
	private Bookmark bookmark; //该书的书签信息
	
	private List<EntryAudioModel> audioModels;  //拥有的音频信息
	
	private List<EntryImageModel> imageModels;  //图片照片集合

}
