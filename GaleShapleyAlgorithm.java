import java.util.*;

public class GaleShapleyAlgorithm{
    
    static boolean checkPrefer(int prefer[][], int n, int w, int m, int m1){
        for(int i=0; i<n; i++){
            if(prefer[w][i] == m1)
                return true;
            
            if(prefer[w][i] == m)
                return false;
        }
        return false;
    }
    
    static void stablePartners(int prefer[][], n){
        int wPartner[] = new int[n];
        boolean mFree[] = new boolean[n];
        
        Arrays.fill(wPartner, -1);
        int freeCount = n;
        
        while(freeCount > 0){
            int m;
            for(m = 0; m<n; m++)
                if(mFree[m] == false)
                    break;
            
            for(int i=0; i<n && mFree[m] == false; i++){
                int w = prefer[m][i];
                
                if(wPartner[w - n] == -1){
                    wPartner[w - n] = m;
                    mFree[m] = true;
                    freeCount --;
                }
                else{
                    int m1 = wPartner[w - n];
                    
                    if(!checkPrefer(prefer, n, w, m, m1)){
                        wPartner[w - n] = m;
                        mFree[m] = true;
                        mFree[m1] = false;
                    }
                }
            }
        }
        
        System.out.println("Partners:");
        for(int i=0; i<n; i++)
            System.out.println( (i + n) + " - " + wPartner[i]);
        
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
    
        int prefer[][] = new int[n*2][n];
    
        for(int i=0; i<n*2; i++){
            for(int j=0; j<n; j++)
                prefer[i][j] = sc.nextInt();
        stablePartners(prefer, n);
    }
}
