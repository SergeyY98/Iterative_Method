/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function_integration;

import static java.lang.Math.abs;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane pane;

    @FXML
    private LineChart<?, ?> LineChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private TextField paramFieldα;

    @FXML
    private TextField paramFieldβ;

    @FXML
    private TextField paramFieldμ;

    @FXML
    private TextField paramFieldε;

    @FXML
    private TextField borderFieldA;

    @FXML
    private TextField borderFieldC;

    @FXML
    private TextField borderFieldB;

    @FXML
    private TextField borderFieldD;

    @FXML
    private TextField Fieldx0;
    
    @FXML
    private TextField Fieldm;
    
    @FXML
    private TextField Fieldn;
    
    @FXML
    private TextField Fieldp;

    @FXML
    private Label Label;
    
    @FXML
    private TextField paramFielda;

    @FXML
    private TextField paramFieldb;

    @FXML
    private RadioButton selectα;

    @FXML
    private ToggleGroup param;

    @FXML
    private RadioButton selectβ;

    @FXML
    private RadioButton selectμ;

    @FXML
    private RadioButton selectε;
    
    @FXML
    void onDrawButtonClicked(ActionEvent event) {
        try {
          Label.setText("Info");
          double α  = Double.parseDouble(paramFieldα.getText());
          double β  = Double.parseDouble(paramFieldβ.getText());
          double μ  = Double.parseDouble(paramFieldμ.getText());
          double ε  = Double.parseDouble(paramFieldε.getText());
          double a  = Double.parseDouble(paramFielda.getText());
          double b  = Double.parseDouble(paramFieldb.getText());
          double A  = Double.parseDouble(borderFieldA.getText());
          double B  = Double.parseDouble(borderFieldB.getText());
          double C  = Double.parseDouble(borderFieldC.getText());
          double D  = Double.parseDouble(borderFieldD.getText());
          double x0  = Integer.parseInt(Fieldx0.getText());
          int n  = Integer.parseInt(Fieldn.getText());
          int m  = Integer.parseInt(Fieldm.getText());
          int p  = Integer.parseInt(Fieldp.getText());
          
          int param = 1;
          if (selectα.isSelected())
              param=1;
          if (selectβ.isSelected())
              param=2;
          if (selectμ.isSelected())
              param=3;
          if (selectε.isSelected())
              param=4;
          LineChart.getData().clear();
          xAxis.setAutoRanging(false);
          xAxis.setLowerBound(A);
          xAxis.setUpperBound(B);
          yAxis.setAutoRanging(false);
          yAxis.setLowerBound(C);
          yAxis.setUpperBound(D);
          XYChart.Series func = new XYChart.Series();
          XYChart.Series funcp = new XYChart.Series();
          func.setName("Функция");
          funcp.setName("Каждая p-я итерация");
          LineChart.setCreateSymbols(false);
          //LineChart.getStylesheets().add(.class.getResource("style.css").toExternalForm());
          double h = (b-a)/300;
          if ((abs(α)<=100)&&(abs(β)<=100)&&(abs(μ)<=100)&&(abs(ε)<=100)&&(abs(a)<=100)&&(abs(b)<=100)&&(abs(A)<=100)&&(abs(B)<=100)&&(abs(C)<=100)&&(abs(D)<=100)&&(x0<=D)&&(x0>C)&&(n>0)&&(m>0)&&(p>0)&&(n<=500)&&(m<=500)&&(p<=25)) {
              Integral integral = new Integral(α, β, μ, ε, a, b, param, n, m);
              for (double i=a;i<=b;i+=h) {
                  double xn = x0;
                  switch (param) {
            case (1):
            for (int j=0;j<m;j++) {
                xn=integral.getFunction(i, β, μ, ε, xn);
            }
        
            for (int j=0;j<n;j++) {
                xn=integral.getFunction(i, β, μ, ε, xn);
                func.getData().add(new XYChart.Data(i, xn));
                if ((m+j+1)%p==0) {
                    funcp.getData().add(new XYChart.Data(i, xn));
                }
            }
            break;
            case (2):
            for (int j=0;j<m;j++) {
                xn=integral.getFunction(α, i, μ, ε, xn);
            }
        
            for (int j=0;j<n;j++) {
                xn=integral.getFunction(α, i, μ, ε, xn);
                func.getData().add(new XYChart.Data(i, xn));
                if ((m+j+1)%p==0) {
                    funcp.getData().add(new XYChart.Data(i, xn));
                }
            }
            break;
            case (3):
                for (int j=0;j<m;j++) {
                xn=integral.getFunction(α, β, i, ε, xn);
            }
        
            for (int j=0;j<n;j++) {
                xn=integral.getFunction(α, β, i, ε, xn);
                func.getData().add(new XYChart.Data(i, xn));
                if ((m+j+1)%p==0) {
                    funcp.getData().add(new XYChart.Data(i, xn));
                }
            }
            break;
            case (4):
                for (int j=0;j<m;j++) {
                xn=integral.getFunction(α, β, μ, i, xn);
            }
        
            for (int j=0;j<n;j++) {
                xn=integral.getFunction(α, β, μ, i, xn);
                func.getData().add(new XYChart.Data(i, xn));
                if ((m+j+1)%p==0) {
                    funcp.getData().add(new XYChart.Data(i, xn));
                }
            }
            break;
        }
              }
              LineChart.getData().add(func);
              LineChart.getData().add(funcp);
          }
          else
              Label.setText("Превышен диапазон параметров");
          
        }
        catch (NumberFormatException e) {
             System.err.println("Неверный формат параметров!");
             Label.setText("Неверный формат параметров!");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
