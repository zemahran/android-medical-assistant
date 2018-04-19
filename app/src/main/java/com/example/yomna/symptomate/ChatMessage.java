package com.example.yomna.symptomate;

/**
 * Created by Yomna on 12/1/2017.
 */

public class ChatMessage {
    public int isMine;
    public String message;
    public ChatMessage(int isMine, String message) {
        super();
        this.message = message;
        if(message.equals("Are you male or female?")){
            this.isMine=2;
        }else{
            if(message.length()>4&&message.substring(0,4).equals("Here")){
                this.isMine=3;
            }else{
                if(message.length()>4&&message.substring(0,4).equals("Your")){
                    this.isMine=4;
                }else{
                    if(message.length()>15&&!message.substring(0,15).equals("How old are you")&&message.substring(message.length()-1).equals("?")&&!message.substring(0,1).equals("W")){
                        this.isMine=5;
                    }else{
                        if(message.length()>9&&message.substring(0,10).equals("conditions")){
                            this.isMine=6;
                        }else
                            this.isMine = isMine;
                    }
                }
            }
        }
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int isMine() {
        return isMine;
    }

    public void setIsMine(int isMine) {
        this.isMine = isMine;
    }
}
