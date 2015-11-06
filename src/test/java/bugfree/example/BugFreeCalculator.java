package bugfree.example;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.Fail.fail;
import org.junit.Test;

public class BugFreeCalculator {

    @Test
    public void sum_adds_quantity() {
        Calculator calc = new Calculator();
        
        double result = calc.sum(10);
        
        then(result).isEqualTo(10);
        
        calc = new Calculator(); 
        result = calc.sum(100);
        then(result).isEqualTo(100);
        
        result = calc.sum(10);
        then(result).isEqualTo(110);
    }
    
    @Test
    public void sum_adds_quantity_of_different_types() {
        Calculator calc = new Calculator();
        
        then(calc.sum(10.5)).isEqualTo(10.5);
    }
    
    @Test
    public void div_divides_by_the_given_quantity() {
        Calculator calc = new Calculator();

        then(calc.div(1)).isEqualTo(0);
        then(calc.div(10)).isEqualTo(0);

        calc = new Calculator(100);
        then(calc.div(20)).isEqualTo(5);
        then(calc.div(2)).isEqualTo(2.5);
    }
    
    /*
    @Test
    public void div_dividing_by_zero() {
        Calculator calc = new Calculator();
        then(calc.div(0)).isEqualTo(Double.NaN);
        
        calc = new Calculator(10);
        then(calc.div(0)).isEqualTo(Double.POSITIVE_INFINITY);
        
        calc = new Calculator(-10);
        then(calc.div(0)).isEqualTo(Double.NEGATIVE_INFINITY);
    }*/
    
    @Test
    public void div_dividing_by_zero() {
        Calculator calc = new Calculator();
        try {
            calc.div(0);
            fail("invalid argument not catched");
        } catch (IllegalArgumentException x) {
            then(x).hasMessageContaining("invalid division by 0");
        }
        
        calc = new Calculator(10);
        try {
            calc.div(0);
            fail("invalid argument not catched");
        } catch (IllegalArgumentException x) {
            then(x).hasMessageContaining("invalid division by 0");
        }
    }
}
