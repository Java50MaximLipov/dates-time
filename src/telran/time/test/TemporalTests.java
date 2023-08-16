package telran.time.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
//import java.time.*;
//import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
//
import org.junit.jupiter.api.Test;

import telran.time.BarMizvaAdjuster;
import telran.time.NextFriday13;
//
//import telran.time.BarMizvaAdjuster;

class TemporalTests {

//	@Test
//	@Deprecated
//	void test() {
//		LocalDate birthDateAS = /* LocalDate.of(1799, 6, 6); */ LocalDate.parse("1799-06-06");
//		LocalDate barMizvaASDate = birthDateAS.plusYears(13);
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMMM, YYYY/d EEEE");
//		System.out.printf("Birthday of AS is %s; Bar Mizva of AS is %s\n", birthDateAS.format(dtf),
//				barMizvaASDate.format(dtf));
//		ChronoUnit unit = ChronoUnit.DAYS;
//		System.out.printf("Number of %s between %s and %s is %d\n", unit, birthDateAS, barMizvaASDate,
//				unit.between(birthDateAS, barMizvaASDate));
//	}

//	@Test
//	void barMizvaAdjusterTest() {
//		TemporalAdjuster adjuster = new BarMizvaAdjuster();
//		LocalDateTime ldt = LocalDateTime.of(2000, 1, 1, 0, 0);
//		LocalDateTime expected = LocalDateTime.of(2013, 1, 1, 0, 0);
//		assertEquals(expected, ldt.with(new BarMizvaAdjuster()));
//		assertThrowsExactly(UnsupportedTemporalTypeExceptiont.class, () -> LocalTime.now().with(adjuster));

	@Test
	void nextFridayAdjuster() {
		TemporalAdjuster adjuster = new NextFriday13();
		LocalDate ld = LocalDate.of(2023, 8, 15);
		LocalDate expected1 = LocalDate.of(2023, 10, 13);
		LocalDate expected2 = LocalDate.of(2024, 9, 13);
		assertEquals(expected1, ld.with(adjuster));
		assertEquals(expected2, expected1.with(adjuster));
	}
}
