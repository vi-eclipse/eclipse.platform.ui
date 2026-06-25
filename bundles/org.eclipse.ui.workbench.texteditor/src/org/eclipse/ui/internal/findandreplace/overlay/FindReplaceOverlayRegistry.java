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

import java.util.WeakHashMap;

import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;

/**
 * Keeps track of the {@link OverlaySearchSettings} shared between
 * {@link FindReplaceOverlay} instances within the same editor stack, as well
 * as the mapping from a workbench part to the overlay currently associated
 * with it. Both maps use weak keys so that entries are automatically cleaned
 * up once the corresponding stack or part is garbage collected.
 */
class FindReplaceOverlayRegistry {

	private static final WeakHashMap<MPartStack, OverlaySearchSettings> STACK_SETTINGS = new WeakHashMap<>();

	private static final WeakHashMap<IWorkbenchPart, FindReplaceOverlay> PART_TO_OVERLAY = new WeakHashMap<>();

	private FindReplaceOverlayRegistry() {
	}

	/**
	 * Returns the settings currently stored for the given stack, or
	 * <code>null</code> if none have been stored yet.
	 */
	static OverlaySearchSettings getSettings(MPartStack stack) {
		return STACK_SETTINGS.get(stack);
	}

	/**
	 * Returns the settings currently stored for the given stack, creating and
	 * storing a new, default-initialized instance if none exist yet.
	 */
	static OverlaySearchSettings getOrCreateSettings(MPartStack stack) {
		return STACK_SETTINGS.computeIfAbsent(stack, __ -> new OverlaySearchSettings());
	}

	/**
	 * Stores the given (immutable) settings for the given stack, replacing any
	 * previously stored settings.
	 */
	static void putSettings(MPartStack stack, OverlaySearchSettings settings) {
		STACK_SETTINGS.put(stack, settings);
	}

	/**
	 * Returns the overlay currently associated with the given part, or
	 * <code>null</code> if none is registered.
	 */
	static FindReplaceOverlay getOverlay(IWorkbenchPart part) {
		return PART_TO_OVERLAY.get(part);
	}

	/**
	 * Registers the given overlay as being associated with the given part.
	 */
	static void registerOverlay(IWorkbenchPart part, FindReplaceOverlay overlay) {
		PART_TO_OVERLAY.put(part, overlay);
	}

	/**
	 * Removes the association between the given part and its overlay.
	 */
	static void unregisterOverlay(IWorkbenchPart part) {
		PART_TO_OVERLAY.remove(part);
	}

}
