// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.LinkedList;
// import java.util.Map;
// import java.util.Queue;

// public class weight_graph {
//     public static void main(String[] args) {
//     Map<vertex, Map<vertex, Integer>> g;
//     vertex a = new vertex('A');
//     vertex b = new vertex('B');
//     vertex c = new vertex('C');
//     vertex d = new vertex('D');
//     vertex e = new vertex('E');
//     Map<vertex, Integer> aNb = new HashMap<vertex, Integer>();
//     Map<vertex, Integer> bNb = new HashMap<vertex, Integer>();
//     Map<vertex, Integer> cNb = new HashMap<vertex, Integer>();
//     Map<vertex, Integer> dNb = new HashMap<vertex, Integer>();
//     Map<vertex, Integer> eNb = new HashMap<vertex, Integer>();

//     aNb.put(b, 1);
//     aNb.put(d, 2);
//     bNb.put(c, 2);
//     cNb.put(d, 2);
//     cNb.put(e, 8);
//     dNb.put(c, 2);
//     dNb.put(e, 3);
//     g.put(a, aNb);
//     g.put(b, bNb);
//     g.put(c, cNb);
//     g.put(d, dNb);
//     g.put(e, eNb);
//     }
//     /*
//     * withe-0
//     * gray-1
//     * black-2
//     */
//     public static void BFS(Map<vertex, Map<vertex, Integer>> G, vertex s) {
// 		s.color = 1;
// 		s.d = 0;
// 		s.pi = null; 
// 		Queue<vertex> Q = new LinkedList<>();
// 		// Q = null;
// 		Q.add(s);
// 		while (!Q.isEmpty()) {
// 			vertex u = Q.poll();
// 			ArrayList<vertex> uL = G.get(u);
// 			for (vertex v : uL) {
// 				if (v.color == 0) {
// 					v.color = 1;
// 					v.d = u.d + 1;
// 					v.pi = u;
// 					Q.add(v);
// 				}
// 			}
// 			u.color = 2;
// 			System.out.println(u);
// 		}
//     }
// }

// class vertex{
//     char key;
//     int d;
//     int color = 0;
//     vertex pi;

//     public vertex(char key) {
//         this.key = key;
//     }

//     public char getKey() {
//         return key;
//     }

//     public void setKey(char key) {
//         this.key = key;
//     }

// }

// // class grahp {
// //     private class vertex{
        
// //     }
// //     char key;
// //     Map<vertex, Integer> neb;
// //     Map<vertex, Map<vertex, Integer>> vertex;
// //     vertex(char key){
// //         this.key = key;
// //     }

// //     public void addNeb(vertex v, int weight) {
// //         neb.put(v, weight);
// //     }

// // }