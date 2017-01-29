class Checkerboard {
    public static void main(String[] arguments) {
        int N = 0;
        if (arguments.length > 0)
            N = Integer.parseInt(arguments[0]);
        for (int j = 0; j < N; j++) {              
        for (int i = 0; i < N; i++) {
            System.out.print("* ");
        }
        System.out.println();
        if (j % 2 != 0) {
            } else {
                System.out.print(" ");
            }
        }
    }
}