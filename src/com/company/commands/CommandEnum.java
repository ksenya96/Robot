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

     START("����"),
     CANCEL("������"),
     CANCEL_IN_PROCEDURE("<html>������<br>� ���������</html>"),
     RETURN("� ������"),
     LINEAR_ALGORITHMS("<html>��������<br>���������</html>"),
     HELP("������"),
     IF_ALGORITHMS("<html>��������� �<br>����������</html>"),
     FOR_ALGORITHMS("<html>�����������<br>���������</html>"),
     PROCEDURE_ALGORITHMS("������������"),
     CONTROL_TASKS("<html>�����������<br>�������</html>"),
     BACK("�����"),

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

