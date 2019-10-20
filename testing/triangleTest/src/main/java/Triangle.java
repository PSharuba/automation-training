public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle() {
        a = 1;
        b = 1;
        c = 1;
    }

    public Triangle(double a, double b, double c) {
        if (a > 0 && b > 0 && c > 0) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public boolean setSides(double a, double b, double c) {
        if (a > 0 && b > 0 && c > 0) {
            this.a = a;
            this.b = b;
            this.c = c;
            return true;
        }
        return false;
    }


    /*
    1  - существует
    -1 - не существует
    0  - вырожденный но существует
     */
    public int exists(){
        double ab=a+b;
        double ac=a+c;
        double bc=c+b;
        if(ab>c && ac>b && bc>a)
            return 1;
        if(ab<c || ac<b || bc<a)
            return -1;
        return 0;
    }
}