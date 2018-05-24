package mobilepad.io.util;

public class StringConcat
{
	private static final int DEFAULT_CAPACITY = 1024;

	public static String concat(String... args){
		StringBuilder builder  = new StringBuilder(DEFAULT_CAPACITY);
		for(String s : args)
			builder.append(s);
		return builder.toString();
	}
}
