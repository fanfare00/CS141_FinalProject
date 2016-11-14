package edu.cpp.cs.cs141.final_project.User_Interface;


import java.io.*;
import java.util.List;

/**
 * The class responsible for managing save data. It should contain static methods for saving and restoring the state of the game.
 *
 */
public abstract class SaveFileManager
{
	public static final String FILE_NAME = "save_data";
	
	public static void save(List<?> dataList) {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			o.writeObject(dataList);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<?> load() {
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE_NAME));
			
			return (List<?>) o.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
