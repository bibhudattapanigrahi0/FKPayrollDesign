package payroll_method;



import java.util.*;
import java.text.*;

interface salary_interface{

public float salary();
public void change_rate(float rate);
public void upload_time(float duration);


}

class weekly_salary implements salary_interface{

	private float sales_rate;
	private float total_salary;
	public float salary(){
		Date date=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("E");
		if(ft.format(date).equals("Fri")){
			float total=total_salary;
			total_salary=0;
			return total;

		}
		else return 0f;

	}
	public void change_rate(float rate){
		sales_rate=rate;
	}
	public void upload_time(float duration){
              if(duration<=8f)
              	total_salary+=duration*sales_rate;
              else{
              	total_salary+=8f*sales_rate+(duration-8f)*1.5f*sales_rate;
              }

	}
	
}
class monthly_salary implements salary_interface{
    
    private float sales_rate;
	

    public float salary(){
    	Date date=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("d");
          if(ft.format(date).equals("26")){
			return sales_rate;

		}
		else return 0f;

    }
	public void change_rate(float rate){
            rate =sales_rate;
	}
	public void upload_time(float duration){
		System.out.println("not applicable");
	}
	



}


class employee{
private String first_name;
private String last_name;

private Float due_amount;






}


public class payroll{
public static void main(String[] args){


}

}