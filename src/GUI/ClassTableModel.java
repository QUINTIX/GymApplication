package src.GUI;

import java.util.ArrayList;
import java.util.HashMap;
import model.Class;
import model.Member;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ClassTableModel extends AbstractTableModel implements TableModelListener{
	
	private String[] names = {"Is Enrolled", "Class Name", "Description", "Capacity", "# Enrolled"};
	private static ArrayList<Class> data = new ArrayList<>();
	private static ArrayList<Object> modifiedData = new ArrayList<>();
	
	public ClassTableModel(){
		data = Class.readFile();
		//for(int i = 0;i < data.size();i++){
		//	for(int j = 0; j < )
			
		//}
	}
	
	public ClassTableModel(ArrayList<Class> data){this.data = data; }
	
	//private static Object[][] dataSet = getValues();
	
	@Override
	public String getColumnName(int i){
		return names[i];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return names.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		Class cla = (Class) data.get(rowIndex);
		if(columnIndex == 0){
			return true;
		}
		return cla.getValue(columnIndex - 1);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex,int columnIndex){
		if (columnIndex == 0) {
			return true;
		}
		return false;
	}
	
	public java.lang.Class<?> getColumnClass(int c){
		if(c == 0){ return Boolean.class; }
		return String.class;
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setValueAt(Boolean value,int rowIndex,int columnIndex){
		//Will be responsible for setting the value if a student is signed up for this class.
	}

}
