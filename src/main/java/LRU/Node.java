package LRU;

public class Node {

    Object key;//键
    Object value;//值
    Node pre;//上一个节点
    Node next;//下一个节点

    public Node(Object key, Object value) {
        this.key = key;
        this.value = value;
    }
}
