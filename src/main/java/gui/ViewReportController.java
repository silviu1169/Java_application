package gui;

import com.sun.javafx.charts.Legend;
import domain.Homework;
import domain.Student;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ViewReportController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    void initialize() {

    }


    public void setDataForFirstReport(Map<Student, Double> averageGrade) {
//        XYChart.Series set1 = new XYChart.Series<>();


        barChart.getData().removeAll();

        averageGrade.forEach((x,y)->{
            XYChart.Series set = new XYChart.Series<>();
            XYChart.Data data = new XYChart.Data(x.getFirstName() + " " + x.getName(), y);
//            if (set <4 )
//                set.getNode().setStyle("-fx-bar-fill: #ff0300");
//            else
//                set.getNode().setStyle("-fx-bar-fill: #00800a");
            set.getData().add(data);
            barChart.getData().add(set);
        });

        HashMap<String, String> colors = new HashMap<String, String>();
        for (XYChart.Series<?, ?> series : barChart.getData())
        {
            String color = "";
            for (XYChart.Data<?, ?> data : series.getData())
            {
//                data.getNode().setStyle("-fx-bar-fill: rgb(" + rgb + ");");

                if (Double.parseDouble(data.getYValue().toString())< 4 ) {
                    data.getNode().setStyle("-fx-bar-fill: red");
                    color = "red";
                }
                else {
                    data.getNode().setStyle("-fx-bar-fill: green");
                    color = "green";
                }
            }
            colors.put(series.getName(), color);

        }
        for (Node n : barChart.getChildrenUnmodifiable()) {
            if (n instanceof Legend) {
                for (Legend.LegendItem items : ((Legend) n).getItems()) {
                    String rgb = colors.get(items.getText());
                    items.getSymbol().setStyle("-fx-bar-fill: " + rgb + ";");
                }
            }
        }

//        barChart.getData().addAll(set1);
        barChart.getYAxis().setAutoRanging(true);
        barChart.getXAxis().setAutoRanging(true);


    }

    public void setDataForSecondReport(Map<Homework, Double> averageGrade) {


        barChart.getData().removeAll();

        averageGrade.forEach((x,y)->{
            XYChart.Series set = new XYChart.Series<>();
            XYChart.Data data = new XYChart.Data(x.getDescription(), y);
//            if (set <4 )
//                set.getNode().setStyle("-fx-bar-fill: #ff0300");
//            else
//                set.getNode().setStyle("-fx-bar-fill: #00800a");
            set.getData().add(data);
            barChart.getData().add(set);
        });

        HashMap<String, String> colors = new HashMap<String, String>();
        for (XYChart.Series<String, Number> series : barChart.getData())
        {
            String color = "";
            for (XYChart.Data<String , Number> data : series.getData())
            {

                if (Double.parseDouble(data.getYValue().toString())< 4 ) {
                    data.getNode().setStyle("-fx-bar-fill: red");
                    color = "red";
                }
                else {
                    data.getNode().setStyle("-fx-bar-fill: green");
                    color = "green";
                }
            }
            colors.put(series.getName(), color);

        }
        for (Node n : barChart.getChildrenUnmodifiable()) {
            if (n instanceof Legend) {
                for (Legend.LegendItem items : ((Legend) n).getItems()) {
                    String rgb = colors.get(items.getText());
                    items.getSymbol().setStyle("-fx-bar-fill:"+rgb + ";");
                }
            }
        }

//        barChart.getData().addAll(set1);
        barChart.getYAxis().setAutoRanging(true);
        barChart.getXAxis().setAutoRanging(true);


    }

    public void setDataForThirdReport(Map<Student, Double> averageGrade) {
        this.setDataForFirstReport(averageGrade);
    }

    public void setDataForFourthReport(Map<Student, Integer> noOfHomeworksShowed) {
        barChart.getData().removeAll();

        noOfHomeworksShowed.forEach((x,y)->{
            XYChart.Series set = new XYChart.Series<>();
            XYChart.Data data = new XYChart.Data(x.getFirstName() + " " + x.getName(), y);
//            if (set <4 )
//                set.getNode().setStyle("-fx-bar-fill: #ff0300");
//            else
//                set.getNode().setStyle("-fx-bar-fill: #00800a");
            set.getData().add(data);
            barChart.getData().add(set);
        });

        HashMap<String, String> colors = new HashMap<String, String>();
        for (XYChart.Series<String, Number> series : barChart.getData())
        {
            String color = "";
            for (XYChart.Data<String , Number> data : series.getData())
            {

                if (Integer.parseInt(data.getYValue().toString()) < 13 ) {
                    data.getNode().setStyle("-fx-bar-fill: red");
                    color = "red";
                }
                else {
                    data.getNode().setStyle("-fx-bar-fill: green");
                    color = "green";
                }
            }
            colors.put(series.getName(), color);

        }
        for (Node n : barChart.getChildrenUnmodifiable()) {
            if (n instanceof Legend) {
                for (Legend.LegendItem items : ((Legend) n).getItems()) {
                    String rgb = colors.get(items.getText());
                    items.getSymbol().setStyle("-fx-bar-fill:"+rgb + ";");
                }
            }
        }


        barChart.getYAxis().setAutoRanging(true);
        barChart.getYAxis().setLabel("No. of homeworks showed");
        barChart.getXAxis().setAutoRanging(true);



    }

}
