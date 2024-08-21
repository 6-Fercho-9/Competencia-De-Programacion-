import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
public class Factores {
    public static void main(String[] args){
        //esPrimo(3);
        //int[] v={1,2,3,5,7,11,13,17,19,37,67};
        long startTime=System.nanoTime();
        int n=5342;
        StringBuilder generacion=generar(n);
        long endTime=System.nanoTime();
        long duracion=(endTime-startTime)/1000000;
        System.out.println("factores "+n+"->"+generacion.toString());
        System.out.println("Tiempo: "+duracion+" milisegundos");
        System.out.println(esPrimo(2671));

    }

    public static void generarPrimos(List<Integer> lista,int n){
        for (int i=2;i<=n;i++){
            if(esPrimo(i)){
                lista.add(i);
            }
        }
    }
    public static StringBuilder generar(int n){
        if(puedeEntrar(n)){
            StringBuilder cadena=new StringBuilder();
            int primo=2;
            do{
                if(n%primo==0){
                    n=n/primo;
                    cadena.append(primo);
                    cadena.append("*");
                }else{
                    primo++;
                }
            }while(n>1);
            cadena.delete(cadena.length()-1,cadena.length());
            return cadena;
        }
        return new StringBuilder(n);
    }
    public static boolean puedeEntrar(int n){
        return n>=2&&n<=100000;
    }
    public static boolean esPrimo(int n){
        //int c=2;
        for (int i=2;i<=n-1;i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }
}
