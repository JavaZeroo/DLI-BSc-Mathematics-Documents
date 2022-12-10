import java.util.Stack;

public class queuebystack {
    private Stack<Integer> s = new Stack<Integer>();

    public queuebystack() {}
    public void enqueue(int n){
        s.push(n);
    }
    public int dequeue(){
        Stack<Integer> temp = new Stack<Integer>();
        int finalSize = s.size() - 1;
        for(int i = 0; i < finalSize; i++){
            temp.push(s.pop());
        }
        int ret = s.pop();
        for(int i = 0; i < finalSize; i++){
            s.push(temp.pop());
        }
        return ret;
    }
}
