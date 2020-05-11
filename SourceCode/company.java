package payroll_method;

import java.util.*;
import java.text.*;



public class company{
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
        
