package payroll_method;

import java.util.*;
import java.text.*;


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
