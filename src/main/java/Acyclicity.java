import java.util.*;
import java.util.stream.Collectors;

public class Acyclicity {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
        }
        System.out.println(acyclic(adj));
    }

    private static int acyclic(ArrayList<Integer>[] adj) {
        //Find the node order
        Stack<Integer> nodes = dfs(adj);
        //Transpose of the matrix
        ArrayList<Integer>[] transpose = transpose(adj);
        //Find Scc
        List<Stack<Integer>> scc = dfsReverse(transpose,nodes);
        return scc.stream().filter(c->c.size()>1).collect(Collectors.toList()).size()>0?1:0;
    }

    private static ArrayList<Integer>[] transpose(final ArrayList<Integer>[]
                                                          adj) {
        ArrayList<Integer>[] transposed = new ArrayList[adj.length];
        for (int i = 0; i < transposed.length; i++) {
            transposed[i] = new ArrayList<>();
        }
        for (int i = 0; i < adj.length; i++) {
            ArrayList<Integer> connections = adj[i];
            for (int j = 0; j < connections.size(); j++) {
                transposed[connections.get(j)].add(i);
            }
        }
        return transposed;
    }

    private static List<Stack<Integer>> dfsReverse(ArrayList<Integer>[] adj,Stack<Integer> nodes){
        List<Stack<Integer>> scc = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        while(!nodes.empty()){
            Integer nextNode = nodes.pop();
            scc.add(explore(adj,nextNode,visited,new Stack<>()));
        }
        return scc;
    }

    private static Stack<Integer> dfs(ArrayList<Integer>[] adj){
        Set<Integer> visited = new HashSet<>();
        Integer nextNode = nextUnvisitedNode(adj,visited);
        Stack<Integer> nodes = new Stack<>();
        while(nextNode!= null) {
            explore(adj, nextNode, visited, nodes);
            if(visited.size() == adj.length) nextNode = null;
            else
                nextNode= nextUnvisitedNode(adj,visited);
        }
        return nodes;
    }

    private static Stack<Integer> explore(ArrayList<Integer>[] adj, Integer
            nextNode, Set<Integer> visited, Stack<Integer> nodes) {
        if(visited.contains(nextNode)) return nodes;
        else{
            visited.add(nextNode);
            ArrayList<Integer> next = adj[nextNode];
            for(int i=0;i<next.size();i++){
                if(next.get(i)!= null) {
                    explore(adj, next.get(i), visited, nodes);
                }
            }
            nodes.add(nextNode);
            return nodes;
        }
    }

    private static Integer nextUnvisitedNode(ArrayList<Integer>[] adj,
                                             Set<Integer> visited){
        int i =0;
        while(i<adj.length)
            if(!visited.contains(i)) return i;
            else i++;
        return null;
    }
}

