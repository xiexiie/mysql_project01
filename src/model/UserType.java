package model;

//主界面进行选择系统管理员，或者是学生，老师
public enum UserType {
	//枚举类书写对象
	ADMIN("主任",0),TEACHER("教师",1),STUDENT("学生",2);//表单的列表顺序
	private String name;
	private int index;
	private UserType(String name,int index){
		this.name = name;
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	@Override
	public String toString(){
		return this.name;
	}
}
