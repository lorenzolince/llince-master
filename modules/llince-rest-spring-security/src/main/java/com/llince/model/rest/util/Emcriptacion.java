/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.llince.model.rest.util;
import java.security.MessageDigest;
import org.springframework.security.access.annotation.Secured;
/**
 *
 * @author loren
 */
public class Emcriptacion {
   
   public static String encripta(String clave,String algorithm)
	{
		byte[] plainText = clave.getBytes();

        MessageDigest md = null;

        try {		
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
        md.reset();		
        md.update(plainText);
        byte[] encodedPassword = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < encodedPassword.length; i++) {
            if ((encodedPassword[i] & 0xff) < 0x10) {
                sb.append("0");
            }

            sb.append(Long.toString(encodedPassword[i] & 0xff, 16));
        }

//        System.out.println("Plain    : " + clave);
//        System.out.println("Encrypted: " + sb.toString()+"1");
        
        return sb.toString();
	} 
   

public static void main(String[] args) {
    System.out.println("#######################");
 System.out.println(Emcriptacion.encripta("admin", "Md5"));
 System.out.println("#######################");

      }
               
	 } 

