package Utils;

/**
 * Classe utilitária para gerenciar emojis e símbolos visuais
 * Fornece uma forma segura de exibir símbolos que funcionam em diferentes consoles
 */
public class EmojiHelper {

    // Modo de operação
    private static boolean useEmojis = false;

    public static void setUseEmojis(boolean use) {
        useEmojis = use;
    }

    public static String getCheckMark() {
        return useEmojis ? "✅" : "[OK]";
    }

    public static String getErrorMark() {
        return useEmojis ? "❌" : "[ERRO]";
    }

    public static String getWarningMark() {
        return useEmojis ? "⚠️" : "[AVISO]";
    }

    public static String getRacingFlag() {
        return useEmojis ? "🏁" : "[CORRIDA]";
    }

    public static String getRacecar() {
        return useEmojis ? "🏎️" : "[CARRO]";
    }

    public static String getClock() {
        return useEmojis ? "⏳" : "[TEMPO]";
    }

    public static String getInput() {
        return useEmojis ? "✨" : ">>>";
    }

    public static String getTrash() {
        return useEmojis ? "🗑️" : "[LIXO]";
    }

    public static String getCalendar() {
        return useEmojis ? "🗓️" : "[CAL]";
    }

    public static String getGear() {
        return useEmojis ? "🔧" : "[CONF]";
    }

    public static String getPlus() {
        return useEmojis ? "➕" : "[+]";
    }

    public static String getArrow() {
        return useEmojis ? "📥" : ">>";
    }

    public static String getDownload() {
        return useEmojis ? "📥" : "[DL]";
    }

    public static String getSuccess() {
        return useEmojis ? "✓" : "[+]";
    }

    public static String getInfo() {
        return useEmojis ? "ℹ️" : "[INFO]";
    }

    public static String getBullet() {
        return useEmojis ? "•" : "-";
    }

    // Método para converter strings com placeholder de emoji
    public static String format(String text) {
        if (useEmojis) {
            return text;
        }

        // Substituições de segurança para Windows
        text = text.replace("✅", "[OK]");
        text = text.replace("❌", "[ERRO]");
        text = text.replace("⚠️", "[AVISO]");
        text = text.replace("🏁", "[CORRIDA]");
        text = text.replace("🏎️", "[CARRO]");
        text = text.replace("⏳", "[TEMPO]");
        text = text.replace("✨", ">>>");
        text = text.replace("🗑️", "[LIXO]");
        text = text.replace("🗓️", "[CAL]");
        text = text.replace("🔧", "[CONF]");
        text = text.replace("➕", "[+]");
        text = text.replace("📥", ">>");
        text = text.replace("✓", "[+]");
        text = text.replace("ℹ️", "[INFO]");
        text = text.replace("•", "-");

        return text;
    }
}

