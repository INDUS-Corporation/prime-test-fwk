/*
 * PrimeTestFwk
 * Copyright 2014 and beyond, INDUS Corporation, Inc.
 * 
 * PrimeTestFwk is free software: you can redistribute it and/or modify
 * it under the terms of the LESSER GNU General Public License version 3 as 
 * published by the Free Software Foundation.
 *
 * PrimeTestFwk is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * LESSER GNU General Public License version 3 for more details.
 *
 * You should have received a copy of the LESSER GNU General Public License
 * version 3 along with PrimeTestFwk. If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package com.induscorp.prime.testing.ui.core.objects.crud;

import org.testng.Assert;

import com.induscorp.prime.testing.ui.core.config.webbrowser.WebBrowser;
import com.induscorp.prime.testing.ui.core.objects.UIObject;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RecordInputHandler {
	private WebBrowser browser;
	private Record record;

	public RecordInputHandler(WebBrowser browser, Record record) {
		this.browser = browser;
		this.record = record;
	}

	public void enterRecordInfoIntoFields() {
		Assert.assertNotNull(browser, "Please specify the browser.");
		Assert.assertNotNull(record, "Please specify the record.");

		ObjectPropertiesAndValues propsAndValues;
		for (UIObject uiObj : record.getRocord().keySet()) {
			propsAndValues = record.getRocord().get(uiObj);
			setComponentValue(uiObj, propsAndValues);
		}
	}

	public void setComponentValue(UIObject uiObj,
			ObjectPropertiesAndValues propsAndValues) {
		/* uiObj.getValidator(browser, null).scrollElementOnViewport(null);

		switch (uiObj.getType()) {
		case textBox:			
			((TextBox) uiObj).getValidator(browser, null).typeText(
					propsAndValues.getValidValues().get(0), 0);			
			break;
		case textArea:
			((TextArea) uiObj).getValidator(browser, null).typeText(
					propsAndValues.getValidValues().get(0));
			break;
		case comboBox:
			if (propsAndValues.getRows().getRows().size() < 1) {
				((ComboBox) uiObj).getValidator(browser, null)
						.selectItems(
								propsAndValues.getRowSelectionMechanism(), 0);
			} else {
				((ComboBox) uiObj).getValidator(browser, null)
						.selectRecords(propsAndValues.getRows(),
								propsAndValues.getRowSelectionMechanism());
			}
			break;		
		case radioButtonGroup:
			((RadioButtonGroup) uiObj).getValidator(browser, null).selectOption(
					propsAndValues.getValidValues().get(0));
			break;
		case radioButton:
			((RadioButton) uiObj).getValidator(browser, null).selectOption();
			break;
		case checkBox:
			if (propsAndValues.getValidValues().size() == 1) {
				if ("checked".equals(propsAndValues.getValidValues().get(0))) {
					((CheckBox) uiObj).getValidator(browser, null)
							.checkAndValidateCheckBox();
				} else if ("unchecked".equals(propsAndValues.getValidValues()
						.get(0))) {
					((CheckBox) uiObj).getValidator(browser, null)
							.uncheckAndValidateCheckBox();
				}
			} else {
				Assert.fail("Please specify the value (checked / unchecked) to mark the checkbox. CheckboxName - "
						+ uiObj.getDisplayName());
			}
			break;
		case dateItem:
			((DateItem) uiObj).getValidator(browser, null).typeText(
					propsAndValues.getValidValues().get(0));
			break;
		case table:
			if (propsAndValues.getValidValues().size() < 1) {
				((Table) uiObj).getValidator(browser, null).selectAllRecords(
						propsAndValues.getRowSelectionMechanism());
			} else {
				((Table) uiObj).getValidator(browser, null).selectRecords(
						propsAndValues.getRows(),
						propsAndValues.getRowSelectionMechanism());
			}
			break;		
		default:
			break;
		} */
	}
}
