package com.example.demo.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.models.VacationPay;

@Service
public class VacationPayForPeriodService {
	List<String> holidayDates = List.of("01-01", "01-02", "01-03", "01-04", "01-05", "01-06", "01-07", "01-08", "02-23",
			"03-08", "05-01", "05-09", "06-12", "11-04");

	public Double calculateVacationPay(double averageSalary, int vacationDays) {
		double vacationPay = averageSalary / 29.3 * vacationDays;
		vacationPay = Math.round(vacationPay * 100);
		return vacationPay / 100;
	}

	public Double calculateVacationPay(double averageSalary, int vacationDays, String startDate)
			throws FileNotFoundException {
		LocalDate start = LocalDate.parse(startDate);
		LocalDate end = start.plusDays(vacationDays - 1);
		int workDays = 0;
		LocalDate date = start;
		while (!date.isAfter(end)) {
			if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY
					&& !isHoliday(date)) {
				workDays++;
			}
			date = date.plusDays(1);
		}
		double vacationPay = averageSalary / 29.3 * workDays;
		vacationPay = Math.round(vacationPay * 100);
		return vacationPay / 100;
	}

	public boolean isHoliday(LocalDate date) {
		MonthDay monthDay = MonthDay.from(date);
		List<MonthDay> holidays = holidayDates.stream().map(dateStr -> MonthDay
				.of(Integer.parseInt(dateStr.substring(0, 2)), Integer.parseInt(dateStr.substring(3))))
				.collect(Collectors.toList());
		return holidays.contains(monthDay);
	}
}
