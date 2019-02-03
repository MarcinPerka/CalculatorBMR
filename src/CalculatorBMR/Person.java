package CalculatorBMR;

class Person {
	private int age;
	private Gender gender;
	private int weight;
	private int height;
	private String activity;

	public Person(int age, Gender gender, int weight, int height, String activity) {
		this.age = age;
		this.gender = gender;
		this.weight = weight;
		this.height = height;
		this.activity = activity;

	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", gender=" + gender + ", weight=" + weight + ", height=" + height + ", activity="
				+ activity + "]";
	}

}
