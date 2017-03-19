package utn.frd.bean;

import java.util.List;
import db.PersistentManager;



public class Persona {
	
	private long id;
	private String name;
	private int age;
	private String gender;
	
	//Constructor, crea una persona con sus atributos
	public Persona(String name, int age, String gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	
	//Constructor, pone a una persona en la lista de personas
	public Persona(int posicion, String name, int age, String gender){
		Persona persona = new Persona(name,age,gender);
		List<Persona> personas = db.PersistentManager.getInstance();
		personas.add(posicion,persona);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
