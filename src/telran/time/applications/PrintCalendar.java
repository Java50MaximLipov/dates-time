package telran.time.applications;

import java.time.*;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class PrintCalendar {
	private static final int TITLE_OFFSET = 10;
	private static final int WEEK_DAYS_OFFSET = 2;
	private static final int COLUMN_WIDTH = 4;
	private static DayOfWeek[] weekDays = DayOfWeek.values();
	private static Locale LOCALE = Locale.getDefault();

	public static void main(String[] args) {
		try {
			RecordArguments recordArguments = getRecordArguments(args);
			printCalendar(recordArguments);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void printCalendar(RecordArguments recordArguments) {
		printTitle(recordArguments.month(), recordArguments.year());
//	HW-25 Start Section ---------------------
		printWeekDays(recordArguments.firstDay());
		printDays(recordArguments.month(), recordArguments.year(), recordArguments.firstDay());
//	HW-25 Finish Section --------------------
	}

//	HW-25 Start Section ---------------------
	private static void printDays(int month, int year, DayOfWeek startWeekDay) {
		int nDays = getMonthDays(month, year);
		int currentWeekDay = (startWeekDay == DayOfWeek.SUNDAY) ? getFirstMonthWeekDay(month, year) + 1
				: getFirstMonthWeekDay(month, year);
//	HW-25 Finish Section --------------------
		System.out.printf("%s", " ".repeat(getFirstColumnOffset(currentWeekDay)));
		for (int day = 1; day <= nDays; day++) {
			System.out.printf("%4d", day);
			if (currentWeekDay == 7) {
				currentWeekDay = 0;
				System.out.println();
			}
			currentWeekDay++;
		}
	}

	private static int getFirstColumnOffset(int currentWeekDay) {
		return COLUMN_WIDTH * (currentWeekDay - 1);
	}

	private static int getFirstMonthWeekDay(int month, int year) {
		LocalDate ld = LocalDate.of(year, month, 1);
		return ld.get(ChronoField.DAY_OF_WEEK);
	}

	private static int getMonthDays(int month, int year) {
		YearMonth ym = YearMonth.of(year, month);
		return ym.lengthOfMonth();
	}

//	HW-25 Start Section ---------------------
	private static void printWeekDays(DayOfWeek startWeekDay) {
		System.out.printf("%s", " ".repeat(WEEK_DAYS_OFFSET));
		for (DayOfWeek dayWeek : weekDays) {
			System.out.printf("%s ", getCorrectWeekDay(dayWeek, startWeekDay));
		}
		System.out.println();
	}

	private static String getCorrectWeekDay(DayOfWeek dayWeek, DayOfWeek startWeekDay) {
		if (startWeekDay == DayOfWeek.SUNDAY) {
			int index = (dayWeek.getValue() + 6) % 7;
			if (index == 0) {
				index = 7;
			}
			return DayOfWeek.of(index).getDisplayName(TextStyle.SHORT, LOCALE);
		}
		return dayWeek.getDisplayName(TextStyle.SHORT, LOCALE);
	}
//	HW-25 Finish Section --------------------

	private static void printTitle(int month, int year) {
		Month monthEn = Month.of(month);
		System.out.printf("%s%s %d\n", " ".repeat(TITLE_OFFSET), monthEn.getDisplayName(TextStyle.FULL, LOCALE), year);
	}

	private static RecordArguments getRecordArguments(String[] args) throws Exception {
		int month = getMonthArg(args);
		int year = getYearArg(args);
		DayOfWeek dayOfWeek = getFirstDayOfWeek(args);
		return new RecordArguments(month, year, dayOfWeek);
	}

//	HW-25 Start Section ---------------------
	private static DayOfWeek getFirstDayOfWeek(String[] args) throws Exception {
		DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
		if (args.length > 2) {
			try {
				dayOfWeek = DayOfWeek.valueOf(args[2].toUpperCase());
			} catch (IllegalArgumentException e) {
				throw new Exception("Invalid day of week");
			}
		}
		return dayOfWeek;
	}
//	HW-25 Finish Section --------------------

	private static int getYearArg(String[] args) throws Exception {
		int yearRes = LocalDate.now().getYear();
		if (args.length > 1) {
			try {
				yearRes = Integer.parseInt(args[1]);
			} catch (NumberFormatException e) {
				throw new Exception("year must be a number");
			}
		}
		return yearRes;
	}

	private static int getMonthArg(String[] args) throws Exception {
		int monthRes = LocalDate.now().getMonthValue();
		if (args.length > 0) {
			try {
				monthRes = Integer.parseInt(args[0]);
				if (monthRes < 1) {
					throw new Exception("Month value must not be less than 1");
				}
				if (monthRes > 12) {
					throw new Exception("Month value must not be greater than 12");
				}
			} catch (NumberFormatException e) {
				throw new Exception("Month value must be a number");
			}
		}
		return monthRes;
	}

}