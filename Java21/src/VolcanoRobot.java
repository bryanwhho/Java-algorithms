class VolcanoRobot {
    String status;
    int speed;
    float temperature;
    
    VolcanoRobot(String in1, int in2, float in3) {
        status = in1;
        speed = in2;
        temperature = in3;
    }
    void checkTemperature() {
        if (temperature > 660) {
            status = "returning home";
            speed = 5;
        }
    }
    
    void showAttributes() {
        System.out.println("Status: " + status);
        System.out.println("Speed: " + speed);
        System.out.println("Temperature: " + temperature);
    }
    
    public static void main(String[] arguments) {
        VolcanoRobot virgil = new VolcanoRobot();
        virgil.status = "exploring";
        virgil.speed = 2;
        virgil.temperature = 510;
        
        virgil.showAttributes();
        virgil.temperature = 670;
        virgil.showAttributes();
        System.out.println("Checking the temperature.");
        virgil.checkTemperature();
        virgil.showAttributes();
    }
}