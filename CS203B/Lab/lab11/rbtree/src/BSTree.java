import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * Some red-black tree implementation referenced from the "Introduction of Algorithms"
 * Code and picture are very similar to the original one.
 * Reference "Introduction of Algorithms" 2nd edition if you need more detail.
 */
public class BSTree<Key extends Comparable<Key>, Value> implements Iterable<KeyValue<Key, Value>> {

    enum NodeColor { RED, BLACK }

    private class Node {
        Key key;
        Value value;
        NodeColor color = NodeColor.BLACK;

        Node left = nil;  // "pointer" to the left child
        Node right = nil;
        Node parent = nil;

        Node( Key k, Value v ) {
            key = k;
            value = v;
        }
    }

    private Node nil = new Node( null, null );
    private Node root = nil;
    private int size = 0;

    public int size() {
        return size;
    }

    /*
     * return null if not found
     */
    public Value get( Key key ) {
        if( key == null )
            return null;

        for( Node node = root; node != nil; ) {
            int compare = node.key.compareTo( key );
            if( compare < 0 )
                node = node.right;
            else if( compare > 0 )
                node = node.left;
            else
                return node.value;
        }

        return null;
    }


        // never execute this when x.right == nil
    private void leftRotate( Node x ) {
        Node y = x.right;
        x.right = y.left;
        if( x.right != nil )
            x.right.parent = x;
        y.parent = x.parent;
        if( x == root )
            root = y;
        else if( x == x.parent.left )
            x.parent.left = y;
        else
            x.parent.right = y;

        x.parent = y;
        y.left = x;

        NodeColor tmp = x.color;
        x.color = y.color;
        y.color = tmp;
    }
    
    // never execute this when y.left == nil
    private void rightRotate( Node y ) {
        Node x = y.left;
        y.left = x.right;
        if( y.left != nil )
            y.left.parent = y;
        x.parent = y.parent;
        if( y == root )
            root = x;
        else if( y == y.parent.left )
            y.parent.left = x;
        else
            y.parent.right = x;
        y.parent = x;
        x.right = y;

        NodeColor tmp = x.color;
        x.color = y.color;
        y.color = tmp;
    }

    private void insertFixup( Node z ) {
        while( z.parent.color == NodeColor.RED ) {
            if( z.parent == z.parent.parent.left ) {   // if z's parent is the left child
                Node y = z.parent.parent.right;
                if( y.color == NodeColor.RED ) {
                    z.parent.color = NodeColor.BLACK;
                    y.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    z = z.parent.parent;
                } else {
                    if( z == z.parent.right ) {
                        z = z.parent;
                        leftRotate(z);
                    }
                    //z.parent.color = NodeColor.BLACK;
                    //z.parent.parent.color = NodeColor.RED;
                    rightRotate(z.parent.parent);
                }
            } else { // else z's parent is the right child
                Node y = z.parent.parent.left;
                if( y.color == NodeColor.RED ) {
                    z.parent.color = NodeColor.BLACK;
                    y.color = NodeColor.BLACK;
                    z.parent.parent.color = NodeColor.RED;
                    z = z.parent.parent;
                } else {
                    if( z == z.parent.left ) {
                        z = z.parent;
                        rightRotate(z);
                    }
                    //z.parent.color = NodeColor.BLACK;
                    //z.parent.parent.color = NodeColor.RED;
                    leftRotate(z.parent.parent);
                }
            }
        }
        root.color = NodeColor.BLACK;
    }

    /**
     * Insert a new node in the tree with key value pair. It node with key exists, replace the value and return the old value.
     * otherwise return null
     */
    public Value put( Key key, Value value ) {
        if( key == null )
            return null;

        if( root == nil ) {
            root = new Node(key, value);
            return null;
        }

        for( Node node = root; node != nil; ) {
            int compare = node.key.compareTo( key );
            if( compare < 0 ) {
                if( node.right == nil ) {
                    node.right = new Node(key, value);
                    node.right.parent = node;
                    node.right.color = NodeColor.RED;
                    size ++;
                    insertFixup(node.right);
                    return null;
                } else
                    node = node.right;
            } else if( compare > 0 ) {
                if( node.left == nil ) {
                    node.left = new Node(key, value);
                    node.left.parent = node;
                    node.left.color = NodeColor.RED;
                    size ++;
                    insertFixup(node.left);
                    return null;
                } else
                    node = node.left;
            } else {
                Value old = node.value;
                node.value = value;
                return old;
            }
        }

        return null;
    }



    private Node min( Node node ) {
        if( node == nil )
            return nil;
        while( node.left != nil )
            node = node.left;
        return node;
    }

    /*
     *   remove node and replace it with its right child
     *   must be called on a node without left child
     */
    private void deleteAndUseRightChild( Node node ) {
        node.right.parent = node.parent;

        if( node == root )
            root = node.right;
        else if( node == node.parent.left )
            node.parent.left = node.right;
        else
            node.parent.right = node.right;
    }

    private void deleteAndUseLeftChild( Node node ) {
        node.left.parent = node.parent;

        if( node == root )
            root = node.left;
        else if( node == node.parent.left )
            node.parent.left = node.left;
        else
            node.parent.right = node.left;
    }

    /*
     * delete the smallest node in the subtree whose root is node
     */
    private Node delMin( Node node ) {
        if( node == nil )
            return nil;
        node = min(node); // delete this node;

        deleteAndUseRightChild(node);
        size --;

        return node;
    }

    private void deleteFixup( Node x ) {
        while( x!=root && x.color == NodeColor.BLACK ) {
            if( x == x.parent.left ) {
                Node w = x.parent.right;
                if( w.color == NodeColor.RED ) {
                    leftRotate(x.parent);
                    w = x.parent.right;
                }
                if( w.left.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK ) {
                    w.color = NodeColor.RED;
                    x = x.parent;
                } else {
                    if( w.right.color == NodeColor.BLACK ) {
                        rightRotate(w);
                        w = x.parent.right;
                    }
                    w.right.color = NodeColor.BLACK;
                    leftRotate(x.parent);
                    break;
                }
            } else { // else x is a right child
                Node w = x.parent.left;
                if( w.color == NodeColor.RED ) {
                    rightRotate(x.parent);
                    w = x.parent.left;
                }
                if( w.left.color == NodeColor.BLACK && w.right.color == NodeColor.BLACK ) {
                    w.color = NodeColor.RED;
                    x = x.parent;
                } else {
                    if( w.left.color == NodeColor.BLACK ) {
                        leftRotate(w);
                        w = x.parent.left;
                    }
                    w.left.color = NodeColor.BLACK;
                    rightRotate(x.parent);
                    break;
                }
            }
        }
        x.color = NodeColor.BLACK;
    }

    public Value delete( Key key ) {
        if( key == null )
            return null;
        
        for( Node node = root; node != nil; ) {
            int compare = node.key.compareTo( key );
            if( compare < 0 )
                node = node.right;
            else if( compare > 0 )
                node = node.left;
            else {     // delete node
                if( node.left == nil ) {
                    deleteAndUseRightChild(node);
                    if( node.color == NodeColor.BLACK )
                        deleteFixup(node.right);
                    size --;
                    return node.value;
                } else if( node.right == nil ) {
                    deleteAndUseLeftChild(node);
                    if( node.color == NodeColor.BLACK )
                        deleteFixup(node.left);
                    size --;
                    return node.value;
                } else {  // both childern exist
                    Node minNode = delMin(node.right);
                    Value old = node.value;
                    node.key = minNode.key;
                    node.value = minNode.value;
                    if( minNode.color == NodeColor.BLACK )
                        deleteFixup(minNode.right);
                    return old;
                }
            }
        }
        return null;
    }

    /**
     * given a node, finds the next node of this node
     */
    private Node next( Node node ) {
        if( node == nil )
            return nil;

        if( node.right != nil )
            return min(node.right);

        while( node.parent != nil && node == node.parent.right )
            node = node.parent;

        return node.parent;
    }

    private class BstIterator implements Iterator<KeyValue<Key, Value>> {

        private Node node = min(root);

        @Override
        public boolean hasNext() {
            return node != nil;
        }

        @Override
        public KeyValue<Key, Value> next() {
            if( !hasNext() )
                throw new NoSuchElementException();
            KeyValue<Key, Value> old = new KeyValue<>(node.key, node.value);
            node = BSTree.this.next(node);
            return old;
        }

    }

    @Override
    public Iterator<KeyValue<Key, Value>> iterator() {
        return new BstIterator();
    }


    private static void test() {
        BSTree<Integer,Double> tree1 = new BSTree<>();
        TreeMap<Integer,Double> tree2 = new TreeMap<>();

        Random rand = new Random();

        for( int i = 0; i < 1000; ++ i ) {
            for( int j = 0; j < 1000; ++ j ) {
                Integer num1 = rand.nextInt(2000);
                Double num2 = rand.nextDouble();
                if( tree1.put(num1, num2) != tree2.put(num1, num2) )
                    System.out.println("Error");
            }
            for( int j = 0; j < 500; ++ j ) {
                int num1 = rand.nextInt(2000);
                if( tree1.delete(num1) !=  tree2.remove(num1) )
                    System.out.println("Error.");
            }
            Iterator<KeyValue<Integer,Double>> ite1 = tree1.iterator();
            Iterator<Entry<Integer,Double>> ite2 =  tree2.entrySet().iterator();
            while(  ite1.hasNext() || ite2.hasNext() ) {
                if( !ite1.hasNext() || !ite1.hasNext() ) {
                    System.out.println("Error..");
                    return;
                }
                if( ite1.next().value != ite2.next().getValue() ) {
                    System.out.println("Error...");
                    return;
                }
            }
            for( int j = 0; j < 10; ++ j ) {
                Integer num1 = rand.nextInt(2000);
                if( tree1.get(num1) != tree2.get(num1) ) {
                    System.out.println("Error....");
                    return;
                }
            }
        }
    }

    public static void main( String[] args ) {
        test();
        System.out.println("Finished");
    }


}