package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCEnchants.lib.Reference;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class HxCEnchantmentDummy {
    public int id;
    public byte MaxLevel, cost, rarity;
    public List<Short> bannedEnchs = Collections.emptyList();
    private EnumHxCEnchantType eType;
    public long charge;
    private HxCEnchantment real = null;
    public HxCEnchantmentDummy() {
    }

    public HxCEnchantmentDummy(int id, String name, byte rarity, byte HxCType, byte MaxLevel, byte cost, long chargeReq, List<Short> enchs) {
        this.id = id;
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = enchs;
        this.rarity = rarity;
        this.eType = Reference.HxCTypes.get(HxCType);
        this.charge = chargeReq;
        real = new HxCEnchantment(this.id, name, this.rarity, HxCType, this.MaxLevel, this.cost, this.charge, this.bannedEnchs);
    }

    public HxCEnchantment getReal() {
        return real;
    }
}