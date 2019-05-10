package com.ipanel.web.utils;

import java.util.HashMap;
import java.util.Map;

import cn.ipanel.util.properties.PropertiesUtil;

public class Constants {

	public static String ELASTICSEARCH_HOST=PropertiesUtil.getValue("config.properties", "elasticsearch.host", "127.0.0.1");
	public static String ELASTICSEARCH_PORT=PropertiesUtil.getValue("config.properties", "elasticsearch.port=", "9300");
	public static String ELASTICSEARCH_CLUSTER_NAME=PropertiesUtil.getValue("config.properties", "elasticsearch.cluster.name", "migration.ac.cn");
	

	public static String FILE_SERVER_IP=PropertiesUtil.getValue("config.properties", "fileServerIp", "127.0.0.1");
	
	public static String FILE_SERVER_PORT=PropertiesUtil.getValue("config.properties", "fileServerPort", "80");
	
	public static String FILE_SERVER_ROOT_PATH=PropertiesUtil.getValue("config.properties", "fileServerRootPath", "usr/local/nginx/html");
	
	public static String BOOK_IMAGE_PATH=PropertiesUtil.getValue("config.properties", "libraryImageDir", "libaray/libraryImages");
	
	public static String BOOK_ALBUM_PATH=PropertiesUtil.getValue("config.properties", "libraryAlbumDir", "libaray/libraryAlbums");
	
	public static String BOOK_TEXT_PATH=PropertiesUtil.getValue("config.properties", "libraryTextDir", "libaray/libraryTexts");
	
	public static String BOOK_AUDIO_PATH=PropertiesUtil.getValue("config.properties", "libraryAudioDir", "libaray/libraryAudios");
	
	//系统路径的分隔符
	public static String SYSTEM_FILE_SEPARATOR_TAG=System.getProperty("file.separator");
	
	//判断输入的是否是数字
	public static final String REGIX_ALL_NUMBER="^[0-9]*[1-9][0-9]*$";
	
	public static final String SESSION_USER_ID = "session_user_id";

	public static final String SESSION_USER_NAME = "session_user_name";

	public static final String SESSION_USER_TYPE = "session_user_type";

	public static final String USER_TYPE_SUPER_ADMIN = "superAdmin";

	public static final String USER_TYPE_NORMAL_ADMIN = "admin";
	
	public static final String SESSION_USER = "session_user";
	
	public static final String TEMP_DIR = "tempFile";//存放临时文件的目录

	public static Map<String, String> USER_TYPE_MAP = null;
	static {
		USER_TYPE_MAP = new HashMap<String, String>();
		USER_TYPE_MAP.put(USER_TYPE_SUPER_ADMIN, "超级管理员");
		USER_TYPE_MAP.put(USER_TYPE_NORMAL_ADMIN, "普通管理员");
	}

}
