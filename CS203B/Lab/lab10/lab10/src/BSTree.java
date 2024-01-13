import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;


public class BSTree<Key extends Comparable<Key>, Value> implements Iterable<KVPair<Key,Value>> {


    class TreeNode {

        public Key key;
        public Value value;
    
        public TreeNode left = null;
        public TreeNode right = null;
        public TreeNode parent = null;
    
        public TreeNode( Key key, Value value ) {
            this.key = key;
            this.value = value;
        }
    }


    private TreeNode root = null;
    private int size = 0;

    public int size() {
        return size;
    }

    public Value get( Key key ) {
        if( key == null )
            return null;
        TreeNode node = root;
        while( node != null ) {
            int compare = node.key.compareTo(key);
            if( compare < 0 )
                node = node.right;
            else if( compare > 0 )
                node = node.left;
            else
                return node.value;
        }
        return null;
    }

    /**
     * @param key
     * @param value
     * @return previous value
     */
    public Value put( Key key, Value value ) {
        if( key == null || value == null )
            return null;
        if( root == null ) {
            root = new TreeNode(key, value);
            size++;
            return null;
        }
        TreeNode node = root;
        while( node != null ) {
            int compare = node.key.compareTo(key);
            if( compare < 0 ) {
                if( node.right == null ) {
                    node.right = new TreeNode(key, value);
                    node.right.parent = node;
                    size++;
                    return null;
                } else
                    node = node.right;
            } else if( compare > 0 ) {
                if( node.left == null ) {
                    node.left = new TreeNode(key, value);
                    node.left.parent = node;
                    size++;
                    return null;
                } else
                    node = node.left;
            } else {
                Value ret = node.value;
                node.value = value;
                return ret;
            }
        }
        return null;
    }

    private TreeNode min( TreeNode node ) {
        if( node == null )
            return null;
        while( node.left != null )
            node = node.left;
        return node;
    }

    public KVPair<Key,Value> min() {
        TreeNode node = min(root);
        return (node==null) ? null : new KVPair<Key,Value>(node.key, node.value);
    }

    private TreeNode next( TreeNode node ) {
        if( node == null )
            return null;
        if( node.right != null )
            return min( node.right );
        while( node.parent != null && node == node.parent.right )
            node = node.parent;
        return (node.parent==null) ? (null) : (node.parent);
    }

    private class NodeIterator implements Iterator<KVPair<Key,Value>> {

        TreeNode node = min(root);
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public KVPair<Key,Value> next() {
            if( ! hasNext() )
                throw new NoSuchElementException("No next node element in BSTree.");
            KVPair<Key,Value> ret = new KVPair<>(node.key, node.value);
            node = BSTree.this.next(node);
            return ret;
        }
        
    }
    
    @Override
    public Iterator<KVPair<Key, Value>> iterator() {
        return new NodeIterator();
    }

    private TreeNode delMin( TreeNode node ) {
        if( node == null )
            return null;
        while( node.left != null )
            node = node.left;
        if( node == root ) {
            root = node.right;
            if( root != null )
                root.parent = null;
        } else if( node == node.parent.left )
            node.parent.left = node.right;
        else
            node.parent.right = node.right;
        
        if( node.right != null )
            node.right.parent = node.parent;

        size --;
        return node;
    }

    public Value delete( Key key ) {
        if( root == null || key == null )
            return null;
        TreeNode node = root;
        while( node != null ) {
            int compare = node.key.compareTo(key);
            if( compare < 0 )
                node = node.right;
            else if( compare > 0 )
                node = node.left;
            else {
                if( node.left == null ) {
                    if(node.right != null)
                        node.right.parent = node.parent;
                    if( node == root ) {
                        root = node.right;
                        if( root != null )
                            root.parent = null;
                    } else if( node == node.parent.left )
                        node.parent.left = node.right;
                    else
                        node.parent.right = node.right;
                    size --;
                    return node.value;
                } else if( node.right == null ) {
                    node.left.parent = node.parent;
                    if( node == root ) {
                        root = node.left;
                        if( root != null )
                            root.parent = null;
                    } else if( node == node.parent.left )
                        node.parent.left = node.left;
                    else
                        node.parent.right = node.left;
                    size --;
                    return node.value;
                } else {
                    TreeNode minNode = delMin(node.right);
                    Value ret = node.value;
                    node.key = minNode.key;
                    node.value = minNode.value;
                    return ret;
                }
            }
        }
        return null;
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
            Iterator<KVPair<Integer,Double>> ite1 = tree1.iterator();
            Iterator<Entry<Integer,Double>> ite2 =  tree2.entrySet().iterator();
            while(  ite1.hasNext() || ite2.hasNext() ) {
                if( !ite1.hasNext() || !ite1.hasNext() ) {
                    System.out.println("Error..");
                    return;
                }
                if( ite1.next().v != ite2.next().getValue() ) {
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
