package server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.collection.CollectionManager;
import server.commands.*;
import server.connection.DatabaseManager;
import server.connection.DatabaseUserManager;
import server.file.FileManager;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.PortUnreachableException;

/**
 * Main class for server
 * @author Konanykhina Antonina
 */
public class Main {
    public static Logger logger = LogManager.getLogger("ServerLogger");
    public static final int PORT = 1821;
    public static final int CONNECTION_TIMEOUT = 60000;
    public static final int MAX_CONNECTIONS = 10;
    public static final String DATABASE_HOST = "jdbc:postgresql://pg:5432/studs";
    public static final String DATABASE_USER = "s314929";
    public static final String DATABASE_PASS = "ooo222";

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Code error");
        }
        String path = System.getenv().get("WAY_OF_THE_NINJA");
        if (path == null) {
            logger.error("Нет переменной с загрузочным файлом");
        }
        FileManager fileManager = new FileManager(path);
        CollectionManager collectionManager = new CollectionManager(fileManager);

        DatabaseManager databaseManager = new DatabaseManager(DATABASE_HOST, DATABASE_USER, DATABASE_PASS);

        DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseManager);

        CommandManager commandManager = new CommandManager(collectionManager, new Commandable[]{
                new ClearCommand(collectionManager), new AddCommand(collectionManager),
                new ShowCommand(collectionManager), new InfoCommand(collectionManager),
                new UpdateIdCommand(collectionManager), new RemoveByIdCommand(collectionManager),
                new RemoveByIndexCommand(collectionManager), new AddIfMaxCommand(collectionManager),
                new RemoveAllByStudentsCountCommand(collectionManager),
                new FilterStartsWithNameCommand(collectionManager),
                new FilterLessThenExpelledCommand(collectionManager), new SaveCommand(collectionManager)});
        Server server = new Server(PORT, CONNECTION_TIMEOUT, commandManager, MAX_CONNECTIONS, databaseUserManager);

        server.run();
    }

}
