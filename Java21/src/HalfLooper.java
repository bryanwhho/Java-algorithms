class HalfLooper {
    public static void main(String[] arguments) {
        int[] denver = {1900000, 1700000, 1700000 };
        int[] philadelphia = { 1900000, 1800000, 1750000 };
        int[] total = new int[denver.length];
        int sum = 0;
        
        for (int i = 0; i < denver.length; i++) {
            total[i] = denver[i] + philadelphia[i];
            System.out.format((i + 2009) + " production: %,d%n", 
                    total[i]);
            sum += total[i];
        }
        
        System.out.format("Average production: %,d%n",
                (sum / denver.length));
    }
}