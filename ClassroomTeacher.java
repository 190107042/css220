import java.util.*;

public class ClassroomTeacher{
	static int[] classPreference(int[] favorite, int n, int m){
		int count[] = new int[n];
		int list[] = new int[n];
		for(int i=0; i<n; i++)
			list[i] = i;

		for(int i=0; i<m; i++)
			count[favorite[i]] ++;

		for(int i=0; i<n; i++){
			for(int j=i+1; j<n; j++){
				if(count[i] < count[j]){
					int temp = count[i];
					count[i] = count[j];
					count[j] = temp;

					temp = list[i];
					list[i] = list[j];
					list[j] = temp; 
				}
			}
		}

		return list;
	}

	static boolean checkPrefer(int classPrefer[][], int n, int class1, int newTeacher, int oldTeacher){
        for(int i=0; i<n; i++){
            if(classPrefer[class1][i] == oldTeacher)
                return false;
            
            if(classPrefer[class1][i] == newTeacher)
                return true;
        }
        return true;
    }
    
    static int[] stableMatch(int teacherPrefer[][], int classPrefer[][],int n){
        int partner[] = new int[n];
        boolean teacherFree[] = new boolean[n];
        
        Arrays.fill(partner, -1);
        int freeClasses = n;
        
        while(freeClasses > 0){
            int teacher;
            for(teacher = 0; teacher<n; teacher++)
                if(teacherFree[teacher] == false)
                    break;
            
            for(int i=0; i<n && teacherFree[teacher] == false; i++){
                int class1 = teacherPrefer[teacher][i];
                
                if(partner[class1] == -1){
                    partner[class1] = teacher;
                    teacherFree[teacher] = true;
                    freeClasses --;
                }
                else{
                    int oldTeacher = partner[class1];

                    if(checkPrefer(classPrefer, n, class1, teacher, oldTeacher)){
                        partner[class1] = teacher;
                        teacherFree[teacher] = true;
                        teacherFree[oldTeacher] = false;
                    }
                }
            }
        }
        
        return partner;
    }
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
    
        int teacherPrefer[][] = new int[n][n];
        for(int i=0; i<n; i++)
            for(int j=0; j<n; j++)
                teacherPrefer[i][j] = sc.nextInt()-1;

        int classPrefer[][] = new int[n][m];
        for(int i=0; i<n; i++){
        	int favorite[] = new int[m];
        	for(int j=0; j<m; j++)
        		favorite[i] = sc.nextInt()-1;
        	classPrefer[i] = classPreference(favorite, n, m);
        }

        int[] partner = stableMatch(teacherPrefer, classPrefer, n);

        System.out.println("Class - Teacher:");
        for(int i=0; i<n; i++){
        	System.out.println((i+1) + " - " + (partner[i]+1));
        }	
    }
}
