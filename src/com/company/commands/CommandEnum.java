package com.company.commands;

/**
 * Created by acer on 27.09.2015.
 */
public enum CommandEnum {
     GO_DOWN("GoDown"),
     GO_RIGHT("GoRight"),
     GO_UP("GoUp"),
     GO_LEFT("GoLeft"),
     IF("is wall"),
     FOR("I = 1,"),
     PROCEDURE("Proc"),
     JUMP_DOWN("JumpDown"),
     JUMP_RIGHT("JumpRight"),
     JUMP_UP("JumpUp"),
     JUMP_LEFT("JumpLeft"),

     START("Пуск"),
     CANCEL("Отмена"),
     CANCEL_IN_PROCEDURE("<html>Отмена<br>в процедуре</html>"),
     RETURN("В начало"),
     LINEAR_ALGORITHMS("<html>Линейные<br>алгоритмы</html>"),
     HELP("Помощь"),
     IF_ALGORITHMS("<html>Алгоритмы с<br>ветвлением</html>"),
     FOR_ALGORITHMS("<html>Циклические<br>алгоритмы</html>"),
     PROCEDURE_ALGORITHMS("Подпрограммы"),
     CONTROL_TASKS("<html>Контрольные<br>задания</html>"),
     BACK("Назад"),

     FIRST("1"),
     SECOND("2"),
     THIRD("3"),
     FORTH("4");

     private String name;

     private CommandEnum(String name) {
          this.name = name;
     }

     public String getName() {
          return name;
     }

}

