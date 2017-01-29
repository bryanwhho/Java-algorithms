public class InstanceCounter {
    private static int numInstances = 0;
    
    protected static int betCount() {
        return numInstances;
    }
    
    private static void addInstance() {
        numInstances++;
    }
    
    InstanceCounter() {
        InstanceCounter.addInstance();
    }
    
    public static void main(String[] arguments) {
        System.out.println("Starting with " + 
                InstanceCounter.betCount() + " objects");
        for (int i = 0; i < 500; i++)
            new InstanceCounter();
        System.out.println("Created " +
                InstanceCounter.betCount() + " objects");
    }
}