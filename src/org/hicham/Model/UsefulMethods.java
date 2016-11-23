package org.hicham.Model;

public class UsefulMethods {
	
	//is Integer method **checks if a given String is an Integer** 
		public static boolean isInteger(String str) {
			if (str == null) {
				return false;
			}
			int length = str.length();
			if (length == 0) {
				return false;
			}
			int i = 0;
			if (str.charAt(0) == '-') {
				if (length == 1) {
					return false;
				}
				i = 1;
			}
			for (; i < length; i++) {
				char c = str.charAt(i);
				if (c < '0' || c > '9') {
					return false;
				}
			}
			return true;
		}
		//this is method that return true if a string is numeric(integer or decimal) using Regex
		public static boolean isNumeric(String str)
		{
		  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
		}


}
