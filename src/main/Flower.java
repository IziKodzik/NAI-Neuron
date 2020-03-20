package main;

import java.util.ArrayList;
import java.util.List;

public class Flower
	implements Neutronizable{

	private String name;
	private List<Double> properties;
	private Boolean nameCode;

	public void setName(String name) {
		this.name = name;
	}

	public boolean getNameCode() {
		return nameCode;
	}

	public void setProperties(List<Double> properties) {
		this.properties = properties;
	}

	public String getName() {
		return name;
	}

	public List<Double> getProperties() {
		return properties;
	}

	public Flower(String name , List<Double> properties){

		this.name = name;
		this.properties = properties;
		if(name != null) {
			if (name.contains("virgin"))
				nameCode = false;
			else
				nameCode = true;
		}
	}


	public Flower(List<Double> properties){

		this.properties = properties;
		this.name = null;
		this.nameCode = null;

	}

	public static Flower parseFlower(String line){

		List<Double> properties = new ArrayList<>();
		String[] split = line.split(",");
		for( int op = 0 ; op < split.length-1; ++ op) {
			try {
				properties.add(Double.valueOf(split[op]));
			}catch (NumberFormatException e){
				e.printStackTrace();
				properties.add(Math.random()*10);
				System.out.println("Prop replaced by random.");
			}
		}

		return new Flower(split[split.length-1],properties);

	}

	public static String nameByCode(boolean code){
		if(code)
			return "Iris-setosa";
		return "Iris-virginica";
	}

	public String toString(){

		return name + " " + properties;

	}

	@Override
	public List<Double> getInputs() {

		return properties;
	}
}
