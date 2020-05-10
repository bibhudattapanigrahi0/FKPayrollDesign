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
	weekly_salary(float sales,float total_salary){
		sales_rate=sales;
		this.total_salary=total_salary;
	}

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
	monthly_salary(float rate){
		sales_rate=rate;
	}

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
	sales_employee(float com,float sale){
		commision=com;
		total_sale=sale;
	}
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
 union_member(float key){
 	weekly_due=key;
 }

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
private String employee_id;
private salary_interface salary;
private sales_interface sales;
private union_interface union_membership;
private float due_amount;
employee(String fname,String lname,String empid,salary_interface s,sales_interface t,
	union_interface u,float d){
	this.first_name=fname;
	this.last_name=lname;
	this.employee_id=empid;
	this.salary=s;
	this.sales=t;
	this.union_membership=u;
	this.due_amount=d;
}


}
 
 class company{
    private Map<String,employee> employee_list=new HashMap<>();
     
    public void add_employee(){
        String fname;
        String lname;
        String empid;
        salary_interface s;
        sales_interface t;
	    union_interface u;
	    float d=0f;
	    Scanner in= new Scanner(System.in);
	    System.out.println("enter first name");
         fname=in.nextLine();
         System.out.println("enter last name");
         lname=in.nextLine();
         while(true){
           System.out.println("enter employee id");
           empid=in.nextLine();
           if(employee_list.containsKey(empid)){
           	System.out.println("emplyee id already exists");
           	System.out.println("if want to quit adding employee press 1");
           	int x;
           	x=in.nextInt();
           	if(x==1)
           		return;

           }
           else break;

         }
         System.out.println("if epmploee if weekly salarised press 1 else press 2");

         int x;
         x=in.nextInt();
         if(x==1){
           	float rate, salary=0f;
           	System.out.println("enter hourly salary rate");
           	rate=in.nextFloat();
           	s=new weekly_salary(rate,salary);
        }
           else{
              float rate;
              System.out.println("enter monthly salary rate");
              rate=in.nextFloat();
           	  s=new monthly_salary(rate);


           }

         System.out.println("if can get sales_commision press 1 else press 2");

         
         x=in.nextInt();
         if(x==1){
			float commision;
			float total_sale=0f;
			System.out.println("enter commision rate");
			commision=in.nextFloat();
			t=new sales_employee(commision,total_sale);
         }
         else{
            t=new nonsales_employee();
         }

         System.out.println("if is a member of union press 1 else press 2");

         
         x=in.nextInt();
         if(x==1){
         	u=new union_member(0f);
         }
         else u=new nonunion_member();

         employee current=new employee(fname,lname,empid,s,t,u,d);
         employee_list.put(empid,current);
         System.out.println("employee added successfully");

 
 }
    public void delete_employee(){

    }
    public void post_timecard(String emp_id,float duration){

    }
    public void post_sales_receipt(String emp_id,float total_sale){

    }




 }






public class payroll{

public static void main(String[] args){

 company flip=new company();
 flip.add_employee();
 
}

}