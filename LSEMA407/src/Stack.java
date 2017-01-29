// Julia Boettcher 2012. Updated 2013.
//
// Implementation of a stack for integer values.
//
// java Stack x1 x2 x3 x4 ...
// interprets the integer command line arguments as follows:
// 0: print  >0: push xi  <0: pop

public class Stack {

    private Item top = null;  // top of the stack

    private class Item {
        private int  value;
        private Item next;
    }

    // takes the top item off the stack and returns its value
    // if stack is empty, returns -1
    public int pop() {
        if (top == null) {
            return -1;
        }
        int x = top.value;
        top = top.next;
        return x;
    }

    // creates a new item with value x and puts it on top of the stack
    public void push(int x) {
        Item p = new Item();
        p.value = x;
        p.next = top;
        top = p;
    }

    // string representation of the stack
    public String toString() {
        String s = "";
        for (Item p=top; p!=null; p=p.next) {
            s += p.value + " -> ";
        }
        s += "null";
        return s;
    }

    // Interpret command line arguments as follows:
    // >0: push  -1: pop 0: print
    public static void main (String[] args) {
        Stack S = new Stack();
        for (int i=0; i < args.length; i++) {
            int x = Integer.parseInt(args[i]);
            if (x>0) {
                System.out.println( "push " + x);
                S.push(x);
            }
            else if (x<0) {
                x = S.pop();
                if (x<0)
                    System.out.println ("pop not possible: stack empty");
                else
                    System.out.println( "pop " + x);
            } else { // x==0
                System.out.println(S);
            }
        }
    }

}

/* output:
java Stack 0 2 0 5 3 0 1 2 9 0 -1 -1 0 6 0 -1 -1 -1 -1 0 -1 -1 0 -1
null
push 2
2 -> null
push 5
push 3
3 -> 5 -> 2 -> null
push 1
push 2
push 9
9 -> 2 -> 1 -> 3 -> 5 -> 2 -> null
pop 9
pop 2
1 -> 3 -> 5 -> 2 -> null
push 6
6 -> 1 -> 3 -> 5 -> 2 -> null
pop 6
pop 1
pop 3
pop 5
2 -> null
pop 2
pop not possible: stack empty
null
pop not possible: stack empty
*/