import java.util.Stack;

public class EvaluarSufija {
    public static int evaluarSufija(String[] sufija){
        Stack<Integer> pila = new Stack<>();
        for(String elemento : sufija){
            if(elemento.matches("\\d+")){
                pila.push(Integer.parseInt(elemento));
            }else{
                int segundo = pila.pop();
                int primero = pila.pop();
                switch (elemento){
                    case "+":
                        pila.push(primero+segundo);
                        break;
                    case "-":
                        pila.push(primero-segundo);
                        break;
                    case "*":
                        pila.push(primero*segundo);
                        break;
                    case "/":
                        pila.push(primero/segundo);
                        break;
                }
            }
        }
        return pila.pop(); 
    }
}
