package com.app.module.common;

import java.util.Date;

public interface IDateUtilService {
	Date addMinuteIntoDate(Date date, Integer addMinuteTime);

	int compareDate(Date date1, Date date2);
}
