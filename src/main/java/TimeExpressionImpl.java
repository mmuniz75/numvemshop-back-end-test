import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

public class TimeExpressionImpl extends TimeExpression{

	protected LocalDate date;
	protected int amount;
	protected ChronoUnit unit;
	
	protected TimeExpressionImpl(){
	}
				
	protected TimeExpressionImpl(LocalDate aDate,int amount, ChronoUnit unit){
		this.date = aDate;
		this.amount = amount;
		this.unit = unit;
	}
		
	public static TimeExpression on(LocalDate aDate) {
        return new TimeExpressionImpl(aDate,1,ChronoUnit.DAYS);
    }
	
	public static TimeExpression dailyEveryFromOnwards(int anAmountOfDays, LocalDate aDate) {
	    return new TimeExpressionImpl(aDate,anAmountOfDays,ChronoUnit.DAYS);
    }
	
	public static TimeExpression monthlyEveryOnFromOnwards(int anAmountOfMonths, int aDayInMonth, YearMonth anYear) {
		LocalDate aDate = LocalDate.of(anYear.getYear(), anYear.getMonthValue(), aDayInMonth);
	    return new TimeExpressionImpl(aDate,anAmountOfMonths,ChronoUnit.MONTHS);
    }
	
	public static TimeExpression yearlyEveryOnFromOnwards(int anAmountOfYears, MonthDay aMonthDay, int anYear) {
		LocalDate aDate = LocalDate.of(anYear, aMonthDay.getMonthValue(), aMonthDay.getDayOfMonth());
		return new TimeExpressionImpl(aDate,anAmountOfYears,ChronoUnit.YEARS);
    }
	
	 public static TimeExpression monthlyEveryOnOfFromOnwards(int anAmountOfMonths, DayOfWeek aDayOfWeek, int aWeekInMonth, YearMonth anYear) {
	    	return new TimeExpressionWeekly(anAmountOfMonths, aDayOfWeek, aWeekInMonth, anYear);
	 }

    protected boolean isRecurringOn(LocalDate aDate) {
			
		long diff = this.date.until(aDate,unit);
		
		return diff%this.amount==0;
    }
}
