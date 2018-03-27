package org.fxf.service;

import java.util.List;

import org.fxf.bean.Dic;

public interface DicService {

	/**
     * 根据类型获取字典表列表
     * @param type 类型
     * @return 字典表列表
     */
	public List<Dic> getListByType(String type);
}
