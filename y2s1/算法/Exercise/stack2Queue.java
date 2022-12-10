public class stack2Queue extends stack{
    public stack2Queue(int size){
        super(size);
    }

    public void enQueue(int n){
        push(n);
    }

    public int deQueue(){
        stack temp = new stack(size);
        int tempTop = top;
        for(int i = 0; i < tempTop; i++){
            temp.push(this.pop());
        }
        int ret = pop();
        for(int i = 0; i < tempTop; i++){
            push(temp.pop());
        }
        return ret;
    }
}
