package com.app.module.common.impl;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.app.module.common.IDateUtilService;

@Service
public class DateUtilServiceImpl implements IDateUtilService {

	@Override
	public Date addMinuteIntoDate(Date date, Integer addMinuteTime) {
		return DateUtils.addMinutes(date, addMinuteTime);
	}

	@Override
	public int compareDate(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
}
