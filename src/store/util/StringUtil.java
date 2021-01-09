package store.util;

/**
 * 处理字符串工具类
 * 
 * @author
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否不为空	
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 *随机生成6位数
	 * @return
	 */
	public static int getRandom(){
		return (int)(Math.random()*9+1)*100000;
	}

}
