import javax.swing.text.html.parser.Parser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;


public class Estacionamento {
    String[] placas;


    public  Estacionamento(int n) throws Exception{ //n vagas
        if(n>0) {
            placas = new String[n];
        }
        else{
            throw new Exception("O estacionamento deve ter >0 vagas");
        }

    }

    public void entrar(String placa,int vaga) throws Exception {
           SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
           String data= formato.format(new Date());
           File arq= new File("historico.csv");
           if(vaga >=1 && vaga <= placas.length  ){
               if(placas[vaga - 1]==null) {

                   try (BufferedWriter bw = new BufferedWriter(new FileWriter(arq,true))) {
                       bw.write(String.format("ENTRADA;%s;%s;%s\n",data,Integer.toString(vaga),placa));
                   }
                   catch (IOException e) {
                       e.printStackTrace();
                   }
                   finally {
                       placas[vaga - 1] = placa;
                   }
               }
            else{
                throw new Exception("A vaga já esta ocupada!");
               }
           }
           else{
                throw new Exception("A vaga informada esta fora do limite do estacionamento");
           }

    }

    public void sair(int vaga) throws Exception{
            SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            String data= formato.format(new Date());
            File arq= new File("historico.csv");
        if(vaga>=1 && vaga<= placas.length){
            if(!(placas[vaga -1] == null)){
                try(BufferedWriter bw= new BufferedWriter(new FileWriter(arq,true))){
                    bw.write(String.format("Saída;%s;%s;%s\n",data,Integer.toString(vaga),placas[vaga-1]));
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                finally {
                    placas[vaga -1 ]=null;
                }
            }
            else {
                throw new Exception("A saída já esta desocupada");
            }
        }
        else{
            throw new Exception("a vaga de saída não é válida");
        }

    }

    public int consultarPlaca(String placa){
       for(int i=1;i<= placas.length;i++){
           if(placa.equals(placas[i-1])){
               return i;
           }
       }
       return -1;
    }



    public void transferir(int vaga1,int vaga2){
        placas[vaga2 -1]= placas[vaga1 -1];
        placas[vaga1 -1]=null;
    }


    public String[] listarGeral(){
        String[] conteudo=new String[placas.length];

        for(int i=1;i<=placas.length;i++){
            if(!(placas[i-1]==null)) {
                conteudo[i - 1] = placas[i - 1];
            }
            else{
                conteudo[i-1]="livre";
            }
        }


        return conteudo;
    }

    public ArrayList<Integer> listarLivres(){
        ArrayList<Integer> livres=new ArrayList<>();
        for(int i=1;i<=placas.length;i++){
            if(placas[i-1]==null){
                livres.add(i);
            }
        }
        return livres;
    }

public void gravarDados(){
        File arq= new File("placas.csv");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(arq))){
            for(int i=1;i<=placas.length;i++) {
                if (placas[i - 1] != null) {
                    bw.write(String.format("%s;%s\n", Integer.toString(i), placas[i - 1]));
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }

}

public void lerDados(){
        ArrayList<String> lidas =new ArrayList<>();
        File arq= new File("placas.csv");
            try(BufferedReader br=new BufferedReader(new FileReader(arq))){
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    String placa = values[1];
                    lidas.add(placa);
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

    }





}
