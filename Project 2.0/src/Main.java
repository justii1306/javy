import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Database db = new Database();

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        String input;
        String[] command;
        while(true){
            input = scan.nextLine();
            command = input.split(" ");
            try {
                if (command[0].equalsIgnoreCase("help")){
                    help(command);
                } else if (command[0].equalsIgnoreCase("create") &&
                        command[1].equalsIgnoreCase("table")) {
                    createTable(input, command);
                } else if (command[0].equalsIgnoreCase("remove") &&
                        command[1].equalsIgnoreCase("table")) {
                    removeTable(command);
                } else if (command[0].equalsIgnoreCase("insert") &&
                        command[1].equalsIgnoreCase("into")) {
                    insertInto(input, command);
                } else if (command[0].equalsIgnoreCase("select") &&
                        command[command.length - 2].equalsIgnoreCase("from")) {
                    select(input, command);
                } else if (command[0].equalsIgnoreCase("update")) {
                    update(input, command);
                } else {
                    System.out.println("Invalid command, type \"help\" to see help information");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid command, type \"help\" to see help information");
            }
        }
    }

    private static void help(String[] command) {
        if (command.length == 1) {
            System.out.println("List of commands : CREATE TABLE, REMOVE TABLE, INSERT INTO, SELECT, UPDATE, DELETE");
            System.out.println("Type \"help [name]\" to see help information for that command");
        } else {
            if (command[1].equalsIgnoreCase("create") &&
                    command[2].equalsIgnoreCase("table")) {
                System.out.println("Creates new table");
                System.out.println("Usage: CREATE TABLE [table name] ([column name 1], ... [column name N])");
            } else if (command[1].equalsIgnoreCase("remove") &&
                    command[2].equalsIgnoreCase("table")) {
                System.out.println("Removes new table");
                System.out.println("Usage: REMOVE TABLE [table name]");
            } else if (command[1].equalsIgnoreCase("insert") &&
                    command[2].equalsIgnoreCase("into")) {
                System.out.println("Inserts values into table");
                System.out.println("Usage: INSERT INTO [table name] ([value1] , [value2], ... ,[valueN])");
            } else if (command[1].equalsIgnoreCase("select")) {
                System.out.println("Shows records that are in the table");
                System.out.println("Usage 1: SELECT * from [table name]");
                System.out.println("Usage 1: SELECT [column name 1], ... [column name N] from [table name]");
            } else if (command[1].equalsIgnoreCase("update")) {
                System.out.println("Updates records");
                System.out.println("Usage: UPDATE [table name] [record number] ([column name 1], ..., [column name N]) ([value1], ..., [valueN])");
            } else if  (command[1].equalsIgnoreCase("delete")) {

            } else {
                System.out.println("Invalid command, type \"help\" to see help information");
            }
        }
    }

    private static void createTable(String input, String command[]){
        String tableName, parentesis;
        String[] columnNames;
        int openParentesis, closeParentesis;
        openParentesis = input.indexOf('(');
        closeParentesis = input.indexOf(')');
        tableName = command[2]; //CREATE TABLE [tableName]
        try {
            parentesis = input.substring(openParentesis + 1, closeParentesis); //extract from parentesis
            columnNames = parentesis.split(", "); //split to separate column names
            if (db.addTable(tableName, columnNames) != null)
                System.out.println("Table with that name is already present in the database!");
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("Invalid command, type \"help\" to see help information");
        }
    }

    private static void removeTable(String[] command) {
        String tableName = command[2]; //REMOVE TABLE [tableName]
        if (db.removeTable(tableName) == null){
            System.out.println("There is no table with that name!");
        }
    }

    private static void insertInto(String input, String[] command) {
        String tableName, parentesis;
        String values[];
        int openParentesis, closeParentesis;
        openParentesis = input.indexOf('(');
        closeParentesis = input.indexOf(')');
        tableName = command[2]; //INSERT INTO [tableName]
        parentesis = input.substring(openParentesis + 1, closeParentesis); //extract from parentesis
        values = parentesis.split((", ")); //split to separate column names
        if (db.getTable(tableName) == null){
            System.out.println("There is no table with that name!");
        } else if (!db.insertIntoTable(tableName, values)){
            System.out.println("Wrong number of values!");
            System.out.println("Expected: " + db.getTable(tableName).getColumnNames().length);
            System.out.println("Received: " + values.length);
        }
    }

    private static void select(String input, String[] command) {
        String tableName = command[command.length-1]; //SELECT [SOMETHING] FROM [tableName]
        if (command[1].equalsIgnoreCase("*")){
            db.select(tableName);
        } else {
            String columnNames[] = Arrays.copyOfRange(command, 1, command.length-2);
            db.select(tableName, columnNames);
        }
    }

    private static void update(String input, String[] command) {
        String tableName = command[1];
        int rowNumber;
        try {
            rowNumber = Integer.parseInt(command[2]);
            int firstOpenParentesis, firstCloseParentesis, secondOpenParentesis, secondCloseParentesis;
            String firstParentesis, secondParentesis;
            String[] columnNames, values;
            if (db.getTable(tableName) == null) {
                System.out.println("There is no table with that name!");
            } else {
                firstOpenParentesis = input.indexOf('(');
                firstCloseParentesis = input.indexOf(')', firstOpenParentesis);
                firstParentesis = input.substring(firstOpenParentesis + 1, firstCloseParentesis); //extract from parentesis
                columnNames = firstParentesis.split(", ");
                secondOpenParentesis = input.indexOf('(', firstCloseParentesis);
                secondCloseParentesis = input.indexOf('(', secondOpenParentesis);
                secondParentesis = input.substring(secondOpenParentesis + 1, secondCloseParentesis);
                values = secondParentesis.split(", ");
                db.update(tableName, rowNumber, columnNames, values);
            }
        } catch (NumberFormatException e){
        System.out.println("Invalid command, type \"help\" to see help information");
    }
    }
}
