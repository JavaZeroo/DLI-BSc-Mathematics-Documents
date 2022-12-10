public class queue2stack extends queue {

    queue2stack(int size) {
        super(size);
    }
    
    public void push(int x){
        this.enQueue(x);
    }

    public int queuesize(){
        // int size = ;
        return tail > head? tail - head : this.size - (head -tail) + 1;
    }

    public int pop(){
        queue temp = new queue(size);
        int tempsize = this.queuesize() - 1;
        for(int i = 0; i < tempsize; i++){
            temp.enQueue(this.deQueue());
        }
        int ret = this.deQueue();
        for(int i = 0; i < tempsize; i++){
            this.enQueue(temp.deQueue());
        }
        return ret;
    }
}
