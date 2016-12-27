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

    public PackageFirstCategory(Item item, SmartPost start, SmartPost destination, boolean sent) {
        super(item, start, destination, sent);
        maxWidth = 60d;
        maxHeight = 36d;
        maxDepth = 59d;
    }
    
    public PackageFirstCategory () {}
    public PackageFirstCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
        maxWidth = 60d;
        maxHeight = 36d;
        maxDepth = 59d;
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
            this.sendMessage = "Matka olise liian pitkä ja TIMO kieltäytyy toimituksesta.\n";
            return false;
        }
        //Else
        this.sendMessage = this.item.getName() + " lähetetty 1.lk pakettina.\n";
        this.item.throwAround();
        if (this.item.isBroken())
            this.sendMessage += "Ikävä kyllä TIMO-mies rikkoi paketin matkalla.\n";
        this.sent = true;
        return true;
    }

    @Override
    public String getColor() {
        return "green";
    }
}
