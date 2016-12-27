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
public class PackageThirdCategory extends Package{

    public PackageThirdCategory(Item item, SmartPost start, SmartPost destination, boolean sent) {
        super(item, start, destination, sent);
        maxWidth = 59d;
        maxHeight =36d;
        maxDepth =60d;
    }

    public PackageThirdCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
        maxWidth = 59d;
        maxHeight =36d;
        maxDepth =60d;
    }
    
    private PackageThirdCategory(Package copy){
        super(copy);
        maxWidth = 59d;
        maxHeight =36d;
        maxDepth =60d;
    }
    
    @Override
    public Package getCopy() {
        return new PackageThirdCategory(this);
    }

    @Override
    public String getCategory() {
        return "3";
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
        this.sendMessage = this.item.getName() + " lähetetty 3.lk pakettina.\n";
        this.item.throwAround();
        this.sent = true;
        if(this.item.isBroken()){
            this.sendMessage += "Ikävä kyllä TIMO-mies rikkoi paketin matkalla.\n";
        }
        return true;
    }

    @Override
    public String getColor() {
        return "red";
    }
}
