package com.wsmarket.wsmarketbackend.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QueryParamsUtil {
	public static List<Long> decodeListBySeparator(String list,String separator) {
		String[] splitedList = list.split(separator);

		return Arrays.asList(splitedList)
			.stream()
			.map(Long::parseLong)
			.collect(Collectors.toList());
	}

	public static String decodeQueryParam(String queryParam) {
		try {
			return URLDecoder.decode(queryParam, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
