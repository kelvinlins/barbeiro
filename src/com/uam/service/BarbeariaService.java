package com.uam.service;

import com.uam.model.Cliente;
import com.uam.model.Situacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class BarbeariaService {

    LinkedBlockingQueue<Cliente> clientQueue = new LinkedBlockingQueue<>(5);
    private Integer tempoLoop = 0, tempoDomindo = 0, tempoTotalDormindo = 0;

    public BarbeariaService() {
    }

    public void main(){

        Integer tempoCorte = 0, idCorte = 0;

        try {
            while (true){

                tempoLoop = 0;

                List<Cliente> clients = this.criaCliente();
                idCorte = idCorte + clients.size();

                for(Cliente cliente: clients){

                    clientQueue.offer(cliente);
                    Situacao situacao = this.cortandoCabelo(cliente, clientQueue, idCorte);
                    tempoLoop = tempoLoop + situacao.getTempo();

                    if((tempoCorte + tempoDomindo)/60 <= 8){
                        if("cortando".equals(situacao.getTipoTempo()))
                            tempoCorte = tempoCorte + situacao.getTempo();
                        else
                            tempoDomindo = tempoDomindo + situacao.getTempo();
                    }else{
                        Integer tempoCortesEmHoras = tempoCorte / 60;
                        Integer tempoRestoCorteHoras = tempoCorte % 60;

                        Integer tempoDormindoEmHoras = tempoTotalDormindo / 60;
                        Integer tempoRestoDormindoHoras = tempoTotalDormindo % 60;

                        System.out.println(situacao.getIdCorte() + " clientes atendidos em "
                                + tempoCortesEmHoras + ":" + tempoRestoCorteHoras);

                        System.out.println("TEMPO DORMINDO: " + tempoDormindoEmHoras + ":" + tempoRestoDormindoHoras + "H");

                        System.exit(0);
                    }

                }

                if(tempoLoop < 60){
                    Situacao situacao = this.cortandoCabelo(null, clientQueue, idCorte);
                    tempoDomindo = 60 - tempoLoop;
                    tempoTotalDormindo = tempoTotalDormindo + tempoDomindo;
                    System.out.println("TEMPO DORMINDO: " + tempoDomindo);
                }
            }

        }catch (Exception e){
            System.out.println("ERRO!");
        }

    }

    public synchronized Situacao cortandoCabelo(Cliente cliente, LinkedBlockingQueue<Cliente> clientQueue, Integer idCorte){
        Situacao situacao = new Situacao();
        Integer tempo = 0;
        try{
            if(clientQueue.size() != 0){
                if(clientQueue.contains(cliente)){

                    tempo = this.tempoCorte();
                    System.out.println("----------------------------------------------------------------------------");
                    System.out.println(cliente.getNome() + " está cortando o cabelo");
                    Thread.sleep((long) (Math.random() * tempo * 100));
                    System.out.println(cliente.getNome() + " demorou " + tempo + " para cortar o cabelo");
                    System.out.println();

                    clientQueue.remove(cliente);

                    idCorte++;
                    situacao.setTempo(tempo);
                    situacao.setTipoTempo("cortando");
                    situacao.setIdCorte(idCorte);

                    return situacao;

                }else{
                    System.out.println("CLIENTE NÃO EXISTENTE AQUI!");
                    tempo = 5;
                    situacao.setTempo(tempo);
                    situacao.setTipoTempo("dormindo");
                }
            }else{
                System.out.println("BARBEIRO ESTÁ DORMINDO!");
            }
            return situacao;
        } catch (Exception e){
            e.printStackTrace();
            return situacao;
        }
    }

    public Cliente criaClientesAleatorio(){

        List<String> arrayNom = Arrays.asList("Kelvin", "Andre", "Gabriel", "Pedro", "Giovanni", "Paulo", "Jõao",
                "Guilherme", "Alex", "Marcos", "Luiz", "Julio", "Alfredro");
        Random random = new Random();

        Cliente NewCliente = new Cliente(1, arrayNom.get(random.nextInt(arrayNom.size()-1)), false);

        return NewCliente;
    }

    private List<Cliente> criaCliente(){
        List<Cliente> clients = new ArrayList<>();

        try {
            for(int i=0;i<2;i++){
                Cliente cliente = this.criaClientesAleatorio();
                clients.add(cliente);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return clients;
    }

    private Integer tempoCorte(){

        List<Integer> tempos = Arrays.asList(25, 30);

        Random random = new Random();

        return tempos.get(random.nextInt(tempos.size()));
    }

}
