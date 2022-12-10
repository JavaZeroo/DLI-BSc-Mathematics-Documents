public class linkList {
    public static void main(String[] args) {
        link link = new link();
        link.add(1);
        link.add(9);
        link.add(5);
        link.add(3);
        link.add(2);

        System.out.println(link);
        
        link.delete(4);
        System.out.println(link);
    }
}



class link {
     private class node {
        int data;
        node pre;
        node next;
        node(){}
        node(int data) {
            this.data = data;
            pre = null;
            next = null;
        }

        void setPre(node node){
            this.pre = node;
            node.next = this;
        }

        void setNext(node node){
            this.next = node;
            node.pre = this;
        }
    }
    node head = new node();
    link(){};
    public void add(int data){
        node node = new node(data);
        if(head.next == null ){
            node.setPre(head);
        }else{
            head.next.setPre(node);
            node.setPre(head);
        }
    }

    public void delete(int data){
        node node = search(data);
        node.pre.setNext(node.next);
    }


    public node search(int data){
        node x = head;
        while(x.data != data && x.next != null){
            x = x.next;
        }
        return x;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        node x = head.next;
        while(x != null){
            sb.append(x.data + "->");
            x = x.next;
        }
        return sb.toString();
    }
}