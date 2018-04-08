package com.wyl.github.rxjavaretrofitokhttp.net;


import java.util.Map;
import java.util.Random;


public class HeaderUtils {
	public static Map<String, String> generalHeaders() {
		return null;
	}

	/**
	 * 获取唯一序列号,格式 产品ID+UID+UNIX时间戳+6位随机数 产品id = KHKJARD
	 * @return
     */
	public static String getSeqnum(){
	return "";
	}

	/**
	 * 获取num位随机数
	 * @param num
	 * @return
     */
	public static String getRandom(int num){
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ;i <num; i++){
			sb.append(random.nextInt(9));
		}
		return  sb.toString();
	}
}
