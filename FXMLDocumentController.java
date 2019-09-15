/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanderpolsolver;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import java.lang.*;
import static java.lang.Double.parseDouble;
import java.util.Arrays;

/**
 *
 * @author louis_3ofz47d
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button RK2;
    
    @FXML
    private Button RK4;
    
    @FXML
    private TextField A;
    
    @FXML
    private TextField omega;
    
    @FXML
    private TextField mu;
    
    @FXML
    private TextField h;
    
    @FXML
    private TextField T;
    
    @FXML
    private TextField Zx;
    
    @FXML
    private TextField Zy;
    
    @FXML
    protected TextArea outputTextArea;
    
    @FXML
    public String a;
    
    @FXML
    public String b;
    
    
    @FXML
    private void RK2buttonclicked(ActionEvent e){
        double [] array = new double[7];
        double d = new Double("0");
        array[0] = parseDouble(A.getText());
        array[1] = parseDouble(mu.getText());
        array[2] = parseDouble(omega.getText());
        array[3] = parseDouble(h.getText());
        array[4] = parseDouble(T.getText());
        array[5] = parseDouble(Zx.getText());
        array[6] = parseDouble(Zy.getText());
        AbstractCreator.setConstants(array);
        outputTextArea.setText("hello");
        AbstractCreator [] ac = new AbstractCreator[2];
        ac[0]=new RK2(array);
        a=ac[0].SolveEquation();
        outputTextArea.setText(a);
    }
    
    @FXML
    private void RK4buttonclicked(ActionEvent e){
        double [] array = new double[7];
        double d = new Double("0");
        array[0] = parseDouble(A.getText());
        array[1] = parseDouble(mu.getText());
        array[2] = parseDouble(omega.getText());
        array[3] = parseDouble(h.getText());
        array[4] = parseDouble(T.getText());
        array[5] = parseDouble(Zx.getText());
        array[6] = parseDouble(Zy.getText());
        AbstractCreator.setConstants(array);
        AbstractCreator [] ac = new AbstractCreator[1];
        ac[0]=new RK4(array);
        b=ac[0].SolveEquation();
        outputTextArea.setText(b);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
