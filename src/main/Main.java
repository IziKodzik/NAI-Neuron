package main;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		if(args.length != 3){
			System.out.println("Wrong arguments amount.");
			System.exit(-2);
		}

		double alpha =-1;

		try {
			alpha =  Double.parseDouble(args[0]);
		}catch (NumberFormatException e){
			System.out.println("Invalid 1st argument");
			System.exit(-1);
		}

		BufferedReader trainFile = null;
		try{

			trainFile = new BufferedReader(new FileReader("data/" + args[1]));
		}catch (FileNotFoundException e){
			e.printStackTrace();
			System.exit(-1);
		}
		BufferedReader testFile = null;
		try{
			testFile = new BufferedReader(new FileReader("data/" + args[2]));
		}catch (IOException e){
			e.printStackTrace();
			System.exit(-1);
		}

		List<Flower> trainList = new ArrayList<>();
		List<Flower> testList = new ArrayList<>();

		try {
			for (String line = trainFile.readLine(); line != null; line = trainFile.readLine())
				trainList.add(Flower.parseFlower(line));

			for(String line = testFile.readLine() ; line != null ; line = testFile.readLine())
				testList.add(Flower.parseFlower(line));

		}catch (IOException e){
			e.printStackTrace();
		}

		double theta;

		do
			theta = (5-(Math.random()*5));
		while (theta == 0);

		List<Double> weights = new ArrayList<>();
		for(int op = 0; op < trainList.get(0).getProperties().size(); ++ op){

			double weight;
			do{
				weight = -Math.random()*5+Math.random()*5;
				weights.add(weight);
			}while(weight == 0);

		}

		Neuron neuron = new Neuron(weights,theta,alpha);

		for(int op = 0 ; op < trainList.size(); ++ op){

			Flower flower = trainList.get(op);
			boolean result = neuron.testObject(flower.getInputs());

			while(result != flower.getNameCode())
				result = neuron.alphaMethod(flower.getInputs(),flower.getNameCode());

		}

		int guessed = 0;

		for(int op = 0 ; op < testList.size(); ++ op){

			Flower flower = testList.get(op);
			boolean result = neuron.testObject(flower.getInputs());

			if(flower.getNameCode() == result)
				guessed++;

			System.out.println("It is " + Flower.nameByCode(flower.getNameCode()) +
					" and i gueesed " + Flower.nameByCode(result));

		}

		System.out.println(Flower.nameByCode(neuron.testObject(trainList.get(0).getInputs())));

		System.out.println("My accuracy is " + (double)guessed/testList.size());

		List<Double> properties = new ArrayList<>();
		properties.add(6.3);
		properties.add(3.3);
		properties.add(6.1);
		properties.add(2.3);


		Flower f = new Flower(properties);
		f.setNameCode(neuron.testObject(f.getInputs()));
		System.out.println(Flower.nameByCode(f.getNameCode()));

		SwingUtilities.invokeLater( () -> new GUI(4,neuron));



	}

}