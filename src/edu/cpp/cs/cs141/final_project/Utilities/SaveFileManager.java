/**
 * CS 141: Intro to Programming and Problem Solving
 * Professor: Edwin Rodríguez
 *
 * Assignment: Final Project
 *
 * Description: Spies vs. Ninjas is a game created to satisfy the requirements, 
 *    as outlined on Blackboard, for professor Edwin Rodríguez's CS141 class at 
 *    Cal Poly Pomona.
 *
 * Team: The Constructors
 *   James McCarthy (C)
 * 	 Owen Dugmore
 * 	 Rigoberto Canales
 *   Yash Bhure
 */
package edu.cpp.cs.cs141.final_project.Utilities;


import java.io.*;
import java.util.List;


/**
 * The class responsible for managing save data. It contains static methods for saving and restoring the state of the game.
 *
 */
public abstract class SaveFileManager
{
	
	/** The Constant FILE_NAME. */
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
			@SuppressWarnings("resource")
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(FILE_NAME));
			
			return (List<?>) o.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}

}
