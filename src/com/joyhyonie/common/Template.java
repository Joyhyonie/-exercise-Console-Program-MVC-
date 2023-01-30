package com.joyhyonie.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory; // Libraries에 설정해ㅑㅇ 임포트 가능
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {

	private static SqlSessionFactory sqlSessionFactory; 
	
	public static SqlSession getSqlSession() {
		
		if(sqlSessionFactory == null) { 
			String resource = "mybatis-config.xml";
			
			try {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return sqlSessionFactory.openSession(false);
	}
}
