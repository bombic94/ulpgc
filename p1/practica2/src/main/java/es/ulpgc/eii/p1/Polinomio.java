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
     * @param v
     */
    public Polinomio(int[] v) {
        if (v.length == 0) {
            v = new int[]{0};
        }
        this.v = v;
        computeGrade();
    }

    public int grado() {
        return this.grade;
    }

    public int coeficiente(int i) {
        if (i > this.v.length) {
            return 0;
        }
        return this.v[i];
    }

    public int[] coeficientes() {
        return this.v;
    }

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

    public float valor(float x) {
        float result = 0;
        for (int i = this.v.length - 1; i >= 0; i--) {
            result += this.v[i] * Math.pow(x, i);
        }
        return result;
    }

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
        this.v = tmp;
        computeGrade();
        shorterVector();
        return this;
    }

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
        this.v = tmp;
        computeGrade();
        shorterVector();
        return this;
    }

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
    private void computeGrade() {
        this.grade = 0;
        for (int i = 0; i < this.v.length; i++) {
            if (this.v[i] != 0) {
                this.grade = i;
            }
        }
    }

    private void shorterVector() {
        int[] tmp = new int[grade + 1];
        System.arraycopy(v, 0, tmp, 0, tmp.length);
        this.v = tmp;
    }

    private void longerVector(int len) {
        int[] tmp = new int[len];
        System.arraycopy(v, 0, tmp, 0, v.length);
        this.v = tmp;
    }
}