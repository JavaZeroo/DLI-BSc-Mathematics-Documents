public class stack2queue extends stack{
    stack2queue(int size) {
        super(size);
    }
    public void enQueue(int x){
        push(x);
    }
    public int deQueue(){
        stack temp = new stack(size);
        int tempTop = top;
        for(int i = 0; i < tempTop; i++){
            temp.push(this.pop());
        }
        int ret = this.pop();
        for(int i = 0; i < tempTop; i++){
            this.push(temp.pop());
        }
        return ret;
    }
}
