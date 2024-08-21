import java.util.ArrayList;
import java.util.List;

public class EjercicioFunciones {
    public static void main(String[] args){
        int a=1;
        int b=4;
        int c=-4213;
        int l=-100;//limiteInferior
        int r=300;//limiteSuperior
        try{
            int x1=getX1(a,b,c);
            int x2=getX2(a,b,c);
            int menorPrimero;
            int mayorDespues;
            if(x1<x2){
                menorPrimero=x1;
                mayorDespues=x2;
            }else {
                menorPrimero = x2;
                mayorDespues = x1;
            }
            System.out.println("menor: "+menorPrimero);
            System.out.println("mayor: "+mayorDespues);
            System.out.println("l: "+l);
            System.out.println("r: "+r);
            boolean estaEnIntervaloL=estaEnElIntervalo(l,menorPrimero,mayorDespues);
            boolean estaEnIntervaloR=estaEnElIntervalo(r,menorPrimero,mayorDespues);
            if(estaEnIntervaloL&&estaEnIntervaloR){//caso 1 saco 1 integral
                String fraccion=ejecutarIntegral(r,l,a,b,c);
                System.out.println(fraccion);
            }else if(!estaEnIntervaloL&&!estaEnIntervaloR){//caso 3,saco 3 intergrales
                String fraccion1=ejecutarIntegral(l,menorPrimero,a,b,c);
                System.out.println("Fraccion 1: "+fraccion1);
                String fraccion2=ejecutarIntegral(menorPrimero,mayorDespues,a,b,c);
                System.out.println("Fraccion 2: "+fraccion2);
                String fraccion3=ejecutarIntegral(mayorDespues,r,a,b,c);
                System.out.println("Fraccion 3: "+fraccion3);
                List<String> listaFracciones=new ArrayList<>();
                listaFracciones.add(fraccion1);
                listaFracciones.add(fraccion2);
                listaFracciones.add(fraccion3);
                System.out.println("Resultado = "+getFraccionEntera(listaFracciones));
            }else if(!estaEnIntervaloL&&estaEnIntervaloR){//caso 2 saco 2 intergrales si solo 1 punto esta en el intervalo y otro no si R esta y  L no
                String fraccion1=ejecutarIntegral(l,menorPrimero,a,b,c);
                System.out.println("Fraccion 1: "+fraccion1);
                String fraccion2=ejecutarIntegral(menorPrimero,r,a,b,c);
                System.out.println("Fraccino 2: "+fraccion2);
                List<String> listaFracciones=new ArrayList<>();
                listaFracciones.add(fraccion1);
                listaFracciones.add(fraccion2);
                System.out.println("Resultado = "+getFraccionEntera(listaFracciones));
            }else{//caso 2 si L esta y R no
                String fraccion1=ejecutarIntegral(l,mayorDespues,a,b,c);
                System.out.println(fraccion1);
                System.out.println("Fraccion 1: "+fraccion1);
                String fraccion2=ejecutarIntegral(mayorDespues,r,a,b,c);
                System.out.println(fraccion2);
                System.out.println("Fraccion 2: "+fraccion2);
                List<String> listaFracciones=new ArrayList<>();
                listaFracciones.add(fraccion1);
                listaFracciones.add(fraccion2);
                System.out.println("Resultado = "+getFraccionEntera(listaFracciones));
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    //metodo para sacar el Maximo comun divisor y sacar la fraccion entera
    public static String getFraccionEntera(List<String> lista){
        int acumulador=1;
        for(String fraccion:lista){
            int indexBarra=fraccion.indexOf('/');
            acumulador*=Integer.parseInt(fraccion.substring(indexBarra+1,fraccion.length()));
        }
        int sumatoriaNumerador=0;
        for(String fraccion:lista){
            int indexBarra=fraccion.indexOf('/');
            int denominador=Integer.parseInt(fraccion.substring(indexBarra+1,fraccion.length()));
            int numerador=Integer.parseInt(fraccion.substring(0,indexBarra));
            sumatoriaNumerador+=(acumulador/denominador)*numerador;
        }
        return getFraccion(sumatoriaNumerador,acumulador);
    }
    //se asume que a siempre es distinto de 0
    public static String ejecutarIntegral(int limiteInferior, int limiteSuperior, int a, int b, int c){
        if(b==0&&c!=0){//si a!=0 y c!=0 y b==0  = >  Ax^2+0Bx+C  => Ax^2 + c
            int denominador=3;
            int numerador= Math.abs((int) (a*(Math.pow(limiteSuperior,3)-Math.pow(limiteInferior,3))+3*c*(limiteSuperior-limiteInferior)));
            return getFraccion(numerador,denominador);
        }else if(b!=0&&c!=0){//si a!=0 b!=0 y c!=0 = > Ax^2+Bx+C
            int denominador=6;
            int numerador=Math.abs((int)(2*a*(Math.pow(limiteSuperior,3)-Math.pow(limiteInferior,3))+3*b*(Math.pow(limiteSuperior,2)-Math.pow(limiteInferior,2))+6*c*(limiteSuperior-limiteInferior)));
            return getFraccion(numerador,denominador);
        }else if(b==0&&c==0){//si b=0 c=0 y a!=0  => Ax^2+0bx+0c => Ax^2
            int denominador=3;
            int numerador= (int) (a*(Math.pow(limiteSuperior,3)-Math.pow(limiteInferior,3)));
            return  getFraccion(numerador,denominador);
        }
        return null;
    }
    public static boolean esCaso2(int x,int limiteInferior,int limiteSuperior){
        return estaEnElIntervalo(x,limiteInferior,limiteSuperior);
    }

    public static String getFraccion(int numerador,int denominador){
        return getNumeroTotal(numerador,denominador,2);
    }
    public static String getNumeroTotal(int numerador,int denominador,int numeroDivisible){
        if(numeroDivisible>denominador){//caso base para parar la recursion
            return numerador+"/"+denominador;
        }
        if(denominador%numeroDivisible!=0){//si el denominador es 4 y por defecto empieza en 2,el 3 no es divisible por 4 no sirve
            //por lo tanto debo avanzar el numero divisible antes de examinar si es divisible el numero
            return getNumeroTotal(numerador,denominador,numeroDivisible+1);
        }
        if(numerador%denominador==0)
            return numerador/denominador+"/"+1;
        if(numerador%numeroDivisible==0)
            return getNumeroTotal(numerador/numeroDivisible,denominador/numeroDivisible,numeroDivisible);
        return getNumeroTotal(numerador,denominador,numeroDivisible+1);
    }
    public int getNumeroDivisible(int numero,int i){
        if(i<=numero){
            if(numero%i==0)
                return i;
            return getNumeroDivisible(numero,i+1);
        }
        return -1;
    }
    public static double resolveIntegral(int A,int B,int a,int b,int c){
        double valorA=(a*(Math.pow( A,3)-Math.pow(B,3)))/3;
        double valorB=(b*(Math.pow(A,2)-Math.pow(B,2)))/2;
        double valorC=c*(A-B);
        return Math.abs(valorA+valorB+valorC);
    }
    //a y b son los limites a analizar en el ejeX
    public static boolean estaEnElIntervalo(int a,int limiteA,int limiteB){
        return (a>=limiteA&&a<=limiteB);
    }
    public static int getX1(int a,int b,int c) throws Exception {
        return (-b+menos4AC(a,b,c))/(2*a);
    }
    public static int getX2(int a,int b,int c) throws Exception {
        return (-b-menos4AC(a,b,c))/(2*a);
    }
    public static int menos4AC(int a,int b,int c) throws Exception{
        int menos4AC=-4*a*c;
        if(b==0&&menos4AC<0)
            throw(new RuntimeException("Error..Valor Imaginario"));
/*        else if (b==0&&menos4AC==0&&a==0)
            throw(new RuntimeException("Error..Division por 0"));*///su asume que A!=0
        return (int)Math.sqrt(Math.pow(b,2)+menos4AC);
    }
}
