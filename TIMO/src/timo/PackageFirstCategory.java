/* TIMO, LUT Olio-ohjelmointi 2016
 * PackageFirstCategory.java
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

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
            this.sendMessage = "Matka oli liian pitkä ja TIMO ei jaksanut loppuun saakka.\n";
            return false;
        }
        //Else
        this.sendMessage = "Paketti lähetetty.\n";
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
