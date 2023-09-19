package coit11134.assignment2;

import java.io.FileNotFoundException;
import java.lang.reflect.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

public class MemberRecordController implements Initializable {

    @FXML
    private TextField memberIDTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private TextField universityTextField;

    @FXML  
    private TextField emailTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField feeTextField;

    @FXML
    private TextField discountTextField;

    @FXML
    private TextField topicTextField;

    @FXML
    private Button saveButton;
      
    @FXML
    private Button exitButton;

    @FXML
    private Label messageLabel;

    private DataHandler dataHandler;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the DataHandler with the file name
        try {
            dataHandler = new DataHandler("members.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void saveButton(ActionEvent event) {
        int memberID = Integer.parseInt(memberIDTextField.getText());
        String fullName = fullNameTextField.getText();
        String university = universityTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        float fee = Float.parseFloat(feeTextField.getText());
        float discount = Float.parseFloat(discountTextField.getText());
        String topic = topicTextField.getText();

        Member member; 

        if (discount == 0) {
            member = (Member) new FullMember(memberID, fullName, university, email, phone, fee );
        } else if (discount == 100) { 
            member = (Member) new KeynoteMember(memberID, fullName, university, email, phone, fee, topic);
        } else {
            member = (Member) new StudentMember(memberID, fullName, university, email, phone, fee, discount);
        }


        dataHandler.addMember((FullMember) member);
        dataHandler.saveData();

        // Provide feedback to the user
        String message = "Member saved: " + fullName;
        messageLabel.setText(message);

        // Clear the input fields after saving
        clearInputFields();
    }
    
    
    @FXML
    private void exitButton (ActionEvent event) {
        System.out.println("You have exited Add Member");
        // Switch to the Search member scene
        App.changeScene(0);
    }

    @FXML
    private void clearInputFields() {
        memberIDTextField.clear();
        fullNameTextField.clear();
        universityTextField.clear();
        emailTextField.clear();
        phoneTextField.clear();
        feeTextField.clear();
        discountTextField.clear();
        topicTextField.clear();
    }

}
