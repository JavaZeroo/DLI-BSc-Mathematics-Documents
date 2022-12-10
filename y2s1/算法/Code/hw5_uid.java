import java.util.Arrays;

public class hw5_uid {
    public static void main(String[] args) {
        char[] arg = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I' };
        TreeNode nodeA = new TreeNode(arg[0]);
        TreeNode nodeB = new TreeNode(arg[1]);
        TreeNode nodeC = new TreeNode(arg[2]);
        TreeNode nodeD = new TreeNode(arg[3]);
        TreeNode nodeE = new TreeNode(arg[4]);
        TreeNode nodeF = new TreeNode(arg[5]);
        TreeNode nodeG = new TreeNode(arg[6]);
        TreeNode nodeH = new TreeNode(arg[7]);
        TreeNode nodeI = new TreeNode(arg[8]);
        nodeB.addLeft(nodeA);
        nodeB.addRight(nodeD);
        nodeD.addLeft(nodeC);
        nodeD.addRight(nodeE);
        nodeF.addLeft(nodeB);
        nodeF.addRight(nodeG);
        nodeG.addRight(nodeI);
        nodeI.addLeft(nodeH);
        TreeNode root = nodeF;
        System.out.println("PreOrder:\n" + root.preOrder());
        System.out.println("\nInOrder:\n" + root.inOrder());
        System.out.println("\nPostOrder:\n" + root.postOrder());
        System.out.println("\nSearch Key of 'C'\n" + root.Search(root, 'C').toString());
        System.out.println("\nSearch Key of 'J'\n" + root.Search(root, 'J').toString());
        System.out.println("\nSuccessor of 'A'\n" + nodeA.successor().toString());
        char[] inOrder = {'2', '0', '2', '0', '3', '2', '9', '3', '0', '1', '2'};
        char[] preOrder = {'2', '0', '2', '0', '2', '3', '9', '0', '3', '1', '2'};
        TreeNode build = new TreeNode();
        build = build.buildTree(preOrder, inOrder);
        System.out.println("\nBuilded BinaryTree:\n" + build.inOrder());
    }

    
}

class TreeNode {
    private char key;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(char arg) {
        this.key = arg;
    }

    public TreeNode() {
    }

    public void addLeft(TreeNode node) {
        this.left = node;
        node.parent = this;
    }

    public void addRight(TreeNode node) {
        this.right = node;
        node.parent = this;
    }

    public String preOrder() {
        StringBuffer sb = new StringBuffer();
        // Visit the root.
        sb.append(this.key + ", ");
        // Traverse the left subtree.
        if (this.left != null)
            sb.append(this.left.preOrder());
        // Traverse the right subtree.
        if (this.right != null)
            sb.append(this.right.preOrder());

        return sb.toString();
    }

    public String inOrder() {
        StringBuffer sb = new StringBuffer();
        // Traverse the left subtree.
        if (this.left != null)
            sb.append(this.left.inOrder());
        // Visit the root.
        sb.append(this.key + ", ");
        // Traverse the right subtree.
        if (this.right != null)
            sb.append(this.right.inOrder());

        return sb.toString();
    }

    public String postOrder() {
        StringBuffer sb = new StringBuffer();
        // Traverse the left subtree.
        if (this.left != null)
            sb.append(this.left.postOrder());
        // Traverse the right subtree.
        if (this.right != null)
            sb.append(this.right.postOrder());
        // Visit the root.
        sb.append(this.key + ", ");

        return sb.toString();
    }

    public TreeNode Search(TreeNode tree, char search) {
        if (tree == null) {
            return new TreeNode('\0');
        } else if (tree.key == search) {
            return tree;
        }

        if (search < tree.key) {
            return Search(tree.left, search);
        } else {
            return Search(tree.right, search);
        }

    }

    public boolean isEmpty() {
        return this.key == '\0';
    }

    public String toString() {
        if (this.isEmpty()) {
            return "TreeNode{ key= Empty }";
        } else {
            return "TreeNode{" + " key= " + key + " }";
        }
    }

    public TreeNode maximum() {
        TreeNode node = new TreeNode();
        while (this != null && this.right != null) {
            node = this.right;
        }
        return node;
    }

    public TreeNode minmum() {
        TreeNode node = new TreeNode();
        while (this != null && this.left != null) {
            node = this.left;
        }
        return node;
    }

    public TreeNode successor() {
        // 1.指定节点的右子树的最小节点
        if (this != null && this.right != null) return this.right.minmum();
        // 2. 右节点不存在时，往上面查找符合一个节点y，该节点的左子树的最大值为该查找节点node，该y.key>node.key
        TreeNode p = this.parent;
        while (p != null && p.right == this) {
            p = p.parent;
        }
        return p;
    }
    
    public TreeNode buildTree(char[] preorder, char[] inorder) {
		if(preorder.length==0 || inorder.length==0) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
		for(int i=0;i<preorder.length;++i) {
			if(preorder[0]==inorder[i]) {
				char[] pre_left = Arrays.copyOfRange(preorder,1,i+1);
				char[] pre_right = Arrays.copyOfRange(preorder,i+1,preorder.length);
				char[] in_left = Arrays.copyOfRange(inorder,0,i);
				char[] in_right = Arrays.copyOfRange(inorder,i+1,inorder.length);
				root.left = buildTree(pre_left,in_left);
				root.right = buildTree(pre_right,in_right);
				break;
			}
		}
		return root;
	}
}