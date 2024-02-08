package com.panda98277.morerangefornoteblock.util.sound

import com.panda98277.morerangefornoteblock.util.Reference
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier

class ModSoundEvent {
    companion object{
        fun of(path:String):SoundEvent{
            return SoundEvent.of(Identifier.of(Reference.MODID,path))
        }
    }
}