module edu.ntnu.idatt1002.team20 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens edu.ntnu.idatt1002.team20 to javafx.fxml;
    opens edu.ntnu.idatt1002.team20.models.expense to javafx.base;
    opens edu.ntnu.idatt1002.team20.models.income to javafx.base;
    opens edu.ntnu.idatt1002.team20.models.goal to javafx.base;

    exports edu.ntnu.idatt1002.team20;
    exports edu.ntnu.idatt1002.team20.models.expense;
    exports edu.ntnu.idatt1002.team20.models.debt;
    exports edu.ntnu.idatt1002.team20.models.income;
    exports edu.ntnu.idatt1002.team20.models.goal;
    exports edu.ntnu.idatt1002.team20.models.user;
    exports edu.ntnu.idatt1002.team20.models.bill;
    opens edu.ntnu.idatt1002.team20.models.bill to javafx.fxml;
    exports edu.ntnu.idatt1002.team20.controllers;
    opens edu.ntnu.idatt1002.team20.controllers to javafx.fxml;
    exports edu.ntnu.idatt1002.team20.utils;
    opens edu.ntnu.idatt1002.team20.utils to javafx.fxml;
}