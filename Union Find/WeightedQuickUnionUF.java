import java.util.*;


/*
	Depth of any node x is at most logN
*/
public class WeightedQuickUnionUF{
	private int id[];
	private int sz[];

	// Initialize: O(n)
	public WeightedQuickUnionUF(int N){
		id = new int[N];
		sz = new int[N];

		for(int i=0; i<N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}

	// Find Root: O(logN) because tree balanced
	private int root(int i){
		while(i != id[i])
			i = id[i];
		return i;
	}

	// Find: O(logN) cause of root function
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}

	// Union: O(logN) cause of root function
	public void union(int p, int q){
		int i = root(p);
		int j = root(q);
		// but union itself O(1)
		id[i] = j;
		if(i == j) return;
		if(sz[i] < sz[j]){
			id[i] = id[j];
			sz[j] += sz[i];
		}
		else{
			id[j] = id[i];
			sz[i] += sz[j];
		}
	}

}