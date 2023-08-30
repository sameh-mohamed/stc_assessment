package com.stc.demo.util;

import java.util.Date;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;

@Component
public class UtilImp {

	public Date getNowDateUTC() {
		TimeZone timeZone = TimeZone.getTimeZone("UTC");
		DateTimeZone dtZone = DateTimeZone.forTimeZone(timeZone);
		DateTime dtus = new DateTime().withZone(dtZone);
		return dtus.toLocalDateTime().toDate();
	}
}
