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


public class Estacionamento {
    private String[] placas;


    public  Estacionamento(int n){ //n vagas
        placas=new String[n];
    }

    public void Entrar(String placa,int vaga) throws VagaException{
           Date data=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(data);
        FileWriter writer=new FileWriter("historico.csv");
        BufferedWriter bw= new BufferedWriter(writer);
            if (vaga > 0 && vaga <= placas.length) {
                if (placas[vaga - 1] == null) {
                    placas[vaga - 1] = placa;
                    //gravadatadeentrada
                   bw.write("entrada: "+ dataFormatada);
                   bw.close();
                   writer.close();
                }
                else{
                    throw new VagaException("A vaga ja esta ocupada amigo");
                }
            }
            else{
                throw new VagaException("A vaga não existe no contexto atual ");
            }
    }

    public void Sair(int vaga){
        placas[vaga-1]="";
        //gravadatadesaida
        Date data=new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formato.format(data);
        FileWriter writer=new FileWriter("historico.csv");
        BufferedWriter bw= new BufferedWriter(writer);

        bw.write("saída: "+ dataFormatada);
        bw.close();
        writer.close();


    }

    public int ConsultarPlaca(String placa) throws VagaException {
       int posicao=-1;
        for(int i=0;i<placas.length;i++) {
            if (placas[i].equals(placa)) {
                posicao=i+1;
            }
        }
      return posicao;
    }

    public ArrayList<Integer> listarLivres(){
        ArrayList<Integer> livres=new ArrayList<>();
        for(int i=0;i<placas.length;i++){
            if(placas[i].isEmpty()){
                livres.add(i+1);
            }
        }
        return livres;
    }

public void GravarDados(){
        FileWriter writer=new FileWriter("placas.csv");
        BufferedWriter bw= new BufferedWriter(writer);
        for(String placa : placas){

            bw.write("\n"+placa);
        }
        bw.close();
        writer.close();
        System.out.println("Placas gravadas com sucesso em " +"placas.csv");
}

public void Lerdados(){
    FileReader reader=new FileReader("placas.csv");
    BufferedReader br= new BufferedReader(reader);
    ArrayList<strings>  lista=new ArrayList<strings>();
        while(line=br.readLine()!=null){
            lista.add(line);
            }
    br.close();
    reader.close();
    System.out.println(lista.toString());

    }

}
