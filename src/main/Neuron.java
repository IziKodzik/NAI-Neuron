package main;

import java.util.ArrayList;
import java.util.List;

public
	class Neuron {

	private List<Double> weights;
	private double theta;
	private double alpha;

	public Neuron(List<Double> weights,double theta,double alpha){

		this.weights = new ArrayList<>(weights);
		this.theta = theta;
		this.alpha = alpha;

	}
	public boolean testObject(List<Double> inputs){

		double net = 0;
		for(int op = 0 ; op < Math.max(inputs.size(),weights.size()) ; ++ op )
			net += inputs.get(op) * this.weights.get(op);

		return net > theta;

	}

	public boolean alphaMethod(List<Double> inputs,boolean expected){

		List<Double> weights = new ArrayList<>();

		for(int op = 0 ; op < this.weights.size(); ++ op){

			if(expected){
				weights.add(this.weights.get(op) + alpha*inputs.get(op));
				theta += alpha*-1;
			}else{
				weights.add(this.weights.get(op) - alpha*inputs.get(op));
				theta -= alpha;
			}
		}

		this.weights = weights;
		return testObject(inputs);

	}

}
