package com.oracle.springapp.util;

public class Utils {
	
	public static String replaceUrlEscape(String content) {
		String result = content;
		result = result.replace("&lt;", "<");
		result = result.replace("&quot;", "\"");
		result = result.replace("&gt;", ">");
		result = result.replace("<br>", "\n");
		result = result.replace("\r", " ");
		result = result.replace("\n", " ");
		return result;
	}
	
	public static void main(String args[]) {
		String aa= "SELECT wv.SKU_CODE as warehouse_sku_code,\r\n"
				+ "       wv.WAREHOUSE_NAME   as warehouse_name,\r\n"
				+ "       wv.QTY_PREOUT       as warehouse_QTY_PREOUT,\r\n"
				+ "       wv.QTY_PREIN        as warehouse_QTY_PREIN,\r\n"
				+ "       wv.QTY_STORAGE      as warehouse_QTY_STORAGE,\r\n"
				+ "       wv.QTY_SHARE        as warehouse_QTY_SHARE,\r\n"
				+ "       wv.QTY_AVAILABLE    as warehouse_QTY_AVAILABLE,\r\n"
				+ "       e.RATIO             as E1_RATIO,\r\n"
				+ "       sv.SKU_CODE         as sharestorage_SKU_CODE,\r\n"
				+ "       sv.SHARE_STORE_ID   as sharestorage_SHARE_STORE_ID,\r\n"
				+ "       sv.SHARE_STORE_NAME as sharestorage_SHARE_STORE_NAME,\r\n"
				+ "       sv.QTY_PREOUT       as sharestorage_QTY_PREOUT,\r\n"
				+ "       sv.QTY_PREIN        as sharestorage_QTY_PREIN,\r\n"
				+ "       sv.QTY_STORAGE      as sharestorage_QTY_STORAGE,\r\n"
				+ "       e1.RATIO            as E2_RATIO,\r\n"
				+ "       cv.SKU_CODE         as channelstorage_SKU_CODE,\r\n"
				+ "       cv.CHANNEL_ID       as channelstorage_CHANNEL_ID,\r\n"
				+ "       cv.CHANNEL_NAME     as channelstorage_CHANNEL_NAME,\r\n"
				+ "       cv.QTY_PREOUT       as channelstorage_QTY_PREOUT,\r\n"
				+ "       cv.QTY_PREIN        as channelstorage_QTY_PREIN,\r\n"
				+ "       cv.QTY_STORAGE      as channelstorage_QTY_STORAGE\r\n"
				+ "  FROM MATCH (wv:WAREHOUSE_SKU )-[e:SUPPLY ]-&gt;(sv:SHARE_STORAGE_SKU )-[e1:SUPPLY ]-&gt;(cv:CHANNEL_STORAGE_SKU )\r\n"
				+ "WHERE wv.SKU_CODE = '127323'\r\n"
				+ "and  cv.CHANNEL_ID = 1527";
		System.out.println(replaceUrlEscape(aa));
		//System.out.println(StringEscapeUtils.escapeEcmaScript(aa));
	}
}
