package com.bellevie.nestmp.db.dao;

import java.util.Date;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bellevie.nestmp.db.dto.DbTime;

public interface DbTimeMapper {
	DbTime selectDbTime();
	String selectTotalDateStr();
	String selectYesterdayStr();
	Date selectDateByAddYear(Integer addYear);
	Date selectDateByAddMonth(Integer addMonth);
	Date selectDateByAddDay(Integer addDay);
	String selectTomorrowStrByFormat(String format);
	String selectTotalDateStrByFormat(String format);
	String selectMonthLaterStrByFormat(@Param("now") String now, @Param("month") String month, @Param("format") String format);
}
