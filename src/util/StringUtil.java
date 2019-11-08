package util;

public class StringUtil {//信息的比较
	public static boolean isEmpty(String str){
		if("".equals(str)|| str == null){
			return true;
		}
		return false;
	}
}
