package com.kronosad.minebackup;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class FileOperations {
	String filePath;
	public FileOperations(String path){
		filePath = path;
	}
	
	public void backupMinecraft(){
		Path toMinecraft = Paths.get(filePath, ".minecraft");
		System.out.println("Using the following Path now: " + toMinecraft);
		if(!Files.exists(toMinecraft)){
			JOptionPane.showMessageDialog(null, "Minecraft could not be found. Please download Minecraft.", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		
	}
}
