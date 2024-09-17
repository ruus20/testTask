package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.services.VacationPayForPeriodService;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class VacationPayForPeriodServiceTest {

	@InjectMocks
	private VacationPayForPeriodService vacationPayForPeriodService;

	@Test
	public void testCalculateVacationPay() throws FileNotFoundException {
		double averageSalary = 30000;
		int vacationDays = 14;
		String startDate = "2024-06-10";
		Double result = vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
		assertEquals(9215.02, result);
	}

	@Test
	public void testIsHoliday() {
		LocalDate holidayDate = LocalDate.of(2024, 1, 1);
		boolean result = vacationPayForPeriodService.isHoliday(holidayDate);
		assertEquals(true, result);
	}

	@Test
	public void testCalculateVacationPay_ZeroVacationDays() throws FileNotFoundException {
		double averageSalary = 30000;
		int vacationDays = 0;
		String startDate = "2024-06-10";
		Double result = vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
		assertEquals(0.0, result);
	}

	@Test
	public void testCalculateVacationPay_AllWeekendsAndHolidays() throws FileNotFoundException {
		double averageSalary = 30000;
		int vacationDays = 8;
		String startDate = "2024-01-01";
		Double result = vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
		assertEquals(0.0, result);
	}

	@Test
	public void testIsHoliday_False() {
		LocalDate date = LocalDate.of(2024, 3, 15);
		boolean result = vacationPayForPeriodService.isHoliday(date);
		assertEquals(false, result);
	}

	@Test
	public void testCalculateVacationPay_WorkingDaysOnly() throws FileNotFoundException {
		double averageSalary = 30000;
		int vacationDays = 5;
		String startDate = "2024-03-18";
		Double result = vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
		assertEquals(5119.45, result);
	}
}
