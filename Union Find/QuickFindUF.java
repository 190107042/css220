import java.util.*;

public class QuickFindUF{
	private int id[];

	// Initialize: O(n)
	public QuickFindUF(int N){
		id = new int[N];
		for(int i=0; i<N; i++)
			id[i] = i;
	}

	// Find: O(1)
	public boolean connected(int p, int q){
		return id[p] == id[q];
	}

	// Union: O(n)
	public void union(int p, int q){
		int pid = id[p];
		int qid = id[q];

		for(int i = 0; i < id.length; i++)
			if(id[i] == pid) 
				id[i] = qid;
	}

}