import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
/*graph class to store source,destination,weight*/
class Graph{
    int src,des,wt;
    Graph(int s,int d,int w){
        src=s;
        des=d;
        wt=w;
    }
}

public class Solution {

    /*
     * Complete the rustMurdered function below.
     */
    static void dijkstras(ArrayList<LinkedList<Graph>> ar,int dist[],int n,int s){
        boolean visited[]=new boolean[n+1];//visited arrary to make sure node is visited or not
        for(int i=0;i<n-1;i++){//for n edges n-1 vertices
            visited[s]=true;
            for(Graph g:ar.get(s)){//iterating to get nodes which are connected to s
                if(!visited[g.src]){//we can visit only onec
                if(g.src!=s){
                    if(dist[g.src]>dist[s]+g.wt){//checking wheather path is reduced or not
                        dist[g.src]=dist[s]+g.wt;
                    }
                }}
                if(!visited[g.des]){//it is undirected graph so we should check vice-versa condition
                if(g.des!=s){
                    if(dist[g.des]>dist[s]+g.wt){////checking wheather path is reduced or not
                        dist[g.des]=dist[s]+g.wt;}
                }}

            }
            int min=Integer.MAX_VALUE,j=0,val=0;
            for(j=1;j<=n;j++){//iterating to get minimum distance from dist array
                if(!visited[j] & dist[j]<min){
                    min=dist[j];
                    val=j;
                }
            }
            s=val;//s is source
        } 
        
    }

    static int[] rustMurdered(int n, int[][] roads,int s) {
        /*
         * Write your code here.
         */
         ArrayList<ArrayList<Integer>> ar=new ArrayList<ArrayList<Integer>>();//this is used to store adjacency list(city roads)
         for(int i=0;i<=n;i++){
             ar.add(i,new ArrayList<Integer>());
         }

         for(int i=0;i<roads.length;i++){//vice-versa connections are added
             ar.get(roads[i][0]).add(roads[i][1]);
             ar.get(roads[i][1]).add(roads[i][0]);
         }

        ArrayList<LinkedList<Graph>> a=new ArrayList<LinkedList<Graph>>();//creating new adjacency list for village roads
         for(int i=0;i<=n;i++){
             a.add(i,new LinkedList<Graph>());
         }         
        int i=0;
        for(i=1;i<=n;i++){
            boolean visited[]=new boolean[n+1];////visited arrary to make sure node is visited or not
            visited[i]=true;
            for(Integer j:ar.get(i)){
                visited[j]=true;
            }
            for(int l=1;l<=n;l++){//creating graph object and storing it a adjacency list
                if(!visited[l]){
                    Graph g=new Graph(i,l,1);
                    a.get(i).add(g);
                    a.get(l).add(g);
                }
            }
        }
        int dist[]=new int[n+1];
        for(i=1;i<=n;i++){
            if(s!=i){
                dist[i]=Integer.MAX_VALUE;
            }
        }        
        dijkstras(a,dist,n,s);//calling dijkstras to get minimum distance to all nodes from source
        int finaldist[]=new int[n-1];//final output array with mimum distances
        int ind=0;
        for(i=1;i<=n;i++){
            if(dist[i]!=0){
                    finaldist[ind++]=dist[i];
            }
        }
        return finaldist;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(scanner.nextLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0].trim());

            int m = Integer.parseInt(nm[1].trim());

            int[][] roads = new int[m][2];

            for (int roadsRowItr = 0; roadsRowItr < m; roadsRowItr++) {
                String[] roadsRowItems = scanner.nextLine().split(" ");

                for (int roadsColumnItr = 0; roadsColumnItr < 2; roadsColumnItr++) {
                    int roadsItem = Integer.parseInt(roadsRowItems[roadsColumnItr].trim());
                    roads[roadsRowItr][roadsColumnItr] = roadsItem;
                }
            }

            int s = Integer.parseInt(scanner.nextLine().trim());

            int[] result = rustMurdered(n, roads,s);

            for (int resultItr = 0; resultItr < result.length; resultItr++) {
                bufferedWriter.write(String.valueOf(result[resultItr]));

                if (resultItr != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}
