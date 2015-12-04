package com.lzq.commons.rand;

import java.util.Random;

/**
 * 
 * @author zhongqiu
 * @date 2015年10月30日
 * @time 下午7:47:36
 */
public class RandomGenerator {

	private static Random random = new Random();
	
	public static String getRandomStringByLength(int length){
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
	}
}
