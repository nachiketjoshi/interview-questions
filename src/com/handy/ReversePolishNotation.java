package com.handy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class ReversePolishNotation {

	/**
	 * This class has all the functionality required in order to extend the
	 * functionality of the RPN Calculator All one needs to do in order to
	 * support an additional operation is to add it to the static map, and
	 * define the behavior of the operator itself. There is also optional
	 * support provided for operators that require more (or less) operands
	 * <p>
	 * Example:
	 * <p>
	 * <code>OPERATORS.put(operatorSymbol, new Operator() {<br>
	 *  public double operate(Double... operands) { <br>
	 *  // insert code here<br>
	 *           }); </code>
	 */
	private abstract static class Operator {

		private static final String DIVIDE = "/";
		private static final String SUBTRACT = "-";
		private static final String MULTIPLY = "*";
		private static final String ADD = "+";
		private static final Map<String, Operator> OPERATORS = new HashMap<>();

		static {
			OPERATORS.put(DIVIDE, new Operator() {
				@Override
				public double operate(Double... operands) {
					double operand2 = operands[1].doubleValue();
					if (operand2 == 0) {
						System.out
								.println("Encountered division by zero .. Aborting calculation");
						return 0;
					}
					return operands[0].doubleValue() / operand2;
				}
			});

			OPERATORS.put(SUBTRACT, new Operator() {
				@Override
				public double operate(Double... operands) {
					return operands[0].doubleValue()
							- operands[1].doubleValue();
				}
			});

			/* Uncomment below to support "−" as subtraction */
			// OPERATORS.put("−", new Operator() {
			// @Override
			// public double operate(Double... operands) {
			// return operands[0].doubleValue()
			// - operands[1].doubleValue();
			// }
			// });

			OPERATORS.put(MULTIPLY, new Operator() {
				@Override
				public double operate(Double... operands) {
					return operands[0].doubleValue()
							* operands[1].doubleValue();
				}
			});

			/* Uncomment below to support "x" as multiplication */
			// OPERATORS.put("x", new Operator() {
			// @Override
			// public double operate(Double... operands) {
			// return operands[0].doubleValue()
			// * operands[1].doubleValue();
			// }
			// });

			OPERATORS.put(ADD, new Operator() {
				@Override
				public double operate(Double... operands) {
					return operands[0].doubleValue()
							+ operands[1].doubleValue();
				}
			});
		}

		private final int numArguments;

		public Operator() {
			this(2);
		}

		public Operator(int numArguments) {
			this.numArguments = numArguments;
		}

		public static Operator getOperator(String operator) {
			return OPERATORS.get(operator);
		}

		public static Map<String, Operator> getOperators() {
			return OPERATORS;
		}

		abstract double operate(Double... operands);

		public int getNumArguments() {
			return this.numArguments;
		}
	}

	/**
	 * Calculates output of a RPN string of inputs.<br>
	 * Validations are done for:<br>
	 * 1. Invalid operators<br>
	 * 2. Insufficient operands for an operator<br>
	 * 3. Invalid sequence
	 */
	static double calculateOutput(String input) {
		Deque<Double> stack = new LinkedList<>();
		String[] tokens = input.split("\\s+");

		for (int i = 0; i < tokens.length; i++) {
			String token = tokens[i];
			try {
				stack.push(Double.valueOf(token));
				continue; // OPERAND
			} catch (NumberFormatException e) {
				// fall through
			}

			// OPERATOR
			Operator operator = Operator.getOperator(token);
			if (operator == null) {
				System.out.println("Unable to determine input : " + token);
				return 0;
			}
			int numArguments = operator.getNumArguments();
			if (stack.size() < numArguments) {
				System.out.println("Insufficient input to evaluate operator : "
						+ token);
				return 0;
			}
			// create a list of operands in left -> right order
			List<Double> operands = new LinkedList<>();
			for (int j = 0; j < numArguments; j++) {
				Double operand = stack.pop();
				if (operands.size() == 0)
					operands.add(operand);
				else
					operands.add(0, operand);
			}
			stack.push(Double.valueOf(operator.operate(operands
					.toArray(new Double[operands.size()]))));
		}

		if (stack.size() == 1)
			return stack.pop().doubleValue();
		System.out.println("Too many values entered as input : " + input);
		return 0;
	}

	public static void main(String[] args) {
		System.out.println("Reverse Polish Notation Calculator");
		System.out.println("Supported Operations : ");
		Random r = new Random();
		for (Entry<String, Operator> entry : Operator.getOperators().entrySet()) {
			Operator operator = entry.getValue();
			Double[] operands = new Double[operator.getNumArguments()];
			for (int i = 0; i < operands.length; i++) {
				operands[i] = Double.valueOf(r.nextInt(10));
			}
			String operatorString = entry.getKey();
			System.out.println(operatorString + "  :  ("
					+ operandsToString(operands) + " " + operatorString
					+ ") = " + operator.operate(operands));
		}

		System.out.println("Enter Input : (type 'exit' to quit)");
		System.out.print(" -> ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String input = reader.readLine().trim();
			while (!input.equalsIgnoreCase("exit")) {
				System.out.println("(" + input + ") = "
						+ calculateOutput(input));
				System.out.print(" -> ");
				input = reader.readLine().trim();
			}
			System.out.println("Exiting..");
			System.exit(0);
		} catch (IOException e) {
			System.err.println("IOException during readline");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private static String operandsToString(Double[] operands) {
		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(operands[i]);
			if (i == operands.length - 1)
				return b.toString();
			b.append(" ");
		}
	}
}
