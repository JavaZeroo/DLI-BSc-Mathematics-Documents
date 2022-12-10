public class queue {
    int[] queue;
    int head = 0, tail = 0;
    int size;
    queue(int size){
        this.size = size + 1;
        this.queue = new int[size + 1];
    }

    public boolean isEmpty() {
        return tail == head;
    }

    public boolean isFull() {
        return (tail + 1) % size == head;
    }
    public void enQueue(int x){
        if(isFull()) throw new IllegalArgumentException("Queue Full");
        queue[(tail++)%size] = x;
    }

    public int deQueue() {
        if(isEmpty()) throw new IllegalArgumentException("Queue Empty");
        return queue[(head++)%size];
    }
}
