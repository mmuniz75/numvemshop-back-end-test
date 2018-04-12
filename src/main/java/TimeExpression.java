import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class TimeExpression {
	
	private LocalDate date;
	private int amount;
	private ChronoUnit unit;
	DayOfWeek aDayOfWeek;
	int aWeekInMonth;
		
	
	private TimeExpression(LocalDate aDate){
		this.date = aDate;
	}
	
	private TimeExpression(LocalDate startDate,int amount,ChronoUnit unit){
		this.date = startDate;
		this.amount = amount;
		this.unit = unit;
	}
	
	private TimeExpression(LocalDate startDate,int amount,ChronoUnit unit,DayOfWeek aDayOfWeek,int aWeekInMonth){
		this.date = startDate;
		this.amount = amount;
		this.unit = unit;
		this.aDayOfWeek = aDayOfWeek;
		this.aWeekInMonth = aWeekInMonth;
	}
	
	

    public static TimeExpression on(LocalDate aDate) {
        return new TimeExpression(aDate);
    }

    public static TimeExpression dailyEveryFromOnwards(int anAmountOfDays, LocalDate aDate) {
        return new TimeExpression(aDate,anAmountOfDays,ChronoUnit.DAYS);
    }

    public static TimeExpression monthlyEveryOnFromOnwards(int anAmountOfMonths, int aDayInMonth, YearMonth anYear) {
    	LocalDate aDate = LocalDate.of(anYear.getYear(), anYear.getMonthValue(), aDayInMonth); 
    	return new TimeExpression(aDate,anAmountOfMonths,ChronoUnit.MONTHS);
    }

    public static TimeExpression monthlyEveryOnOfFromOnwards(int anAmountOfMonths, DayOfWeek aDayOfWeek, int aWeekInMonth, YearMonth anYear) {
    	
    	LocalDate tempDate = LocalDate.of(anYear.getYear(), anYear.getMonthValue(), 1);
    	
    	LocalDate aDate = tempDate.with(TemporalAdjusters.dayOfWeekInMonth(aWeekInMonth, aDayOfWeek));
    	
    	//if found next month return for back week
    	if(aDate.getMonthValue()!=tempDate.getMonthValue()) {
    		aDate = tempDate.with(TemporalAdjusters.dayOfWeekInMonth(aWeekInMonth-1, aDayOfWeek));
    	}	
    	    	    	
    	return new TimeExpression(aDate,anAmountOfMonths,ChronoUnit.MONTHS,aDayOfWeek,aWeekInMonth){
       	 public boolean isRecurringOn(LocalDate aDate) {
       		 
       		 if(!isRecurring(aDate))
       			return false;	 
       		 
     		WeekFields weekFields = WeekFields.of(Locale.getDefault());

     		int weekInMonth = this.aWeekInMonth;
     		// If week in month stored is greatter then current month roll back one week
     		if(weekInMonth==5) {
	            LocalDate tempDate = aDate.with(TemporalAdjusters.lastInMonth(this.aDayOfWeek)); // get last week of the month
	       		int lastWeek = tempDate.get(weekFields.weekOfMonth());
	       		weekInMonth = this.aWeekInMonth>lastWeek?lastWeek:weekInMonth;
     		}			
       		
       		boolean checkWeek = aDate.getDayOfWeek()==this.aDayOfWeek && 
       				             aDate.get(weekFields.weekOfMonth())==weekInMonth;
       		return checkWeek;
       	 }
       };
    }

    public static TimeExpression yearlyEveryOnFromOnwards(int anAmountOfYears, MonthDay aMonthDay, int anYear) {
    	LocalDate aDate = LocalDate.of(anYear, aMonthDay.getMonthValue(), aMonthDay.getDayOfMonth()); 
    	return new TimeExpression(aDate,anAmountOfYears,ChronoUnit.YEARS);
    }

    public boolean isRecurringOn(LocalDate aDate) {
    	return isRecurring(aDate);
    }
    
    protected boolean isRecurring(LocalDate aDate) {
    	if(aDate.isBefore(date))
    		return false;
    	
    	if( this.date.isEqual(aDate))
		   	return true;
    	
    	if(unit==null)
    		throw new IllegalStateException("no unit set");
    	
    	long diff = this.date.until(aDate,unit);
		
    	return diff%this.amount==0;
    }
}
