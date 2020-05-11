package payroll_method;

import java.util.*;
import java.text.*;
import org.json.simple.JSONObject;


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