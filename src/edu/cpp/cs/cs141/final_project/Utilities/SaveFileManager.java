package edu.cpp.cs.cs141.final_project.Utilities;


import java.io.*;
import java.util.List;

/**
 * The class responsible for managing save data. It contains static methods for saving and restoring the state of the game.
 *
 */
public abstract class SaveFileManager
{
	public static final String FILE_NAME = "save_data";
	
	/**
	 * Saves the list of objects to the disk.
	 * @param dataList The list of objects to save.
	 */
	public static void save(List<?> dataList) {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			o.writeObject(dataList);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads objects from the disk.
	 * @return The list of objects retrieved.
	 */
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
