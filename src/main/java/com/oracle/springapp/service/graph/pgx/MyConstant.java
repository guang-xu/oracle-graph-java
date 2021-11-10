package com.oracle.springapp.service.graph.pgx;

import java.util.HashMap;
import java.util.Map;

public class MyConstant {
	public static Map<String,Integer> parameter = new HashMap<String,Integer>(){
		private static final long serialVersionUID = 1L;
		{
			put("785240",	1511);
			put("785397",	1755);
			put("785240",	1537);
			put("785240",	1530);
			put("785734",	1770);
			put("786835",	1533);
			put("785227",	1764);
			put("785240",	1572);
			put("785240",	1551);
			put("785239",	1731);
			put("785240",	1561);
			put("785240",	1760);
			put("784965",	1752);
			put("785240",	1542);
			put("785240",	1773);
			put("785240",	1778);
			put("785240",	1535);
			put("785240",	1781);
			put("785571",	1736);
			put("785240",	1512);
			put("785240",	1548);
			put("785240",	1776);
			put("785240",	1790);
			put("785233",	1524);
			put("785239",	1743);
			put("785240",	1767);
			put("785240",	1571);
			put("785233",	1519);
			put("785240",	1531);
			put("785240",	1756);
			put("785909",	1528);
			put("787402",	1744);
			put("785240",	1557);
			put("785232",	1754);
			put("785299",	1536);
			put("785239",	1748);
			put("785240",	1775);
			put("785240",	1780);
			put("785470",	1534);
			put("785716",	1763);
			put("785240",	1771);
			put("787402",	1794);
			put("784965",	1765);
			put("785470",	1527);
			put("785571",	1749);
			put("785240",	1783);
			put("785240",	1777);
			put("788651",	1750);
		}
	};
	
	public static final String SELECT_QUERY_PARAM2= "SELECT wv.SKU_CODE as warehouse_sku_code, "
			+ "       wv.WAREHOUSE_NAME   as warehouse_name, "
			+ "       wv.QTY_PREOUT       as warehouse_QTY_PREOUT, "
			+ "       wv.QTY_PREIN        as warehouse_QTY_PREIN, "
			+ "       wv.QTY_STORAGE      as warehouse_QTY_STORAGE, "
			+ "       wv.QTY_SHARE        as warehouse_QTY_SHARE, "
			+ "       wv.QTY_AVAILABLE    as warehouse_QTY_AVAILABLE, "
			+ "       e.RATIO             as E1_RATIO, "
			+ "       sv.SKU_CODE         as sharestorage_SKU_CODE, "
			+ "       sv.SHARE_STORE_ID   as sharestorage_SHARE_STORE_ID, "
			+ "       sv.SHARE_STORE_NAME as sharestorage_SHARE_STORE_NAME, "
			+ "       sv.QTY_PREOUT       as sharestorage_QTY_PREOUT, "
			+ "       sv.QTY_PREIN        as sharestorage_QTY_PREIN, "
			+ "       sv.QTY_STORAGE      as sharestorage_QTY_STORAGE, "
			+ "       e1.RATIO            as E2_RATIO, "
			+ "       cv.SKU_CODE         as channelstorage_SKU_CODE, "
			+ "       cv.CHANNEL_ID       as channelstorage_CHANNEL_ID, "
			+ "       cv.CHANNEL_NAME     as channelstorage_CHANNEL_NAME, "
			+ "       cv.QTY_PREOUT       as channelstorage_QTY_PREOUT, "
			+ "       cv.QTY_PREIN        as channelstorage_QTY_PREIN, "
			+ "       cv.QTY_STORAGE      as channelstorage_QTY_STORAGE "
			+ "  FROM MATCH (wv:WAREHOUSE_SKU )-[e:SUPPLY ]->(sv:SHARE_STORAGE_SKU )-[e1:SUPPLY ]->(cv:CHANNEL_STORAGE_SKU ) "
		//	+ "WHERE wv.SKU_CODE = :skuCode"
		//	+ "and  cv.CHANNEL_ID = :channelId";
	+ "WHERE wv.SKU_CODE = ?"
	+ "and  cv.CHANNEL_ID = ?";
	
	
	public static final String SELECT_QUERY_PARAM1 = "SELECT wv.SKU_CODE as warehouse_sku_code, "
			+ "       wv.WAREHOUSE_NAME   as warehouse_name, "
			+ "       wv.QTY_PREOUT       as warehouse_QTY_PREOUT, "
			+ "       wv.QTY_PREIN        as warehouse_QTY_PREIN, "
			+ "       wv.QTY_STORAGE      as warehouse_QTY_STORAGE, "
			+ "       wv.QTY_SHARE        as warehouse_QTY_SHARE, "
			+ "       wv.QTY_AVAILABLE    as warehouse_QTY_AVAILABLE, "
			+ "       e.RATIO             as E1_RATIO, "
			+ "       sv.SKU_CODE         as sharestorage_SKU_CODE, "
			+ "       sv.SHARE_STORE_ID   as sharestorage_SHARE_STORE_ID, "
			+ "       sv.SHARE_STORE_NAME as sharestorage_SHARE_STORE_NAME, "
			+ "       sv.QTY_PREOUT       as sharestorage_QTY_PREOUT, "
			+ "       sv.QTY_PREIN        as sharestorage_QTY_PREIN, "
			+ "       sv.QTY_STORAGE      as sharestorage_QTY_STORAGE, "
			+ "       e1.RATIO            as E2_RATIO, "
			+ "       cv.SKU_CODE         as channelstorage_SKU_CODE, "
			+ "       cv.CHANNEL_ID       as channelstorage_CHANNEL_ID, "
			+ "       cv.CHANNEL_NAME     as channelstorage_CHANNEL_NAME, "
			+ "       cv.QTY_PREOUT       as channelstorage_QTY_PREOUT, "
			+ "       cv.QTY_PREIN        as channelstorage_QTY_PREIN, "
			+ "       cv.QTY_STORAGE      as channelstorage_QTY_STORAGE "
			+ "  FROM MATCH (wv:WAREHOUSE_SKU )-[e:SUPPLY ]->(sv:SHARE_STORAGE_SKU )-[e1:SUPPLY ]->(cv:CHANNEL_STORAGE_SKU ) "
			+ "WHERE wv.SKU_CODE = ?";
	
	
	public static final String UPDATE_GRAPH_CHANNEL_STORAGE = "     UPDATE v3 SET (v3.QTY_STORAGE = :QTY_STORAGE) "
											 + " FROM MATCH  (v3:CHANNEL_STORAGE_SKU) "
											 + "      WHERE v3.SKU_CODE = :SKU_CODE ";
}
