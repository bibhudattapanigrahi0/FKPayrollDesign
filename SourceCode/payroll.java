package payroll_method;

import java.util.*;
import java.text.*;
import org.json.simple.JSONObject;

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
  public void submit_fee(HashMap<String,Float> charges);

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
  public void submit_fee(HashMap<String,Float> charges){
    for(Map.Entry<String,Float> s:charges.entrySet()){
         Float val=s.getValue();
         weekly_due+=val;

    }
    System.out.println("fee successfully a[pplied");

  }


}

class nonunion_member implements union_interface{
  public float calculate_due(){
  	return 0f;
  }
  public void submit_fee(HashMap<String,Float> charges){
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
private String acc_no;
private String address;
private String paymaster;
private int payment_option;
employee(String fname,String lname,String empid,salary_interface s,sales_interface t,
	union_interface u,float d,String a,String b,String c,int option){
	this.first_name=fname;
	this.last_name=lname;
	this.employee_id=empid;
	this.salary=s;
	this.sales=t;
	this.union_membership=u;
	this.due_amount=d;
	this.acc_no=a;
	this.address=b;
	this.paymaster=c;
	this.payment_option=option;
}

public void posttime(float duration){
	salary.upload_time(duration);

}
public void postsale(float sale){
	sales.add_sales(sale);
}
public void add_to_union(){
  if(union_membership instanceof union_member){
  	System.out.println("already a member");
  }
  else{
  	union_membership=new union_member(0f);
  	System.out.println("successfuly added to union");
  }

}

public void charge_members(HashMap<String,Float> services){
	union_membership.submit_fee(services);
}

public void change_salaryrate(float rate){
	salary.change_rate(rate);
}

public void change_commision(float sale){
	sales.change_commision(sale);
}

public void payment(){
	due_amount+=union_membership.calculate_due();
	float sal=salary.salary();
	sal+=sales.sales_commision();
	if(sal>=due_amount){
      sal-=due_amount;
     System.out.println("salary to be credited is "+ sal);
     if(payment_option==1)
     System.out.println("salary is credited to account no "+ acc_no);
     if(payment_option==2){
     	System.out.println("your paycheck is sent to adress "+ address);
     }
     if(payment_option==3){
     	System.out.println("plz collect your paycheck from paymaster "+ paymaster);
     }

	}
	else{
         due_amount-=sal;

	}


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
	    String a,b,c;
	    int option;
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
         System.out.println("enter bank account number");
         a=in.nextLine();
         System.out.println("enter adress");
         b=in.nextLine();
         System.out.println("enter payment_master");
         c=in.nextLine();
         System.out.println("enter payment method");
         System.out.println("if want to credit to bank enter 1");
         System.out.println("if want your paycheck send to address enter 2");
         System.out.println("if want to collect from payment master enter 3");
         option=in.nextInt();

         employee current=new employee(fname,lname,empid,s,t,u,d,a,b,c,option);
         employee_list.put(empid,current);
         System.out.println("employee added successfully");

 
 }
    public void delete_employee(){
    	  String empid;
           Scanner in= new Scanner(System.in);
	      System.out.println("enter employee_id of employee to be deleted");
	      empid=in.nextLine();
	      if(employee_list.containsKey(empid)){
	      	employee_list.remove(empid);
	         System.out.println("employee record deleted successfully");


	      }
	      else{
	      	System.out.println("record not found");

	      }

    }
    public void post_timecard(){
           
           System.out.println("enter employee_id of uploading employee");
           String empid;float duration;
           Scanner in= new Scanner(System.in);
           empid=in.nextLine();
           System.out.println("enter number of hour worked");
           duration =in.nextFloat();
           if(duration>24f){
           	System.out.println("invalid entry");
           }
           else{
              if(employee_list.containsKey(empid)){
                employee_list.compute(empid,(k,v)->{v.posttime(duration);return v;});
                System.out.println("time card posted successfully");

              }
              else{
              	System.out.println("empid not found");
              }
              
           }

           



    }
    public void post_sales_receipt(){
           System.out.println("enter employee_id of uploading employee");
           String empid;float sale;
           Scanner in= new Scanner(System.in);
           empid=in.nextLine();    
           System.out.println("enter sales amount");
           sale=in.nextFloat();
              if(employee_list.containsKey(empid)){
                employee_list.compute(empid,(k,v)->{v.postsale(sale);return v;});
                System.out.println("sales amount posted successfully");

              }
              else{
              	System.out.println("empid not found");
              }         



    }

    public void add_union_membership(){
     String empid;
     Scanner in= new Scanner(System.in);
     empid=in.nextLine(); 
     if(employee_list.containsKey(empid)){
        employee_list.compute(empid,(k,v)->{v.add_to_union();return v;});

     }
     else{
       System.out.println("empid not found");

     }



    }

    public void add_service_charges(){
     String empid;
     String name;
     float charge;
     Scanner in= new Scanner(System.in);
     empid=in.nextLine(); 
        if(employee_list.containsKey(empid)){
               HashMap<String,Float> services=new HashMap<>();
               while(true){
               	System.out.println("if want to enter serive press 1 else press 2");
               	int x;
               	x=in.nextInt();
               	if(x!=1)
               		break;
               	System.out.println("enter service name");
               	name=in.nextLine();
               	System.out.println("enter service charge");
               	charge=in.nextFloat();
               	services.put(name,Float.valueOf(charge));
                }
                employee_list.compute(empid,(k,v)->{v.charge_members(services);return v;});

        }
        else
        {
        	System.out.println("empid not found");
        }





    }


    public void edit_employee_details(){
     String empid;
     Scanner in= new Scanner(System.in);
     empid=in.nextLine(); 
     if(employee_list.containsKey(empid)){

        System.out.println("press 1 to change salary_rate");
        System.out.println("press 2 to chnage commision rate");
        int x;
        x=in.nextInt();
        if(x==1){
        	float sale;
        	System.out.println("enter modified salary rate");
        	sale=in.nextFloat();
        	employee_list.compute(empid,(k,v)->{v.change_salaryrate(sale);return v;});


        }
        if(x==2){
        	float sale;
        	System.out.println("enter modified commision rate");
        	sale=in.nextFloat();
        	employee_list.compute(empid,(k,v)->{v.change_commision(sale);return v;});



        }
 }
     else{
       System.out.println("empid not found");

     }


    }


    public void run_payrol(){
    	for(Map.Entry<String,employee> s:employee_list.entrySet()){
         employee val=s.getValue();
         val.payment();

    }

    }

  
    





 }
        






public class payroll{

public static void main(String[] args){

 company flip=new company();
 Scanner in= new Scanner(System.in);

while(true){
System.out.println("if want to modify company details press 1 else press 0");
int d;
d=in.nextInt();
if(d!=1)
	break;
System.out.println("for adding employee press 1");
System.out.println("for deleting employee press 2");
System.out.println("for posting time card press 3");
System.out.println("for posting sales amount press 4");
System.out.println("to add a employee to union press 5");
System.out.println("to add service charges press 6");
System.out.println("to change employee detail press 7");
d=in.nextInt();
if(d==1){

	flip.add_employee();
}
if(d==2)
{
	flip.delete_employee();
}
if(d==3){
	flip.post_timecard();
}
if(d==4){
	flip.post_sales_receipt();
}
if(d==5){
	flip.add_union_membership();
}
if(d==6){
	flip.add_service_charges();
}
if(d==7){
	flip.edit_employee_details();
}

}

flip.run_payrol();
 
}

}