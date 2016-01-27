package com.songlin.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.songlin.controller.IndexController;
import com.songlin.controller.LoginController;
import com.songlin.model.User;


public class MyAppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
//		loadPropertyFile("datasource.properties");  
		PropKit.use("a_little_config.txt");
		me.setDevMode(PropKit.getBoolean("devMode", false));
		me.setEncoding("utf-8");
		me.setViewType(ViewType.JSP);
		
	}

	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("basePath"));
		
	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
        C3p0Plugin c3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());
        me.add(c3p0Plugin);
        
//        C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),getProperty("user"), getProperty("password").trim());
//        me.add(c3p0Plugin);
		
     // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        me.add(arp);
        arp.addMapping("user_table", User.class); // 映射blog 表到 Blog模型
	}

	@Override
	public void configRoute(Routes me) {
//		me.add("/", IndexController.class);
		me.add("/", LoginController.class);
	}

	
}
