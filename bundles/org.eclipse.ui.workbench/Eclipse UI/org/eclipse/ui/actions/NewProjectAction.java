package org.eclipse.ui.actions;

/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.wizard.WizardDialog;

import org.eclipse.ui.*;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.internal.*;
import org.eclipse.ui.internal.dialogs.NewWizard;
import org.eclipse.ui.internal.misc.Assert;

/**
 * Standard action for launching the create project selection
 * wizard.
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 */
public class NewProjectAction extends Action {

	/**
	 * The wizard dialog width
	 */
	private static final int SIZING_WIZARD_WIDTH = 500;

	/**
	 * The wizard dialog height
	 */
	private static final int SIZING_WIZARD_HEIGHT = 500;
	
	/**
	 * The workbench window this action will run in
	 */
	private IWorkbenchWindow window;

/**
 * This default constructor allows the the action to be called from the welcome page.
 */
public NewProjectAction() {
	this(((Workbench)PlatformUI.getWorkbench()).getActiveWorkbenchWindow());
}

/**
 * Creates a new action for launching the new project
 * selection wizard.
 *
 * @param window the workbench window to query the current
 * 		selection and shell for opening the wizard.
 */
public NewProjectAction(IWorkbenchWindow window) {
	super(WorkbenchMessages.getString("NewProjectAction.text")); //$NON-NLS-1$
	Assert.isNotNull(window);
	this.window = window;
	setImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_CTOOL_NEW_WIZ));
	setHoverImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_CTOOL_NEW_WIZ_HOVER));
	setDisabledImageDescriptor(WorkbenchImages.getImageDescriptor(IWorkbenchGraphicConstants.IMG_CTOOL_NEW_WIZ_DISABLED));
	setToolTipText(WorkbenchMessages.getString("NewProjectAction.toolTip"));	 //$NON-NLS-1$
	WorkbenchHelp.setHelp(this, IHelpContextIds.NEW_ACTION);
}

/* (non-Javadoc)
 * Method declared on IAction.
 */
public void run() {
	// Create wizard selection wizard.
	IWorkbench workbench = PlatformUI.getWorkbench();
	NewWizard wizard = new NewWizard();
	wizard.setProjectsOnly(true);
	ISelection selection = window.getSelectionService().getSelection();
	IStructuredSelection selectionToPass = StructuredSelection.EMPTY;
	if (selection instanceof IStructuredSelection)
		selectionToPass = (IStructuredSelection) selection;
	wizard.init(workbench, selectionToPass);
	IDialogSettings workbenchSettings = WorkbenchPlugin.getDefault().getDialogSettings();
	IDialogSettings wizardSettings = workbenchSettings.getSection("NewWizardAction");//$NON-NLS-1$
	if(wizardSettings==null)
		wizardSettings = workbenchSettings.addNewSection("NewWizardAction");//$NON-NLS-1$
	wizard.setDialogSettings(wizardSettings);
	wizard.setForcePreviousAndNextButtons(true);

	// Create wizard dialog.
	Shell parent = window.getShell();
	WizardDialog dialog = new WizardDialog(parent, wizard);
	dialog.create();
	dialog.getShell().setSize( Math.max(SIZING_WIZARD_WIDTH, dialog.getShell().getSize().x), SIZING_WIZARD_HEIGHT );
	WorkbenchHelp.setHelp(dialog.getShell(), IHelpContextIds.NEW_PROJECT_WIZARD);

	// Open wizard.
	dialog.open();
}
}
