package com.panda98277.morerangefornoteblock

import com.panda98277.morerangefornoteblock.block.NoteBlockBase
import com.panda98277.morerangefornoteblock.util.Reference
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

class AllBlocks {
    companion object {
        private val NOTE_BLOCK_P = NoteBlockBase("noteblock_p.json", 2, FabricBlockSettings.create().hardness(4.0F))
        private val NOTE_BLOCK_P4 = NoteBlockBase("noteblock_p4.json", 4, FabricBlockSettings.create().hardness(4.0F))
        private val NOTE_BLOCK_M = NoteBlockBase("noteblock_m", -2, FabricBlockSettings.create().hardness(4.0F))
        fun registry() {
            Registry.register(Registries.BLOCK, Identifier(Reference.MODID, NOTE_BLOCK_P.name), NOTE_BLOCK_P)
            Registry.register(Registries.ITEM, Identifier(Reference.MODID, NOTE_BLOCK_P.name), BlockItem(NOTE_BLOCK_P, FabricItemSettings()))

            Registry.register(Registries.BLOCK, Identifier(Reference.MODID, NOTE_BLOCK_P4.name), NOTE_BLOCK_P4)
            Registry.register(Registries.ITEM, Identifier(Reference.MODID, NOTE_BLOCK_P4.name), BlockItem(NOTE_BLOCK_P4, FabricItemSettings()))

            Registry.register(Registries.BLOCK, Identifier(Reference.MODID, NOTE_BLOCK_M.name), NOTE_BLOCK_M)
            Registry.register(Registries.ITEM, Identifier(Reference.MODID, NOTE_BLOCK_M.name), BlockItem(NOTE_BLOCK_M, FabricItemSettings()))
        }

    }


}