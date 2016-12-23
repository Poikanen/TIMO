/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timo;

/**
 *
 * @author Toivo
 */
public class PackageFirstCategory extends Package{
    
    public PackageFirstCategory () {}
    public PackageFirstCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
    }
    
    private PackageFirstCategory(Package copy){
        super(copy);
    }
    
    @Override
    public Package getCopy() {
        return new PackageFirstCategory(this);
    }

    @Override
    public String getCategory() {
        return "1";
    }

    @Override
    public String toString() {
        return this.getItem().getName() +": "+ this.getStart().getCity() +" to "+ this.getDestination().getCity();
    }

    @Override
    public boolean send() {
        //Return false if already sent
        if(this.sent){
            return false;
        }
        //Else if
        if(this.start.getGp().getDistanceTo(this.destination.getGp()) > 150){
            this.sent = false;
            this.sendMessage = "Matka oli liian pitkä ja TIMO ei jaksanut loppuun saakka.\n";
            return false;
        }
        //Else
        this.sent = true;
        this.sendMessage = "Paketti lähetetty.\n";
        return true;
    }

    @Override
    public String getColor() {
        return "green";
    }
}
