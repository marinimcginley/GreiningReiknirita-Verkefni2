import java.util.Collections;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class GreedyTree {
	private int[] values;
	private double[] frequencies;
	private Node tree;
	private int n;
	private int m;

	public GreedyTree(int n, int m) {
		this.n = n;
		this.m = m;
		values = new int[n];
		frequencies = new double[n];
		createValues();
		allocateFrequencies();
		createTree();
	}

	public void startSearch() {
		int temp = m;
		while (temp > 0) {
			for (int i = 0; i < n; i++) {
				int max = (int) (temp * frequencies[i]);
				for (int j = 0; j < max; j++) {
					if (temp == 0) break;
					search(values[i]);
					temp--;
				}
				if (temp == 0) break;
			}
		}
	}

	public void createValues() {
		for (int i = 0; i < n; i++) {
			values[i] = i + 1;
		}
		shuffleValues();
	}

	public void shuffleValues() {
		Collections.shuffle(Arrays.asList(values));
	}

	public void allocateFrequencies() {
		double[] a = createArray();
		double C = 1 / sumOfArray(a);
		frequencies = new double[values.length];
		for (int i = 0; i < frequencies.length; i++) {
			frequencies[i] = C / i;
		}
	}

	public double sumOfArray(double[] array) {
		double sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}

	public double[] createArray() {
		double[] a = new double[n];
		for (int i = 0; i < n; i++) {
			a[i] = 1 / (i+1);
		}
		return a;
	}

	public void createTree() {
		tree = new Node(values[0], frequencies[0]);
		for (int i = 1; i < values.length; i++) {
			addNode(values[i], frequencies[i], tree);
		}
	}

	public void addNode(int val, double freq, Node tempRoot) {
		if (val > tempRoot.getValue()) {
			if (tempRoot.getRight() == null) {
				tempRoot.setRight(new Node(val, freq));
			}
			else addNode(val, freq, tempRoot.getRight());
		} else {
			if (tempRoot.getLeft() == null) {
				tempRoot.setLeft(new Node(val, freq));
			}
			else addNode(val, freq, tempRoot.getLeft());
		}
	}

	public boolean search(int val) {
		return search(val, tree);
	}

	public boolean search(int val, Node tempRoot) {
		if (val == tempRoot.getValue()) {
			return true;
		}
		if (val > tempRoot.getValue()) {
			if (tempRoot.getRight() == null) {
				return false;
			}
			else return search(val, tempRoot.getRight());
		} else {
			if (tempRoot.getLeft() == null) {
				return false;
			}
			else return search(val, tempRoot.getLeft());
		}
	}
}