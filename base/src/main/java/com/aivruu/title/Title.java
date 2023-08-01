package com.aivruu.title;

import com.aivruu.title.adapt.ServerAdaptModel;
import com.aivruu.title.impl.SimpleAdaptManagerImpl;
import com.aivruu.title.impl.SimpleTitleManagerImpl;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Title {
	/**
	 * Returns a new instance of SimpleAdaptManagerImpl.
	 *
	 * @return A SimpleAdaptManagerImpl instance.
	 */
	@Contract(value = " -> new", pure = true)
	static @NotNull SimpleAdaptManagerImpl newAdaptManager() {
		return new SimpleAdaptManagerImpl();
	}
	
	/**
	 * Returns a new instance of SimpleTitleManagerImpl.
	 *
	 * @param adapt A ServerAdaptModel object that can be null.
	 *
	 * @return A SimpleTitleManagerImpl instance.
	 */
	@Contract(value = "_ -> new", pure = true)
	static @NotNull SimpleTitleManagerImpl newTitleManager(final @Nullable ServerAdaptModel adapt) {
		return new SimpleTitleManagerImpl(adapt);
	}
}
