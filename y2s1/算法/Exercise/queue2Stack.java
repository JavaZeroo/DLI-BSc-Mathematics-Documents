public class queue2Stack extends queue{
    public queue2Stack(int size) {
        super(size);
    }
    public void push(int n){
        if(this.isFull()) throw new IllegalArgumentException("Full");
        queue[++head] = n;
    }

    public int pop() { 
        if(this.isEmpty()) throw new IllegalArgumentException("Empty");
        queue temp = new queue(size);
        for(int i = tail + 1; i < head; i++){
            temp.enQueue(this.deQueue());
        }
        int ret = this.deQueue();
        this.queue = temp.getQueue();
        head--;
        tail = -1 ;
        return ret;
    }
}
