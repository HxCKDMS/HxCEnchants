package HxCKDMS.HxCEnchants.api;

import HxCKDMS.HxCCore.Configs.Configurations;
import HxCKDMS.HxCCore.api.Utils.LogHelper;
import HxCKDMS.HxCEnchants.lib.Reference;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class HxCEnchantmentDummy {
    public int id;
    public byte MaxLevel, cost, rarity;
    public List<Short> bannedEnchs = Collections.emptyList();
    public String name;
    private EnumHxCEnchantType eType;
    public long charge;
    private HxCEnchantment real = null;
    public byte HxCType;
    public HxCEnchantmentDummy() {
    }

    public HxCEnchantmentDummy(int id, String name, byte rarity, byte HxCType, byte MaxLevel, byte cost, long chargeReq, List<Short> enchs) {
        this.id = id;
        this.MaxLevel = MaxLevel;
        this.cost = cost;
        this.bannedEnchs = enchs;
        this.name = name;
        this.HxCType = HxCType;
        this.rarity = rarity;
        this.eType = Reference.HxCTypes.get(HxCType);
        this.charge = chargeReq;
//        real = new HxCEnchantment(this.id, name, this.rarity, HxCType, this.MaxLevel, this.cost, this.charge, this.bannedEnchs);
    }

    public HxCEnchantment getReal() {
        return real;
    }

    public void init() {
        if (Configurations.DebugMode) LogHelper.info(String.format("%1$s %2$s %3$s %4$s %5$s %6$s %7$s %8$s", this.id, this.name, this.rarity, this.HxCType, this.MaxLevel, this.cost, this.charge, this.bannedEnchs), Reference.MOD_ID);
        real = new HxCEnchantment(this.id, this.name, this.rarity, this.HxCType, this.MaxLevel, this.cost, this.charge, this.bannedEnchs);
    }
}