package bugfree.example;

public class Calculator {
    double memory;

    public Calculator() {
        this.memory = 0;
    }

    public Calculator(double d) {
        this.memory = d;
    }
    
    public double sum(double i) {
        memory += i;
        
        return memory;
    }

    public double div(double d) {    
        if (d == 0) {
            throw new IllegalArgumentException("invalid division by 0");
        }
        memory /= d;
        
        return memory;
    }
}
