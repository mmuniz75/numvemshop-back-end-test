import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class TimeExpressionWeekly extends TimeExpressionImpl{

	private DayOfWeek aDayOfWeek;
	private int aWeekInMonth;
	
		
	protected TimeExpressionWeekly(int anAmountOfMonths, DayOfWeek aDayOfWeek, int aWeekInMonth, YearMonth anYear){
		this.amount = anAmountOfMonths;
		this.aDayOfWeek = aDayOfWeek;
		this.aWeekInMonth = aWeekInMonth;
	}
	
	public boolean isRecurringOn(LocalDate aDate) {
		WeekFields weekFields = WeekFields.of(Locale.getDefault());
		// Last week of month
		LocalDate lastWeekDate = aDate.with(TemporalAdjusters.lastInMonth(this.aDayOfWeek)); 
		
		int weekInMonth = this.aWeekInMonth==5?lastWeekDate.get(weekFields.weekOfMonth()):this.aWeekInMonth;

		boolean checkWeek = aDate.getDayOfWeek() == this.aDayOfWeek
				&& aDate.get(weekFields.weekOfMonth()) == weekInMonth;
		
		return checkWeek;

	}
	  
}
