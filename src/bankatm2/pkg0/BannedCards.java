/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankatm2.pkg0;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author tding
 */
public class BannedCards {
    
    public LinkedList<UserCard> cards = new LinkedList<UserCard>();
    public Iterator<UserCard> cardIterator = cards.iterator();
    
    public BannedCards(){
        
    }
    
    public boolean checkCard(String iban){ // if List contains same iban as parameter -> return false, else -> return true
        while(cardIterator.hasNext()){
            UserCard temp = cardIterator.next();
            
            if(temp.iban.equals(iban)) return false;
        }
        return true;
    }
    
    public void addCard(UserCard c){
        cards.add(c);
    }
}
