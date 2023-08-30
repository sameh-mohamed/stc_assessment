package com.stc.demo;

import java.util.ArrayList;
import java.util.List;

public class SolvingProblem {
	private static String checkString(String str) {
	    char ch;
	    boolean capitalFlag = false;
	    Boolean px=false;
	    int p=2;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	         if (Character.isUpperCase(ch)) {
	             capitalFlag = true;
	        } 
	         System.out.println(p +"  "+ch);
	         if(String.valueOf(ch).equals("(") || String.valueOf(ch).equals(")")) {
	        	  if(p % 2 == 0 && String.valueOf(ch).equals("(")) {
	  	        	p+=1;
	  	        }
	  	        else if(p% 2 != 0 && String.valueOf(ch).equals(")")) {
	  	        	p+=1;
	  	        }else {
	  	        	px=true;
	  	        } 
	         }
	    }
	    if(capitalFlag) {
	    	  return "String Have Capital Char";
	    }
	    else if(px) {
	    	  return "problem in Prancesse";
	    }
	    else
	    { return "finr_and_continue" ;}
	}
	
	
	public static  String reverseSTR(String str) {
		char ch;
		StringBuilder bs=new StringBuilder();
		if(str !=null && str.length() <=2000 && checkString(str).equals("finr_and_continue")){	
			List<Integer> pag=new ArrayList<>();
			 for(int i=0;i < str.length();i++) {
				 ch=str.charAt(i);
		     	 bs.append(ch);
				 if(String.valueOf(ch).equals("(")) {
					 pag.add(i+1);			
				 }else if(String.valueOf(ch).equals(")")) {
					 pag.add(i);
					
					String sub= bs.substring(pag.get(0),pag.get(1));
					if(sub.length()>1) {
					 String revstr="";
					 for (int w = 0; w < sub.length(); w++) {
						 revstr = sub.charAt(w) + revstr;
						}
					 bs.replace(pag.get(0),pag.get(1), revstr);
						
					}
					pag.clear();
				 }

			 }
		}
		
		return bs.toString(); 
	}
	
	public static void main(String[] args) {
	System.out.print(reverseSTR("ddddddq(sameh)(adel)(roma)dddddssssssssssssaaaaaaaaaaaaaaaa"));
	//  output  ddddddq(hemas)(leda)(amor)dddddssssssssssssaaaaaaaaaaaaaaaa
	}
}
