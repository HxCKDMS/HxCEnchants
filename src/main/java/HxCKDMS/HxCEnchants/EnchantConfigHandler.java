package HxCKDMS.HxCEnchants;

public class EnchantConfigHandler {
    public static boolean isEnabled(String name, String type) {
        switch(type) {
            case("armor") : return Boolean.parseBoolean(Configurations.ArmorEnchants.get(name).split(", ")[0]);
            case("weapon") : return Boolean.parseBoolean(Configurations.WeaponEnchants.get(name).split(", ")[0]);
            case("other") : return Boolean.parseBoolean(Configurations.Enchants.get(name).split(", ")[0]);
            case("tools") : return Boolean.parseBoolean(Configurations.ToolEnchants.get(name).split(", ")[0]);
            default : return false;
        }
    }

    public static int[] getData(String name, String type) {
        String[] tmp;
        switch(type) {
            case("armor") : tmp = Configurations.ArmorEnchants.get(name).split(", ");
                break;
            case("weapon") : tmp = Configurations.WeaponEnchants.get(name).split(", ");
                break;
            case("other") : tmp = Configurations.Enchants.get(name).split(", ");
                break;
            case("tools") : tmp = Configurations.ToolEnchants.get(name).split(", ");
                break;
            default : tmp = null;
                break;
        }
        if (tmp != null) {
            int[] tmp2 = new int[tmp.length-1];
            for (int i = 1; i < tmp.length; i++) {
                tmp2[i-1] = Integer.parseInt(tmp[i].trim());
            }
            return tmp2;
        } else return null;
    }
}