package com.panda98277.morerangefornoteblock.block

import com.google.common.collect.Lists
import com.panda98277.morerangefornoteblock.util.ModSoundHandler
import net.minecraft.block.BlockState
import net.minecraft.block.NoteBlock
import net.minecraft.block.enums.Instrument
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import org.jetbrains.annotations.NotNull

class NoteBlockBase(val name: String, private val indexModifier: Int, settings: Settings?) : NoteBlock(settings) {
    private val instrumentsPP: List<SoundEvent> = Lists.newArrayList(
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

    private val instrumentsP4: List<SoundEvent> = Lists.newArrayList(
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

    private val instrumentsMM: List<SoundEvent> = Lists.newArrayList(
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

    private fun getInstrument(id: Int): SoundEvent {
        var ordinal = id
        if (ordinal < 0 || ordinal >= instrumentsPP.size) {
            ordinal = 0
        }

        return when (indexModifier) {
            2 -> instrumentsPP[ordinal]

            4 -> instrumentsP4[ordinal]

            else -> instrumentsMM[ordinal]
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onSyncedBlockEvent(
        @NotNull state: BlockState?,
        world: World?,
        pos: BlockPos?,
        type: Int,
        data: Int
    ): Boolean {
        val instrument = state!!.get(INSTRUMENT) as Instrument
        val f: Float
        if (instrument.shouldSpawnNoteParticles()) {
            val i = state.get(NOTE) as Int
            f = getNotePitch(i)
            world!!.addParticle(
                ParticleTypes.NOTE,
                pos!!.x.toDouble() + 0.5,
                pos.y.toDouble() + 1.2,
                pos.z.toDouble() + 0.5,
                i.toDouble() / 24.0,
                0.0,
                0.0
            )
        } else {
            f = 1.0f
        }

        world!!.playSound(
            null as PlayerEntity?,
            pos!!.x.toDouble() + 0.5,
            pos.y.toDouble() + 0.5,
            pos.z.toDouble() + 0.5,
            getInstrument(instrument.ordinal),
            SoundCategory.RECORDS,
            3.0f,
            f,
            world.random.nextLong()
        )
        return true

    }
}