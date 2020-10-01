import java.util.Scanner;

public class LostCow {
    public static void main(String[] args){
        Scanner stdin = new Scanner(System.in);

        int x = stdin.nextInt();
        int y = stdin.nextInt();
        int count = 1;
        int i =0;
        double z = x;
        double a = 0;
        double fin = 0;
        if(y-x>0) {
            while(z<y){
                if(i > 0){
                    a = Math.abs(z-x);
                }
                z = x + Math.pow(-1,i)*count;
                count*=2;
                i++;
                fin = fin + Math.abs(z-x) + a;

                if(z > y){
                    fin = fin + y - z;
                }
            }
        }
        else {
            while(z>y){
                if(i > 0){
                    a = Math.abs(z-x);
                }
                z = x + Math.pow(-1,i)*count;
                count*=2;
                i++;
                fin = fin + Math.abs(z-x) + a;

                if(z < y){
                    fin = fin - y + z;
                }
            }
        }
        int finish = (int) fin;
        System.out.println(finish);
    }
}
