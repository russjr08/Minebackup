package com.kronosad.minebackup;

import javax.swing.JOptionPane;

public class CheckOS {
	
	public static String os = System.getProperty("os.name").toLowerCase();
	public static void main(String[] args){
		System.out.println("Please do not run this class itself, use MainWindow. Closing");
		JOptionPane.showMessageDialog(null, "Do not run this class by itself, use MainWindow. Closing", "Error", JOptionPane.ERROR_MESSAGE);
		System.out.println("[Debug]" + os);
		System.exit(0);
		
	}
	
	public static boolean isWindows(){
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("win")){
			
			System.out.println("You are using Windows");
			return true;			
		}
		System.out.println(os);
		return false;
	}
	
	public static boolean isLinuxUnix(){
		if(os.contains("nix") || os.contains("nux")){
			System.out.println("You are using Linux/Unix");
			return true;
		}
		return false;
	}
	
	public static boolean isMac(){
		if(os.contains("mac")){
			System.out.println("You are using Mac");
			return true;
		}
		return false;
	}
	
	public static boolean isSolaris(){
		if(os.contains("sunos")){
			System.out.println("You are using Solaris");
			return true;
		}
		return false;
	}
}
