

package com.mycompany.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class Cliente3 {

    public static void main(String[] args) throws IOException{
        ServerSocket socketEspecial = new ServerSocket (8080);
        Socket cliente = socketEspecial.accept();
        int c=0;
        
        PrintWriter escritor =  new PrintWriter(cliente.getOutputStream(), true);
        BufferedReader lectorSocket = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
         
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        
        String entrada, ent; 
        String mensaje;
        String diferencia;
        Random random = new Random();
        int intento=0;
        int numero = random.nextInt(10+1);
        System.out.println("el numero ganador es " + numero);
        while(intento <2){
        while((entrada = lectorSocket.readLine())!= null){
            System.out.println(entrada.toUpperCase());

            
                
            try{
                Integer.parseInt(entrada);
                c=Integer.parseInt(entrada);
                if(c<=10){
                    if(c==numero){
                        mensaje="Ganaste! Quieres volver a jugar? Y/N";
                        escritor.println(mensaje);
                        if ((ent = lectorSocket.readLine().toUpperCase())  == "Y") {
                            intento = 0;
                            numero = 0;
                            numero = random.nextInt(10+1);
                            mensaje = "Adivina el numero aleatorio del 1 al 10";
                            escritor.println(mensaje);
                            System.out.println("El numero ganador es " + numero);
                        }else{
                            mensaje = "FIN";
                            escritor.println();
                        }
                    }else{
                        if(c<numero){
                            diferencia="mayor";
                        }else{
                            diferencia="menor";
                        }
                        
                        if(intento==2){
                            mensaje = "Perdiste, Quieres volver a jugar? Y/N";
                            escritor.println(mensaje);
                            if ((ent = lectorSocket.readLine().toUpperCase())  == "Y") {
                                intento = 0;
                                numero = 0;
                                numero = random.nextInt(10+1);
                                mensaje = "Adivina el numero aleatorio del 1 al 10";
                                escritor.println(mensaje);
                                System.out.println("El numero ganador es " + numero);
                            }else{
                                mensaje = "FIN";
                                escritor.println();
                            }
                        }
                        if (intento < 2) {
                            intento++;
                            mensaje = "Incorrecto, el numero es " +diferencia+ ", te quedan " +(3-intento)+ " intentos";
                            escritor.println(mensaje);
                        }
                        
                    }
                }else{
                    mensaje="Escribe un numero menor a 10";
                    escritor.println(mensaje);
                }
                
            }catch(NumberFormatException e){
                    mensaje="Escribe un numero valido del 1 al 10";
                    escritor.println(mensaje);
                    }
            
            }
    }
    }
}
