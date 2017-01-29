class TriangleCompare {
    public static void main(String[] arguments) {
        int x = 0;
        int y = 0;
        int z = 0;
                
        if (arguments.length == 3) {
            x = Integer.parseInt(arguments[0]);
            y = Integer.parseInt(arguments[1]);
            z = Integer.parseInt(arguments[2]);
        int sumX = y + z;
        int sumY = x + z;
        int sumZ = x + y;
            System.out.println("The three numbers are " + x + " " + y + " " 
                    + z);
            if (x >= sumX) {
                System.out.println("true");
            }
            else if (y >= sumY) {
                System.out.println("true");
            }
            else if (z >= sumZ) {
                System.out.println("true");
             }
            else {
                System.out.println("false");
            }
         }
        else {
            System.out.println("Please enter 3 positive numbers");
        }
}