package mobilepad.io.util;

/**
 * Utility class for concatenating string using string builder
 */
public class StringConcat
{
	private static final int DEFAULT_CAPACITY = 1024;


	/**
	 * concatenate the strings
	 * @param args the strings
	 * @return concatenated string
	 */
	public static String concat(String... args){
		StringBuilder builder  = new StringBuilder(DEFAULT_CAPACITY);
		for(String s : args)
			builder.append(s);
		return builder.toString();
	}
}
