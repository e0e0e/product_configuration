package pl.sda.pms.feature;

import java.util.List;

public class TableValues {

	String name;
	List<Row> rowList;
	
	public TableValues(String name, List<Row> rowList) {
		this.name = name;
		this.rowList = rowList;
	}
	public TableValues() {

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Row> getRowList() {
		return rowList;
	}
	public void setRowList(List<Row> rowList) {
		this.rowList = rowList;
	}
	

}