public class queueandstsackTest {
    public static void main(String[] args) {
        stack stack = new stack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println("pop Stack");
        for(int i = 0; i < 4; i++) {
            System.out.println(stack.pop());
        }



        queue2Stack queue2Stack = new queue2Stack(4);
        queue2Stack.push(1);
        queue2Stack.push(2);
        queue2Stack.push(3);
        queue2Stack.push(4);
        System.out.println("pop queue2Stack");
        for(int i = 0; i < 4; i++) {
            System.out.println(queue2Stack.pop());
        }


        queue queue = new queue(4);
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        System.out.println("queue deQueue");
        for(int i = 0; i < 4; i++) {
            System.out.println(queue.deQueue());
        }

        stack2Queue stack2Queue = new stack2Queue(4);
        stack2Queue.enQueue(1);
        stack2Queue.enQueue(2);
        stack2Queue.enQueue(3);
        stack2Queue.enQueue(4);
        System.out.println("stack2Queue deQueue");
        for(int i = 0; i < 4; i++) {
            System.out.println(stack2Queue.deQueue());
        }
    }
}
