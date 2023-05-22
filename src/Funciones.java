import java.util.Stack;

public class Funciones {

    int contNumMov=0;

    public void limpiar(Stack <String> torreA,Stack <String> torreB,Stack <String> torreC){
        contNumMov=0;
        torreA.clear();
        torreB.clear();
        torreC.clear();
    }

    public double numMinMov (int n){
        return Math.pow(2,n)-1;
    }

    public String torres(int n){
        if (n==1){
            return "#";
        }else if(n<=0){
            return "";
        }else{
            return torres(n-1)+"#";
        }
    }

    public Stack<String> TorreA(int n, Stack<String>a){
        for(int i=0;i<n;i++){
            a.push(torres(n-i));
        }
        return a;
    }

    public void cambiarDeTorre(Stack<String>a,Stack<String>b){
        b.push(a.pop());
    }

    public void resolver(int numDiscos, Stack<String> origen, Stack<String> auxiliar, Stack<String> destino) {

        if (numDiscos == 1) {
            cambiarDeTorre(origen, destino);
        } else {

            resolver(numDiscos - 1, origen, destino, auxiliar);
            cambiarDeTorre(origen, destino);
            resolver(numDiscos - 1, auxiliar, origen, destino);
        }
    }

}
