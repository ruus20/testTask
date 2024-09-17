package com.example.demo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.config.HolidayConfig;
import com.example.demo.services.VacationPayForPeriodService;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VacationPayForPeriodServiceTest {

    @Mock
    private HolidayConfig holidayConfig;

    @InjectMocks
    private VacationPayForPeriodService vacationPayForPeriodService;

    @Test
    public void testCalculateVacationPay() throws FileNotFoundException {
        double averageSalary = 30000;
        int vacationDays = 14;
        String startDate = "2024-06-10";
        List<String> holidays = Arrays.asList("06-12");
        Mockito.when(holidayConfig.getHolidayDates()).thenReturn(holidays);
        Double result = vacationPayForPeriodService.calculateVacationPay(averageSalary, vacationDays, startDate);
        assertEquals(9.0,result);
   }

    @Test
    public void testIsHoliday() {
        LocalDate holidayDate = LocalDate.of(2024, 1, 1);
        List<String> holidays = Arrays.asList("01-01", "01-02","01-03", "01-04","01-05", "01-06","01-07");
        Mockito.when(holidayConfig.getHolidayDates()).thenReturn(holidays);

        boolean result = vacationPayForPeriodService.isHoliday(holidayDate);

        assertEquals(true, result); 
    }
}
