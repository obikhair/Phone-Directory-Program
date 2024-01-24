package prog02;

/**
 * A program to query and modify the phone directory stored in csc220.txt.
 *
 * @author vjm
 */
public class Main {

    /**
     * Processes user's commands on a phone directory.
     *
     * @param fn The file containing the phone directory.
     * @param ui The UserInterface object to use
     *           to talk to the user.
     * @param pd The PhoneDirectory object to use
     *           to process the phone directory.
     */
    public static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) {
        pd.loadData(fn);
        boolean changed = false;

        String[] commands = {"Add/Change Entry", "Look Up Entry", "Remove Entry", "Save Directory", "Exit"};

        String name, number, oldNumber;

        while (true) {
            int c = ui.getCommand(commands);
            switch (c) {
                case -1:
                    ui.sendMessage("You shut down the program, restarting.  Use Exit to exit.");
                    break;
                case 0:
                    name = ui.getInfo("Enter name ");
                    if (name == null) {
                        break;
                    }
                    if (name.equals("")) {
                        ui.sendMessage("Blank names are not allowed.");
                        break;
                    }
                    number = ui.getInfo("Enter number ");
                    if (number == null) {
                        break;
                    }
                    oldNumber = pd.addOrChangeEntry(name, number);
                    if (oldNumber == null){
                        ui.sendMessage(name + " was added to the directory\n" +
                                "New number: " + number);
                        changed = true;
                    }
                    else {
                        ui.sendMessage("Number for " + name + " was changed\n" +
                                "Old number: " + oldNumber +
                                "New number: " + number);
                        changed = true;
                    }
                    break;
                case 1:
                    name = ui.getInfo("Enter the name ");
                    if (name == null) {
                    break;
                    }
                    if (name.equals("")) {
                        ui.sendMessage("Blank names are not allowed.");
                        break;
                    }
                    number = pd.lookupEntry(name);
                    if (number == null){
                        ui.sendMessage(name + " is not listed in the directory");
                        break;
                    }
                    ui.sendMessage("The number for " + name + " is " + number);
                    break;
                case 2:
                    name = ui.getInfo("Enter name");
                    if (name == null) {
                        break;
                    }
                    if (name.equals("")) {
                        ui.sendMessage("Blank names are not allowed.");
                        break;
                    }
                    number = pd.removeEntry(name);
                    if (number == null){
                        ui.sendMessage(name + " is not listed in the directory");
                    }
                    else {
                        ui.sendMessage("Removed entry with name " + name + " and number " + number);
                        changed = true;
                    }
                    break;
                case 3:
                    pd.save();
                    changed = false;
                    break;
                case 4:
                    if (changed){
                        ui.sendMessage("The directory is changed. I am going to ask you if you want to exit without saving.");
                        String[] yes_no_command = {"Yes", "No"};
                        int response = ui.getCommand(yes_no_command);
                        if (response == 1){
                            break;
                        }


                    }
                    return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fn = "csc220.txt";
        // PhoneDirectory pd = new ArrayBasedPD();
        PhoneDirectory pd = new SortedPD();
        //UserInterface ui = new ConsoleUI();

        UserInterface ui = new GUI("Phone Directory");
        //UserInterface ui = new TestUI("Phone Directory");
        processCommands(fn, ui, pd);
    }
}
