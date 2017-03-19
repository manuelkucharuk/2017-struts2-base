package utn.frd.action;

import utn.frd.bean.Persona;
import db.PersistentManager;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;

public class PersonaAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	/* campos del Action que van a ser visibles en el JSP */
	private long id;
	private String name;
	private String age;
	private String gender;
	private List<Persona> personas;

	public String execute(){
		personas = PersistentManager.getInstance();
		return SUCCESS;
	}
	
	public String save(){
		personas = PersistentManager.getInstance();
		int edad = 0;
		try{
			edad = Integer.parseInt(age);
		} catch(Exception e){
			addActionError("Ocurrió un error con la edad");
			return ERROR;
		}
		
		//Si el id existe, modificar. Sino agregar una nueva persona
		if(existePersona(id,personas)){
			Persona persona = encontrarPersona(id,personas);
			persona.setName(name);
			persona.setAge(edad);
			persona.setGender(gender);
		}
		else {
			personas.add(new Persona(name, edad, gender));
		}
		
		/*Blanquear formulario. 
		  Por defecto el id=-1 hace que se cree una nueva persona*/
		id=-1;
		name="";
		age="";
		gender="";
		
		return SUCCESS;
	}
	
	public String delete(){
		personas = PersistentManager.getInstance();
		personas.removeIf(p->p.getId()==id);
		return SUCCESS;
	}

	public String modify(){
		personas = PersistentManager.getInstance();
		Persona persona = encontrarPersona(id,personas);
		id = persona.getId();
		name = persona.getName();
		age = String.valueOf(persona.getAge());
		gender = persona.getGender();
		return SUCCESS;
	}
	
	private boolean existePersona(long id, List<Persona> personas){
		return personas.stream().anyMatch(p->p.getId()==id);
	}
	
	private Persona encontrarPersona(long id, List<Persona> personas){
		try{
			return personas.stream().filter(p->p.getId()==id).findFirst().get();
		} catch(Exception e){
			addActionError("No se encuentra la persona");
			throw e;
		}
	}
	
	/* Getters y Setters de los campos que serán visibles en el JSP */
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

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	
}
