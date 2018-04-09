import java.util.Scanner;
import java.lang.System;

public class Test {
	private int n;
	private int m;

	public Test() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Veldu fjölda lykla: ");
		n = sc.nextInt();
		System.out.println("Veldu fjölda leita: ");
		m = sc.nextInt();
	}

	public void testTreap() {
		long startTimeOfMakingTree = System.nanoTime();
		Treap treap = new Treap(n, m);
		long estimatedTimeOfMakingTree = System.nanoTime() - startTimeOfMakingTree;
		System.out.println("Uppsetning á treap með " + n + " lyklum tók: " + estimatedTimeOfMakingTree + " nanósekúndur");

		long startTimeOfSearches = System.nanoTime();
		treap.startSearch();
		long estimatedTimeOfSearching = System.nanoTime() - startTimeOfSearches;
		System.out.println(m + " leitir á treap tóku: " + estimatedTimeOfSearching + " nanósekúndur");
	}

	public void testGreedyTree() {
		long startTimeOfMakingTree = System.nanoTime();
		GreedyTree greedytree = new GreedyTree(n, m);
		long estimatedTimeOfMakingTree = System.nanoTime() - startTimeOfMakingTree;
		System.out.println("Uppsetning á gráðugu tré með " + n + " lyklum tók: " + estimatedTimeOfMakingTree + " nanósekúndur");

		long startTimeOfSearches = System.nanoTime();
		greedytree.startSearch();
		long estimatedTimeOfSearching = System.nanoTime() - startTimeOfSearches;
		System.out.println(m + " leitir á gráðugu tré tóku: " + estimatedTimeOfSearching + " nanósekúndur");
	}

	public static void main(String[] args) {
		Test test = new Test();
		test.testTreap();
		test.testGreedyTree();
	}
}

