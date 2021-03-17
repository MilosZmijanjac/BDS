import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class RasporedController implements Initializable {
    public BorderPane root;
    public BorderPane contentPane;
    public Button btnBack;
    public Button btnNext;
    public Label lblTimeSpan;
    public GridPane controlGrid;
    public Button btnMonth;
    public Button btnWeek;
    public Button dodajButton;
    public ComboBox<String> predavacComboBox;
    public ComboBox<String> kategorijaComboBox;
    public Spinner<Integer> minuteSpinner;
    public Spinner<Integer> hourSpinner;
    public DatePicker datePicker;
    public ComboBox<String> tipComboBox;
    public AnchorPane adminPane;
    private List<RasporedInfo> entries;
    private LocalDateTime startDateTime=LocalDateTime.now();
    RasporedDAO rdao=new RasporedDAO();
    ZaposleniDAO zdao=new ZaposleniDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tipComboBox.getItems().addAll("Teorija","Vožnja");
        kategorijaComboBox.getItems().addAll("Sve","A1","A","B1","B","C1","C","D1","D","B+ E","C1+E","D1+E","C+E");
        for(Zaposleni z : zdao.getZaposleni())
        predavacComboBox.getItems().add(z.getIme()+", "+z.getPrezime()+", "+z.getJmbg());
        entries=rdao.getRasporedi();
        btnWeek.setDisable(true);

        createWeeklyCalendar(contentPane);
    }

    private void formatCalendar(GridPane calendarGrid){
        final int BORDERS = 5;
        int numColumns = 7;

        List<ColumnConstraints> cc = calendarGrid.getColumnConstraints();
        for (int currentColumn = 0; currentColumn < numColumns; currentColumn++){
            ColumnConstraints col = new ColumnConstraints();
            col.setPercentWidth(Math.ceil(100.0 / numColumns));
            cc.add(col);
        }


        Label lblSunday = new Label("Nedelja");
        Label lblMonday = new Label("Ponedjeljak");
        Label lblTuesday = new Label("Utorak");
        Label lblWednesday = new Label("Srijeda");
        Label lblThursday = new Label("Cetvrtak");
        Label lblFriday = new Label("Petak");
        Label lblSaturday = new Label("Subota");

        lblSunday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblMonday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblTuesday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblWednesday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblThursday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblFriday.setPadding(new Insets(0, 0, BORDERS, 0));
        lblSaturday.setPadding(new Insets(0, 0, BORDERS, 0));

        GridPane.setHalignment(lblSunday, HPos.CENTER);
        GridPane.setHalignment(lblMonday, HPos.CENTER);
        GridPane.setHalignment(lblTuesday, HPos.CENTER);
        GridPane.setHalignment(lblWednesday, HPos.CENTER);
        GridPane.setHalignment(lblThursday, HPos.CENTER);
        GridPane.setHalignment(lblFriday, HPos.CENTER);
        GridPane.setHalignment(lblSaturday, HPos.CENTER);

        calendarGrid.add(lblSunday, 0, 0);
        calendarGrid.add(lblMonday, 1, 0);
        calendarGrid.add(lblTuesday, 2, 0);
        calendarGrid.add(lblWednesday, 3, 0);
        calendarGrid.add(lblThursday, 4, 0);
        calendarGrid.add(lblFriday, 5, 0);
        calendarGrid.add(lblSaturday, 6, 0);
    }

    private void createWeeklyCalendar(BorderPane contentPane){
        GridPane calendarGrid = new GridPane();
        formatCalendar(calendarGrid);

        LocalDate currentDay;
        if (!startDateTime.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
            currentDay = startDateTime.with(DayOfWeek.SUNDAY).minusDays(7).toLocalDate();
        }
        else{
            currentDay = startDateTime.toLocalDate();
        }

        final LocalDate dateForLambda = currentDay;


        controlGrid.getChildren()
                .forEach(node -> {
                    if (node instanceof Label){
                        if ("lblTimeSpan".equals(((Label) node).getId())) {
                            ((Label) node).setText(
                                    dateForLambda.getMonth() + " " + dateForLambda.getDayOfMonth()+"-"+(dateForLambda.getDayOfMonth()+6));
                        }
                    }
                    else if (node instanceof Button){
                        switch (((Button) node).getId()) {
                            case "btnBack" -> ((Button) node).setOnAction(e -> {
                                startDateTime = startDateTime.minusWeeks(1);
                                createWeeklyCalendar(contentPane);
                            });
                            case "btnNext" -> ((Button) node).setOnAction(e -> {
                                startDateTime = startDateTime.plusWeeks(1);
                                createWeeklyCalendar(contentPane);
                            });
                        }
                    }
                });

        final int DAYS_IN_WEEK = 7;
        int currentDayInWeek;

        for (currentDayInWeek = 0; currentDayInWeek < DAYS_IN_WEEK; currentDayInWeek++){

            ScrollPane day = new ScrollPane();
            day.setStyle("-fx-background: white;");
            day.prefHeightProperty().bind(calendarGrid.heightProperty());

            FlowPane daysAppointments = new FlowPane();
            daysAppointments.setPrefWrapLength(0);


            if (entries != null){
                for (RasporedInfo entry : entries){
                    if (entry.getDatumPocetka().isEqual(currentDay)){
                        Label lblAppointment = new Label(entry.toString());
                        lblAppointment.setOnMouseClicked(mouseEvent -> Platform.runLater(()->{ Alert alert = new Alert(Alert.AlertType.NONE);
                        alert.initStyle(StageStyle.UNDECORATED);
                        DialogPane dialogPane= alert.getDialogPane();
                        dialogPane.setStyle("-fx-background-color: #f9d900;");
                        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE));
                        alert.getDialogPane().getButtonTypes().add(new ButtonType("Ukloni", ButtonBar.ButtonData.NO));
                        alert.setContentText(((Raspored)entry).toStringVerbose());
                            alert.showAndWait().filter(response->response== alert.getDialogPane().getButtonTypes().get(1))
                                    .ifPresent(response ->{
                                                removeRaspored((Raspored) entry);
                                                createWeeklyCalendar(contentPane);
                                            }
                                    );
                     }));
                        lblAppointment.setStyle("-fx-border-color: black;" +
                                "-fx-background-color: #A7D3B1;" +
                                "-fx-border-radius: 10 10 10 10;" +
                                "-fx-background-radius: 10 10 10 10;");
                        lblAppointment.setPrefHeight(60);
                        daysAppointments.getChildren().add(lblAppointment);

                    }
                }
            }

            day.setContent(daysAppointments);
            currentDay = currentDay.plusDays(1);
            calendarGrid.add(day, currentDayInWeek, 1);

        }

        contentPane.setCenter(calendarGrid);

    }

    private void createMonthlyCalendar(BorderPane contentPane){

        GridPane calendarGrid = new GridPane();
        formatCalendar(calendarGrid);

        LocalDate firstDayOfMonth = startDateTime.toLocalDate().withDayOfMonth(1);
        final int daysInMonth = firstDayOfMonth.getMonth().length(firstDayOfMonth.isLeapYear());
        System.out.println(daysInMonth);

        int firstDayInMonth = firstDayOfMonth.getDayOfWeek().getValue();
        if (firstDayInMonth >= 7) firstDayInMonth -= 7;
        int lastDayInMonth = firstDayInMonth + daysInMonth;
        double endIndex = Math.ceil((firstDayInMonth + daysInMonth) / 7.0) * 7;

        final LocalDate dateForLambda = startDateTime.toLocalDate();
        controlGrid.getChildren()
                .forEach(node -> {
                    if (node instanceof Label){
                        if ("lblTimeSpan".equals(((Label) node).getId())) {
                            ((Label) node).setText(
                                    dateForLambda.getMonth() + ", " + dateForLambda.getYear());
                        }
                    }
                    else if (node instanceof Button){
                        switch (((Button) node).getId()) {
                            case "btnBack" -> ((Button) node).setOnAction(e -> {
                                startDateTime = startDateTime.minusMonths(1);
                                createMonthlyCalendar(contentPane);
                            });
                            case "btnNext" -> ((Button) node).setOnAction(e -> {
                                startDateTime = startDateTime.plusMonths(1);
                                createMonthlyCalendar(contentPane);
                            });
                        }
                    }
                });

        for (int currentDayInCalendar = 0, currentDayInWeek = 0, currentDayInMonth = 1; currentDayInCalendar < endIndex; currentDayInCalendar++, currentDayInWeek++){
            ScrollPane day = new ScrollPane();
            day.prefHeightProperty().bind(calendarGrid.heightProperty().divide(endIndex == 35 ? 5 : 6));

            FlowPane daysAppointments = new FlowPane();
            daysAppointments.setPrefWrapLength(1);

            if (currentDayInCalendar >= firstDayInMonth && currentDayInCalendar < lastDayInMonth){
                Label lblDay = new Label("\t\t\t  " + currentDayInMonth);
                daysAppointments.getChildren().add(lblDay);
                if (entries != null){
                    for (RasporedInfo entry : entries){
                        if (entry.getDatumPocetka().isEqual(LocalDate.of(startDateTime.getYear(),
                                startDateTime.getMonth(),
                                startDateTime.withDayOfMonth(currentDayInMonth).getDayOfMonth()))){
                            //Labela koja prikazuje informacije o rasporedu
                            Label lblAppointment = new Label(entry.toString());
                            lblAppointment.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
                            lblAppointment.setOnMouseClicked(mouseEvent -> Platform.runLater(()->{ Alert alert = new Alert(Alert.AlertType.NONE);
                                alert.initStyle(StageStyle.UNDECORATED);
                                DialogPane dialogPane= alert.getDialogPane();
                                dialogPane.setStyle("-fx-background-color: #f9d900;");
                                alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.CANCEL_CLOSE));
                                alert.getDialogPane().getButtonTypes().add(new ButtonType("Ukloni", ButtonBar.ButtonData.NO));
                                alert.setContentText(((Raspored)entry).toStringVerbose());
                                alert.showAndWait().filter(response->response== alert.getDialogPane().getButtonTypes().get(1))
                                        .ifPresent(response ->{
                                            removeRaspored((Raspored) entry);
                                            createMonthlyCalendar(contentPane);
                                        }
                                );
                            }));
                            lblAppointment.setStyle("-fx-border-color: black;" +
                                    "-fx-background-color: #A7D3B1;" +
                                    "-fx-border-radius: 10 10 10 10;" +
                                    "-fx-background-radi" +
                                    "us: 10 10 10 10;");
                            lblAppointment.setPrefHeight(30);
                            daysAppointments.getChildren().add(lblAppointment);
                        }
                    }
                    ++currentDayInMonth;
                }
                day.setContent(daysAppointments);
            }
            else{
                day.setStyle("-fx-background: lightgray;");
            }

            if (currentDayInWeek >= 7) currentDayInWeek = 0;
            calendarGrid.add(day, currentDayInWeek, (int)(currentDayInCalendar / 7) + 1);
        }
        contentPane.setCenter(calendarGrid);
    }

    private void removeRaspored(Raspored entry) {
        System.out.println(entry.getRasporedID());
        rdao.removeRaspored(entry.getRasporedID());
        entries.remove(entry);
    }

    public void bntMonthViewClicked(ActionEvent actionEvent) {
        btnWeek.setDisable(false);
        btnMonth.setDisable(true);
        createMonthlyCalendar(contentPane);
    }

    public void bntWeekViewClicked(ActionEvent actionEvent) {
        btnWeek.setDisable(true);
        btnMonth.setDisable(false);
        createWeeklyCalendar(contentPane);

    }

    public void dodajButtonClicked(ActionEvent actionEvent) {
        Raspored raspored=new Raspored();
        String[] str=predavacComboBox.getValue().split(", ");
        raspored.setImePredavaca(str[0]);
        raspored.setPrezimePredavaca(str[1]);
        raspored.setTip(tipComboBox.getValue());
        raspored.setKategorija(kategorijaComboBox.getValue());
        raspored.setVrijemePocetka(LocalTime.of(hourSpinner.getValue(),minuteSpinner.getValue()));
        raspored.setDatumPocetka(datePicker.getValue());
        rdao.insertRaspored(raspored,str[2]);
        entries.clear();
        entries=rdao.getRasporedi();
        if(btnWeek.isDisabled())
            createWeeklyCalendar(contentPane);
        else
            createMonthlyCalendar(contentPane);
    }
}
