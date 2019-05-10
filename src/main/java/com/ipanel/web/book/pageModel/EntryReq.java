package com.ipanel.web.book.pageModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图书数据模型
 * @author fangg
 * 2017年5月10日 上午11:40:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryReq {
	
	private Integer page = 1;
	
	private Integer rows = 10;	
	
	private Integer id; //图书唯一id

	private String title;//图书名称
	
	private Integer yearsId; //  年代id: 1古代 2近现代 3现当代
	
	private String auditStatus;//状态   （分类状态：0未审核 1已审核）
	
	private String uid;//图书第三方id 
	
	private String global_guid;//图书唯一编码 
	
	private Integer langId ;// 语种id
	
	private String langName;//图书绑定语种
	
	private Integer cpId; //所属的内容提供商Id(appId这个单词获取不到参数值)
	
	private String cpName; //所属的内容提供商名称
	
	private Integer serialId;// 所属的图书套书信息
	
	private String  serialName;// 所属的图书套书信息
	
	private Integer catId; // 所属的分类id集合
	
	private Integer catName; //所属的分类名称集合
	
	private Integer rankNumber;// 排序序号
	
	private Integer operation; // -1是降一位，1是升一位
	
	private String stbID; //用户唯一标识
	
	private Integer flag; //用于区分文字图书 还是 音频图书，0是文字，1是音频
	
	private Integer fontSize; //文字图书的字体大小
	
	private Integer totalPages; //文字图书总页数
	
	private Integer currentPage; //文字图书当前阅读页码
	
	private Integer totalSeconds; //音频图书总时长，单位秒
	
	private Integer currentSeconds; //音频图书当前阅读时间点，单位秒

}
