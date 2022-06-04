module online.adambem.btac {
	requires javafx.controls;
	requires javafx.fxml;


	opens online.adambem.btac to javafx.fxml;
	exports online.adambem.btac;
}