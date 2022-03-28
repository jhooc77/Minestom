package net.minestom.server.entity.metadata.monster.zombie;

import net.minestom.server.collision.BoundingBox;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.Metadata;
import net.minestom.server.entity.metadata.monster.MonsterMeta;
import org.jetbrains.annotations.NotNull;

public class ZombieMeta extends MonsterMeta {
    public static final byte OFFSET = MonsterMeta.MAX_OFFSET;
    public static final byte MAX_OFFSET = OFFSET + 3;

    public ZombieMeta(@NotNull Entity entity, @NotNull Metadata metadata) {
        super(entity, metadata);
    }

    public boolean isBaby() {
        return super.metadata.getIndex(OFFSET, false);
    }

    public void setBaby(boolean value) {
        if (isBaby() == value) {
            return;
        }
        this.consumeEntity((entity) -> {
            BoundingBox bb = entity.getBoundingBox();
            if (value) {
                double width = bb.width() / 2;
                entity.setBoundingBox(width, bb.height() / 2, width);
            } else {
                double width = bb.width() * 2;
                entity.setBoundingBox(width, bb.height() * 2, width);
            }
        });
        super.metadata.setIndex(OFFSET, Metadata.Boolean(value));
    }

    public boolean isBecomingDrowned() {
        return super.metadata.getIndex(OFFSET + 2, false);
    }

    public void setBecomingDrowned(boolean value) {
        super.metadata.setIndex(OFFSET + 2, Metadata.Boolean(value));
    }

}
