package com.cheezedfish.spartancompat.util;

/**
 * Copyright (C) 2019  ObliviousSpartan
 * 
 * This program is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses.
 * 
 * Full credit goes to OblviousSpartan for this code.
 * https://github.com/ObliviousSpartan/SpartanWeaponryArcana/blob/master/src/main/java/com/oblivioussp/spartanweaponryarcana/util/ConditionFactoryModId.java
*/

import java.util.function.BooleanSupplier;

import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.Loader;

public class ConditionFactoryModId implements IConditionFactory
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json) 
	{
		String modId = json.get("mod").getAsString();
		boolean result = Loader.isModLoaded(modId);
		return () -> result;
	}

}