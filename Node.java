public class Node {
	int value;
	double freq;
	Node left;
	Node right;
	int sum;
	Node parent;

	public Node(int value, double freq) {
		this.value = value;
		this.freq = freq;
		left = null;
		right = null;
		sum = 0;
		parent = null;
	}

	public boolean equals(Node that) {
		if (that == null) return false;
		if (freq == that.getFreq()) {
			if (value == that.getValue()) {
				return true;
			}
		}
		return false;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public Node getParent() {
		return parent;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public void setFreq(double freq) {
		this.freq = freq;
	}

	public double getFreq() {
		return freq;
	}

	public int getValue() {
		return value;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getSum() {
		return sum;
	}
}