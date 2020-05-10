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



interface sales_interface{

    public float sales_commision();
    public void add_sales(float value);
    public void change_commision(float rate);

}

class sales_employee implements sales_interface{
	private float commision;
	private float total_sale;
	public float sales_commision(){
		Date date=new Date();
        SimpleDateFormat ft=new SimpleDateFormat("E");
		if(ft.format(date).equals("Fri")){
			float total=total_sale*commision;
			total_sale=0;
			return total;

		}
		else return 0f;
	}
    public void add_sales(float value){
    		total_sale+=value;
    }
    public void change_commision(float rate){
    		commision=rate;
    }



}

class nonsales_employee implements sales_interface{
	public float sales_commision(){
		return 0f;
	}
    public void add_sales(float value){
    	System.out.println("not applicable");
    }
    public void change_commision(float rate){
    	System.out.println("not applicable");
    }


}


interface union_interface{

  public float calculate_due();
  public void submit_fee(Map<String,Float> charges);

}

class union_member implements union_interface{
private float weekly_due;

  public float calculate_due(){
  	float total=weekly_due;
  	weekly_due=0f;
  	return total;
  }
  public void submit_fee(Map<String,Float> charges){
    for(Map.Entry<String,Float> s:charges.entrySet()){
         Float val=s.getValue();
         weekly_due+=val;

    }

  }


}

class nonunion_member implements union_interface{
  public float calculate_due(){
  	return 0f;
  }
  public void submit_fee(Map<String,Float> charges){
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

 System.out.println("not applicable");
 
}

}