---
name: Subterranean Life-Support Interface
colors:
  surface: '#131313'
  surface-dim: '#131313'
  surface-bright: '#393939'
  surface-container-lowest: '#0e0e0e'
  surface-container-low: '#1b1b1c'
  surface-container: '#202020'
  surface-container-high: '#2a2a2a'
  surface-container-highest: '#353535'
  on-surface: '#e5e2e1'
  on-surface-variant: '#d0c6ab'
  inverse-surface: '#e5e2e1'
  inverse-on-surface: '#303030'
  outline: '#999077'
  outline-variant: '#4d4632'
  surface-tint: '#e9c400'
  primary: '#fff5dc'
  on-primary: '#3a3000'
  primary-container: '#ffd600'
  on-primary-container: '#705d00'
  inverse-primary: '#705d00'
  secondary: '#ffb693'
  on-secondary: '#561f00'
  secondary-container: '#fe6b00'
  on-secondary-container: '#572000'
  tertiary: '#dbffde'
  on-tertiary: '#003918'
  tertiary-container: '#34f885'
  on-tertiary-container: '#006e35'
  error: '#ffb4ab'
  on-error: '#690005'
  error-container: '#93000a'
  on-error-container: '#ffdad6'
  primary-fixed: '#ffe170'
  primary-fixed-dim: '#e9c400'
  on-primary-fixed: '#221b00'
  on-primary-fixed-variant: '#544600'
  secondary-fixed: '#ffdbcc'
  secondary-fixed-dim: '#ffb693'
  on-secondary-fixed: '#351000'
  on-secondary-fixed-variant: '#7a3000'
  tertiary-fixed: '#62ff96'
  tertiary-fixed-dim: '#00e475'
  on-tertiary-fixed: '#00210b'
  on-tertiary-fixed-variant: '#005226'
  background: '#131313'
  on-background: '#e5e2e1'
  surface-variant: '#353535'
typography:
  headline-xl:
    fontFamily: Barlow Condensed
    fontSize: 48px
    fontWeight: '800'
    lineHeight: 52px
    letterSpacing: 0.05em
  headline-xl-mobile:
    fontFamily: Barlow Condensed
    fontSize: 32px
    fontWeight: '800'
    lineHeight: 36px
    letterSpacing: 0.05em
  headline-lg:
    fontFamily: Barlow Condensed
    fontSize: 36px
    fontWeight: '700'
    lineHeight: 40px
    letterSpacing: 0.04em
  headline-lg-mobile:
    fontFamily: Barlow Condensed
    fontSize: 28px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: 0.04em
  headline-md:
    fontFamily: Barlow Condensed
    fontSize: 28px
    fontWeight: '700'
    lineHeight: 32px
    letterSpacing: 0.03em
  headline-sm:
    fontFamily: Barlow Condensed
    fontSize: 22px
    fontWeight: '600'
    lineHeight: 26px
    letterSpacing: 0.02em
  body-lg:
    fontFamily: Chivo
    fontSize: 18px
    fontWeight: '500'
    lineHeight: 26px
  body-md:
    fontFamily: Chivo
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-sm:
    fontFamily: Chivo
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  label-lg:
    fontFamily: JetBrains Mono
    fontSize: 15px
    fontWeight: '700'
    lineHeight: 20px
    letterSpacing: 0.08em
  label-md:
    fontFamily: JetBrains Mono
    fontSize: 13px
    fontWeight: '600'
    lineHeight: 18px
    letterSpacing: 0.06em
  label-sm:
    fontFamily: JetBrains Mono
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 16px
    letterSpacing: 0.05em
rounded:
  sm: 0.125rem
  DEFAULT: 0.25rem
  md: 0.375rem
  lg: 0.5rem
  xl: 0.75rem
  full: 9999px
spacing:
  gutter: 1.5rem
  gutter-mobile: 0.75rem
  margin: 2rem
  margin-mobile: 1rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2.5rem
---

## Brand & Style

This design system establishes an uncompromising, mission-critical operational interface for life-support refuge chambers deployed in underground mining environments. The visual language bridges heavy-duty physical control gear and ultra-legible digital displays. It communicates stability, immediate operational clarity, and psychological reassurance under extreme sensory deprivation, smoke, or physical shock.

The visual style blends **Tactile / Skeuomorphic** industrial hardware details with **High-Contrast Utilitarian Functionalism**. Interfaces mimic powder-coated cast-aluminum housings, recessed instrument dials, stamped metal instruction plates, physical push-buttons with mechanical travel, and glowing prismatic multi-element LED indicators. Every component is engineered for rapid visual acquisition through fogged respirator visors, low-oxygen disorientation, and ambient darkness.

## Colors

The palette reproduces real-world safety-rated electrical hardware enclosures and backlit signaling devices:

- **Primary (`#FFD600` Canary Yellow):** High-lux luminous push-to-activate surfaces, audio initiation controls, primary operator action targets, and critical callout bezels.
- **Secondary (`#FF6B00` Safety Orange):** Structural hazard bezels, housing borders, active system caution rings, and structural enclosure highlights.
- **Tertiary (`#00E676` Emerald Green):** Atmosphere stabilized indicator, scrubber online status, positive intercom link, and verified safety state indicators.
- **Neutral (`#1E1E1E` Matte Dark Charcoal):** Stamped, powder-coated structural backdrop engineered to eliminate light glare while maximizing contrast against all illuminated states.
- **Supporting Status Beacon Tones:**
  - `#FF1744` (Crimson Red): Atmosphere compromise, scrubber failure, hard fault emergency state.
  - `#FF9100` (Amber Orange): Gas buildup alert, threshold caution, auxiliary battery depletion.
  - `#2979FF` (Cobalt Blue): Radio link scanning, telemetry uplink established, voice synthesis transmitting.
  - `#121212` (Recessed Basin Charcoal): Inset plate grooves, structural shadows, tactile wells.
  - `#F5F5F5` (High-Phosphor White): Crisp legibility screen readouts and stamped instructional labels.

## Typography

Typography prioritizes extreme mechanical legibility under duress:

- **Headlines (`Barlow Condensed`):** Dense, authoritative, and reminiscent of embossed DIN equipment plates and safety signage. Condensed forms permit large font sizes within rigid panel enclosures without wrapping. All headline text renders in uppercase for unambiguous scanning.
- **Body (`Chivo`):** Modern industrial grotesque with wide apertures and solid stroke weights. Prevents letterforms from blooming or blurring on backlit industrial touchscreens.
- **Data & Status Readouts (`JetBrains Mono`):** Monospaced, zero-ambiguity alphanumeric character set. Distinguishes oxygen percentages, battery runtimes, frequency channels, and step-by-step procedural indices.

## Layout & Spacing

The layout operates on a modular, fixed industrial grid representing bolted sub-panels inside an equipment chassis. Standard viewports use a 12-column matrix with broad gutters to prevent touch-target crowding for gloved hands.

- **Mobile / Compact Rugged Handhelds:** 4-column layout; elements span 2 or 4 columns; margins compress to 1rem to preserve primary actuation surface area. Minimum touch area is strictly 56px by 56px.
- **Console / Wall-Mount Panel (Desktop / Industrial Displays):** 12-column layout organized into compartmentalized quadrants: System Beacon Header, Primary Voice Guide Sequence (center stage), Environmental Telemetry (right rail), and Manual Override Array (base rail).
- **Rhythm:** Spacing follows mechanical mounting increments (`space-sm` for inset bevel spacing, `space-md` for sub-cluster groupings, and `space-xl` for physical module isolation barriers).

## Elevation & Depth

Depth is tactile, physical, and skeuomorphic. Rather than ethereal floating drop shadows, this design system models physical material relief:

- **Recessed Insets (Depressed Wells):** Inner shadows create a countersunk effect into the dark charcoal `#1E1E1E` powder-coated plate (`inset 0 3px 6px rgba(0, 0, 0, 0.85), inset 0 1px 1px rgba(0, 0, 0, 0.95)` with a top edge light highlight `1px solid rgba(255, 255, 255, 0.08)`).
- **Extruded Push Buttons & Switches:** Multi-layered rim lighting. Top highlight (`inset 0 2px 0 rgba(255, 255, 255, 0.45)`), bottom mechanical shadow (`0 6px 0 #9E7D00, 0 10px 12px rgba(0, 0, 0, 0.7)`), shifting down 4px on active press state to simulate physical switch travel.
- **Safety Orange Bezels (`#FF6B00`):** Solid 3px to 4px raised chamfered borders surrounding critical interaction zones, paired with machine-screw corner accents.
- **LED Indicator Lenses:** Concentric fresnel ring simulation using layered radial gradients. Active illuminated states cast a radial chromatic aura (`0 0 16px [color], 0 0 32px [color-alpha]`) over the surrounding charcoal metal panel.

## Shapes

The design system employs a **Soft (`1`)** corner geometry. Radii are clamped strictly to 0.25rem (4px) on base interactive items and 0.5rem (8px) on outer structural bezels. Sharp geometric edges reflect machined, stamped, and die-cast fabrication.

Circular shapes are restricted strictly to physical-mimic rotary dials, toggle indicators, and emergency round mushroom buttons. Cards, telemetry readouts, voice visualizer tracks, and module backings remain rectilinear with chamfered or tight radius corners.

## Components

### Primary Push-Buttons (Voice Activation & Step Advance)
- High-lux Canary Yellow (`#FFD600`) body with black (`#000000`) bold condensed text.
- 4px thick extruded mechanical bottom border (`#B29500`).
- Depressed state (`:active`): eliminates vertical displacement, applies dark inner shadow (`inset 0 4px 8px rgba(0,0,0,0.6)`).
- Enclosed in an Industrial Orange (`#FF6B00`) safety bezel featuring diagonal warning hatch marks for critical triggers.

### Tactical LED Status Beacons
- Multistate circular physical jewel lenses:
  - **Nominal:** Vivid Emerald Green (`#00E676`) with 12px outer diffusion.
  - **Caution:** Amber Orange (`#FF9100`) pulsing at 1Hz.
  - **Critical Failure:** Crimson Red (`#FF1744`) rapid strobe at 2.5Hz.
  - **Voice Transmit / Link:** Cobalt Blue (`#2979FF`) steady glow.
- Unlit state: Dark desaturated tint with deep inset shadow and realistic polycarbonate lens reflection.

### Audio Guide Progress Cards
- Stamped metal aesthetic: charcoal surface (`#252525`) bordered by an engraved 1px line (`#333333`) and an outer 2px safety line.
- Left-edge indexed numbering rendered in high-contrast monospaced font (`JetBrains Mono`).
- Current active instruction glows Canary Yellow with a left-side 6px vertical indicator bar.

### Mechanical Check Switches & Radio Selectors
- Heavy-duty toggle levers and rocker switches with physical directional shading.
- Active toggle locks into an illuminated inset slot showing fluorescent green backing; inactive state exposes an internal recessed shadow.

### Input Fields & Parameter Dials
- Digital LCD readouts with high-phosphor green or amber glow on deep obsidian backdrops.
- Segmented numeric stylings for gas concentration thresholds (CO, CO2, O2, pressure).

### Enclosure Housings & Perimeter Frames
- Heavy-gauge matte charcoal panels with faux hex-bolt anchors positioned in the outer corners (`space-md` offsets).
- Top bar displays shelter serial designation, battery reserve gauge, and acoustic feedback VU-meter.