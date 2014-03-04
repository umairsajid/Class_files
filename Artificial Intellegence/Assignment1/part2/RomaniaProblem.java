/**
 * An implementation of the Romania map problem
 * 
 */ 
import java.util.ArrayList;
public class RomaniaProblem extends Problem {
  
    // The different locations in the simplified map of part of Romania 
    public static final int ORADEA = 0;
    public static final int ZERIND = 1;
    public static final int ARAD = 2;
    public static final int TIMISOARA = 3;
    public static final int LUGOJ = 4;
    public static final int MEHADIA = 5;
    public static final int DOBRETA = 6;
    public static final int SIBIU = 7;
    public static final int RIMNICU_VILCEA = 8;
    public static final int CRAIOVA = 9;
    public static final int FAGARAS = 10;
    public static final int PITESTI = 11;
    public static final int GIURGIU = 12;
    public static final int BUCHAREST = 13;
    public static final int NEAMT = 14;
    public static final int URZICENI = 15;
    public static final int IASI = 16;
    public static final int VASLUI = 17;
    public static final int HIRSOVA = 18;
    public static final int EFORIE = 19;
    
    public static final String[] CITIES ={ "Oradea","Zerind","Arad","Timisoara","Lugoj","Mehadia","Dobreta",
                                       "Sibiu","RimnicuVilcea","Craiova","Fagaras","Pitesti","Giurgiu",
                                       "Bucharest","Neamt","Urziceni","Iasi","Vaslui","Hirsova","Eforie"
                                       };
    
    public static final double[][] DIST =  {   
                //O    Z    A    T    L    M    D    S    R    C    F    P    G    B    N    U    I    V    H    E          
        /*O*/ {   0,   71,  -1,  -1,  -1,  -1,  -1,  151, -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*O*/ 
        /*Z*/ {   71,  0,   75,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*Z*/ 
        /*A*/ {   -1,   75,  0,  118, -1,  -1,  -1,  140, -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*A*/ 
        /*T*/ {   -1,  -1,  118,  0,  111, -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*T*/ 
        /*L*/ {   -1,  -1,  -1,  111, 0,   70,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*L*/ 
        /*M*/ {   -1,  -1,  -1,  -1,  70,  0,   75,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*M*/ 
        /*D*/ {   -1,  -1,  -1,  -1,  -1,  75,  0,   -1,  -1,  120, -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*D*/ 
        /*S*/ {   151, -1,  140, -1,  -1,  -1,  -1,  0,   80,  -1,  99,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*S*/ 
        /*R*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  80,  0,   146, -1,  97,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*R*/ 
        /*C*/ {   -1,  -1,  -1,  -1,  -1,  -1,  120, -1,  146, 0,   -1,  138, -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1   }, /*C*/ 
        /*F*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  99,  -1,  -1,  0,   -1,  -1,  211, -1,  -1,  -1,  -1,  -1,  -1   }, /*F*/ 
        /*P*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  97,  138, -1,  0,   -1,  101, -1,  -1,  -1,  -1,  -1,  -1   }, /*P*/ 
        /*G*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  0,   90,  -1,  -1,  -1,  -1,  -1,  -1   }, /*G*/ 
        /*B*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  211, 101, 90,  0,   -1,  85,  -1,  -1,  -1,  -1   }, /*B*/ 
        /*N*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  0,   -1,  87,  -1,  -1,  -1   }, /*N*/ 
        /*U*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  85,  -1,  0,   -1,  142, 98,  -1   }, /*U*/ 
        /*I*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  87,  -1,  0,   92,  -1,  -1   }, /*I*/ 
        /*V*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  142, 92,  0,   -1,  -1   }, /*V*/ 
        /*H*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  98,  -1,  -1,  0,   86   }, /*H*/ 
        /*E*/ {   -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  -1,  86,  0    }  /*E*/ 
                //O    Z    A    T    L    M    D    S    R    C    F    P    G    B    N    U    I    V    H    E         
     };

    public static double[] SLD =
      //O    Z    A    T    L    M    D    S    R    C    F    P    G    B    N    U    I    V    H    E        
      { 380, 374, 366, 329, 244, 241, 242, 253, 193, 160, 178, 98,  77,  0,   234, 80,  226, 199, 151, 161 };
    
    private State  start;    
    
    public static void printCities() {
        for (int i=0; i<DIST.length; i++)
        {
            System.out.println(CITIES[i]);
            for (int j=0; j<DIST[i].length; j++)
            {
                if (DIST[i][j]>0)
                    System.out.printf("\t\t--> %s = %3.0f\n",CITIES[j],DIST[i][j]);
            }
            System.out.println();
        }
    }
    
    public RomaniaProblem(String beginCity) {
        //Find index.
        int idx=-1;
        for(int i=0; i<CITIES.length; i++)
        {
            if(CITIES[i].equalsIgnoreCase(beginCity))
            {
                idx=i;
                break;
            }
        }
        if (idx==-1)
        {
            System.out.println("City name does not exist in Romania");
            System.exit(1);
        }
        start = new State(beginCity,idx);    
    }
    
    public State getStartState(){
        return start;
    }
    
    public  Node createNode(State s, int d, double g, double h, Node p){
        return new RomaniaNode(this,start,d,g,h,p);
    }
    
    public  double getStepCost(Node n1, Node n2) {
        int i = n1.getState().getID();
        int j = n2.getState().getID();
        return DIST[i][j];
    }
    
    public double getHeuristicValue(Node n){ //Not needed unless using informed search
        int i = n.getState().getID();       
        return SLD[i];
    }
    
    public  boolean isGoalState(State state){
       return (state.getValue().equals(CITIES[BUCHAREST])? true:false) ;
    }
    
    //utility function to get the hard-coded neighbors of each city
    //call delegated by node
    public ArrayList<Node> getNodeSuccessors(RomaniaNode node) {
        ArrayList<Node> succs = new ArrayList<Node>(10);           
        State current = node.getState();
           
        //Generate the successors of current
        int idx = current.getID();
        for(int i=0; i<DIST[idx].length; i++)
        {
            if (DIST[idx][i] > 0)          
            {
                //Make a new state, and node and add to successors
                State temp = new State(CITIES[i],i);
                
                RomaniaNode child = new RomaniaNode(this, temp,node.getDepth(),0,0,node);
                double g = node.getG()+ getStepCost(node,child);
                double h = getHeuristicValue(child);
                child.setG(g);
                child.setH(g);
                succs.add(child);
            }
        }

        if (succs.size()==0)
            return null;
        else
            return succs;   
    }

}
