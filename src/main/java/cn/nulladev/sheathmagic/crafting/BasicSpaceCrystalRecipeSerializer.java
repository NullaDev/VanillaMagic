package cn.nulladev.sheathmagic.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class BasicSpaceCrystalRecipeSerializer implements RecipeSerializer<BasicSpaceCrystalRecipe> {
    private static final int SIZE = 3;

    public static final BasicSpaceCrystalRecipeSerializer INSTANCE = new BasicSpaceCrystalRecipeSerializer();

    @Override
    public BasicSpaceCrystalRecipe fromJson(ResourceLocation id, JsonObject json) {
        var patternJsonArray = json.getAsJsonArray("pattern");
        var pattern = new String[patternJsonArray.size()];
        if (pattern.length != SIZE) {
            throw new JsonSyntaxException("Invalid pattern");
        } else {
            for(int i = 0; i < pattern.length; ++i) {
                var s = GsonHelper.convertToString(patternJsonArray.get(i), "pattern[" + i + "]");
                if (s.length() != SIZE) {
                    throw new JsonSyntaxException("Invalid pattern");
                }

                pattern[i] = s;
            }
        }

        var key = new HashMap<String, Ingredient>();
        var keyJsonObject = json.getAsJsonObject("key");
        for (var entry : keyJsonObject.entrySet()) {
            key.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
        }

        var result = CraftingHelper.getItemStack(GsonHelper.getAsJsonObject(json, "result"), true, true);
        return new BasicSpaceCrystalRecipe(id, pattern, key, result);
    }

    @Override
    public @Nullable BasicSpaceCrystalRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        String[] pattern = new String[SIZE];
        for (int i = 0; i < SIZE; i++) {
            pattern[i] = buf.readUtf();
        }

        int keysNum = buf.readVarInt();
        HashMap<String, Ingredient> key = new HashMap<>();

        for (int i = 0; i < keysNum; i++) {
            var keyName = buf.readUtf();
            var keyValue = Ingredient.fromNetwork(buf);
            key.put(keyName, keyValue);
        }

        ItemStack result = buf.readItem();

        return new BasicSpaceCrystalRecipe(id, pattern, key, result);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, BasicSpaceCrystalRecipe recipe) {
        var pattern = recipe.pattern;
        for (String s : pattern) {
            buf.writeUtf(s);
        }

        int keysNum = recipe.keys.size();
        buf.writeVarInt(keysNum);

        for (var entry : recipe.keys.entrySet()) {
            buf.writeUtf(entry.getKey());
            entry.getValue().toNetwork(buf);
        }

        buf.writeItem(recipe.result);
    }
}
