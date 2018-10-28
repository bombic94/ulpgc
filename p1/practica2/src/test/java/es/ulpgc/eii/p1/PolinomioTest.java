package es.ulpgc.eii.p1;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Test cases for Polynom class
 */
public class PolinomioTest {

    /***********************************************
     * Constructor testing
     **********************************************/
    @Test
    public void testConstructorNoParams() {
        Polinomio p = new Polinomio();
        assertEquals("0", p.toString());
    }

    @Test
    public void testConstructorsWithParamsEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals("0", p.toString());
    }

    @Test
    public void testConstructorsWithParamsSize1() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals("1", p.toString());
    }

    @Test
    public void testConstructorsWithParamsSize2() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals("x+1", p.toString());
    }

    @Test
    public void testConstructorsWithParamsWithValue() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals("3x^3+2x+1", p.toString());
    }

    @Test
    public void testConstructorsWithParamsZeroValue() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals("0", p.toString());
    }

    /***********************************************
     * Grade testing
     **********************************************/
    @Test
    public void testGradeEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(0, p.grado());
    }

    @Test
    public void testGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(0, p.grado());
    }

    @Test
    public void testGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.grado());
    }

    @Test
    public void testGradeMoreThan2() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(3, p.grado());
    }

    @Test
    public void testGradeZeroValues() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals(0, p.grado());
    }

    /***********************************************
     * Coeficient testing
     **********************************************/
    @Test
    public void testCoeficientEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals(0, p.coeficiente(5));
    }

    @Test
    public void testCoeficientGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.coeficiente(0));
    }

    @Test
    public void testCoeficientGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.coeficiente(1));
    }

    @Test
    public void testCoeficientGradeMoreThan1() {
        int[] v = new int []{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(3, p.coeficiente(3));
        assertEquals(0, p.coeficiente(5));
        assertEquals(0, p.coeficiente(-2));
        assertEquals(1, p.coeficiente(0));
    }

    @Test
    public void testCoeficientZeroValues() {
        int[] v = new int[]{0, 0, 0, 0};
        Polinomio p = new Polinomio(v);
        assertEquals(0, p.coeficiente(5));
    }

    /***********************************************
     * Coeficient array testing
     **********************************************/
    @Test
    public void testCoeficientsEmpty() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(new int[]{0}, p.coeficientes());
    }

    @Test
    public void testCoeficientsGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(v, p.coeficientes());
    }

    @Test
    public void testCoeficientsGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(v, p.coeficientes());
    }

    @Test
    public void testCoeficientsGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(v, p.coeficientes());
    }

    @Test
    public void testCoeficientsGradeMoreThan1WithZeroes() {
        int[] v = new int[]{1, 2, 0, 3, 0, 0};
        Polinomio p = new Polinomio(v);
        assertArrayEquals(new int[]{1, 2, 0, 3}, p.coeficientes());
    }

    /***********************************************
     * Change of coeficient testing
     **********************************************/
    @Test
    public void testChangeOfCoeficientForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals("3x^3+2x+1", p.toString());
        assertEquals(3, p.grado());
        p.coeficiente(5, 5);
        assertEquals("5x^5+3x^3+2x+1", p.toString());
        assertEquals(5, p.grado());
        p.coeficiente(5, 3);
        assertEquals("3x^5+3x^3+2x+1", p.toString());
        assertEquals(5, p.grado());
        p.coeficiente(5, 0);
        assertEquals("3x^3+2x+1", p.toString());
        assertEquals(3, p.grado());
    }

    @Test
    public void testChangeOfCoeficientForGrade0() {
        int[] v = new int[]{};
        Polinomio p = new Polinomio(v);
        assertEquals("0", p.toString());
        assertEquals(0, p.grado());
        p.coeficiente(5, 5);
        assertEquals("5x^5", p.toString());
        assertEquals(5, p.grado());
    }

    @Test
    public void testChangeOfCoeficientForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals("x+1", p.toString());
        assertEquals(1, p.grado());
        p.coeficiente(5, 5);
        assertEquals("5x^5+x+1", p.toString());
        assertEquals(5, p.grado());
    }

    /***********************************************
     * Value testing
     **********************************************/
    @Test
    public void testValue0ForGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.valor(0), 0.1);
    }

    @Test
    public void testValue1ForGrade0() {
        int[] v = new int[]{1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.valor(1), 0.1);
    }

    @Test
    public void testValue0ForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.valor(0), 0.1);
    }

    @Test
    public void testValue1ForGrade1() {
        int[] v = new int[]{1, 1};
        Polinomio p = new Polinomio(v);
        assertEquals(2, p.valor(1), 0.1);
    }

    @Test
    public void testValue0ForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(1, p.valor(0), 0.1);
    }

    @Test
    public void testValue1ForGradeMoreThan1() {
        int[] v = new int[]{1, 2, 0, 3};
        Polinomio p = new Polinomio(v);
        assertEquals(6, p.valor(1), 0.1);
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
        assertEquals("3", p1.suma(p2).toString());
        assertEquals(0, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1Grade1P2Grade0() {
        int[] v1 = new int[]{1, 1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("x+3", p1.suma(p2).toString());
        assertEquals(1, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1GradeMoreThan1P2Grade0() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{0};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("3x^3+2x+1", p1.suma(p2).toString());
        assertEquals(3, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1Grade0P2Grade1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("x+2", p1.suma(p2).toString());
        assertEquals(1, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1Grade1P2Grade1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{3, 4};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("6x+4", p1.suma(p2).toString());
        assertEquals(1, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1GradeMoreThan1P2Grade1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("3x^3+3x+2", p1.suma(p2).toString());
        assertEquals(3, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1Grade1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("2x^5+8x^4+6x^3+3x^2+7x+6", p1.suma(p2).toString());
        assertEquals(5, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1Grade0P2GradeMoreThan1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("2x^5+8x^4+6x^3+3x^2+5x+6", p1.suma(p2).toString());
        assertEquals(5, p1.suma(p2).grado());
    }

    @Test
    public void testSumForP1GradeMoreThan1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("2x^5+8x^4+9x^3+3x^2+7x+6", p1.suma(p2).toString());
        assertEquals(5, p1.suma(p2).grado());
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
        assertEquals("-1", p1.resta(p2).toString());
        assertEquals(0, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1Grade1P2Grade0() {
        int[] v1 = new int[]{1, 1};
        int[] v2 = new int[]{2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("x-1", p1.resta(p2).toString());
        assertEquals(1, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1GradeMoreThan1P2Grade0() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{0};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("3x^3+2x+1", p1.resta(p2).toString());
        assertEquals(3, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1Grade0P2Grade1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("-x", p1.resta(p2).toString());
        assertEquals(1, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1Grade1P2Grade1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{3, 4};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("-2x-2", p1.resta(p2).toString());
        assertEquals(1, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1GradeMoreThan1P2Grade1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{1, 1};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("3x^3+x", p1.resta(p2).toString());
        assertEquals(3, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1Grade1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("-2x^5-8x^4-6x^3-3x^2-3x-4", p1.resta(p2).toString());
        assertEquals(5, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1Grade0P2GradeMoreThan1() {
        int[] v1 = new int[]{1};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("-2x^5-8x^4-6x^3-3x^2-5x-4", p1.resta(p2).toString());
        assertEquals(5, p1.resta(p2).grado());
    }

    @Test
    public void testSubstractForP1GradeMoreThan1P2GradeMoreThan1() {
        int[] v1 = new int[]{1, 2, 0, 3};
        int[] v2 = new int[]{5, 5, 3, 6, 8, 2};
        Polinomio p1 = new Polinomio(v1);
        Polinomio p2 = new Polinomio(v2);
        assertEquals("-2x^5-8x^4-3x^3-3x^2-3x-4", p1.resta(p2).toString());
        assertEquals(5, p1.resta(p2).grado());
    }

}