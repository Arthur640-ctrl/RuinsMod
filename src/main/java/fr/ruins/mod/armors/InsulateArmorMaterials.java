package fr.ruins.mod.armors;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public enum InsulateArmorMaterials implements ArmorMaterial {

    INSULATE_ARMOR("insulate_armor", 10, new int[]{0, 1, 2, 0}, 1,
            SoundEvents.ARMOR_EQUIP_LEATHER, 0.0f, 0.05f,
            () -> Ingredient.EMPTY);

    private final String name;
    private final int durabilityMultiplier;
    private final int[] protectionAmounts;
    private final int enchantability;
    private final net.minecraft.sounds.SoundEvent equipSound;
    private final float toughness;
    private final float knockbackResistance;
    private final java.util.function.Supplier<Ingredient> repairIngredient;

    private static final int[] BASE_DURABILITY = {13, 15, 16, 11};

    InsulateArmorMaterials(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability,
                      net.minecraft.sounds.SoundEvent equipSound, float toughness, float knockbackResistance,
                      java.util.function.Supplier<Ingredient> repairIngredient) {

        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.protectionAmounts = protectionAmounts;
        this.enchantability = enchantability;
        this.equipSound = equipSound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    // Retourne la durabilité finale pour chaque pièce
    @Override
    public int getDurabilityForSlot(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getIndex()] * this.durabilityMultiplier;
    }

    // Retourne le nombre de points d’armure par pièce
    @Override
    public int getDefenseForSlot(EquipmentSlot slot) {
        return this.protectionAmounts[slot.getIndex()];
    }

    // Chance que l’armure reçoive des enchantements
    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    // Son quand le joueur met l’armure
    @Override
    public net.minecraft.sounds.SoundEvent getEquipSound() {
        return this.equipSound;
    }

    // Item pour réparer l’armure
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    // Nom utilisé pour les textures
    @Override
    public String getName() {
        return "ruinsmod:" + this.name;
    }

    // Résistance supplémentaire
    @Override
    public float getToughness() {
        return this.toughness;
    }

    // Résistance au recul
    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

}
