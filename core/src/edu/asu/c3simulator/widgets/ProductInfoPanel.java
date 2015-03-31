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
	Product testProduct;
	Skin skin;

	public ProductInfoPanel(Skin skin)
	{
		// TODO: remove product interface, implement actual product class,
		// refresh product info
		this.skin = skin;
		testProduct = new ProductTestingStub();
		productInfo = new Table(skin);
		Label productionCostLabel = new Label("Production cost: $"
				+ testProduct.getProductionCost(), skin);
		productInfo.add(productionCostLabel).left().row();
		Label laborLabel = new Label("Labor: ", skin);
		productInfo.add(laborLabel).left().row();
		Label designEmployeeLabel = new Label("Design employee", skin);
		productInfo.add(designEmployeeLabel).left().row();
		// TODO: place real employees in dropdown box, add functionality when
		// selected
		SelectBox<String> businessSelectionBox = new SelectBox<String>(skin);

		businessSelectionBox.setItems("Select..", "Employee 1", "Employee 2",
				"Employee 3");
		businessSelectionBox.setSelected("Select..");
		productInfo.add(businessSelectionBox).row();
		productInfo.add("").row();

		Label materialsLabel = new Label("Materials: $" + testProduct.getMaterialCost(),
				skin);
		productInfo.add(materialsLabel).left().row();
		productInfo.add("").row();
		Label efficiencyLabel = new Label("Efficiency: " + testProduct.getEfficiency()
				+ "/hr", skin);
		productInfo.add(efficiencyLabel).left().row();
		productInfo.add("").row();
		Label sellPriceLabel = new Label("Selling Price: $"
				+ testProduct.getSellingPrice(), skin);
		productInfo.add(sellPriceLabel).left().row();
		productInfo.add("").expand().row();
	}

	/**
	 * Turns the adds history of the product to the productInfoPanel
	 */
	public void addHistoryPanel()
	{
		Label labelSold = new Label("Total sold: " + testProduct.getNumSold(),
				skin);
		productInfo.add(labelSold).left().row();
		productInfo.add("").row();
		Label labelWorth = new Label("Total worth: $"
				+ testProduct.getTotalWorth(), skin);
		productInfo.add(labelWorth).left().row();
		productInfo.add("").row();
		productInfo.add();
	}

	public Table getProductInfo()
	{
		return productInfo;
	}

}
