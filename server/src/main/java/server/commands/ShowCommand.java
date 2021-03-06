package server.commands;

import messages.AnswerMsg;
import server.Main;
import server.collection.CollectionManager;
import general.data.User;

public class ShowCommand implements Commandable{
    CollectionManager collectionManager;
    /**
     * Constructor, just set variables for work
     * @param col Collection Manager
     */
    public ShowCommand(CollectionManager col){
        collectionManager = col;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public boolean execute(String arg, Object obArg, AnswerMsg ans, User user) {
        ans.AddAnswer("Коллекция:\n");
        ans.AddAnswer(collectionManager.getList());
        Main.logger.info("Пользователь запросил коллекцию");
        return true;
    }
}
