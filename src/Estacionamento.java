<<<<<<< HEAD
import javax.swing.text.html.parser.Parser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
=======
package Default;

>>>>>>> 75f3e07be99aeea2eff7b078d3257a1edd0c8049
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
=======
import java.util.Scanner;
>>>>>>> 75f3e07be99aeea2eff7b078d3257a1edd0c8049


public class Estacionamento {
    String[] placas;


<<<<<<< HEAD
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
=======
    public  Estacionamento(int n) throws Exception {
    	
    	if (n <= 0) {
    		throw new Exception("Número inválido para tamanho de vagas");
    	}
    	
    	placas = new String[n];
    }

    public void entrar(String placa,int vaga) throws Exception {  // ocupa a vaga com a placa e grava data de entrada no arquivo “historico.csv”      
       
        if (vaga < 1 || vaga > placas.length) {
            throw new Exception("Vaga fora do limite de 1 a " + placas.length);
        }
        if (placas[vaga - 1] != null) {
            throw new Exception("Vaga já ocupada.");
>>>>>>> 75f3e07be99aeea2eff7b078d3257a1edd0c8049
        }
        
        placas[vaga - 1] = placa;
        this.registrarHistorico(vaga, placa, "entrada");    
    }

<<<<<<< HEAD
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




=======
    public void sair(int vaga) throws Exception { // desocupa a vaga e grava data de saída no arquivo “historico.csv”
    	
    	if (vaga < 1 || vaga > placas.length) {
            throw new Exception("Vaga fora do limite de 1 a " + placas.length);
        }
        if (placas[vaga - 1] == null) {
            throw new Exception("Vaga já desocupada.");
        }
        
        String placa = placas[vaga - 1];
        placas[vaga - 1] = null;
        
        this.registrarHistorico(vaga, placa, "saida"); 
    }

    public int consultarPlaca(String placa) { // retorna a vaga da placa, ou -1 caso a placa não exista.
    	
       for (int i = 0; i < placas.length; i++) {
    	   if (placas[i] != null && placas[i].equals(placa)) {
    		   return i + 1;
    	   }
       }
       return -1;
    }
    
    public void transferir(int vaga1, int vaga2) throws Exception { // move a placa da vaga1 para a vaga2
    	
    	if (vaga1 == vaga2) {
    		throw new Exception("Você já tá nessa vaga");
    	}
    	if ((vaga1 < 1 || vaga1 > placas.length) || (vaga2 < 1 || vaga2 > placas.length)) {
    		throw new Exception("Vaga fora do limite de 1 a " + placas.length);
    	}
    	if (placas[vaga1 - 1] == null) {
    		throw new Exception("Vaga " + vaga1 + " está desocupada.");
    	}
    	if (placas[vaga2 - 1] != null) {
    		throw new Exception("Vaga " + vaga2 + " já ocupada.");
    	}
    	
    	placas[vaga2 - 1] = placas[vaga1 - 1];
    	placas[vaga1 - 1] = null;
    }
    
    public String[] listarGeral() { // retorna o conteúdo das N vagas (placa ou “livre”)
    	
    	// // Cria um novo array de String com o tamanho do número de vagas do estacionamento
    	int tamanho = placas.length;
    	String[] conteudoVagas = new String[tamanho];
    	
    	for (int i = 0; i < placas.length; i++) { // percorre todas as vagas do Estacionamento
    		if (placas[i] != null) {              // se tiver ocupada, adiciona a placa na posição correspondente do array
    			conteudoVagas[i] = placas[i];
    		} else {                              // se tiver vazia, adiciona "livre" na posição correspondente do array  
    			conteudoVagas[i] = "livre";
    		}
    	}
    	return conteudoVagas;
    }

    public ArrayList<Integer> listarLivres() { // retorna os números das vagas livres
    	
    	ArrayList<Integer> livres = new ArrayList<>();
    	
    	for (int i = 0; i < placas.length; i++) {
    		if (placas[i] == null) {
    			livres.add(i + 1);   // se a vaga tiver vazia, adiciona o indice + 1 (nº da vaga) no ArrayList
    		}
    	}
    	return livres;
    }

    public void gravarDados() throws IOException { // gravar no arquivo “placas.csv”, a placa de cada vaga ocupada no momento
    	
        FileWriter writer = new FileWriter(new File("placas.csv"), true);
        BufferedWriter bw = new BufferedWriter(writer);
        
        for(int i = 0; i < placas.length; i++) {       
        	if (placas[i] != null) { 					
        		bw.write((i + 1) + ";" + placas[i] + "\n");        		
        	}
        }
        
        bw.close(); // o BufferedWriter já possui um método "close()" que também fecha o FileWriter.
    }

    public void lerDados() throws FileNotFoundException { // ler do arquivo “placas.csv”, a placa de cada vaga ocupada no momento
    	
    	Scanner arquivo = new Scanner(new File("placas.csv"));
    	String linha;
    	String[] placa;
    	
    	do {
    		linha = arquivo.nextLine();
    		placa = linha.split(";");
    		System.out.println(placa[1]);
    		
    	} while (arquivo.hasNextLine());
    		
    	arquivo.close();

    }

	private void registrarHistorico(int vaga, String placa, String tipo) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String dataHora = formatter.format(now);
		FileWriter writer = new FileWriter(new File("historico.csv"), true);
		writer.append(dataHora + ";" + vaga + ";" + placa + ";" + tipo + "\n");
		writer.close();
	}
>>>>>>> 75f3e07be99aeea2eff7b078d3257a1edd0c8049

}
