import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Lab4 {
    PriorityQueue<trie> kla = new PriorityQueue<>();
    public trie current;
    public static String inputFile = "commands";
    private int size = 0;
    private int height = 0;
    ArrayList list = new ArrayList();
    public trie root;
    private static class trie implements Comparable<trie> {
        private trie current;
        private trie root;
        private int height;
        private String string;
        public String large;
        private trie left;
        private trie right;
        public trie getLeft() {
            return this.left = left;
        }
        public trie getRight() {
            return this.right = right;
        }
        public Integer getHeight() {
            return this.height = height;
        }
        public String getString() {
            return this.string;
        }
        public trie(int height, trie left, trie right) {
            this.left=left;
            this.height=height;
            this.right=right;
        }
        public trie(String string, trie left, trie right) {
            this.left=left;
            this.right=right;
            this.string=string;
        }
        public trie() {

        }
        public trie(String string) {
            this.string=string;
        }
        public trie(Integer height) {
            this.height=height;
        }
        @Override
        public int compareTo(trie node) {
            return Integer.compare(height, node.getHeight());
        }
    }
    public boolean insert(String bc) {
        /*String b = String.valueOf(bc);
        if(!b.startsWith("1")) {
            b = String.format("%07d", bc);
        }
        System.out.println(b+ " ^^^^^^^^^");*/
        String b = bc;
        if(current==null&&root==null) {
            kla.add(new trie(0));
            root=kla.poll();
            current=root;
        }
        if(insert(root, b)) {
            return true;
        }
        return false;

    }

    public boolean insert( trie a, String b ) {
        trie left=kla.poll();
        trie right=kla.poll();
        //System.out.println("1 circle");
        String[] var =  b.split("");
        if(var[a.getHeight()].equals("0")) {
            if(a.left == null) {
                //System.out.println(a.getHeight()+1);
                trie k = new trie(b, left, right);
                a.left=k;
                if(height==0) {
                    height++;
                }
                if (height <a.getHeight()+1) {
                    height = a.getHeight()+1;
                }
                if(a.right != null) {
                    //System.out.println("end" +" L: "+ a.left.getString() + " R:" +a.right.getString());
                }
                return true;
            }
            if(a.left!=null&&a.left.getString()==null) {
                insert(a.left, b);
            }
            if(a.left!=null&&a.left.getString()!=null) {
                String kv = a.left.getString();
                String[] kvl= kv.split("");
                if(kvl[a.getHeight()].equals(var[a.getHeight()])) {
                    String c = a.left.getString();
                    //System.out.println(c);
                    trie v = new trie(a.getHeight()+1);
                    a.left=v;
                    if (height <v.getHeight()+1) {
                        height = v.getHeight()+1;
                    }
                    trie newa =a.left;
                    trie k = new trie(b, left, right);
                    if (var[v.getHeight()].equals("1")) {
                        newa.right = k;
                        //System.out.println("right");
                    }
                    if (var[v.getHeight()].equals("0")) {
                        newa.left = k;
                        //System.out.println("left");
                    }
                    insert(newa, c);
                }
            }

        }
        if(var[a.getHeight()].equals("1")) {
            //System.out.println(b);
            if(a.right == null) {
                trie k = new trie(b, left, right);
                a.right=k;
                if (height <a.getHeight()+1) {
                    height = a.getHeight()+1;
                }
                //System.out.println(a.getHeight()+1);
                if(a.left != null) {
                    //System.out.println("end" +" L: "+ a.left.getString() + " R:" +a.right.getString());
                }
                return true;
            }
            if(a.right!=null&&a.right.getString()==null) {
                insert(a.right, b);
            }
            if(a.right!=null&&a.right.getString()!=null) {
                String kv = a.right.getString();
                String[] kva = kv.split("");
                if (kva[a.getHeight()].equals(var[a.getHeight()])) {
                    String c = a.right.getString();
                    //System.out.println(c);
                    trie v = new trie(a.getHeight() + 1);
                    a.right = v;
                    if (height <v.getHeight()+1) {
                        height = v.getHeight()+1;
                    }
                    trie newa = a.right;
                    trie k = new trie(b, left, right);
                    if (var[v.getHeight()].equals("1")) {
                        newa.right = k;
                        //System.out.println("right");
                    }
                    if (var[v.getHeight()].equals("0")) {
                        newa.left = k;
                        //System.out.println("left");
                    }
                    insert(newa, c);
                }
            }
        }
        return true;
    }
    public void print() {
        //ArrayList list = new ArrayList();
        trieToList(root);
        for (int n =0; n< list.size(); n++) {
            System.out.print(list.get(n) + " ");
        }
        System.out.println("");
        list.clear();
    }
    public void trieToList( trie a) {
        if(a.left!=null) {
            if(a.left.getString()!=null) {
                list.add(a.left.getString());
                //System.out.println("add:"+ a.left.getString());
            }else{
                trieToList(a.left);
            }
        }
        if(a.right!=null) {
            if(a.right.getString()!=null) {
                list.add(a.right.getString());
                //System.out.println("add:"+ a.right.getString());
            }else{
                trieToList(a.right);
            }
        }
    }
    public void largest() {
        System.out.println(getlargest(root));
    }
    public String getlargest(trie a) {
        String largest = "null";
        while (a.right!=null||a.left!=null) {
            if (a.right != null) {
                if (a.right.getString() != null) {
                    largest = a.right.getString();
                    break;
                }
                a=a.right;
            } else if (a.left != null) {
                if (a.left.getString() != null) {
                    largest = a.left.getString();
                    break;
                }
                a=a.left;
            }
        }
        return largest;
    }
    public void smallest() {
        System.out.println(getsmallest(root));
    }
    public String getsmallest(trie a) {
        String smallest = "null";
        while (a.right!=null||a.left!=null) {
            if (a.left != null) {
                if (a.left.getString() != null) {
                    smallest = a.left.getString();
                    break;
                }
                a=a.left;
            } else if (a.right != null) {
                if (a.right.getString() != null) {
                    smallest = a.right.getString();
                    break;
                }
                a=a.right;
            }
        }
        return smallest;
    }
    public String search(trie a, String b) {
        int count = 0;
        String[] b1 = b.split("");
        while (a.left!=null||a.right!=null) {
            if(b1[count].equals("0")) {
                if(a.left!=null) {
                    if (a.left.getString() != null) {
                        return a.left.getString();
                    }
                    a = a.left;
                    count++;
                } else {
                    if(a.right.getString()!=null) {
                        return a.right.getString();
                    }
                    a=a.right;
                    count++;
                }
            }
            if(b1[count].equals("1")) {
                if (a.right!=null) {
                    if (a.right.getString() != null) {
                        return a.right.getString();
                    }
                    a = a.right;
                    count++;
                } else {
                    if (a.left.getString() != null) {
                        return a.left.getString();
                    }
                    a=a.left;
                    count++;
                }
            }
        }
        return a.getString();
    }
    public void search(String a) {
        System.out.println(search(root, a));
    }
    public void getsize(trie a) {
        if(a.left!=null) {
            if(a.left.getString()!=null) {
                size++;
            }else{
                getsize(a.left);
            }
        }
        if(a.right!=null) {
            if(a.right.getString()!=null) {
                size++;
            }else{
                getsize(a.right);
            }
        }
    }
    public int getheight(trie a) {
        if (a==null) {
            return 0;
        } else {
            int lh = getheight(a.left);
            int rh = getheight(a.right);
            return Math.max(lh, rh)+1;
        }
    }
    public void size() {
        getsize(root);
        System.out.println(size);
        size=0;
    }
    public void height (){
        getsize(root);
        int b =getheight(root);
        if(size == 1) {
            b =getheight(root)-1;
        }
        size = 0;
        System.out.println(b );
    }
    public static void main(String[] args) {
        Lab4 a = new Lab4();
        Scanner t = new Scanner(System.in);
        try {
            File kl = new File("src/"+ inputFile);
            t = new Scanner(kl);
        } catch(FileNotFoundException e) {
            System.out.println("not found");
        }
        while (t.hasNextLine()) {
            String l = t.nextLine();
            String[] km =l.split(" ");
            if(km[0].equals("insert")) {
                String b = km[1];
                a.insert(b);
            } else if (km[0].equals("search")) {
                String la =km[1];
                a.search(la);
            } else if(km[0].equals("print")) {
                a.print();
            } else if(km[0].equals("largest")) {
                a.largest();
            } else if(km[0].equals("smallest")) {
                a.smallest();
            } else if (km[0].equals("height")) {
                a.height();
            } else if (km[0].equals("size")) {
                a.size();
            }
        }

    }
}
