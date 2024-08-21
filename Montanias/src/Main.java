//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //StringBuilder cad=new StringBuilder(100000);
        //la cadena no puede ser negativa
        try{
            System.out.println(getPosicionMontaniaMasAlta("+-+-+++--+--"));
            System.out.println(getPosicionMontaniaMasAlta("+++-++-+--+++-+++--+"));
          //  System.out.println(getPosicionMontaniaMasAlta(cad.toString()));
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }
    public static int getPosicionMontaniaMasAlta(String secuencia) throws Exception{
        StringBuilder secuenciaBuilder=new StringBuilder(secuencia);
        int mayor=0;
        int acumuladorPosicionCadena =0;
        boolean sw;
        int posicionMayor=0;
        for (int i = 0; i < secuenciaBuilder.length(); i++) {
            if(secuenciaBuilder.charAt(i)=='+'){
                acumuladorPosicionCadena +=1;
                sw=true;
            }else{
                acumuladorPosicionCadena +=-1;
                sw=false;
            }
            //if no se si es necesario
            if (acumuladorPosicionCadena ==-1)
                throw new RuntimeException("Error..Camino no valido..");
            if(sw==false){
                if(acumuladorPosicionCadena+1>mayor){
                    mayor=acumuladorPosicionCadena+1;
                    posicionMayor=i;
                }
            }
        }
        if(posicionMayor==0) return acumuladorPosicionCadena;
        return posicionMayor;
    }
}