import java.awt.Point;

class FourDPoint extends Point {
    int w;
    int z;
    
    FourDPoint(int w, int x, int y, int z) {
        super(x, y);
        this.w = w;
        this.z = z;
    }
    
    public static void main(String[] arguments) {
        FourDPoint fourd = new FourDPoint(1, 2, 3, 4);
        System.out.println("w is " + fourd.w);
        System.out.println("x is " + fourd.x);
        System.out.println("y is " + fourd.y);
        System.out.println("z is " + fourd.z);
        
    }
}
        
