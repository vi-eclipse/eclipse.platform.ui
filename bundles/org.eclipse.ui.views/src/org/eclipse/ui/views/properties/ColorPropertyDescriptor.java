package org.eclipse.ui.views.properties;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColorCellEditor;

/**
 * Descriptor for a property that has a color value which should be edited
 * with a color cell editor.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * <p>
 * Example:
 * <pre>
 * IPropertyDescriptor pd = new ColorPropertyDescriptor("fg", "Foreground Color");
 * </pre>
 * </p>
 */
public class ColorPropertyDescriptor extends PropertyDescriptor {
/**
 * Creates an property descriptor with the given id and display name.
 * 
 * @param id the id of the property
 * @param displayName the name to display for the property
 */
public ColorPropertyDescriptor(Object id, String displayName) {
	super(id, displayName);
}
/**
 * The <code>ColorPropertyDescriptor</code> implementation of this 
 * <code>IPropertyDescriptor</code> method creates and returns a new
 * <code>ColorCellEditor</code>.
 * <p>
 * The editor is configured with the current validator if there is one.
 * </p>
 */
public CellEditor createPropertyEditor(Composite parent) {
	CellEditor editor = new ColorCellEditor(parent);
	if (getValidator() != null)
		editor.setValidator(getValidator());
	return editor;
}
}
