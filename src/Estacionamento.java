package Default;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Estacionamento {
    private String[] placas;


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
        }
        
        placas[vaga - 1] = placa;
        this.registrarHistorico(vaga, placa, "entrada");    
    }

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

}
