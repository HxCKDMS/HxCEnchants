package HxCKDMS.HxCEnchants.enchantment;

public enum EnumEnchant {

    testEnchant(99, 1, 10, 1);

    public static EnumEnchant[] enchants = values();
    private int id, minlevel, maxlevel, weight;
    EnumEnchant(int ID, int MinLevel, int MaxLevel, int Weight) {
        id = ID; minlevel = MinLevel; maxlevel = MaxLevel;
        weight = Weight;
    }
    public static EnumEnchant getEnumLabel(int id) {
        for(EnumEnchant enchant : enchants) {
            if(enchant.id == id)
                return enchant;
        }
        return null;
    }
    public int id() {return this.id;}

    //currently just a thought gonna test soon
}
