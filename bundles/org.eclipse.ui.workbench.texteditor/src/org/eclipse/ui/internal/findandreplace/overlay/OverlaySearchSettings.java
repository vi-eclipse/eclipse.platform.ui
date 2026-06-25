/*******************************************************************************
 * Copyright (c) 2026 Vector Informatik GmbH and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.ui.internal.findandreplace.overlay;

/**
 * Holds the search/replace settings that are shared by all
 * {@link FindReplaceOverlay} instances within the same editor stack. Sharing
 * within a stack means that navigating between editors in a tab group will
 * always show the same search term and options, while independent editor stacks
 * (e.g. side-by-side layouts) can carry different, unrelated search sessions.
 * <p>
 * Instances are immutable. Settings are only persisted once, when the overlay
 * is closed or disposed (see {@code FindReplaceOverlay.saveToStackSettings()}),
 * rather than on every single modification.
 * </p>
 */
public record OverlaySearchSettings(String findString, String replaceString, boolean caseSensitive,
		boolean wholeWord, boolean regex, boolean searchInSelection, boolean replaceBarOpen, boolean overlayOpen) {

	public OverlaySearchSettings() {
		this("", "", false, false, false, false, false, false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public String getFindString() {
		return findString;
	}

	public String getReplaceString() {
		return replaceString;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public boolean isWholeWord() {
		return wholeWord;
	}

	public boolean isRegex() {
		return regex;
	}

	public boolean isSearchInSelection() {
		return searchInSelection;
	}

	public boolean isReplaceBarOpen() {
		return replaceBarOpen;
	}

	public boolean isOverlayOpen() {
		return overlayOpen;
	}

	/**
	 * Returns a copy of these settings with only {@link #isOverlayOpen()} changed,
	 * used by {@code FindReplaceOverlay.setOverlayOpenInStack(boolean)} to update
	 * the open/closed marker without touching the actual search/replace state.
	 */
	public OverlaySearchSettings withOverlayOpen(boolean setOverlayOpen) {
		return new OverlaySearchSettings(findString, replaceString, caseSensitive, wholeWord, regex,
				searchInSelection, replaceBarOpen, setOverlayOpen);
	}

}

