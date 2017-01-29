class VolcanoApplication {
    public static void main(String[] arguments) {
        VolcanoRobot mante = new VolcanoRobot();
        mante.status = "exploring";
        mante.speed = 2;
        mante.temperature = 510;
        
        mante.showAttributes();
        System.out.println("Increasing speed to 3.");
        mante.speed = 3;
        mante.showAttributes();
        System.out.println("Changing temperature to 670");
        mante.temperature = 670;
        mante.showAttributes();
        System.out.println("Checking the temperature.");
        mante.checkTemperature();
        mante.showAttributes();
    }
}