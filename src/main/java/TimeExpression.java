import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;

public class TimeExpression {
		
    public static TimeExpression on(LocalDate aDate) {
        return TimeExpressionImpl.on(aDate);
    }

    public static TimeExpression dailyEveryFromOnwards(int anAmountOfDays, LocalDate aDate) {
        return TimeExpressionImpl.dailyEveryFromOnwards(anAmountOfDays, aDate);
    }

    public static TimeExpression monthlyEveryOnFromOnwards(int anAmountOfMonths, int aDayInMonth, YearMonth anYear) {
    	return TimeExpressionImpl.monthlyEveryOnFromOnwards(anAmountOfMonths, aDayInMonth, anYear);
    }

    public static TimeExpression monthlyEveryOnOfFromOnwards(int anAmountOfMonths, DayOfWeek aDayOfWeek, int aWeekInMonth, YearMonth anYear) {
    	return TimeExpressionImpl.monthlyEveryOnOfFromOnwards(anAmountOfMonths, aDayOfWeek, aWeekInMonth, anYear);
    }

    public static TimeExpression yearlyEveryOnFromOnwards(int anAmountOfYears, MonthDay aMonthDay, int anYear) {
    	return TimeExpressionImpl.yearlyEveryOnFromOnwards(anAmountOfYears, aMonthDay, anYear);
    }

    protected boolean isRecurringOn(LocalDate aDate){
    	return false;
    }; 
        
}
