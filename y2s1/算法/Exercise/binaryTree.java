public class binaryTree {
    public static void main(String args[]){
        tree tree = new tree();
        tree.addNode('F');
        tree.addNode('B');
        tree.addNode('G');
        tree.addNode('A');
        tree.addNode('D');
        tree.addNode('I');
        tree.addNode('C');
        tree.addNode('E');
        tree.addNode('H');
        tree.inOrder();
        System.out.println("");
        tree.preOrder();
        System.out.println("");

        System.out.println(tree.findSuccessor(tree.search('A')));
    }
}

class tree {
    treeNode root;

    public tree(){}

    private treeNode addNode(treeNode current, char data) {
        if (current == null) {
            return new treeNode(data);
        }
        if (data < current.getData()) {
            current.setLeft(addNode(current.getLeft(), data));
            current.getLeft().setParent(current);
        } else if (data > current.getData()) {
            current.setRight(addNode(current.getRight(), data));
            current.getRight().setParent(current);
        } else {
            return current;
        }
        return current;
    }

    public void addNode(char data) {
        if(isEmpty()) {
            root = new treeNode(data);
            return;
        }
        root = addNode(root, data);
    }

    public treeNode getRoot() {
        return root;
    }
    public void setRoot(treeNode root) {
        this.root = root;
    }

    //判断树是否为空
    public boolean isEmpty() {
        return root == null;
    }

    public void preOrder(){
        preOrder(root);
    }
    public void preOrder(treeNode node) {
        if(node == null) return;
        System.out.print(node.getData());
        preOrder(node.getLeft());
        preOrder(node.getRight());
    }

    public void inOrder(){
        inOrder(root);
    }

    public void inOrder(treeNode node) {
        if(node == null) return;
        inOrder(node.getLeft());
        System.out.print(node.getData());
        inOrder(node.getRight());
    }

    public void postOrder(){
        postOrder(root);
    }
    public void postOrder(treeNode node) {
        if(node == null) return;
        postOrder(node.getLeft());
        postOrder(node.getRight());
        System.out.print(node.getData());
    }

    public treeNode search(char data){
        if(data == root.getData()) return root;
        else{
            treeNode temp = root;
            while(data != temp.getData()){
                if(data < temp.getData()) temp = temp.getLeft();
                else temp = temp.getRight();
            }
            return temp;
        }
    }

    public treeNode findSuccessor(treeNode node) {
        if(node.getRight() != null) {
            return minmum(node.getRight());
        }else{
            while(node.getParent()!=null && node.getParent().getLeft() != node){
                node = node.getParent();
            }
            return node.getParent();
        }
    } 
    public treeNode minmum(treeNode node) {
        if(node.getLeft() != null)
            return minmum(node.getLeft());
        else return node;
    }
}

class treeNode {
    private char data;
    private treeNode left, right, parent;

    public treeNode(){}

    public treeNode(char data) {
        this.data = data;
    }
    
    public treeNode getParent() {
        return parent;
    }

    public void setParent(treeNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "treeNode [data=" + data + "]";
    }

    public char getData() {
        return data;
    }
    public void setData(char data) {
        this.data = data;
    }
    public treeNode getLeft() {
        return left;
    }
    public void setLeft(treeNode left) {
        this.left = left;
    }
    public treeNode getRight() {
        return right;
    }
    public void setRight(treeNode right) {
        this.right = right;
    }
}
