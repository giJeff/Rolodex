
//Imports are listed in full to show what's being used
//could just import javafx.*
import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MainWindow extends Application
{

	private final TableView<Person> table = new TableView<>();
	private final ObservableList<Person> tableData = FXCollections.observableArrayList();
	Rolodexy rolodexy = new Rolodexy(tableData);

	String musicFile = "8-bit-Arcade4.mp3";    

	Media sound = new Media(new File(musicFile).toURI().toString());
	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	// JavaFX application still use the main method.
	// It should only ever contain the call to the launch method
	public static void main(String[] args)
	{
		launch(args);
	}

	// starting point for the application
	// this is where we put the code for the user interface
	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Rolodexy");

		// BorderLayout layout manager
		BorderPane mainLayout = new BorderPane();
		mainLayout.setPadding(new Insets(20, 20, 20, 20));

		mainLayout.setTop(makeTop());
		mainLayout.setCenter(makeCenter());
		mainLayout.setBottom(makeBottom());
		mainLayout.setLeft(makeLeft());
		// componentLayout.setRight(makeRight()); //didn't use the right side yet

		Scene mainScene = new Scene(mainLayout, 1000, 1000);
		double value = 0.3;
		mediaPlayer.setVolume(value);
		mediaPlayer.play();

		// Add the Scene to the Stage
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	private Pane makeTop()
	{
		StackPane stack = new StackPane();
		Browser browser = new Browser();
		stack.setPrefSize(500, 500);
		stack.getChildren().add(browser);
		StackPane.setAlignment(browser, Pos.CENTER);
		return stack;
	}

	@SuppressWarnings("unchecked")
	private Pane makeCenter()
	{

		BorderPane border = new BorderPane();
		border.setPadding(new Insets(20, 20, 20, 20));

		VBox vBox = new VBox(20);

		GridPane gridPane = new GridPane();

		Label firstNameLabel = new Label("First Name: ");
		TextField firstNameText = new TextField();
		Label lastNameLabel = new Label("Last Name: ");
		TextField lastNameText = new TextField();
		Label phoneLabel = new Label("Phone: ");
		TextField phoneText = new TextField();
		Label emailLabel = new Label("Email:");
		TextField emailText = new TextField();

		gridPane.add(firstNameLabel, 0, 0, 1, 1);
		gridPane.add(firstNameText, 0, 1, 1, 1);
		gridPane.add(lastNameLabel, 1, 0, 1, 1);
		gridPane.add(lastNameText, 1, 1, 1, 1);
		gridPane.add(phoneLabel, 2, 0, 1, 1);
		gridPane.add(phoneText, 2, 1, 1, 1);
		gridPane.add(emailLabel, 3, 0, 1, 1);
		gridPane.add(emailText, 3, 1, 1, 1);

		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TableColumn<Person, FirstName> firstNameColumn = new TableColumn<Person, FirstName>("First Name");
		firstNameColumn.setMinWidth(160);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

		TableColumn<Person, LastName> lastNameColumn = new TableColumn<Person, LastName>("Last Name");
		lastNameColumn.setMinWidth(160);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

		TableColumn<Person, Phone> phoneColumn = new TableColumn<Person, Phone>("Phone");
		phoneColumn.setMinWidth(160);
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

		TableColumn<Person, Email> emailColumn = new TableColumn<Person, Email>("Email");
		emailColumn.setMinWidth(160);
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

		table.setItems(tableData);
		table.getColumns().addAll(firstNameColumn, lastNameColumn, phoneColumn, emailColumn);
		table.setMaxHeight(Control.USE_PREF_SIZE);
		// table.setPrefWidth(645.0);

		vBox.getChildren().addAll(gridPane, table);

		// make buttons
		Button add = new Button("Add");
		// Button hash = new Button("Hash");
		Button delete = new Button("Delete");
		
		add.setStyle("-fx-font: 22 arial; -fx-base: #99ceff;");
		delete.setStyle("-fx-font: 22 arial; -fx-base: #ffe066;");

		// make all buttons the same size
		add.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		delete.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		VBox right = new VBox();
		right.setSpacing(10);
		right.setPadding(new Insets(0, 20, 10, 20));
		right.getChildren().addAll(add, delete);

		listControllerAdd(firstNameText, lastNameText, phoneText, emailText, table, tableData, add);
		// listControllerHash(firstNameText, lastNameText, phoneText, emailText, table,
		// tableData, hash);
		listController(table, tableData, delete);

		// border.setTop();
		border.setLeft(vBox);
		border.setRight(right);
		// border.setBottom();

		return border;
	}

	private Pane makeBottom()
	{

		Button btnSave = new Button("Save");
		Button btnExit = new Button("Exit");

		btnSave.setStyle("-fx-font: 22 arial; -fx-base: #41f46b;");
		btnExit.setStyle("-fx-font: 22 arial; -fx-base: #f95c5c;");

		btnSave.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

		btnExit.setOnAction(e ->
		{
			Platform.exit();
		});

		btnSave.setOnAction(e ->
		{
			rolodexy.save(tableData);
		});

		TilePane tp = new TilePane(Orientation.HORIZONTAL);
		tp.setAlignment(Pos.CENTER_RIGHT);
		tp.setPadding(new Insets(20, 10, 20, 0));
		tp.setHgap(10.0);
		tp.getChildren().addAll(btnSave, btnExit);

		return tp;

	}

	private Pane makeLeft()
	{
		VBox vb = new VBox();
		Label searchLabel = new Label("Search: ");
		TextField searchText = new TextField();

		FilteredList<Person> fl = new FilteredList<>(tableData, p -> true);

		searchText.textProperty().addListener((observable, oldValue, newValue) ->
		{
			fl.setPredicate(person ->
			{
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
				{
					return true;
				}
				String userSelectedFilter = newValue.toLowerCase();

				if (person.getFirstName().toString().toLowerCase().contains(userSelectedFilter))
				{
					return true; // Filter matches first name.
				} else if (person.getLastName().toString().toLowerCase().contains(userSelectedFilter))
				{
					return true; // Filter matches last name.
				} else if (person.getPhone().toString().toLowerCase().contains(userSelectedFilter))
				{
					return true; // Filter matches phone.
				} else if (person.getEmail().toString().toLowerCase().contains(userSelectedFilter))
				{
					return true; // Filter matches email.
				}
				return false; // Does not match.
			});
		});

		SortedList<Person> sortedData = new SortedList<>(fl);
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		table.setItems(sortedData);

		vb.getChildren().addAll(searchLabel, searchText);
		vb.setAlignment(Pos.CENTER);
		vb.setSpacing(10);
		return vb;

	}

	@SuppressWarnings("unused")
	private Pane makeRight()
	{
		return null;

	}

	private void listControllerAdd(TextField fNameText, TextField lNameText, TextField phoneText, TextField emailText,
			TableView<Person> table, ObservableList<Person> tableData, Button add)
	{
		/*
		 * Start CRUD code
		 */
		add.setOnAction(e ->
		{
			String firstName = fNameText.getText();
			String lastName = lNameText.getText();
			String phone = phoneText.getText();
			String email = emailText.getText();

			FirstName firstNameObj;
			LastName lastNameObj;
			Phone phoneObj;
			Email emailObj;
			try
			{
				firstNameObj = new FirstName(firstName);
				lastNameObj = new LastName(lastName);
				phoneObj = new Phone(phone);
				emailObj = new Email(email);

				if (firstNameObj.isValidName(firstName) && lastNameObj.isValidName(lastName)
						&& phoneObj.isValidPhone(phone) && emailObj.isValidEmail(email))
				{
					tableData.add(new Person(firstNameObj, lastNameObj, phoneObj, emailObj));
					fNameText.clear();
					lNameText.clear();
					phoneText.clear();
					emailText.clear();
				}
			} catch (BadInput e1)
			{
				Alert dialog = new Alert(Alert.AlertType.ERROR);
				dialog.setHeaderText(null);
				dialog.setContentText(e1.getMessage());
				dialog.showAndWait();
			}

		});

	}

	// private void listControllerHash(TextField fNameText, TextField lNameText,
	// TextField phoneText, TextField emailText,
	// TableView<Person> table, ObservableList<Person> tableData, Button hash)
	// {
	// /*
	// * Start CRUD code
	// */
	// hash.setOnAction(e ->
	// {
	// String firstName = fNameText.getText();
	// String lastName = lNameText.getText();
	// String phone = phoneText.getText();
	// String email = emailText.getText();
	//
	// FirstName firstNameObj;
	// LastName lastNameObj;
	// Phone phoneObj;
	// Email emailObj;
	// try
	// {
	// firstNameObj = new FirstName(firstName);
	// lastNameObj = new LastName(lastName);
	// phoneObj = new Phone(phone);
	// emailObj = new Email(email);
	//
	// if (firstNameObj.isValidName(firstName) && lastNameObj.isValidName(lastName)
	// &&
	// phoneObj.isValidPhone(phone) && emailObj.isValidEmail(email))
	// {
	// tableData.add(new Person(firstNameObj, lastNameObj, phoneObj.hashCode(),
	// emailObj.hashCode()));
	// fNameText.clear();
	// lNameText.clear();
	// phoneText.clear();
	// emailText.clear();
	// }
	// } catch (BadInput e1)
	// {
	// Alert dialog = new Alert(Alert.AlertType.ERROR);
	// dialog.setHeaderText(null);
	// dialog.setContentText(e1.getMessage());
	// dialog.showAndWait();
	// }
	//
	// });
	//
	// }

	private void listController(TableView<Person> table, ObservableList<Person> tableData, Button delete)
	{
		/*
		 * Start CRUD code
		 */
		delete.setOnAction(e ->
		{
			boolean check = table.getSelectionModel().isEmpty();
			if (tableData.size() >= 1 && !check)
			{
				Person index = table.getSelectionModel().getSelectedItem();
				//System.out.println(index.toString());
				tableData.remove(index);
				long sleepTime = 999;
				try
				{
					Thread.sleep(sleepTime);
					Alert dialog = new Alert(Alert.AlertType.INFORMATION);
					dialog.setHeaderText(null);
					dialog.setContentText("You deleted: " + index.toString());
					dialog.showAndWait();
				} catch (InterruptedException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

	}

}
