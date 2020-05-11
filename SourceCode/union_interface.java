package payroll_method;

import java.util.*;
import java.text.*;


public interface union_interface{

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
