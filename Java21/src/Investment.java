public class Investment {
    public static void main(String[] arguments) {
        float inv = 14000;
        System.out.println("$"+ inv + " would be worth...");
        // Investment increases in value by 40% in first year
        inv = 1.4F * inv;
        // Then decreases by $1,500 in second year
        inv = inv - 1500F;
        //Then increases 12% in the third year
        inv = inv * 1.12F;
        System.out.println("$" + inv);
        
    }
}