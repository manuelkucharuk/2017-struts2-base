package db;
import utn.frd.bean.Persona;
import java.util.List;
import java.util.ArrayList;

public class PersistentManager {
	private static List<Persona> data = new ArrayList<Persona>();
	
	public static List<Persona> getInstance(){
		return data;
	}

}

