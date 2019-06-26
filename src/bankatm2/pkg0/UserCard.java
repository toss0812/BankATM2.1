/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankatm2.pkg0;

/**
 *
 * @author tding
 */
public class UserCard {
    String iban;
    
    public UserCard(String i){
        iban = i;
    }
    
    public String getIban(){
        return this.iban;
    }
    
    public void setIban(String i){
        iban = i;
    }
}
