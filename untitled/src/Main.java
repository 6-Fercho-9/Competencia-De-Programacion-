import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        //Scanner input2=new Scanner(System.in);
        //int N=input2.nextInt();
        long startTime=System.nanoTime();
        int[]v={284};
        start(v);
        long endTime=System.nanoTime();
        long duracion=(endTime-startTime)/1000000;
        System.out.println("Tiempo: "+duracion+" milisegundos");
    }
    public static void start(int v[]){

        //Scanner input2=new Scanner(System.in);
        //int N=input2.nextInt();
        int c=0;
        //if(puedePasar(N)){
        while(c<v.length) {
            //   Scanner input=new Scanner(System.in);
            int n=v[c];
            boolean b=false;
            if(puedePasar(n)){
                String cadena="";
                int sumaDivisoresN=sumaDivisores(n);
                boolean esPerfecto=esPerfecto(n,sumaDivisoresN);
                if(esPerfecto){
                    cadena+=n+" es perfecto";
                    System.out.println(cadena);
                }else{
                    boolean esRomantico=esRomantico(n,sumaDivisoresN);
                    if(esRomantico){
                        cadena+=n+" es Romantico";
                        b=true;
                    }
                    boolean esAbundante=esAbundante(n,sumaDivisoresN);
                    if(esAbundante){
                        if(b)
                            cadena+=" Abundante";
                        else
                            cadena+=n+" es Abundante";
                    }
                    if(!esAbundante&&!esRomantico){
                        cadena+=n+" es Complicado";
                    }
                    System.out.println(cadena);
                }
            }
            c++;
        }
        //}
    }
    public static boolean puedePasar(int n){
        return n>=1&&n<=100000;
    }
    public static int sumaDivisores(int n){
        if(n==0||n==1)
            return 0;
        int suma=0;
        for(int i=1;i<=n/2;i++){
            if(n%i==0){
                suma+=i;
            }
        }

        return suma;
    }
    public static boolean esPerfecto(int n,int sumaDivisoresN){
        return n==sumaDivisoresN;
    }
    public static boolean esRomantico(int n,int sumaDivisoresN){
        int sumaDivisoresDeSumaDivisores=sumaDivisores(sumaDivisoresN);
        return n==sumaDivisoresDeSumaDivisores;
    }
    public static boolean esAbundante(int n,int sumaDivisoresN){
        return sumaDivisoresN>n;
    }

}