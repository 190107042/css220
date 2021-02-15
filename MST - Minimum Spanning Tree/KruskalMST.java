import java.util.*;

public class KruskalMST{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int V = sc.nextInt();//num of Vertices
		int E = sc.nextInt();//num of Edges

		Graph graph = new Graph(V);
		Edge edge[] = new Edge[E];

		for(int i=0; i<E; i++){
			int v1 = sc.nextInt(); //first Vertex
			int v2 = sc.nextInt(); //second Vertex
			int weight = sc.nextInt(); //weight of Edge between first end second Vertices

			edge[i] = new Edge(v1, v2, weight);
		}
		graph.addEdges(edge);

		graph.KruskalMST();
	}
}

class Subset{
	int parent, rank;
}

class Edge implements Comparable<Edge>{
	int v1, v2, weight;
	Edge(){
		
	}
	Edge(int v1,int v2, int weight){
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public int compareTo(Edge o){
		return this.weight - o.weight;
	}
}

class Graph{

	private int V, E;
	private Edge edge[]; 

	Graph(int V){
		this.V = V;
	}

	public void addEdges(Edge edge[]){
		E = edge.length; 
		this.edge = edge;
	}

	int find(Subset subsets[], int i){
		if (subsets[i].parent != i) 
			subsets[i].parent = find(subsets, subsets[i].parent);
		return subsets[i].parent;
	}

	void union(Subset subsets[], int x, int y){
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);
 
        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;
        else {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }

    void KruskalMST(){
        Edge result[] = new Edge[V]; 
       
        // An index variable, used for result[]
        int e = 0; 
       
        // An index variable, used for sorted edges
        int i = 0; 
        for (i = 0; i < V; ++i)
            result[i] = new Edge();
 
        Arrays.sort(edge);
 
        Subset subsets[] = new Subset[V];
        for (i = 0; i < V; ++i){
            subsets[i] = new Subset();
            subsets[i].parent = i;
            subsets[i].rank = 0;
        }
        i = 0; 
 
        while (e < V - 1) {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
 
            int x = find(subsets, next_edge.v1);
            int y = find(subsets, next_edge.v2);
 
            if (x != y) {
                result[e++] = next_edge;
                union(subsets, x, y);
            }
        }
 
        System.out.println("Following are the edges in " + "the constructed Kruskal's MST");
        
        int minimumCost = 0;
        for (i = 0; i < e; ++i){
            System.out.println(result[i].v1 + " -- " + result[i].v2 + " == " + result[i].weight);
            minimumCost += result[i].weight;
        }
        System.out.println("Minimum Cost Spanning Tree " + minimumCost);
    }
}