package com.zypuk.innovation.flogin;

import java.sql.ResultSet;

public class LoadDataToMemory{

private class Register{

 private int ID;
 private String username;
  private String password;
 private byte[] template1;

 public Register(int id, String name, byte[] template1,String pwd){
	 this.setID(id);
	 this.setName(name);
 	 this.setTemplate1(template1);
         this.setPwd(pwd);
 	 
 }

 private void setID(int id){
	 this.ID = id;
 }
 private void setPwd(String pwd){
	 this.password = pwd;
 }

 private void setName(String name){
	 this.username = name;
 }

 public int getID(){
     return this.ID;
 }
 
 public String getName(){
	 return this.username;
 }
 public String getPwd(){
	 return this.password;
 }
 
 private void setTemplate1(byte[] template){
     this.template1 = template;
 }
 

 public byte[] getTemplate1(){
     return this.template1;
 }
 

}

private Register array[];
private int numberOfRegisters = 0;

public int numberOfRegisters (){
	return this.numberOfRegisters;
}

public byte[] getTemplate1(int index){
	return this.array[index].getTemplate1();
}



public int getID(int index){
	return this.array[index].ID;
}

public String getName(int index){
	return this.array[index].username;
}
public String getPassword(int index){
	return this.array[index].password;
}

public void appendToMemory(int ID, String name, byte[] temp1,String pwd){
	
	Register reg = new Register (ID,name,temp1,pwd);
	this.array[this.numberOfRegisters] = reg;
	this.numberOfRegisters++;
}

public void loadDataToMemory(ResultSet rs) throws Exception{
 
	 int counter  = 0;
	 array = new Register[3000];

	 while (rs.next()){
	 
	      	byte[] template1 = rs.getBytes("thumb");
	      	
	      	String name = rs.getString("username");
                System.out.println(name);
                String pwd = rs.getString("password");
	      	int ID = rs.getInt("id");

	        Register reg = new Register(ID,name,template1,pwd);
	        array[counter] = reg;
	        
	        counter++;
	 }

	 this.numberOfRegisters = counter;
}

}
