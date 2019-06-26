/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankatm2.pkg0;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tding
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /* MAIN PROGRAM FLOW*/
        /*
        [Card Screen] > [Pin Screen]
        
        [Pin Screen](
            if valid combination > [Option Screen]
            if invalid > retry
                if 3x > block card
        )
        
        [Option Screen] > {Quick Select} > [Withdraw Screen, QS = false], {Longway round} > [Withdraw Screen, QS = false], {Balance Check} > [Balance Check Screen]
        
        [Withdraw Screen](
            with QS == true
            > {R100}, {R500}, {R1000} > allways without receipt
            if QS == false
            > same as QS == true and {Custom Withdraw}, > after choosing > [Receipt Screen]
        )
        
        [Receipt Screen](
            > {Digital Receipt, Print on screen}, {Fysical Receipt, send to Printer}, {No Receipt}
            > Send instructions to Dispenser
        ) > retrun to [Card Screen]
        
        
        */
        
        Program p = new Program();
        p.reset();
    }
}

class Program{
    //## FIELDS ##//
    UserCard currentCard = new UserCard("");
    String tempPin;
    int failedPinAttempts = 0;
    BannedCards bannedCards = new BannedCards();
    
    //## CONNECTORS ##//
    ApiConnection api = new ApiConnection("school.pyonium.com");
    
    Hardware keypadAndRFID = new Hardware("COM4");
    Hardware printer = new Hardware("COM5");
    Hardware dispenser = new Hardware("COM6");
    
    //## METHODS ##//
    public void run(){
        //TODO: Load [Card Screen]
        
        //TODO: Input RFID/Arduino
        currentCard.setIban(keypadAndRFID.getInput());
        
        //TODO: Check ID for banned card
        if(!bannedCards.checkCard(currentCard.getIban())){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            reset();
        }
        //TODO: Input Keypad/Arduino
        tempPin = keypadAndRFID.getInput();
        
        
        try {
            //TODO: Check Pin&Iban combination
            api.checkPin(currentCard.getIban(), tempPin);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        //TODO: Show "screens"
        
        //TODO: Output printer
        
        //TODO: Output Gelddispenser
        
        //TODO: Reset
        reset();
    }
    
    public void reset(){
        currentCard = null;
        run();
    }
}

