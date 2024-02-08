package com.panda98277.morerangefornoteblock

import net.fabricmc.api.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MoreRangeForNoteblock : ModInitializer {


    override fun onInitialize() {
        AllBlocks.registry();
    }

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger("morerangefornoteblock")

    }
}