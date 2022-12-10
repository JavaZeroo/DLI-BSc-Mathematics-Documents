import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
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
        // System.out.println("DFS");
        // DFSIterate(G, s);
    }

    public static void BFS(HashMap<vertex, ArrayList<vertex>> g, vertex s){
        s.setColor(1);
        Queue<vertex> q = new LinkedList<>();
        q.offer(s);
        while(!q.isEmpty()){
            vertex v = q.poll();
            if(v.getColor() != 2){
                for(vertex u : g.get(v)){
                    u.setColor(1);
                    u.setD(v.getD() + 1);
                }
                System.out.println(v);
            }
            v.setColor(2);
        }

    }

    public static void DFSIterate(HashMap<vertex, ArrayList<vertex>> g, vertex v){
        Stack<vertex> s = new Stack<>();
        s.push(v);
        while(!s.isEmpty()){
            v = s.pop();
            if(v.getColor() == 0){
                System.out.println(v);
                for(vertex u : g.get(v)){
                    s.push(u);
                }
            }
            v.setColor(2);
        }
    }
}



class vertex {
    char data;
    vertex pi;
    int d = Integer.MAX_VALUE;
    int color = 0;
    public boolean isVisited() {
        return visited;
    }


    public void setVisited(boolean visited) {
        this.visited = visited;
    }


    boolean visited = false;
    
    public vertex(char data) {
        this.data = data;
    }


    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }


    public vertex getPi() {
        return pi;
    }


    public void setPi(vertex pi) {
        this.pi = pi;
    }


    public int getD() {
        return d;
    }


    public void setD(int d) {
        this.d = d;
    }


    public char getData() {
        return data;
    }


    public void setData(char data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "vertex [data=" + data + "]";
    }

    
}