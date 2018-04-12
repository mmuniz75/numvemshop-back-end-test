import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;

public class TimeExpression {

    public static TimeExpression on(LocalDate aDate) {
        throw new RuntimeException("Must implement method: on");
    }

    public static TimeExpression dailyEveryFromOnwards(int anAmountOfDays, LocalDate aDate) {
        throw new RuntimeException("Must implement method: dailyEveryFromTo");
    }

    public static TimeExpression monthlyEveryOnFromOnwards(int anAmountOfMonths, int aDayInMonth, YearMonth anYear) {
        throw new RuntimeException("Must implement method: monthlyEveryOnFromTo");
    }

    public static TimeExpression monthlyEveryOnOfFromOnwards(int anAmountOfMonths, DayOfWeek aDayOfWeek, int aWeekInMonth, YearMonth anYear) {
        throw new RuntimeException("Must implement method: monthlyEveryOnOfFromTo");
    }

    public static TimeExpression yearlyEveryOnFromOnwards(int anAmountOfYears, MonthDay aMonthDay, int anYear) {
        throw new RuntimeException("Must implement method: yearlyEveryOnFromOnwards");
    }

    public boolean isRecurringOn(LocalDate aDate) {
        return false;
    }
}
