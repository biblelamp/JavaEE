/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import boundary.MessageFacade;
import entities.Message;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Sergey Irjupin
 * @version 0.1 dated Jan 15, 2017
 */
@Named(value = "MessageView")
@RequestScoped
public class MessageView {

    // Creates a new field
    private final Message message;
    
    /**
     * Creates a new instance of MessageView
     */
    public MessageView() {
        this.message = new Message();
    }

    // Injects the MessageFacade session bean using the @EJB annotation
    @EJB
    private MessageFacade messageFacade;
    
    // Calls getMessage to retrieve the message
    public Message getMessage() {
       return message;
    }

    // Returns the total number of messages
    public int getNumberOfMessages(){
       return messageFacade.findAll().size();
    }

    // Saves the message and then returns the string "theend"
    public String postMessage(){
       this.messageFacade.create(message);
       return "theend";
    }
}
