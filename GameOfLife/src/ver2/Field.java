package ver2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Field {

	private int[][] grid;
	private int width;
	private int height;
	private static final int bitStrLength = 8;

	//private static final int seed = 123;
	private static final Random rand = new Random();

	private int underPopulation = 2;
	private int overPopulation = 3;

	private HashMap<String, Integer> ruleTable;
	private boolean useRuleTable = false;

	/**
	 * Make sure to either initialize or randomize after construction.
	 * 
	 * @param height
	 *            Height of the field.
	 * @param width
	 *            Width of the field.
	 */
	public Field(int width, int height) {
		this.height = height;
		this.width = width;
		this.grid = new int[height][width];
	}

	/**
	 * Make sure to either initialize or randomize after construction.
	 * 
	 * @param height
	 *            Height of the field.
	 * @param width
	 *            Width of the field.
	 * @param underPopulation
	 *            New parameter for underPopulation. 2 is the default.
	 * @param overPopulation
	 *            New parameter for overPopulation. 3 is the default.
	 */
	public Field(int width, int height, int underPopulation, int overPopulation) {
		this(width, height);
		this.underPopulation = underPopulation;
		this.overPopulation = overPopulation;
	}

	/**
	 * Make sure to either initialize or randomize after construction.
	 * 
	 * @param height
	 *            Height of the field.
	 * @param width
	 *            Width of the field.
	 * @param ruleNumber
	 *            Rule number to use for the rule table.
	 */
	public Field(int width, int height, int ruleNumber) {
		this(width, height);
		this.ruleTable = generate2DCARuleTable(ruleNumber);
		this.useRuleTable = true;
	}

	public int getFieldIndex(int x, int y) {
		return grid[y][x];
	}

	public void setFieldIndex(int x, int y, int value) {
		grid[posMod(y, height)][posMod(x, width)] = value;
	}

	public void randomize() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = rand.nextInt(2);
			}
		}
	}

	public void initialize() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				grid[i][j] = 0;
			}
		}
	}

	@Override
	public String toString() {
		String ret = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				ret += grid[i][j];
			}
			ret += '\n';
		}
		return ret;
	}

	public String neighborsBitString(int x, int y) {
		int[] neighborVals = neighborsValues(x, y);
		String ret = "";
		for (int i = 0; i < neighborVals.length; i++) {
			if (neighborVals[i] > 0)
				ret += 1;
			else
				ret += 0;
		}
		return ret;
	}

	/**
	 * Produce all binary strings of n bits length.
	 * 
	 * @param n
	 *            length of the binary strings.
	 * @return Array of binary strings.
	 */
	public static String[] binaryStrings(int n) {
		int numIterations = (int) Math.pow(2, n);
		String[] strs = new String[numIterations];
		for (int i = 0; i < numIterations; i++) {
			String sTemp = Integer.toBinaryString(i);
			// Append zeroes until length s == n
			while (sTemp.length() != n) {
				sTemp = '0' + sTemp;
			}
			strs[i] = sTemp;
		}
		return strs;
	}

	/**
	 * Generate the rule table for a 2D CA with the given ruleNumber.
	 * 
	 * @param ruleNumber
	 *            The rule number of the CA, which determines output for cell
	 *            configurations.
	 * @return The rule number's mappings of input to output.
	 */
	public static HashMap<String, Integer> generate2DCARuleTable(int ruleNumber) {
		HashMap<String, Integer> ruleMap = new HashMap<>();
		String[] inputStrings = binaryStrings(bitStrLength);
		String ruleString = Integer.toBinaryString(ruleNumber);
		while (ruleString.length() != inputStrings.length)
			ruleString = '0' + ruleString;
		// Reverse so that we get it in ascending order ("00000000" ...
		// "11111111")
		ruleString = new StringBuilder(ruleString).reverse().toString();
		for (int i = 0; i < inputStrings.length; i++) {
			ruleMap.put(inputStrings[i],
					Character.getNumericValue(ruleString.charAt(i)));
		}
		return ruleMap;
	}
	
	public static int randomRuleNumber(){
		int upperBound = (int) Math.pow(2, Math.pow(2, bitStrLength));
		return rand.nextInt(upperBound);
	}

	private int[] neighborsValues(int x, int y) {
		return new int[] { safeGet(x - 1, y), safeGet(x - 1, y - 1),
				safeGet(x, y - 1), safeGet(x + 1, y - 1), safeGet(x + 1, y),
				safeGet(x + 1, y + 1), safeGet(x, y + 1), safeGet(x - 1, y + 1) };
	}

	public int numAliveNeighbors(int x, int y) {
		return numAlive(neighborsBitString(x, y));
	}

	private int numAlive(String s) {
		int aliveFreq = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			int val = Character.getNumericValue(c);
			if (val > 0)
				++aliveFreq;
		}
		return aliveFreq;
	}

	public int safeGet(int x, int y) {
		return grid[posMod(y, height)][posMod(x, width)];
	}

	public static int posMod(int a, int b) {
		return (a % b + b) % b;
	}

	public void nextIteration() {
		int[][] nextGrid = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				nextGrid[i][j] = nextStatus(j, i);
			}
		}
		// Change array.
		grid = nextGrid;
	}

	private int nextStatus(int x, int y) {
		int aliveNeighbors = numAliveNeighbors(x, y);
		int myValue = getFieldIndex(x, y);
		if (!useRuleTable) {
			return simpleSurvival(aliveNeighbors, myValue);
		} else {
			return ruleTable.get(neighborsBitString(x, y));
		}
	}

	private int simpleSurvival(int aliveNeighbors, int myValue) {
		if (myValue > 0) {
			// Live cell logic.
			if (aliveNeighbors < underPopulation) {
				// Die.
				return 0;
			}
			if (aliveNeighbors > overPopulation) {
				// Die.
				return 0;
			} else {
				// Live. Increment survival counter.
				return myValue + 1;
			}
		} else {
			// Dead cell logic.
			if (aliveNeighbors == overPopulation) {
				// Reproduction.
				return myValue + 1;
			} else {
				return 0;
			}
		}
	}

	public void iterateAndPrint() {
		this.nextIteration();
		System.out.println(this);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static void main(String[] args) {
		Field f = new Field(20, 10);
		f.randomize();
		HashMap<String, Integer> m = generate2DCARuleTable(30);
		for (Map.Entry<String, Integer> entry : m.entrySet()) {
			String k = entry.getKey();
			Integer i = entry.getValue();
			System.out.println(k + " --> " + i);
		}
	}
}
