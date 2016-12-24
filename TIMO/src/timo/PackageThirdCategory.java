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
    }

    public PackageThirdCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
    }
    
    private PackageThirdCategory(Package copy){
        super(copy);
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
        return "3";
    }

    @Override
    public boolean send() {
        //Return false if already sent
        if(this.sent){
            return false;
        }
        this.sendMessage = "Paketti lähetetty.\n";
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
