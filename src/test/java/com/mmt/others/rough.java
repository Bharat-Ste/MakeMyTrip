package com.mmt.others;

public class rough 
{

	public static void main(String[] args)
	{
		
		String st= "Rs 7,532";
		String b[]= st.split(" ");
		int i1 = Integer.parseInt(b[1].replaceAll(",", ""));
		System.out.println(i1);
		

		StringBuffer bf= new StringBuffer("Rs 8,074 Zero");
		
		for(int i=0;i<bf.length();i++)
		{
			
			if(bf.charAt(i)<48 || bf.charAt(i)>57)
			{
				
				bf.deleteCharAt(i);
				i--;
			}
			
		}
		System.out.println("removed alfa numeric values : " + bf.toString());
		
		
		
		
		

	}

	


}
