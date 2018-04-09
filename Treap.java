import java.util.Scanner;
import java.util.Collections;
import java.util.Arrays;

public class Treap {
	private double[] priorities;
	private int[] searches;
	private int[] values;
	private Node tree;
	private int n;
	private int m;

	public Treap(int n, int m) {
		this.n = n;
		this.m = m;
		values = new int[n];
		priorities = new double[n];
		createValues();
		createPriorities();
		createTree();
	}

	public void createValues() {
		for (int i = 0; i < n; i++) {
			values[i] = i+1;
		}
		shuffleValues();
	}

	public void shuffleValues() {
		Collections.shuffle(Arrays.asList(values));
	}

	public void createPriorities() {
		int temp = m;
		int i;
		for (i = 0; i < n; i++) {
			int random = (int) (Math.random()*m/10);
			if ((temp-random) <= 0) {
				priorities[i] = temp;
				break;
			}
			temp -= random;
			priorities[i] = random;
		}
		for (int j = i; j < n; j++) {
			priorities[j] = 0;
		}
	}


	// priorities[i] == 1 er oftast leitað að því hann hækkar upp í n
	public void startSearch() {
		int temp = m;
		while (temp > 0) {
			for (int i = 0; i < n; i++) {
				if (temp == 0) break;
				if (priorities[i] == n) continue;
				search(values[i]);
				priorities[i]++;
				temp--;;
			}
		}

	}

	public void createTree() {
		tree = new Node(values[0], priorities[0]);
		for (int i = 1; i < values.length; i++) {
			addNode(values[i], priorities[i]);
		}
	}

	public void addNode(int val, double priority) {
		findRightPlaceToInsert(val, priority, tree);
	}

	public void findRightPlaceToInsert(int val, double priority, Node tempRoot) {
		if (val > tempRoot.getValue()) {
			if (tempRoot.getRight() == null) {
				tempRoot.setRight(new Node(val, priority));
				tempRoot.getRight().setParent(tempRoot);
				addNode(tempRoot.getRight());
			} else {
				findRightPlaceToInsert(val, priority, tempRoot.getRight());
			}
		}
		else {
			if (tempRoot.getLeft() == null) {
				tempRoot.setLeft(new Node(val, priority));
				tempRoot.getLeft().setParent(tempRoot);
				addNode(tempRoot.getLeft());
			} else {
				findRightPlaceToInsert(val, priority, tempRoot.getLeft());
			}
		}
	}

	// child er einhver hnútur í trénu
	public void addNode(Node child) {
		// ef child er rót nú þegar
		if (child.getParent() == null) return;
		// ef child hefur hærri forgang en foreldri sitt
		if (child.getFreq() < child.getParent().getFreq()) {
			// ef barn er vinstra barn foreldris síns
			if (child.equals(child.getParent().getLeft())) {
				//System.out.print("gubb");
				Node temp = LeftRotation(child);
				child = temp;
				addNode(child);
			} else {
				Node temp = RightRotation(child);
				child = temp;
				addNode(child);
			}
		}
	}

	public Node LeftRotation(Node x) {
		Node y = x.getParent();
		Node temp = x.getRight();
		if (y.getParent() == null) {
			x.setParent(null);
			tree = x;
		}
		else if (y.equals(y.getParent().getRight())) {
			Node parentOfY = y.getParent();
			parentOfY.setRight(x);
			x.setParent(parentOfY);
		} else {
			Node parentOfY = y.getParent();
			parentOfY.setLeft(x);
			x.setParent(parentOfY);
		}
		x.setRight(y);
		y.setParent(x);
		y.setLeft(temp);
		if (temp != null) temp.setParent(y);
		return y;
	}

	public Node RightRotation(Node y) {
		Node x = y.getParent();
		Node temp = y.getLeft();
		if (x.getParent() == null) {
			y.setParent(null);
			tree = y;
		} else if (x.equals(x.getParent().getLeft())) {
			Node parentOfX = x.getParent();
			y.setParent(parentOfX);
			parentOfX.setLeft(y);

		} else {
			Node parentOfX = x.getParent();
			parentOfX.setRight(y);
			y.setParent(parentOfX);
		}
		x.setParent(y);
		y.setLeft(x);
		x.setRight(temp);
		if (temp != null) temp.setParent(x);
		return y;
	}

	public boolean search(int val) {
		return search(val, tree);
	}

	public boolean search(int val, Node tempRoot) {
		if (tempRoot == null) return false;
		/* 
		Ef réttur hnútur hefur verið fundinn er slembigildi búið til
		og hnútur svo færður upp tréð ef slembigildið er hærra en forgangur 
		hnútsins
		*/
		if (val == tempRoot.getValue()) {
			double random = Math.random() * values.length + 1;
			if (random > tempRoot.getFreq()) {
				tempRoot.setFreq(random);
				addNode(tempRoot);
			}
			return true;
		} else if (val < tempRoot.getValue()) {
			return search(val, tempRoot.getLeft());
		} else {
			return search(val, tempRoot.getRight());
		}
	}

}


/*
Treap er búið til á venjulegan hátt með öllum lyklunum, 
en við hverja leit að gildi x í trénu, þá er búin til ný slembitala 
og ef hún er lægri en núverandi forgangur x þá er nýja slembitalan 
notuð sem forgangurinn og x hugsanlega fært upp tréð.
*/