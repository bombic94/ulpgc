package es.ulpgc.eii.p1;

/**
 * Class representing Polynom object
 */
public class Polinomio {

    /**
     * Degree of polynom
     */
    private int grade;

    /**
     * Array of coefficients
     */
    private int[] v;

    /**
     * Default constructor. Create polynom with empty param
     */
    public Polinomio() {
        this(new int[]{});
    }

    /**
     * Constructor with array parameter
     * If array is empty, create one with 0 value
     * @param v Array of coefficients
     */
    public Polinomio(int[] v) {
        if (v.length == 0) {
            v = new int[]{0};
        }
        this.v = new int[v.length];
        System.arraycopy(v, 0, this.v, 0, this.v.length);
        computeGrade();
        shorterVector();
    }

    /**
     * Degree of polynom
     * @return Degree value
     */
    public int grado() {
        return this.grade;
    }

    /**
     * Value of coefficient on given position.
     * If given position is not valid number, return 0.
     * @param i Position of coefficient
     * @return Value of coefficient
     */
    public int coeficiente(int i) {
        if (i > this.v.length || i < 0) {
            return 0;
        }
        return this.v[i];
    }

    /**
     * Array of coefficients.
     * @return copy of array of coefficients
     */
    public int[] coeficientes() {
        int[] copy = new int[this.v.length];
        System.arraycopy(v, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     * Set coefficient on given position to value
     * @param index Position of coefficient
     * @param value New calue of coefficient
     */
    public void coeficiente(int index, int value) {
        if (index == this.v.length - 1 && value == 0) {
            this.v[index] = value;
            computeGrade();
            shorterVector();
        } else if (this.v.length < index) {
            longerVector(index + 1);
            this.v[index] = value;
            computeGrade();
        } else {
            this.v[index] = value;
        }

    }

    /**
     * Compute value of polynom with given variable value
     * @param x Valoue of variable
     * @return Computed value of polynom
     */
    public float valor(float x) {
        float result = 0;
        for (int i = this.v.length - 1; i >= 0; i--) {
            result += this.v[i] * Math.pow(x, i);
        }
        return result;
    }

    /**
     * Sum of two polynoms
     * @param p Polynom to add
     * @return Polynom made by sum of two polynoms
     */
    public Polinomio suma(Polinomio p) {
        int[] tmp = new int[Math.max(p.v.length, this.v.length)];
        for (int i = 0; i < tmp.length; i++) {
            if (i >= this.v.length) {
                tmp[i] = p.v[i];
            } else if (i >= p.v.length) {
                tmp[i] = this.v[i];
            } else {
                tmp[i] = this.v[i] + p.v[i];
            }
        }
        return new Polinomio(tmp);
    }

    /**
     * Difference of two polynoms
     * @param p Polynom to subtract
     * @return Polynom made by subtraction of two polynoms
     */
    public Polinomio resta(Polinomio p) {
        int[] tmp = new int[ Math.max(p.v.length, this.v.length)];
        for (int i = 0; i < tmp.length; i++) {
            if (i >= this.v.length) {
                tmp[i] = -1 * p.v[i];
            } else if ((i >= p.v.length)){
                tmp[i] = this.v[i];
            } else {
                tmp[i] = this.v[i] - p.v[i];
            }
        }
        return new Polinomio(tmp);
    }

    /**
     * Mathematical equation of polynom
     * @return Mathematical equation of polynom
     */
    @Override
    public String toString() {
        if (grade == 0) {
            return Integer.toString(v[0]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = v.length - 1; i >= 0; i--) {
            if (v[i] == 0) {
                continue;
            }

            if (i < v.length - 1 && v[i] > 0) {
                sb.append("+");
            } else if (v[i] < 0) {
                sb.append("-");
            }

            if (Math.abs(v[i]) != 1 && i > 0) {
                sb.append(Math.abs(v[i]));
            }
            if (i > 1) {
                sb.append("x^").append(i);
            }
            if (i == 1) {
                sb.append("x");
            }
            if (i == 0) {
                sb.append(Math.abs(v[i]));
                break;
            }
        }

        return sb.toString();
    }

    /** PRIVATE METHODS */

    /**
     * Used to compute grade after change of array
     */
    private void computeGrade() {
        this.grade = 0;
        for (int i = 0; i < this.v.length; i++) {
            if (this.v[i] != 0) {
                this.grade = i;
            }
        }
    }

    /**
     * Make array shorter
     */
    private void shorterVector() {
        int[] tmp = new int[grade + 1];
        System.arraycopy(v, 0, tmp, 0, tmp.length);
        this.v = tmp;
    }

    /**
     * Make array londer
     * @param len Size of new array
     */
    private void longerVector(int len) {
        int[] tmp = new int[len];
        System.arraycopy(v, 0, tmp, 0, v.length);
        this.v = tmp;
    }
}