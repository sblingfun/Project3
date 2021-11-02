package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import java.util.StringTokenizer;



public class SampleController {
	
	Roster roster = new Roster();
	//private boolean printingAlphabetically = false;
	
	@FXML
    private RadioButton BAMajor;

    @FXML
    private RadioButton BAMajor1;

    @FXML
    private RadioButton CSMajor;

    @FXML
    private RadioButton CSMajor1;

    @FXML
    private RadioButton CTState;

    @FXML
    private RadioButton EEMajor;

    @FXML
    private RadioButton EEMajor1;

    @FXML
    private RadioButton ITMajor;

    @FXML
    private RadioButton ITMajor1;

    @FXML
    private RadioButton international;

    @FXML
    private RadioButton MEMajor;

    @FXML
    private RadioButton MEMajor1;

    @FXML
    private RadioButton NYState;

    @FXML
    private RadioButton NonResident;

    @FXML
    private RadioButton Resident;

    @FXML
    private Button addStudentButton;

    @FXML
    private TextField creditHrField;

    @FXML
    private TextArea displayField;

    @FXML
    private TextField finAidAmountField;

    @FXML
    private ToggleGroup majorChoice;

    @FXML
    private ToggleGroup majorChoice1;

    @FXML
    private TextField nameField;

    @FXML
    private TextField nameField1;

    @FXML
    private Button payButton;

    @FXML
    private TextField paymentAmountField;
    
    @FXML
    private DatePicker paymentDateField;
    
    @FXML
    private ToggleGroup paymentMajorChoice;
    
    @FXML
    private Tab paymentTab;

    @FXML
    private CheckBox printAlphaCheck;

    @FXML
    private Button printButton;

    @FXML
    private Button printByPayment;
    
    @FXML
    private Tab profileTab;

    @FXML
    private Button removeStudentButton;

    @FXML
    private ToggleGroup residentChoice;
    
    @FXML
    private Button rosterTuitionButton;

    @FXML
    private Button setFinAidButton;
    
    @FXML
    private CheckBox studyAbroad;
    
    @FXML
    private TextField tempTuitionField;

    @FXML
    private RadioButton tristate;

    @FXML
    private ToggleGroup tristateChoice;
    
    @FXML
    private VBox tristateGroup;
    
    @FXML
    private Button tuitionButton;

    @FXML
    void addStudent(ActionEvent event) {
    	
    	try {
    		String name = null;
    		Major major = null;
    		int creditHrsInt = 0;
    		if ( nameField.getText().isBlank() ) {
    			displayField.appendText("Must enter a student name.\n");    			
    		}
    		else name = nameField.getText();
    		if ( majorChoice.getSelectedToggle() != null ) {
    			RadioButton majChoice = (RadioButton)majorChoice.getSelectedToggle();
    			major = Major.lookup(majChoice.getText());
    		}
    		else displayField.appendText("Must select a major.\n");
    		if ( !creditHrField.getText().isBlank() ) {
    			String creditHrs = creditHrField.getText();
    			creditHrsInt = Integer.parseInt(creditHrs);
    			roster.isValidCreditHrs(creditHrsInt);
    		}
    		else displayField.appendText("Must enter student's credit hours.\n");
    		
    		if ( name != null && major != null && creditHrsInt != 0 ) {
    			if ( residentChoice.getSelectedToggle() != null ) {
    				RadioButton resChoice = (RadioButton)residentChoice.getSelectedToggle();
    				//displayField.appendText(resChoice.getText() + "\n");
    				if ( resChoice.getText().compareTo("Resident") == 0 ) {    				
    					Student addResStudent = new Resident( name, major, creditHrsInt );
    					roster.add( addResStudent );
    					displayField.appendText("Resident student added.\n");    				
    				}
    				else if ( resChoice.getText().compareTo("Non-Resident") == 0 ) {
    					if ( tristate.isSelected() ) {
    						if ( tristateChoice.getSelectedToggle() == null ) {
    							displayField.appendText("A tristate student must be from NY or CT.\n");
    						}
    						else {
    							String triState = null;
    							RadioButton triChoice = (RadioButton)tristateChoice.getSelectedToggle();
    							if ( triChoice.getText().compareTo("New York") == 0 ) {
    								triState = "NY";
    							}
    							else if ( triChoice.getText().compareTo("Connecticut") == 0) {
    								triState = "CT";
    							}       					
    							Student addTriStudent = new TriState( name, major, creditHrsInt, triState );
    							roster.add( addTriStudent );
    							displayField.appendText("Tristate student added.\n");
    						}
    					}
    					else if ( international.isSelected() ) {
    						if ( creditHrsInt < ConstantIdentifiers.FULLTIMEHRS) {
    							displayField.appendText("International students must be fulltime.\n");
    					}
    						else {
    							boolean abroadStatus = studyAbroad.isSelected();
    							
    							/**																				//THINK I CAN DELETE CUZ DOING IN international class
    							if ( abroadStatus && creditHrsInt > ConstantIdentifiers.FULLTIMEHRS ) {
    								displayField.appendText("Students studying abroad are limited to 12 credits.\n");					//Might need to make hitting the checkmark finding the student and updating study abroad status
    							}
    							
    							else {
        							Student addIntStudent = new International( name, major, creditHrsInt, abroadStatus );
        							roster.add( addIntStudent );
        							displayField.appendText("International student added.\n");
    							}
    							**/
    						} 
    					}
    					else {
    						Student addNonResStudent = new NonResident( name, major, creditHrsInt );
    						roster.add( addNonResStudent );
    						displayField.appendText("Non-Resident student added.\n");
    					}
    				}
    			
    			}
    			else displayField.appendText("Student must be resident or non-resident.\n");
    		}
    		/**
    		if ( name != null && major != null && creditHrsInt != 0 ) {		   		
    			Student addStudent = new Student(name, major, creditHrsInt);
    			roster.add( addStudent );
    			displayField.appendText("Student added.\n");   	
    		}
    		**/ 		
    	}
    	catch (NumberFormatException e) {
    		displayField.appendText( "Invalid credit hours.\n" );
    	}
    	catch (IllegalArgumentException e) {
    		displayField.appendText( e.getMessage() + "\n" );
    	}
    	catch (Exception E) {
    		displayField.appendText( "Error occured.\n" );
    		displayField.appendText( E.getMessage() );
    	}
    }
    
    @FXML
    void calcRosterTuition(ActionEvent event) {
    	roster.calcTuition();
    	displayField.appendText( "Roster tuition calculated.\n" );
    }
    
    @FXML
    void makePayment(ActionEvent event) {
    	try {
    		String name = null;
    		Major major = null;
    		String date = null;
    		Date paymentDate = null;
    		if ( nameField1.getText().isBlank() ) {
    			displayField.appendText("Must enter a student name.\n");    			
    		}
    		else name = nameField1.getText();
    		if ( paymentMajorChoice.getSelectedToggle() != null ) {
    			RadioButton majChoice = (RadioButton)paymentMajorChoice.getSelectedToggle();
    			major = Major.lookup(majChoice.getText());
    		}
    		else displayField.appendText("Must select a major.\n");
    		if ( paymentDateField.getValue() == null ) {
    			displayField.appendText("Must enter a payment date.\n");
    		}
    		else {
    			date = paymentDateField.getValue().toString();
    			StringTokenizer st = new StringTokenizer( date,"-" );
    			String year = st.nextToken();
    			String month = st.nextToken() + "/";
    			String day = st.nextToken() + "/";
    			date = month + day + year;
    			paymentDate = new Date( date );
    			
    			/**
    			if ( !paymentDate.isValid() ) {
    				displayField.appendText("Invalid payment date.\n");
    				paymentDate = null;
    			} 			
    			**/
    		}    		
    		if ( name != null && major != null && paymentDate != null ) {
    			Student paymentID = new Student( name, major, ConstantIdentifiers.NONE );
    			float paymentAmt = 0;
    			if ( !paymentAmountField.getText().isBlank() ) {
        			String paymentAmount = paymentAmountField.getText();
        			paymentAmt = Float.parseFloat(paymentAmount);
        			roster.makePayment( paymentAmt, paymentID, paymentDate );
        			displayField.appendText("Payment added.\n");
        		}
        		else displayField.appendText("Must enter a payment amount.\n");	
    		}
    		
    	}
    	catch (NumberFormatException e) {
    		displayField.appendText( "Invalid payment amount.\n" );
    	}
    	catch (IllegalArgumentException e) {
    		displayField.appendText( e.getMessage() + "\n" );
    	}
    	catch (Exception E) {
    		displayField.appendText( "Error occured.\n" );
    		displayField.appendText( E.getMessage() );
    	}
    }
    
    @FXML
    void printPaymentDate(ActionEvent event) {
    	String printedRoster = roster.printByPaymentDate();
    	displayField.appendText( printedRoster );    	
    }

    @FXML
    void printRoster(ActionEvent event) {
    	String printedRoster;
    	if ( printAlphaCheck.isSelected() ) { //print alphabetically
    		printedRoster = roster.printByName(); ////////////////////////Might need to make copy of list to keep unordered list saved
    	}
    	else {
    		printedRoster = roster.print();  
    	}
    	displayField.appendText( printedRoster );    	
    }

    @FXML
    void removeStudent(ActionEvent event) {
    	try {
    		String name = null;
    		Major major = null;
    		if ( nameField.getText().isBlank() ) {
    			displayField.appendText( "Must enter a student name.\n" );    			
    		}
    		else name = nameField.getText();
    		if ( majorChoice.getSelectedToggle() != null ) {
    			RadioButton choice = (RadioButton)majorChoice.getSelectedToggle();
    			major = Major.lookup( choice.getText() );
    		}
    		else displayField.appendText("Must select a major.\n");
    		if ( !nameField.getText().isBlank() && majorChoice.getSelectedToggle() != null ) {
        		int creditHrsInt = 0;
        		Student remStudent = new Student(name, major, creditHrsInt);
        		roster.remove(remStudent);    		
        		displayField.appendText( "Student removed.\n" );
    		}
    	}
    	catch (NumberFormatException e) {
    		displayField.appendText( "Invalid credit hours.\n" );
    	}
    	catch (IllegalArgumentException e) {
    		displayField.appendText( e.getMessage() + "\n" );
    	}
    }
    
    @FXML
    void sampleTuitionCalc(ActionEvent event) {
    	try {
    		//addStudent(event);
    		tempTuitionField.clear();
    		String name = null;
    		Major major = null;
    		int creditHrsInt = 0;
    		if ( nameField.getText().isBlank() ) {
    			displayField.appendText("Must enter a student name.\n");    			
    		}
    		else name = nameField.getText();
    		if ( majorChoice.getSelectedToggle() != null ) {
    			RadioButton majChoice = (RadioButton)majorChoice.getSelectedToggle();
    			major = Major.lookup(majChoice.getText());
    		}
    		else displayField.appendText("Must select a major.\n");
    		if ( !creditHrField.getText().isBlank() ) {
    			String creditHrs = creditHrField.getText();
    			creditHrsInt = Integer.parseInt(creditHrs);
    			roster.isValidCreditHrs(creditHrsInt);
    		}
    		else displayField.appendText("Must enter student's credit hours.\n");
    		if ( name != null && major != null && creditHrsInt != 0 ) {
    			if ( residentChoice.getSelectedToggle() != null ) {
    				RadioButton resChoice = (RadioButton)residentChoice.getSelectedToggle();
    				if ( resChoice.getText().compareTo("Resident") == 0 ) {    				
    					Student addResStudent = new Resident( name, major, creditHrsInt );    					
    					tempTuitionField.appendText( String.valueOf( roster.calcTempTuition( addResStudent )) );    				
    				}
    				else if ( resChoice.getText().compareTo("Non-Resident") == 0 ) {
    					if ( tristate.isSelected() ) {
    						if ( tristateChoice.getSelectedToggle() == null ) {
    							displayField.appendText("A tristate student must be from NY or CT.\n");
    						}
    						else {
    							String triState = null;
    							RadioButton triChoice = (RadioButton)tristateChoice.getSelectedToggle();
    							if ( triChoice.getText().compareTo("New York") == 0 ) {
    								triState = "NY";
    							}
    							else if ( triChoice.getText().compareTo("Connecticut") == 0) {
    								triState = "CT";
    							}       					
    							Student addTriStudent = new TriState( name, major, creditHrsInt, triState );
    							tempTuitionField.appendText( String.valueOf( roster.calcTempTuition( addTriStudent )) );
    						}
    					}
    					else if ( international.isSelected() ) {
    						if ( creditHrsInt < ConstantIdentifiers.FULLTIMEHRS) {
    							displayField.appendText("International students must be fulltime.\n");
    					}
    						else {
    							boolean abroadStatus = studyAbroad.isSelected();
    							if ( abroadStatus && creditHrsInt > ConstantIdentifiers.FULLTIMEHRS ) {
    								displayField.appendText("Students studying abroad are limited to 12 credits.\n");
    							}
    							else {
        							Student addIntStudent = new International( name, major, creditHrsInt, abroadStatus );
        							tempTuitionField.appendText( String.valueOf( roster.calcTempTuition( addIntStudent )) );
    							}
    						} 
    					}
    					else {
    						Student addNonResStudent = new NonResident( name, major, creditHrsInt );
    						tempTuitionField.appendText( String.valueOf( roster.calcTempTuition( addNonResStudent ) ) );
    					}
    				}
    			
    			}
    			else displayField.appendText("Student must be resident or non-resident.\n");
    		}		
    		//float tempTuition = roster.calcTempTuition(null)
    		//float tempTuition = roster[  roster.getSize() ].TuitionDue() ;
    	}
    	
    	catch (NumberFormatException e) {
    		displayField.appendText( "Invalid credit hours.\n" );
    	}
    	catch (IllegalArgumentException e) {
    		displayField.appendText( e.getMessage() + "\n" );
    	}
    }

    @FXML
    void selectBAMajor(ActionEvent event) {
    	//radio cant be toggled
    	//BAMajor.setToggleGroup(majorChoice); // might not be needed
    	//BAMajor.setOnAction(event);
    	//(BAMajor.isSelected());
    	//if (!BAMajor.isSelected()) BAMajor.setSelected(false);
    	//else if (BAMajor.isSelected()) BAMajor.setSelected(true);
    }
    
    @FXML
    void selectBAMajor1(ActionEvent event) {
    	//MIGHT NOT NEED THESE
    }

    @FXML
    void selectCSMajor(ActionEvent event) {
    	//CSMajor.setToggleGroup(majorChoice);
    }
    
    @FXML
    void selectCSMajor1(ActionEvent event) {
    	//CSMajor.setToggleGroup(majorChoice);
    }
    
    @FXML
    void selectCT(MouseEvent event) {

    }

    @FXML
    void selectEEMajor(ActionEvent event) {

    }
    
    @FXML
    void selectEEMajor1(ActionEvent event) {

    }
    
    @FXML
    void selectInternational(MouseEvent event) {
    	if (international.isSelected()) {
    			studyAbroad.setDisable(false);
    			tristate.setDisable(true);
    	}
    	else if (!international.isSelected()) {
			studyAbroad.setDisable(true);
			tristate.setDisable(false);
    	}
    }

    @FXML
    void selectITMajor(ActionEvent event) {

    }
    
    @FXML
    void selectITMajor1(ActionEvent event) {

    }

    @FXML
    void selectMEMajor(ActionEvent event) {

    }
    
    @FXML
    void selectMEMajor1(ActionEvent event) {

    }
    
    @FXML
    void selectNY(MouseEvent event) {

    }
    
    
    @FXML
    void selectNonResident(ActionEvent event) {
    	tristate.setDisable(false);
    	international.setDisable(false);
    }

    @FXML
    void selectResident(ActionEvent event) {
    	tristate.setDisable(true);
    	NYState.setDisable(true);
    	CTState.setDisable(true);
    	//International.setDisable(false);
    }
    
    @FXML
    void selectTristate(MouseEvent event) {
    	if ( tristate.isSelected() ) {
        	NYState.setDisable(false);
        	CTState.setDisable(false);
        	international.setDisable(true);
    	}
    	else if (!tristate.isSelected()) {
        	NYState.setDisable(true);
        	CTState.setDisable(true);
        	international.setDisable(false);
    	}
    	
    	

    }
    
    @FXML
    void setFinAid(ActionEvent event) {
    	try {
    		String name = null;
    		Major major = null;
    		if ( nameField1.getText().isBlank() ) {
    			displayField.appendText("Must enter a student name.\n");    			
    		}
    		else name = nameField1.getText();
    		if ( paymentMajorChoice.getSelectedToggle() != null ) {
    			RadioButton majChoice = (RadioButton)paymentMajorChoice.getSelectedToggle();
    			major = Major.lookup(majChoice.getText());
    		}
    		else displayField.appendText("Must select a major.\n");
    		if ( name != null && major != null ) {
    			Student paymentID = new Student( name, major, ConstantIdentifiers.NONE );
    			float aidAmt = 0;
    			if ( !finAidAmountField.getText().isBlank() ) {
        			String aidAmount = finAidAmountField.getText();
        			aidAmt = Float.parseFloat(aidAmount);
        			roster.awardFinAid(aidAmt, paymentID);;
        			displayField.appendText("Award applied.\n");
        		}
        		else displayField.appendText("Must enter an amount for award.\n");	
    		}
    	}
    	catch (NumberFormatException e) {
    		displayField.appendText( "Invalid award amount.\n" );
    	}
    	catch (IllegalArgumentException e) {
    		displayField.appendText( e.getMessage() + "\n" );
    	}
    }
    
    @FXML
    void switchProfileTab(ActionEvent event) { 						//Delete
    	//majorChoice.clearSelectedToggle() ;
    	//profileTab.sel
    	//tabPane.getSelectionModel().selectNext();
    }

    /**
    @FXML
    void selectPrintAlpha(ActionEvent event) {
    	//printAlphaCheck.fire();
    	//setOnMouseClicked(event)
    	//printAlphaCheck.setSelected( !printAlphaCheck.isSelected() );
    }
    **/
  
}
