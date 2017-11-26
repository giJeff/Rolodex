import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

class Browser extends Region
{

	//simple webview demo to take up space and give you google
	final WebView browser = new WebView();
	final WebEngine webEngine = browser.getEngine();

	public Browser()
	{
		getStyleClass().add("browser");
		webEngine.load("http://www.google.com/");
		getChildren().add(browser);
	}

	@Override
	protected void layoutChildren()
	{
		double w = getWidth();
		double h = getHeight();
		layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
	}

	@Override
	protected double computePrefWidth(double height)
	{
		return 300;
	}

	@Override
	protected double computePrefHeight(double width)
	{
		return 300;
	}
}