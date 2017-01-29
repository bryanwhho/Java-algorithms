class HalfDollars {
    public static void main(String[] arguments) {
        int[] denver = { 1900000, 1700000, 1700000};
        int[] philadelphia = new int[denver.length];
        int[] total = new int[denver.length];
        int average;
        
        philadelphia[0] = 1900000;
        philadelphia[1] = 1800000;
        philadelphia[2] = 1750000;
        
        total[0] = denver[0] + philadelphia[0];
        total[1] = denver[1] + philadelphia[1];
        total[2] = denver[2] + philadelphia[2];
        average = (total[0] + total[1] + total[2]) / 3;
        
        System.out.print("2009 production: ");
        System.out.format("%, d%n", total[0]);
        System.out.print("2010 production: ");
        System.out.format("%, d%n", total[1]);
        System.out.print("2011 production: ");
        System.out.format("%, d%n", total[2]);
        System.out.print("Average production: ");
        System.out.format("%, d%n", average);
        
        
    }
}