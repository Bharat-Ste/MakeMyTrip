package com.mmt.others;

public class rough {

	public static void main(String[] args)
	{
		String st= "Rs 7,532";
		String b[]= st.split(" ");
		int i = Integer.parseInt(b[1].replaceAll(",", ""));
		System.out.println(i);
		

	}

}
