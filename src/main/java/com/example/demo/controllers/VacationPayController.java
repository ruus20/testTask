package com.example.demo.controllers;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.VacationPay;
import com.example.demo.services.VacationPayForPeriodService;

@RestController
public class VacationPayController {
	private final VacationPayForPeriodService vacationPayForPeriodService;

	public VacationPayController(VacationPayForPeriodService vacationPayForPeriodService) {
		this.vacationPayForPeriodService = vacationPayForPeriodService;
	}

	@GetMapping("/calculate")
	public ResponseEntity<Double> calculateVacationPay(@RequestParam double averageSalary,
			@RequestParam int vacationDays, @RequestParam(required = false) String startDate) throws FileNotFoundException {
		try {
			double vacationPay = startDate == null
					? vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays)
					: vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
			return ResponseEntity.ok(vacationPay);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
