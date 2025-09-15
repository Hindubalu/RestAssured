package ra.day1;

public class PostRequestBodyPOJO {
	
	String name;
	String email;
	String mobile;
	String CoursesArr[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String[] getCoursesArr() {
		return CoursesArr;
	}
	public void setCoursesArr(String[] coursesArr) {
		CoursesArr = coursesArr;
	}

}
