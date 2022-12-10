package solution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class graph {
    public static void main(String[] args) {
        HashMap<vertex, ArrayList<vertex>> G = new HashMap<vertex, ArrayList<vertex>>();
        vertex r = new vertex('r');
        vertex s = new vertex('s');
        vertex t = new vertex('t');
        vertex u = new vertex('u');
        vertex v = new vertex('v');
        vertex w = new vertex('w');
        vertex x = new vertex('x');
        vertex y = new vertex('y');
        ArrayList<vertex> rNb = new ArrayList<vertex>(Arrays.asList(s, v));
        ArrayList<vertex> sNb = new ArrayList<vertex>(Arrays.asList(r, w));
        ArrayList<vertex> tNb = new ArrayList<vertex>(Arrays.asList(w, x, u));
        ArrayList<vertex> uNb = new ArrayList<vertex>(Arrays.asList(t, x, y));
        ArrayList<vertex> vNb = new ArrayList<vertex>(Arrays.asList(r));
        ArrayList<vertex> wNb = new ArrayList<vertex>(Arrays.asList(s, t, x));
        ArrayList<vertex> xNb = new ArrayList<vertex>(Arrays.asList(w, t, u ,y));
        ArrayList<vertex> yNb = new ArrayList<vertex>(Arrays.asList(x, u));
        G.put(r, rNb);G.put(s, sNb);G.put(t, tNb);G.put(u, uNb);G.put(v, vNb);G.put(w, wNb);G.put(x, xNb);G.put(y, yNb);
        BFS(G, s);
    }

    public static void BFS(HashMap<vertex, ArrayList<vertex>> g, vertex s) {
        s.setColor(1);
        s.setD(0);
        Queue<vertex> q = new LinkedList<>();
        q.offer(s);
        while(!q.isEmpty()){
            vertex u = q.poll();
            ArrayList<vertex> uNb = g.get(u);
            for(vertex v: uNb){
                if(v.getColor() == 0){
                    v.setColor(1);
                    v.setD(u.getD() + 1);
                    q.offer(v);
                }
            }
            u.setColor(2);
            System.out.println(u);
        }
    }

    public static void DFS(HashMap<vertex, ArrayList<vertex>> g, vertex v){
        Stack<vertex> s = new Stack<>();
        s.push(v);
        while(!s.isEmpty()){
            v = s.pop();
            if(v.getColor() == 0){
                v.setColor(1);
                System.out.println(v);
                for(vertex u: g.get(v)){
                    s.push(u);
                    u.setD(v.getD() + 1);
                }
            }
        }
    }
}

class vertex {
    char key;
    int color = 0;
    int d = 0;
    vertex pi;
    public vertex getPi() {
        return pi;
    }

    public void setPi(vertex pi) {
        this.pi = pi;
    }


    vertex(char key){
        this.key = key;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char key) {
        this.key = key;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "vertex [d=" + d + ", key=" + key + "]";
    }


}