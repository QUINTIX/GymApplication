//Jose Linares jlinare3@masonlive.gmu.edu G# G00855944

package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Class {

	private String name;//Name of class
	private String description;//Description of the class
	private int capacity;//The max capacity of the class
	private int enrollment;//Current enrollment
	private ArrayList<String> members;//All IDs of students in the class
	private static int classCount = 0;//Total number of 

	public Class(String name, String description, int capacity, int enrollment, ArrayList<String> members) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
		this.members = members;
		classCount++;
	}
	
	public Class(String name, String description, int capacity, int enrollment) {
		this.name = name;
		this.description = description;
		this.capacity = capacity;
		this.enrollment = enrollment;
		members = new ArrayList<String>();
		classCount++;
	}
	
	public Object getValue(int i){
        switch(i){
            case 0:
                return this.name;
            case 1:
                return this.description;
            case 2:
                return this.capacity;
            case 3:
                return this.enrollment;
        }
        return "";
                
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCapacity() {
		return capacity;
	}
	
	//adds a member
	public boolean enrollMember(String memberID)
	{	if(isFull()) {return false;} //business rule: cannot enroll in full class
		if(members.contains(memberID)) {return false;} //sanity check for double enrollment
		
		members.add(memberID); 
		enrollment++;
		return true;
	}
	
	//removes a member
	public boolean unenrollMember(String memberID)
	{	if(enrollment==0) return false;
		if(!members.contains(memberID)) {return false;}
		members.remove(memberID);
		enrollment--;
		return true;
	}
	
	public boolean isFull(){return enrollment == capacity;}
//	public void setCapacity(int capacity) {
//		this.capacity = capacity;
//	}

	public int getEnrollment() {
		return enrollment;
	}

//	public void setEnrollment(int enrollment) {
//		this.enrollment = enrollment;
//	}

	public ArrayList<String> getMembers() {
		return members;
	}

	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}
	
	public static int getCount(){
		return classCount;
	}
	
	//Reads a file and returns contents inside an array of Class objects
	public static ArrayList<Class> readFile(){
		ArrayList<Class> classes = new ArrayList<>();
		String filename = "classes.txt";
		File file = new File(filename);
		int capacity, enrollment=0;
		try {
			Scanner scan = new Scanner(file);
			//scan.useDelimiter(",");
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				Scanner br = new Scanner(line);
				br.useDelimiter(",");
				String name = br.next();
				String description = br.next();
				capacity = br.nextInt();
				enrollment = br.hasNext() ? br.nextInt() : 0;
				Class cl = new Class(name,description,capacity,enrollment);
				classes.add(cl);
				br.close();
			}
			scan.close();
			//br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return classes;
	}
	
	//Writes the contents inside a Class array into a file
	public static void writeFile(ArrayList<Class> classes){
		 BufferedWriter bw = null;
	        try{
	        	 bw = new BufferedWriter(new FileWriter("classes.txt", false));
	        	for(int i = 0;i < classes.size(); i++){
		            bw.write(classes.get(i).toString());
		            if(i != classes.size() - 1){
		            	bw.write(",");
		            }
		           // bw.newLine();
	        	}
	        }catch(FileNotFoundException e){
	            System.out.println("File can not be created");
	        } catch (IOException ex) {
	            System.out.println("Error in Input/Output");
	        }finally{
	            try {
	                bw.close();
	            } catch (IOException ex) {
	        }
	    }
	}
	
	

	@Override
	public String toString() {
		//String mem = "";
		//for(int i = 0; i < members.size();i++){
		//	mem = mem + this.members.get(i) + " ";
		//}
		return name + "," + description + "," + capacity
				+ "," + enrollment;
	}
	
	
}
