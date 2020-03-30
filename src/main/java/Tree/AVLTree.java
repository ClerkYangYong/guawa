package Tree;

public class AVLTree {

    public int data;
    public int depth;
    public int balance;
    public AVLTree parent;
    public AVLTree left;
    public AVLTree right;

    public AVLTree(int data) {
        this.data = data;
        this.depth = 1;
        this.balance = 0;
        this.left = null;
        this.right = null;
    }

    public AVLTree(int data, int depth, int balance, AVLTree parent, AVLTree left, AVLTree right) {
        this.data = data;
        this.depth = depth;
        this.balance = balance;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public AVLTree getParent() {
        return parent;
    }

    public void setParent(AVLTree parent) {
        this.parent = parent;
    }

    public AVLTree getLeft() {
        return left;
    }

    public void setLeft(AVLTree left) {
        this.left = left;
    }

    public AVLTree getRight() {
        return right;
    }

    public void setRight(AVLTree right) {
        this.right = right;
    }


    public static void insert(AVLTree root, int data) {
        //如果插入的数据小于根节点，像左节点添加
        if (root.getData() < data) {
            if (root.left != null) {
                insert(root.left, data);
            } else {
                root.left = new AVLTree(data);
                root.left.parent = root;
            }
        } else {
            if (root.right != null) {
                insert(root.right, data);
            } else {
                root.right = new AVLTree(data);
                root.right.parent = root;
            }
        }
        //插入之后，计算平衡因子
        root.balance = calcBalance(root);

        //左子树高，应该右旋
        if (root.balance >= 2) {
            //右孙高，先左旋
            if (root.right.balance == -1) {
                left_rotate(root.left);
            }
            right_rotate(root);
        }
        if (root.balance <= -2) {
            //左孙高，先右旋
            if (root.right.balance == 1) {
                right_rotate(root.right);
            }
            left_rotate(root);
        }
        //调整之后，重新计算树的平衡因子和深度
        root.balance = calcBalance(root);
        root.depth = calcDepth(root);
    }

    //右旋
    private static void right_rotate(AVLTree p) {
        //一次旋转涉及到祖父，父亲，右儿子
        AVLTree pParent = p.parent;
        AVLTree pLeftSon = p.left;
        AVLTree pRightGrandSon = pLeftSon.right;

        //左子变父
        pLeftSon.parent = pParent;
        if (pParent != null) {
            if (p == pParent.left) {
                pParent.left = pLeftSon;
            } else if (p == pParent.right) {
                pParent.right = pLeftSon;
            }
        }
        pLeftSon.right = p;
        p.parent = pLeftSon;
        //右孙变左孙
        p.left = pRightGrandSon;
        if (pRightGrandSon != null) {
            pRightGrandSon.parent = p;
        }
        p.depth = calcDepth(p);
        p.balance = calcBalance(p);
        pLeftSon.depth = calcDepth(pLeftSon);
        pLeftSon.balance = calcBalance(pLeftSon);
    }

    //左旋
    private static void left_rotate(AVLTree p) {
        //一次旋转涉及到祖父，父亲，左儿子
//一次旋转涉及到祖父，父亲，右儿子
        AVLTree pParent = p.parent;
        AVLTree pRightSon = p.right;
        AVLTree pLeftGrandSon = pRightSon.left;

        //左子变父
        pRightSon.parent = pParent;
        if (pParent != null) {
            if (p == pParent.right) {
                pParent.right = pRightSon;
            } else if (p == pParent.left) {
                pParent.left = pRightSon;
            }
        }
        pRightSon.left = p;
        p.parent = pRightSon;
        //右孙变左孙
        p.right = pLeftGrandSon;
        if (pLeftGrandSon != null) {
            pLeftGrandSon.parent = p;
        }
        p.depth = calcDepth(p);
        p.balance = calcBalance(p);
        pRightSon.depth = calcDepth(pRightSon);
        pRightSon.balance = calcBalance(pRightSon);
    }

    public static int calcBalance(AVLTree p) {
        int left_depth;
        int right_depth;
        if (p.left != null) {
            left_depth = p.left.depth;
        } else {
            left_depth = 0;
        }
        if (p.right != null) {
            right_depth = p.right.depth;
        } else {
            right_depth = 0;
        }
        return left_depth - right_depth;
    }

    public static int calcDepth(AVLTree p) {
        int depth = 0;
        if (p.left != null) {
            depth = p.left.depth;
        }
        if (p.right != null && depth < p.right.depth) {
            depth = p.right.depth;
        }
        depth++;
        return depth;
    }

    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree(10);
        insert(avlTree,11);
        System.out.println(avlTree.toString());
    }
}
