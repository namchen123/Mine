package model;

public class BangXepHang {
	private int rank;
	private String name;
	private String time;
	private String level;
	public BangXepHang(String name, String time, String level) {
		super();
		this.name = name;
		this.time = time;
		this.level = level;
	}
	public BangXepHang() {
		super();
	}
	@Override
	public String toString() {
		return "Name= " + name + ", time= " + time + ", level= " + level;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLevel() {
		return level;
	}
	public void setLever(String level) {
		this.level = level;
	}
	
}
