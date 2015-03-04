/**
 * 
 */
package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.asu.c3simulator.simulation.Product;
import edu.asu.c3simulator.testing.stubs.ProductTestingStub;

/**
 * Creates a panel with detailed product info for product screens in the manage
 * section of the hub.
 * 
 * @author Justin
 *
 */
public class ProductInfoPanel
{
	Table productInfo;
	public ProductInfoPanel(Skin skin){
		// TODO: remove product interface, implement actual product class, refresh product info
		Product testProduct = new ProductTestingStub();
		productInfo = new Table(skin);
		Label text = new Label("Production cost: $"
				+ testProduct.getProductionCost(), skin);
		productInfo.add(text).left().row();
		Label text2 = new Label("Labor: ", skin);
		productInfo.add(text2).left().row();
		Label text3 = new Label("Design employee", skin);
		productInfo.add(text3).left().row();
		// TODO: place real employees in dropdown box, add functionality when
		// selected
		SelectBox<String> businessSelectionBox = new SelectBox<String>(skin);

		businessSelectionBox.setItems("Select..", "Employee 1", "Employee 2",
				"Employee 3");
		businessSelectionBox.setSelected("Select..");
		productInfo.add(businessSelectionBox).row();
		productInfo.add("").row();

		Label text4 = new Label("Materials: $" + testProduct.getMaterialCost(),
				skin);
		productInfo.add(text4).left().row();
		productInfo.add("").row();
		Label text6 = new Label("Efficiency: " + testProduct.getEfficiency()
				+ "/hr", skin);
		productInfo.add(text6).left().row();
		productInfo.add("").row();
		Label text7 = new Label("Selling Price: $"
				+ testProduct.getSellingPrice(), skin);
		productInfo.add(text7).left().row();
		productInfo.add("").expand().row();
	}
	public Table getProductInfo(){
		return productInfo;
	}

}
