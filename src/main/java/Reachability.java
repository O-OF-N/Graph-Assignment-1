import java.util.*;
import java.util.stream.Collectors;

public class Reachability {

private static int numberOfComponents(ArrayList<Integer>[] graph, int source, int
        destination, Set<Integer> visited){
    ArrayList<Integer> nodes = graph[source];
    visited.add(source);
    boolean found = nodes.stream().filter(node-> node == destination).collect
            (Collectors.toList()).size()>0;
    if(found) return 1;
    for(Integer node:nodes){
        if(visited.contains(node)) continue;
        int result = numberOfComponents(graph,node,destination,visited);
        if(result==1) return 1;
    }
    return 0;
}

public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
    for (int i = 0; i < n; i++) {
        adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < m; i++) {
        int x, y;
        x = scanner.nextInt();
        y = scanner.nextInt();
        adj[x - 1].add(y - 1);
        adj[y - 1].add(x - 1);
    }
    int u = scanner.nextInt();
    int v = scanner.nextInt();
    Long curr = Calendar.getInstance().getTimeInMillis();
    System.out.println(numberOfComponents(adj,u-1,v-1,new HashSet<>()));
    System.out.println(Calendar.getInstance().getTimeInMillis()-curr);
}
}

