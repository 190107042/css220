import java.util.*;


//Disadvantages:
//Tree based, tree can get tall
//Find too expensive
public class QuickUnionUF{
	private int id[];

	// Initialize: O(n)
	public QuickUnionUF(int N){
		id = new int[N];
		for(int i=0; i<N; i++)
			id[i] = i;
	}

	// Find Root: O(n)
	private int root(int i){
		while(i != id[i])
			i = id[i];
		return i;
	}

	// Find: O(n) cause of root function
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}

	// Union: O(n) cause of root function
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		// but union itself O(1)
		id[i] = j;
	}

}