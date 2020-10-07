package utv.asgmt1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.*;

/**
 * BMI calculator. Part 1 of assignment 1. Javautveckling @ Nackademin -20
 * 
 * @author Joakim Edberg
 *
 */
public class BmiCalculator {

	public static void main(String[] args) {

		runTestCode();
		initBmiCalculator();

	}

	/**
	 * Initializes the calculator with a graphic user interface.
	 */
	static void initBmiCalculator() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		JTextField weight = new JTextField(25);
		JTextField length = new JTextField(25);
		JButton calculate = new JButton("Beräkna");
		JLabel result = new JLabel();

		panel.add(new JLabel("Vikt (kg): "));
		panel.add(weight);
		panel.add(new JLabel("Längd (m): "));
		panel.add(length);
		panel.add(calculate);
		panel.add(result);

		frame.add(panel);
		frame.setSize(300, 200);
		frame.setVisible(true);

		// Listener on button to calculate BMI.
		calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Makes sure the strings are not empty and contain numbers.
				if (!weight.getText().isEmpty() && !length.getText().isEmpty()) {
					if (weight.getText().matches("^[+-]?(\\d*\\.)?\\d+$")
							&& length.getText().matches("^[+-]?(\\d*\\.)?\\d+$")) {

						// Creates a new object of Person, retrieves and displays the BMI and weight class.
						Person person = new Person(Double.valueOf(weight.getText()), Double.valueOf(length.getText()));
						result.setText("BMI: " + person.getBmi() + " " + person.getWeightClass());
					}
				}
			}
		});
	}

	static void runTestCode() {
		DecimalFormat df = new DecimalFormat("#.##");
		DecimalFormat df2 = new DecimalFormat("#.#");
		Random random = new Random();

		df.setRoundingMode(RoundingMode.HALF_UP);
		df2.setRoundingMode(RoundingMode.HALF_UP);

		for (int i = 10; i >= 0; i--) {
			Person person = new Person((Double.valueOf(df2.format(40 + (150 - 40) * random.nextDouble()))),
					(Double.valueOf(df.format(1.4 + (2.1 - 1.4) * random.nextDouble()))));
			System.out.println("Vikt: " + person.getWeight() + "\nLängd: " + person.getLength() + "\nBMI: "
					+ person.getBmi() + "\nViktklass: " + person.getWeightClass() + "\n");
		}
	}

	/**
	 * Representing a person with necessary values for a BMI calculation.
	 * 
	 * @author Joakim Edberg
	 *
	 */
	static class Person {

		/**
		 * Stores the weight of the Person.
		 */
		private double weight;

		/**
		 * Stores the length of the Person.
		 */
		private double length;

		/**
		 * Stores the BMI of the Person.
		 */
		private double bmi;

		/**
		 * Stores the weight-class of the Person.
		 */
		private String weightClass;

		/**
		 * Constructs a Person with their weight, length, BMI and weight-class.
		 * 
		 * @param weight weight of Person.
		 * @param length length of Person.
		 */
		Person(double weight, double length) {
			this.length = length;
			this.weight = weight;
			calculateBMI();
			setWeightClass();
		}

		/**
		 * Retrieves the length.
		 * 
		 * @return the length value.
		 */
		double getLength() {
			return length;
		}

		/**
		 * Retrieves the weight.
		 * 
		 * @return the weight value.
		 */
		double getWeight() {
			return weight;
		}

		/**
		 * Retrieves the BMI.
		 * 
		 * @return the bmi value.
		 */
		double getBmi() {
			return bmi;
		}

		/**
		 * Retrieves the weight-class.
		 * 
		 * @return the weight-class.
		 */
		String getWeightClass() {
			return weightClass;
		}

		/**
		 * Sets the weight-class of Person.
		 */
		void setWeightClass() {
			if (bmi < 18.5)
				weightClass = "Undervikt";
			else if (bmi >= 18.5 && bmi <= 25)
				weightClass = "Normalvikt";
			else if (bmi > 25 && bmi <= 30)
				weightClass = "Övervikt";
			else if (bmi > 30 && bmi <= 35)
				weightClass = "Fetma klass 1";
			else if (bmi > 35 && bmi <= 40)
				weightClass = "Fetma klass 2";
			else
				weightClass = "Fetma klass 3";
		}

		/**
		 * Calculates and sets the BMI of Person, based on their length and weight.
		 */
		void calculateBMI() {
			DecimalFormat df = new DecimalFormat("#.#");
			df.setRoundingMode(RoundingMode.HALF_UP);

			// formula: bmi = m/l² [kg/m]
			bmi = Double.valueOf(df.format((weight) / (length * length)));
		}

	}
}
