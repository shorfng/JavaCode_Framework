package com.loto.metaobjecthandler.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 16:54</p>
 * <p>PageName：MyMetaObjectHandler.java</p>
 * Function：自动填充规则
 */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		Object version = getFieldValByName("version", metaObject);
		if (null == version) {
			// 如果属性 version 为空，则进行填充为2
			setFieldValByName("version", 2, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
	}
}

