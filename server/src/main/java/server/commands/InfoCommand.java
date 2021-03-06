package server.commands;

import messages.AnswerMsg;
import server.Main;
import server.collection.CollectionManager;
import general.data.User;

public class InfoCommand implements Commandable{
    CollectionManager collectionManager;

    /**
     * Constructor, just set variables for work
     * @param col Collection Manager
     */
    public InfoCommand(CollectionManager col){
        collectionManager = col;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public boolean execute(String arg, Object obArg, AnswerMsg ans, User user) {
        ans.AddAnswer(collectionManager.getInfo());
        Main.logger.info("Пользователь получил информацю о колекции");
        return true;
    }
}
