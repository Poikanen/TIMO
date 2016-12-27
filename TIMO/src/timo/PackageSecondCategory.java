/*
 * Authors: 	Toivo Mattila
 *		Tommi Wäänänen
 *
 * Last modified: 2016-12-27
 */
package timo;

public class PackageSecondCategory extends Package{

    public PackageSecondCategory(Item item, SmartPost start, SmartPost destination, boolean sent) {
        super(item, start, destination, sent);
        maxWidth = 11d;
        maxHeight = 36d;
        maxDepth = 60d;
    }

    public PackageSecondCategory(Item item, SmartPost start, SmartPost destination) {
        super(item, start, destination);
        maxWidth = 11d;
        maxHeight = 36d;
        maxDepth = 60d;
    }
    
    private PackageSecondCategory(Package copy){
        super(copy);
    }
    
    @Override
    public Package getCopy() {
        return new PackageSecondCategory(this);
    }

    @Override
    public String getCategory() {
        return "2";
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
        this.sent = true;
        this.sendMessage = "Paketti lähetetty.\n";
        return true;
    }

    @Override
    public String getColor() {
        return "blue";
    }
}
