package kr.or.ddit.reflection;

import java.io.Serializable;

public class SampleVO implements Serializable{
	
	@Deprecated
	public String id;
	protected String name;
	private int age;

	public SampleVO() {
		super();
	}

	public SampleVO(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public String getId() throws Exception {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	@Deprecated
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	
}
