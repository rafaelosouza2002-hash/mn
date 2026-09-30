# Console Operacional de Sobrevivência — Câmara de Refúgio (Kiosk Android APK)

Projeto 100% gratuito e Open Source para painéis industriais e tablets dedicados em câmaras de refúgio de mineração subterrânea.

---

## 🛡️ Características de Segurança Crítica

1. **100% Full Offline:**
   - Todos os estilos (Tailwind CSS compilado) e fontes técnicas (`Chakra Petch`, `Share Tech Mono`, `Chivo`) estão embutidos localmente na pasta `assets/`.
   - Zero dependência de internet ou CDNs externos.

2. **Kiosk Mode (Modo Quiosque Nativo):**
   - **Tela Cheia Imersiva (*Immersive Sticky*):** Barras de status e de navegação completamente ocultas.
   - **Tela Sempre Ligada (*Keep Screen On*):** A tela nunca apaga ou bloqueia por inatividade durante uma emergência.
   - **Launcher / Home App:** O aplicativo declara `CATEGORY_HOME` no manifesto. Pode ser definido como a tela inicial padrão do tablet nas configurações do Android.
   - **Auto-Boot:** Inicialização automática no boot do sistema operacional (`BOOT_COMPLETED`).
   - **Orientação Fixa:** Travado em modo horizontal (*Landscape*).
   - **Trava de Saída:** Botão "Voltar" desativado para evitar fechamento acidental por operadores em situação de pânico.

3. **Síntese de Voz Offline (PT-BR):**
   - Ponte JavaScript nativa (`window.AndroidBridge.speak`) conectada ao motor nativo `TextToSpeech` do Android.
   - Fallback automático para `window.speechSynthesis`.

---

## 📂 Estrutura de Arquivos

```
stitch_mining_refuge_chamber_guide/
├── index.html          # Aplicação web estática 100% offline
├── style.css           # CSS minificado e compilado do Tailwind
├── fonts.css           # Folha de estilos das fontes locais
├── fonts/              # Arquivos de fontes WOFF2 embutidos
└── android/            # Código-fonte completo do projeto Android Studio
    ├── app/
    │   ├── src/main/
    │   │   ├── AndroidManifest.xml  (Permissões de Boot, Kiosk e Home)
    │   │   ├── java/com/bssl/refugekiosk/
    │   │   │   ├── MainActivity.kt    (Lógica Kiosk, WebView e Immersive)
    │   │   │   ├── WebAppInterface.kt (Ponte TTS nativa offline)
    │   │   │   └── BootReceiver.kt    (Início automático ao ligar o aparelho)
    │   │   └── assets/                (Cópia local dos arquivos web)
    │   └── build.gradle
    ├── build.gradle
    └── settings.gradle
```

---

## 🚀 Como Compilar o APK (100% Gratuito)

### Opção 1: GitHub Actions (Sem instalar nada no computador)
1. Crie um repositório no GitHub e envie os arquivos deste projeto.
2. Acesse a aba **Actions** no GitHub.
3. O workflow `Build Refuge Chamber Kiosk APK` será executado automaticamente.
4. Ao término (~2 minutos), baixe o arquivo `.apk` diretamente na seção **Artifacts**.

### Opção 2: Android Studio (Gratuito da Google)
1. Abra o [Android Studio](https://developer.android.com/studio).
2. Selecione **Open** e aponte para a pasta `stitch_mining_refuge_chamber_guide/android`.
3. Vá no menu **Build > Build Bundle(s) / APK(s) > Build APK(s)**.
4. O arquivo `.apk` gerado estará em `app/build/outputs/apk/debug/app-debug.apk`.

### Opção 3: Linha de Comando (Terminal)
```bash
cd stitch_mining_refuge_chamber_guide/android
gradle assembleDebug
```
O APK final será gerado em `app/build/outputs/apk/debug/app-debug.apk`.
