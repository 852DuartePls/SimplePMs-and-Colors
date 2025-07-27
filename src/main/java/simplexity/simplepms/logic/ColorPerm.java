package simplexity.simplepms.logic;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public enum ColorPerm {
    ALLOW_COLOR("simplepms.use.color", TagResolver.builder()
            .resolver(StandardTags.color())
            .resolver(StandardTags.rainbow())
            .resolver(StandardTags.gradient())
            .build()),
    ALLOW_DECORATION("simplepms.use.decoration", TagResolver.builder()
            .resolver(StandardTags.decorations())
            .resolver(StandardTags.newline())
            .build()),

    ALLOW_CLICKS("simplepms.use.clicks", TagResolver.builder()
            .resolver(StandardTags.clickEvent())
            .resolver(StandardTags.hoverEvent())
            .resolver(StandardTags.insertion())
            .build()),

    ALLOW_ALL("simplepms.use.all", StandardTags.defaults());

    // No bueno, no no
    public static final Map<Character, String> LEGACY_TO_MINI = Map.ofEntries(
            Map.entry('0', "black"),
            Map.entry('1', "dark_blue"),
            Map.entry('2', "dark_green"),
            Map.entry('3', "dark_aqua"),
            Map.entry('4', "dark_red"),
            Map.entry('5', "dark_purple"),
            Map.entry('6', "gold"),
            Map.entry('7', "gray"),
            Map.entry('8', "dark_gray"),
            Map.entry('9', "blue"),
            Map.entry('a', "green"),
            Map.entry('b', "aqua"),
            Map.entry('c', "red"),
            Map.entry('d', "light_purple"),
            Map.entry('e', "yellow"),
            Map.entry('f', "white"),
            Map.entry('k', "obfuscated"),
            Map.entry('l', "bold"),
            Map.entry('m', "strikethrough"),
            Map.entry('n', "underlined"),
            Map.entry('o', "italic"),
            Map.entry('r', "reset")
    );

    private final String permission;
    private final TagResolver resolver;

    ColorPerm(String permission, TagResolver resolver) {
        this.permission = permission;
        this.resolver = resolver;
    }

    public String getPermission() {
        return permission;
    }

    public TagResolver getResolver() {
        return resolver;
    }

    public static @NotNull TagResolver buildResolver(CommandSender sender) {
        TagResolver.Builder builder = TagResolver.builder();
        for (ColorPerm colorperm : ColorPerm.values()) {
            if (sender.hasPermission(colorperm.getPermission())) {
                builder.resolver(colorperm.getResolver());
            }
        }
        return builder.build();
    }
}

