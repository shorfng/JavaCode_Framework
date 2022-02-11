package com.loto.injector.Injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;

import java.util.List;

/**
 * <p>Author：蓝田_Loto</p>
 * <p>Date：2021-02-23 15:39</p>
 * <p>PageName：MySqlInjector.java</p>
 * Function：自定义sql注入器
 */

public class MySqlInjector extends DefaultSqlInjector {
	@Override
	public List<AbstractMethod> getMethodList() {
		List<AbstractMethod> methodList = super.getMethodList();

		// 扩充自定义方法
		methodList.add(new FindAll());

		return methodList;
	}
}