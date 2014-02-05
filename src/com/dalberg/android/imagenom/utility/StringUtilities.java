package com.dalberg.android.imagenom.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUtilities {
	
	public static String makeUrlEncoded(String input) throws UnsupportedEncodingException{
		return URLEncoder.encode(input, "UTF-8");
	}

}
