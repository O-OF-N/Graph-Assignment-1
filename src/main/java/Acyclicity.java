import java.util.*;

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
    Stack<Integer> nodes = dfs(adj);
    ArrayList<Integer>[] transpose = transpose(adj);
    Arrays.stream(transpose).forEach(System.out::println);
    return 10000;
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

private static Integer[][] dfsReverse(ArrayList<Integer>[] adj){
    return null;
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
    nodes.stream().forEach(System.out::println);
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

