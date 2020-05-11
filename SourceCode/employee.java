
package payroll_method;

import java.util.*;
import java.text.*;


public class employee{
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
 