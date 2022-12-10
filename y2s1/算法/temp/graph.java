import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class graph {
    public static void main(String[] args) {
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
        HashMap<vertex, ArrayList<vertex>> g = new HashMap<vertex, ArrayList<vertex>>();
        g.put(r, rNb);g.put(s, sNb);g.put(t, tNb);g.put(u, uNb);g.put(v, vNb);g.put(w, wNb);g.put(x, xNb);g.put(y, yNb);
        // DFS(g, s);
        BFS(g, s);
    }

    public static void BFS(HashMap<vertex, ArrayList<vertex>> g, vertex s) {
        s.setColor(1);
        Queue<vertex> q = new LinkedList<>();
        q.offer(s);
        while(!q.isEmpty()) {
            vertex u = q.poll();
            for(vertex v : g.get(u)){
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
    int color=0, d=0;
    vertex pi;
    
    public vertex(char data) {
        this.data = data;
    }
    public char getIndex() {
        return data;
    }
    public void setIndex(char index) {
        this.data = index;
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
    public vertex getPi() {
        return pi;
    }
    public void setPi(vertex pi) {
        this.pi = pi;
    }
    
    @Override
    public String toString() {
        return "vertex [d=" + d + ", data=" + data + "]";
    }
}
