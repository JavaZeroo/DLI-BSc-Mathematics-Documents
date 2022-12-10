import java.util.LinkedList;
import java.util.Queue;

public class stackbyqueue {
    Queue<Integer> q = new LinkedList<Integer>();

    public stackbyqueue(){}

    public void push(int n){
        q.offer(n);
    }

    public int pop() {
        Queue<Integer> temp = new LinkedList<Integer>();
        int finalSize = q.size() - 1;
        for(int i = 0; i < finalSize; i++) temp.offer(q.poll());
        int ret = q.poll();
        for(int i = 0; i < finalSize; i++) q.offer(temp.poll());
        return ret;
    }
    
}
