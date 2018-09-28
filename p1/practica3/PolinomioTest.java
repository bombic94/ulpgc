import org.junit.*;
import static org.junit.Assert.*; 

public class PolinomioTest {
    
    /***********************************************
     * Constructor testing
     **********************************************/
    @Test
    public void testConstructorNoParams() {
        Polinomio p = new Polinomio();
        assertEquals(p.toString(), "0");
    }
    
    @Test
    public void testConstructorsWithParamsEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "0");
    }
    
    @Test
    public void testConstructorsWithParamsSize1() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "1");
    }
    
    @Test
    public void testConstructorsWithParamsSize2() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "x+1");
    }
    
    @Test
    public void testConstructorsWithParamsWithValue() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "3x^3+2x+1");
    }
    
    @Test
    public void testConstructorsWithParamsZeroValue() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "0");
    }
    
    /***********************************************
     * Grade testing
     **********************************************/
    @Test
    public void testGradeEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(p.grado(), 0);
    }
    
    @Test
    public void testGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.grado(), 0);
    }
    
    @Test
    public void testGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.grado(), 1);
    }
    
    @Test
    public void testGradeMoreThan2() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.grado(), 3);
    }
    
    @Test
    public void testGradeZeroValues() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals(p.grado(), 0);
    }    
    
    /***********************************************
     * Coeficient testing
     **********************************************/
    @Test
    public void testCoeficientEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(p.coeficiente(5), 0);
    }
    
    @Test
    public void testCoeficientGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.coeficiente(0), 1);
    }
    
    @Test
    public void testCoeficientGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.coeficiente(1), 1);
    }
    
    @Test
    public void TestCoeficientGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.coeficiente(3), 3);
        assertEquals(p.coeficiente(5), 0);
        assertEquals(p.coeficiente(0), 1);
    }
    
    @Test
    public void testCoeficientZeroValues() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals(p.coeficiente(5), 0);
    }
    
    /***********************************************
     * Coeficient array testing
     **********************************************/
    @Test
    public void testCoeficientsEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(p.coeficientes(), new int[]{0});
    }
    
    @Test
    public void testCoeficientsGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(p.coeficientes(), v);
    }
    
    @Test
    public void testCoeficientsGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(p.coeficientes(), v);
    }
    
    @Test
    public void testCoeficientsGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(p.coeficientes(), v);
    }
    
    /***********************************************
     * Change of coeficient testing
     **********************************************/
    @Test
    public void testChangeOfCoeficientForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "3x^3+2x+1");
        assertEquals(p.grado(), 3);
        p.coeficiente(5, 5);
        assertEquals(p.toString(), "5x^5+3x^3+2x+1");
        assertEquals(p.grado(), 5);
        p.coeficiente(5, 3);
        assertEquals(p.toString(), "3x^5+3x^3+2x+1");
        assertEquals(p.grado(), 5);
        p.coeficiente(5, 0);
        assertEquals(p.toString(), "3x^3+2x+1");
        assertEquals(p.grado(), 3);
    }
    
    @Test
    public void testChangeOfCoeficientForGrade0() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "0");
        assertEquals(p.grado(), 0);
        p.coeficiente(5, 5);
        assertEquals(p.toString(), "5x^5");
        assertEquals(p.grado(), 5);
    }
    
    @Test
    public void testChangeOfCoeficientForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.toString(), "x+1");
        assertEquals(p.grado(), 1);
        p.coeficiente(5, 5);
        assertEquals(p.toString(), "5x^5+x+1");
        assertEquals(p.grado(), 5);
    }
    
    /***********************************************
     * Value testing
     **********************************************/
    @Test
    public void testValue0ForGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(0), 1, 0.1);
    }
    
    @Test
    public void testValue1ForGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(1), 1, 0.1);
    }

    @Test
    public void testValue0ForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(0), 1, 0.1);
    }
    
    @Test
    public void testValue1ForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(1), 2, 0.1);
    }
    
    @Test
    public void testValue0ForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(0), 1, 0.1);
    }
    
    @Test
    public void testValue1ForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(p.valor(1), 6, 0.1);
    }
    
    /***********************************************
     * Sum testing
     **********************************************/
    @Test
    public void testSumForP1Grade0P2Grade0() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "3");
    }
    
    @Test
    public void testSumForP1Grade1P2Grade0() {
        int[] v1 = new int[]{1, 1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "x+3");
    }
    
    @Test
    public void testSumForP1GradeMoreThan1P2Grade0() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{0};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "3x^3+2x+1");
    }
    
    @Test
    public void testSumForP1Grade0P2Grade1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "x+2");
    }
    
    @Test
    public void testSumForP1Grade1P2Grade1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{3, 4};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "6x+4");
    }
    
    @Test
    public void testSumForP1GradeMoreThan1P2Grade1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "3x^3+3x+2");
    }
    
    @Test
    public void testSumForP1Grade1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "2x^5+8x^4+6x^3+3x^2+7x+6");
    }
    
    @Test
    public void testSumForP1Grade0P2GradeMoreThan1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "2x^5+8x^4+6x^3+3x^2+5x+6");
    }
    
    @Test
    public void testSumForP1GradeMoreThan1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.suma(p2).toString(), "2x^5+8x^4+9x^3+3x^2+7x+6");
    }
    
    /***********************************************
     * Substract testing
     **********************************************/
     @Test
    public void testSubstractForP1Grade0P2Grade0() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-1");
    }
    
    @Test
    public void testSubstractForP1Grade1P2Grade0() {
        int[] v1 = new int[]{1, 1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "x-1");
    }
    
    @Test
    public void testSubstractForP1GradeMoreThan1P2Grade0() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{0};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "3x^3+2x+1");
    }
    
    @Test
    public void testSubstractForP1Grade0P2Grade1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-x");
    }
    
    @Test
    public void testSubstractForP1Grade1P2Grade1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{3, 4};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-2x-2");
    }
    
    @Test
    public void testSubstractForP1GradeMoreThan1P2Grade1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "3x^3+x");
    }
    
    @Test
    public void testSubstractForP1Grade1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-2x^5-8x^4-6x^3-3x^2-3x-4");
    }
    
    @Test
    public void testSubstractForP1Grade0P2GradeMoreThan1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-2x^5-8x^4-6x^3-3x^2-5x-4");
    }
    
    @Test
    public void testSubstractForP1GradeMoreThan1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals(p1.resta(p2).toString(), "-2x^5-8x^4-3x^3-3x^2-3x-4");
    }
    
}