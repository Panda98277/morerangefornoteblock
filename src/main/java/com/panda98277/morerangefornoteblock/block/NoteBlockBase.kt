package com.panda98277.morerangefornoteblock.block

import com.google.common.collect.Lists
import com.panda98277.morerangefornoteblock.util.ModSoundHandler
import net.minecraft.block.BlockState
import net.minecraft.block.NoteBlock
import net.minecraft.block.enums.Instrument
import net.minecraft.entity.Entity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.registry.tag.ItemTags
import net.minecraft.sound.SoundEvent
import net.minecraft.stat.Stats
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.World
import net.minecraft.world.event.GameEvent
import org.jetbrains.annotations.Nullable

class NoteBlockBase : NoteBlock {
    val name:String
    val indexModifier:Int


    val PUBLIC_INSTRUMENTS_PP: List<SoundEvent> = Lists.newArrayList(
        ModSoundHandler.harp,
        ModSoundHandler.basedrum,
        ModSoundHandler.snare,
        ModSoundHandler.hat,
        ModSoundHandler.bass,
        ModSoundHandler.flute,
        ModSoundHandler.bell,
        ModSoundHandler.guitar,
        ModSoundHandler.chime,
        ModSoundHandler.xylobone
    )

    val PUBLIC_INSTRUMENTS_P4: List<SoundEvent> = Lists.newArrayList(
        ModSoundHandler.p4_harp,
        ModSoundHandler.p4_basedrum,
        ModSoundHandler.p4_snare,
        ModSoundHandler.p4_hat,
        ModSoundHandler.p4_bass,
        ModSoundHandler.p4_flute,
        ModSoundHandler.p4_bell,
        ModSoundHandler.p4_guitar,
        ModSoundHandler.p4_chime,
        ModSoundHandler.p4_xylobone
    )

    val PUBLIC_INSTRUMENTS_MM: List<SoundEvent> = Lists.newArrayList(
        ModSoundHandler.mm_harp,
        ModSoundHandler.mm_basedrum,
        ModSoundHandler.mm_snare,
        ModSoundHandler.mm_hat,
        ModSoundHandler.mm_bass,
        ModSoundHandler.mm_flute,
        ModSoundHandler.mm_bell,
        ModSoundHandler.mm_guitar,
        ModSoundHandler.mm_chime,
        ModSoundHandler.mm_xylobone
    )

    constructor(name: String, indexModifier: Int, settings: Settings?):super(settings){
        this.name = name
        this.indexModifier = indexModifier
    }

    private fun playNote(@Nullable entity: Entity?, state: BlockState, world: World, pos: BlockPos?) {
        if ((state.get(INSTRUMENT) as Instrument).isNotBaseBlock || world.getBlockState(pos!!.up()).isAir) {
            world.addSyncedBlockEvent(pos, this, 0, 0)
            world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos)
        }
    }

    override fun onUse(
        state: BlockState?,
        world: World?,
        pos: BlockPos?,
        player: PlayerEntity?,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult {
        val itemStack = player!!.getStackInHand(hand)
        if (itemStack.isIn(ItemTags.NOTEBLOCK_TOP_INSTRUMENTS) && hit!!.side == Direction.UP) {
            return ActionResult.PASS
        } else if (world!!.isClient) {
            return ActionResult.SUCCESS
        } else {
            var blockState = state!!.cycle(NOTE) as BlockState
            world.setBlockState(pos, blockState, 3)
            this.playNote(player, blockState, world, pos)
            player.incrementStat(Stats.TUNE_NOTEBLOCK)
            return ActionResult.CONSUME
        }
    }

}