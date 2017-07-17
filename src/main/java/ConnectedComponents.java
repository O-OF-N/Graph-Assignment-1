import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConnectedComponents {
    private static void reach(ArrayList<Integer>[] adj, int source,Set<Integer> unvisited) {
        unvisited.remove(source);
        System.out.println(source+":::"+unvisited);
        for(Integer node: adj[source])
            if(unvisited.contains(node)) reach(adj,node,unvisited);
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
        Arrays.stream(adj).forEach(System.out::println);
        Set<Integer> unvisited = IntStream.range(0,n).boxed().collect
                (Collectors.toSet());
        System.out.println(unvisited);
        int count = 0;
        while(unvisited.size()>0){
            reach(adj,unvisited.iterator().next(),unvisited);
            System.out.println("u="+unvisited);
            count++;
        }
        System.out.println(count);
    }
}

