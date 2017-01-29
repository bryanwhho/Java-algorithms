class PowersOfK {
    public static void main(String[] arguments) {
        long k = 0;
        
        if (arguments.length > 0) {
            k = Long.parseLong(arguments[0]);
                    } else {
            System.out.println("Cannot enter '0' for argument");
        }
        
        System.out.print("The powers of k are ");
        long m = k;
        
        for (int i = 0; m < Long.MAX_VALUE && m*k >0; i++) {
            m *= k;
            System.out.print(m + " ");
                }
        System.out.print(". ");
    }
}