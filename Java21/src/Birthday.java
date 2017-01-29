import java.util.StringTokenizer;

class Birthday {
    
    public static void main(String[] rguments) {
        String person1 = "04/29/2013";
        StringTokenizer b1 = new StringTokenizer(person1, "/");
        System.out.println("Token 1: " + b1.nextToken());
        System.out.println("Token 2: " + b1.nextToken());
        System.out.println("Token 3: " + b1.nextToken());
    }
}